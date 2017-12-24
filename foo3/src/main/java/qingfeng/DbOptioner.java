package qingfeng;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

public class DbOptioner {

	final String card_suf_for_debug = "776";

	final static boolean deBug = true;

	final int card_seria_num = 7;

	DruidDataSource dds;

	ThreadPoolExecutor errorLogThreadPool;

	private String errorLogPath;

	public DbOptioner(String errorLogPath, String dbName, DruidDataSource dds, ThreadPoolExecutor errorLogThreadPool,
			boolean debug) {
		this.errorLogPath = errorLogPath;
		this.errorLogThreadPool = errorLogThreadPool;
		this.dds = dds;
	}

	private DruidPooledConnection getConntection(String db_name, boolean debug) throws Exception {
		return this.dds.getConnection();
	}

	private void wirteToFile(final List<TransPreCardInfo> transPreCardInfoList, final String errorLogPath) {
		errorLogThreadPool.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("write to " + errorLogPath);
				appendWriteToFile(errorLogPath, ransPreCardInfoToErrorInfo(transPreCardInfoList));
			}
		});
	}

	private String ransPreCardInfoToErrorInfo(List<TransPreCardInfo> transPreCardInfoList) {
		StringBuilder sbl = new StringBuilder();
		for (TransPreCardInfo transPreCardInfo : transPreCardInfoList) {
			sbl.append(transPreCardInfo.toErrorStr());
		}
		return sbl.toString();
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

	public void imp(String cardSection, List<TransPreCardInfo> transPreCardInfoList) {
		long currentTimeMillis = System.currentTimeMillis();

		if (transPreCardInfoList == null || transPreCardInfoList.isEmpty()) {
			System.out.println("no import data ");
		}

		try {
			DruidPooledConnection accountConn = getConntection(cardSection, deBug);
			insertAccount(accountConn, transPreCardInfoList);

			DruidPooledConnection cardConn = getConntection(cardSection, deBug);
			insertCard(cardConn, transPreCardInfoList);

			DruidPooledConnection usersConn = getConntection(cardSection, deBug);
			insertUsers(usersConn, transPreCardInfoList);
		} catch (Exception e) {
			wirteToFile(transPreCardInfoList, this.errorLogPath);
			e.printStackTrace();
		}

		System.out.println(" use times  " + (System.currentTimeMillis() - currentTimeMillis));
	}

	private void insertCard(DruidPooledConnection conn, List<TransPreCardInfo> list) throws Exception {
		if (conn == null || list == null || list.isEmpty()) {
			System.out.println(" nothing to insert card ");
			return;
		}
		String sql = "insert into card(cardid,merchno ,  begindate     , enddate ,  	status  , 	qcpassed, rank  ,inbatchno   ,outbatchno    , isspecial     , salesman, scardid ,  ismoney ,  printflag     , serial  , type  ,pwd   , deposit , isnet ,cardmode,  currency) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			for (TransPreCardInfo transPreCardInfo : list) {
				int index = 1;
				pstmt.setString(index++, transPreCardInfo.getCardid());
				pstmt.setString(index++, transPreCardInfo.getMerchno());
				pstmt.setTimestamp(index++, transPreCardInfo.getBegindate());
				pstmt.setTimestamp(index++, transPreCardInfo.getEnddate());
				pstmt.setString(index++, transPreCardInfo.getStatus());
				pstmt.setString(index++, transPreCardInfo.getQcpassed());
				pstmt.setString(index++, transPreCardInfo.getRank());
				pstmt.setString(index++, transPreCardInfo.getInbatchno());
				pstmt.setString(index++, transPreCardInfo.getOutbatchno());
				pstmt.setString(index++, transPreCardInfo.getIsspecial());
				pstmt.setString(index++, transPreCardInfo.getSalesman());
				pstmt.setString(index++, transPreCardInfo.getScardid());
				pstmt.setString(index++, transPreCardInfo.getIsmoney());
				pstmt.setString(index++, transPreCardInfo.getPrintflag());
				pstmt.setString(index++, transPreCardInfo.getSerial());
				pstmt.setString(index++, transPreCardInfo.getType());
				pstmt.setString(index++, transPreCardInfo.getPwd());
				pstmt.setDouble(index++, Double.parseDouble(transPreCardInfo.getDeposit()));
				pstmt.setString(index++, transPreCardInfo.getIsnet());
				pstmt.setString(index++, transPreCardInfo.getCardmode());
				pstmt.setString(index++, transPreCardInfo.getCurrency());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			conn.commit();
			System.out.println(" insert card  " + list.size() + "  record  data ");
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (haveParmaryKeyError(e.getMessage())) {
				throw e;
			}
		} finally {
			conn.close();
		}
	}

	private void insertAccount(DruidPooledConnection conn, List<TransPreCardInfo> list) throws Exception {
		if (conn == null || list == null || list.isEmpty()) {
			System.out.println(" nothing to insert account ");
			return;
		}
		String[] accountNum = new String[] { "0", "1", "2", "3", "4", "5" };
		String sql = "insert into account(subject,ownerid,balance,realbalance,version,currency) values(?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			for (TransPreCardInfo transPreCardInfo : list) {
				for (int i = 0; i < accountNum.length; i++) {
					int index = 1;
					pstmt.setString(index++, accountNum[i]);
					pstmt.setString(index++, transPreCardInfo.getCardid());
					pstmt.setDouble(index++, 0.0);
					pstmt.setDouble(index++, 0.0);
					pstmt.setInt(index++, 0);
					pstmt.setString(index++, null);
					pstmt.addBatch();
				}
			}
			pstmt.executeBatch();
			conn.commit();
			System.out.println(" insert Account  " + (list.size() * accountNum.length) + "  record  data ");
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (haveParmaryKeyError(e.getMessage())) {
				throw e;
			}
		} finally {
			conn.close();
		}
	}

	private void insertUsers(DruidPooledConnection conn, List<TransPreCardInfo> list) throws Exception {
		if (conn == null || list == null || list.isEmpty()) {
			System.out.println(" nothing to insert users ");
			return;
		}
		String sql = "insert into users(personid,password,type) values(?,hash(?,'md5'),?)";
		PreparedStatement pstmt = null;
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			for (TransPreCardInfo transPreCardInfo : list) {
				int index = 1;
				pstmt.setString(index++, transPreCardInfo.getCardid());
				pstmt.setString(index++, transPreCardInfo.getPwd_value());
				pstmt.setString(index++, "3");
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			conn.commit();
			System.out.println(" insert users  " + list.size() + "  record  data ");
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			if (haveParmaryKeyError(e.getMessage())) {
				throw e;
			}
		} finally {
			conn.close();
		}
	}

	private boolean haveParmaryKeyError(String message) {
		return message.contains("PK_");
	}

}
