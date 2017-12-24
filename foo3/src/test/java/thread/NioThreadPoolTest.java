package thread;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class NioThreadPoolTest {

	private static FileOutputStream fos;

	public static void main(String[] args) throws Exception {
		long currentTimeMillis = System.currentTimeMillis();
		makeAccountFile("D:/1.txt");
		System.out.println(System.currentTimeMillis() - currentTimeMillis);
	}

	private static void makeAccountFile(String filePath) throws Exception {
		fos = new FileOutputStream(filePath);
		FileChannel fc = fos.getChannel();
		// 创建一个buffer并把准备写的数据填充进去;
		ByteBuffer bb = ByteBuffer.allocate(1024);
		for (int i = 1; i <= 100; i++) {
			// 数据源
			byte[] message = new Content(99992992 + i, "张三" + i, "2017-10-20", 5, "+", "消费").toString().getBytes("GBK");
			bb.put(message);
			if (i % 25 == 0) {
				bb.flip();
				fc.write(bb);
				bb.clear();
			}
		}
	}

	static FileInputStream fin;

	static FileChannel fc = fin.getChannel();
	// 一次读取文件，读取的字节缓存数
	static ByteBuffer fbb = ByteBuffer.allocate(1024 * 5);
	static boolean EOF = false;
	static ByteBuffer bb = ByteBuffer.allocate(500);

	private static void readFromFile(String filePath) throws Exception {
		fin = new FileInputStream(filePath);
		fc = fin.getChannel();
		// 一次读取文件，读取的字节缓存数
		fbb = ByteBuffer.allocate(1024 * 5);
		bb = ByteBuffer.allocate(500);
	}

	public boolean hasNext() throws IOException {

		if (EOF)
			return false;
		if (fbb.position() == fbb.limit()) {// 判断当前位置是否到了缓冲区的限制
			if (readByte() == 0)
				return false;
		}
		while (true) {
			if (fbb.position() == fbb.limit()) {
				if (readByte() == 0)
					break;
			}
			byte a = fbb.get();
			if (a == 13) {
				if (fbb.position() == fbb.limit()) {
					if (readByte() == 0)
						break;
				}
				return true;
			} else {
				if (bb.position() < bb.limit()) {
					bb.put(a);
				} else {
					if (readByte() == 0)
						break;
				}
			}
		}
		return true;
	}

	private int readByte() throws IOException {
		// 使缓冲区做好了重新读取已包含的数据的准备：它使限制保持不变，并将位置设置为零。
		fbb.rewind();
		// 使缓冲区做好了新序列信道读取或相对 get 操作的准备：它将限制设置为当前位置，然后将该位置设置为零。
		fbb.clear();
		if (this.fc.read(fbb) == -1) {
			EOF = true;
			return 0;
		} else {
			fbb.flip();
			return fbb.position();
		}
	}

	public byte[] next() {
		bb.flip();

		// 此处很重要，返回byte数组方便，行被分割的情况下合并，否则如果正好达到缓冲区的限制时，一个中文汉字被拆了两个字节，就会显示不正常
		byte tm[] = Arrays.copyOfRange(bb.array(), bb.position(), bb.limit());
		bb.clear();
		return tm;
	}

}

class Content {
	private long accountNo;
	private String name;
	private String date;
	private double money;
	private String mark;
	private String desc;

	public Content(long accountNo, String name, String date, double money, String mark, String desc) {
		super();
		this.accountNo = accountNo;
		this.name = name;
		this.date = date;
		this.money = money;
		this.mark = mark;
		this.desc = desc;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return accountNo + "," + name + "," + date + "" + money + "," + mark + "," + desc + "\r\n";
	}

}
