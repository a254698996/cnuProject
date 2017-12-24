package qingfeng;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.druid.pool.DruidDataSource;

//read ftp file version 
public class ReadFileTest {
	String ftpUrl = "10.153.29.33";
	String userName = "qingfeng";
	String password = "qingfeng";
	int port = 21;
	String ftpRootDir = "";

	public static void main(String[] args) throws Exception {
		String appRootPath = "F:\\tarzan\\2017-11-17-库文件\\qingfeng_init_file";
		new ReadFileTest().getImpFile(appRootPath);

		// System.out.println("010071".substring(0, 5));
		// System.out.println("010071".substring(0, 6));
	}

	public void getImpFile(String appRootPath) {
		try {
			Set<String> dbSet = new HashSet<String>();
			dbSet.add("01007");
			dbSet.add("01008");

			String errorLogDir = "img_log_error";
			FtpUtils.makeDirecotory(ftpUrl, userName, port, password, ftpRootDir, errorLogDir);

			String local_imp_log_init = appRootPath + File.separator + "imp_log.log";
			String ftp_imp_log = "imp_log";

			boolean download = FtpUtils.download(ftpUrl, userName, port, password, ftpRootDir, ftp_imp_log,
					local_imp_log_init);
			if (!download) {
				Thread.sleep(100);
				boolean upload = FtpUtils.upload(ftpUrl, userName, port, password, ftpRootDir, local_imp_log_init,
						ftp_imp_log);
				if (!upload) {
					System.out.println("upload fail  ");
					return;
				}
			}

			String[] ftpRootPathTxtFileList = FtpUtils.list(ftpUrl, userName, port, password, ftpRootDir);
			if (ftpRootPathTxtFileList.length == 0) {
				System.out.println(" no imp data file ... ");
				return;
			}

			Set<String> impFileSet = new HashSet<String>();
			for (String string : ftpRootPathTxtFileList) {
				if (string.lastIndexOf(".txt") != -1) {
					impFileSet.add(string);
				}
			}

			if (impFileSet.isEmpty()) {
				System.out.println(" no imp data file ... ");
				return;
			}

			File impLogFile = new File(local_imp_log_init);
			RandomAccessFile raf = new RandomAccessFile(impLogFile, "r");

			List<String> historyImpFailList = new ArrayList<String>();

			while (true) {
				String readLine = raf.readLine();
				if (StringUtils.isNotBlank(readLine)) {
					String[] split = readLine.trim().split(":");
					if (!split[1].equals("1")) {
						historyImpFailList.add(split[0]);
					} else {
						impFileSet.remove(split[0]);
					}
				} else {
					break;
				}
			}
			raf.close();

			impFileSet.addAll(historyImpFailList);

			for (String impFileName : impFileSet) {

				String impAppTempFile = appRootPath + File.separator + "currImp.txt";
				boolean impFileDownloadSuccess = FtpUtils.download(ftpUrl, userName, port, password, ftpRootDir,
						impFileName, impAppTempFile);
				if (!impFileDownloadSuccess) {
					System.out.println(" download: " + impFileName + " from ftp fail");
					continue;
				}

				boolean isTest = true;
				String errorLogPath = appRootPath + File.separator + impFileName;
				File tempImpErrorInfoFile = new File(errorLogPath);
				if (tempImpErrorInfoFile.exists()) {
					tempImpErrorInfoFile.delete();
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				tempImpErrorInfoFile.createNewFile();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				String dbName = impFileName.substring(0, 5);
				String impPropCardBin = impFileName.substring(0, 6);

				if (!dbSet.contains(dbName)) {
					System.out.println("not exist database: " + dbName);
					continue;
				}

				boolean result = new ReadFileTest2().imp(errorLogPath, impAppTempFile, dbName, impPropCardBin, isTest);

				FtpUtils.upload(ftpUrl, userName, port, password, errorLogDir, errorLogPath, impFileName + ".err");

				String impResultLog = impFileName + ":" + (result ? "1" : "0") + "\r\n";

				appendWriteToFile(local_imp_log_init, impResultLog);
				
				FtpUtils.upload(ftpUrl, userName, port, password, ftpRootDir, local_imp_log_init, ftp_imp_log);
			}
			System.out.println(" imp  finish .... ");
		} catch (Exception e) {
			e.printStackTrace();
			 
		}
	}

	private void appendWriteToFile(String fileName, String content) {
		RandomAccessFile randomFile = null;
		try {
			// 打开一个随机访问文件流，按读写方式
			randomFile = new RandomAccessFile(fileName, "rw");
			// 文件长度，字节数
			long fileLength = randomFile.length();
			// 将写文件指针移到文件尾。
			randomFile.seek(fileLength);
			randomFile.writeBytes(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (randomFile != null) {
				try {
					randomFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void imp() throws Exception {

		Map<String, ImpProp> impPropMap = new HashMap<String, ImpProp>();

		ImpProp impProp_010071 = new ImpProp();
		impProp_010071.setCardBin("010071");
		impProp_010071.setMerchno("611200000100021");
		impProp_010071.setSalesman("GOMEACC");
		impProp_010071.setCardmode("014");

		ImpProp impProp_010081 = new ImpProp();
		impProp_010081.setCardBin("010081");
		impProp_010081.setMerchno("611200000100021");
		impProp_010081.setSalesman("GOMECOIN");
		impProp_010081.setCardmode("013");

		ImpProp impProp_010073 = new ImpProp();
		impProp_010073.setCardBin("010073");
		impProp_010073.setMerchno("611200000100021");
		impProp_010073.setSalesman("GOMEACC");
		impProp_010073.setCardmode("014");

		impPropMap.put("010071", impProp_010071);
		impPropMap.put("010081", impProp_010081);
		impPropMap.put("010073", impProp_010073);

		ImpProp impProp = impPropMap.get("010071");

		File fin = new File("F:\\tarzan\\2017-11-17-库文件\\010073-7-20\\制卡文件拆分7--1.txt");

		int bufSize = 1_000_000;
		int everyListSize = 500;

		int readFile_num = 1;
		CountDownLatch readFileCount = new CountDownLatch(readFile_num);

		int corePoolSize = 5;
		int maximumPoolSize = 5;
		long keepAliveTime = 50;
		TimeUnit unit = TimeUnit.SECONDS;

		BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
		ThreadPoolExecutor dBOptionThreadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
				unit, workQueue);
		String errorLogPath = "F:\\tarzan\\2017-11-17-库文件\\impDbError.txt";
		String dbName = "900199";

		boolean debug = true;
		DruidDataSource dds = initDbSource(debug, dbName);

		int logCorePoolSize = 1;
		int logMaximumPoolSize = 1;
		long logKeepAliveTime = 500;
		TimeUnit logUnit = TimeUnit.SECONDS;
		BlockingQueue<Runnable> logWorkQueue = new LinkedBlockingQueue<Runnable>();
		ThreadPoolExecutor errorLogThreadPool = new ThreadPoolExecutor(logCorePoolSize, logMaximumPoolSize,
				logKeepAliveTime, logUnit, logWorkQueue);

		long currentTimeMillis = System.currentTimeMillis();

		new Thread(new ReadFile(fin, readFileCount, bufSize, everyListSize, dBOptionThreadPool, errorLogPath, dbName,
				dds, errorLogThreadPool, impProp, debug)).start();

		readFileCount.await();

		System.out.println(
				"ReadFileTest use  :" + ((System.currentTimeMillis() - currentTimeMillis)) / 1000 + " seconds ");

		dBOptionThreadPool.shutdown();
		errorLogThreadPool.shutdown();
		dds.close();
		System.out.println("ReadFileTest finish ...");

	}

	private static DruidDataSource getDDS(String url, String username, String password) {
		DruidDataSource d = new DruidDataSource();
		d.setUrl(url);
		d.setUsername(username);
		d.setPassword(password);
		d.setMaxActive(20);
		d.setInitialSize(5);
		try {
			d.init();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static DruidDataSource initDbSource(boolean debug, String dbName) {
		String url = "";
		String username = "";
		String password = "";
		if (!debug) {
			if (dbName.equals("01008")) {
				url = "jdbc:sybase:Tds:sybtran1.db.bjebc.com:5000/eb_01008?charset=cp936&jconnect_version=0";
				username = "eb_01008";
				password = "Gome_63133177";
			} else if (dbName.equals("01007")) {
				url = "jdbc:sybase:Tds:sybtran1.db.bjebc.com:5000/eb_01007?charset=cp936&jconnect_version=0";
				username = "eb_01007";
				password = "Gome_63133177";
			}
		} else {
			url = "jdbc:jtds:sybase://10.153.29.48:5000/db_900199";
			username = "db_900199";
			password = "ebc_63133063";
		}
		return getDDS(url, username, password);
	}

}
