package qingfeng;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.druid.pool.DruidDataSource;

public class ReadFile implements Runnable {
	private File fin;
	private int bufSize;
	private int everyListSize;
	private CountDownLatch cdl;
	private ThreadPoolExecutor dBOptionThreadPool;

	private String errorLogPath;
	private String dbName;
	private boolean debug;
	private DruidDataSource dds;
	private ThreadPoolExecutor errorLogThreadPool;
	private ImpProp impProp;

	public ReadFile(File fin, CountDownLatch cdl, int bufSize, int everyListSize, ThreadPoolExecutor dBOptionThreadPool,
			String errorLogPath, String dbName, DruidDataSource dds, ThreadPoolExecutor errorLogThreadPool,
			ImpProp impProp, boolean debug) {
		super();
		this.fin = fin;
		this.cdl = cdl;
		this.dBOptionThreadPool = dBOptionThreadPool;
		this.bufSize = bufSize;
		this.everyListSize = everyListSize;
		this.errorLogPath = errorLogPath;
		this.dbName = dbName;
		this.debug = debug;
		this.dds = dds;
		this.errorLogThreadPool = errorLogThreadPool;
		this.impProp = impProp;
	}

	@Override
	public void run() {
		try {
			readFile();
			cdl.countDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("resource")
	public void readFile() throws Exception {
		Date startDate = new Date();
		FileChannel fcin = new RandomAccessFile(fin, "r").getChannel();
		ByteBuffer rBuffer = ByteBuffer.allocate(bufSize);

		List<List<TransPreCardInfo>> allCardList = readFileByLine(fcin, rBuffer);
		Date endDate = new Date();

		System.out.println(startDate + "|" + endDate);// 测试执行时间
		System.out.println("allCardList size " + allCardList.size());//
		// 测试执行时间
		int taskCount = allCardList.size();

		CountDownLatch cdl = new CountDownLatch(taskCount);
		for (List<TransPreCardInfo> list : allCardList) {
			DbOptioner readFromFile = new DbOptioner(errorLogPath, dbName, dds, errorLogThreadPool, debug);
			dBOptionThreadPool.execute(new Writer(list, dbName, readFromFile, cdl));
		}

		cdl.await();

		System.out.println("finish....  " + fin.getAbsolutePath());

		if (fcin.isOpen()) {
			fcin.close();
		}
	}

	public List<List<TransPreCardInfo>> readFileByLine(FileChannel fcin, ByteBuffer rBuffer) {
		List<List<TransPreCardInfo>> resultList = new ArrayList<>();
		List<TransPreCardInfo> dataList = null;// 存储读取的每行数据
		byte[] lineByte = new byte[0];
		String encode = "UTF-8";

		int index = 0;
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		try {
			// temp：由于是按固定字节读取，在一次读取中，第一行和最后一行经常是不完整的行，因此定义此变量来存储上次的最后一行和这次的第一行的内容，
			// 并将之连接成完成的一行，否则会出现汉字被拆分成2个字节，并被提前转换成字符串而乱码的问题
			byte[] temp = new byte[0];
			while (fcin.read(rBuffer) != -1) {// fcin.read(rBuffer)：从文件管道读取内容到缓冲区(rBuffer)
				int rSize = rBuffer.position();// 读取结束后的位置，相当于读取的长度
				byte[] bs = new byte[rSize];// 用来存放读取的内容的数组
				rBuffer.rewind();// 将position设回0,所以你可以重读Buffer中的所有数据,此处如果不设置,无法使用下面的get方法
				rBuffer.get(bs);// 相当于rBuffer.get(bs,0,bs.length())：从position初始位置开始相对读,读bs.length个byte,并写入bs[0]到bs[bs.length-1]的区域
				rBuffer.clear();

				int startNum = 0;
				int LF = 10;// 换行符
				int CR = 13;// 回车符
				boolean hasLF = false;// 是否有换行符
				for (int i = 0; i < rSize; i++) {
					if (bs[i] == LF) {
						hasLF = true;
						int tempNum = temp.length;
						int lineNum = i - startNum;
						lineByte = new byte[tempNum + lineNum];// 数组大小已经去掉换行符

						System.arraycopy(temp, 0, lineByte, 0, tempNum);// 填充了lineByte[0]~lineByte[tempNum-1]
						temp = new byte[0];
						System.arraycopy(bs, startNum, lineByte, tempNum, lineNum);// 填充lineByte[tempNum]~lineByte[tempNum+lineNum-1]

						String line = new String(lineByte, 0, lineByte.length, encode);// 一行完整的字符串(过滤了换行和回车)

						if (index == 0 || index % everyListSize == 0) {
							dataList = new ArrayList<TransPreCardInfo>(everyListSize);
							resultList.add(dataList);
						}
						index++;
						TransPreCardInfo str2ProCardInfo = str2ProCardInfo(line, timestamp);
						if (str2ProCardInfo != null) {
							dataList.add(str2ProCardInfo);
						}

						// 过滤回车符和换行符
						if (i + 1 < rSize && bs[i + 1] == CR) {
							startNum = i + 2;
						} else {
							startNum = i + 1;
						}
					}
				}
				if (hasLF) {
					temp = new byte[bs.length - startNum];
					System.arraycopy(bs, startNum, temp, 0, temp.length);
				} else {// 兼容单次读取的内容不足一行的情况
					byte[] toTemp = new byte[temp.length + bs.length];
					System.arraycopy(temp, 0, toTemp, 0, temp.length);
					System.arraycopy(bs, 0, toTemp, temp.length, bs.length);
					temp = toTemp;
				}
			}
			if (temp != null && temp.length > 0) {// 兼容文件最后一行没有换行的情况
				String line = new String(temp, 0, temp.length, encode);
				TransPreCardInfo str2ProCardInfo = str2ProCardInfo(line, timestamp);
				dataList.add(str2ProCardInfo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultList;
	}

	private TransPreCardInfo str2ProCardInfo(String str, Timestamp timestamp) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		String[] split = str.split(",");
		TransPreCardInfo transPreCardInfo = null;
		if (debug) {
			transPreCardInfo = new TransPreCardInfo(split[0] + "776");
		} else {
			transPreCardInfo = new TransPreCardInfo(split[0]);
		}
		transPreCardInfo.setPwd_value(split[1]);
		transPreCardInfo.setSecondTrack(split[2]);
		transPreCardInfo.setBegindate(timestamp);
		transPreCardInfo.setPwd("y");
		transPreCardInfo.setMerchno(this.impProp.getMerchno());
		transPreCardInfo.setSalesman(this.impProp.getSalesman());
		transPreCardInfo.setCardmode(this.impProp.getCardmode());
		transPreCardInfo.setStatus("1");
		transPreCardInfo.setIsspecial("n");
		transPreCardInfo.setIsmoney("1");
		transPreCardInfo.setPrintflag("1");
		transPreCardInfo.setDeposit("0");
		transPreCardInfo.setIsnet("1");

		return transPreCardInfo;
	}
}

class Writer implements Runnable {

	private List<TransPreCardInfo> list;

	private DbOptioner rff;

	private String cardSection;

	private CountDownLatch cdl;

	public Writer(List<TransPreCardInfo> list, String cardSection, DbOptioner rff, CountDownLatch cdl) {
		super();
		this.list = list;
		this.rff = rff;
		this.cdl = cdl;
		this.cardSection = cardSection;
	}

	@Override
	public void run() {
		try {
			rff.imp(cardSection, list);
			cdl.countDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}