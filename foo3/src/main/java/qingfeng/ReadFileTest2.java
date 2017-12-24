package qingfeng;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.alibaba.druid.pool.DruidDataSource;

// read local file version 
public class ReadFileTest2 {
	public boolean imp(String errorLogPath, String impFilePath, String dbName, String impPropCardBin, boolean debug)
			throws Exception {
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

		ImpProp impProp = impPropMap.get(impPropCardBin);

		int bufSize = 1000000;
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

		DruidDataSource dds = initDbSource(debug, dbName);

		int logCorePoolSize = 1;
		int logMaximumPoolSize = 1;
		long logKeepAliveTime = 500;
		TimeUnit logUnit = TimeUnit.SECONDS;
		BlockingQueue<Runnable> logWorkQueue = new LinkedBlockingQueue<Runnable>();
		ThreadPoolExecutor errorLogThreadPool = new ThreadPoolExecutor(logCorePoolSize, logMaximumPoolSize,
				logKeepAliveTime, logUnit, logWorkQueue);

		long currentTimeMillis = System.currentTimeMillis();

		File fin = new File(impFilePath);
		new Thread(new ReadFile(fin, readFileCount, bufSize, everyListSize, dBOptionThreadPool, errorLogPath, dbName,
				dds, errorLogThreadPool, impProp, debug)).start();

		readFileCount.await();

		System.out.println(
				"ReadFileTest use  :" + ((System.currentTimeMillis() - currentTimeMillis)) / 1000 + " seconds ");

		dBOptionThreadPool.shutdown();
		errorLogThreadPool.shutdown();
		dds.close();
		System.out.println("ReadFileTest finish ...");

		return true;
	}

	private DruidDataSource getDDS(String url, String username, String password) {
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

	public DruidDataSource initDbSource(boolean debug, String dbName) {
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