package qingfeng;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;

public class TTTTTT {
	private static final String usermerch_202 = "usermerch_202";
	// 新增01008电子账户ID
	private static final String DB01008_0_27 = "900199";
	// 新增01007电子账户ID
	private static final String eb_01007 = "eb_01007";

	// final String[] cardSection = new String[] { "01007", "01008" };
	final String[] cardSection_test = new String[] { "900199", "900181" };

	final String[] cardSection = cardSection_test;

	final String card_suf_for_debug = "776";

	final static boolean deBug = true;

	final int card_seria_num = 7;

	Map<String, DruidDataSource> dsMap = new HashMap<String, DruidDataSource>();

	public static void main(String[] args) {
		// testApp() ;
		try {
			testImp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testApp() {
		String addZeroForNum = new TTTTTT().addZeroForNum("123", 7);
		System.out.println("addZeroForNum  " + addZeroForNum);
	}

	private static void testImp() throws Exception {
		long currentTimeMillis = System.currentTimeMillis();
		Long everySectionNum = 2L;

		TTTTTT tttttt = new TTTTTT();
		Connection ec_clear_conn = tttttt.getConntection(usermerch_202, deBug);
		List<EveryLimit> querySection = tttttt.getQuerySection(everySectionNum, ec_clear_conn);
		System.out.println("querySection  size  " + querySection.size());

		for (EveryLimit everyLimit : querySection) {
			Connection conn = tttttt.getConntection(usermerch_202, deBug);
			List<TransPreCardInfo> transPreCardInfoList = tttttt.getTransPreCardInfo(conn, everyLimit);

			System.out.println("everyLimit  " + everyLimit);
			if (transPreCardInfoList == null || transPreCardInfoList.isEmpty()) {
				System.out.println("no import data ");
				continue;
			}

			// for (TransPreCardInfo transPreCardInfo : transPreCardInfoList) {
			// System.out.println(transPreCardInfo);
			// }

			try {
				Connection accountConn = tttttt.getConntection(everyLimit.getConnStr(), deBug);
				tttttt.insertAccount(accountConn, transPreCardInfoList);

				Connection cardConn = tttttt.getConntection(everyLimit.getConnStr(), deBug);
				tttttt.insertCard(cardConn, transPreCardInfoList);

				Connection usersConn = tttttt.getConntection(everyLimit.getConnStr(), deBug);
				tttttt.insertUsers(usersConn, transPreCardInfoList);

				Connection ebcClearConn = tttttt.getConntection(usermerch_202, deBug);
				tttttt.updatePreCardInfo(ebcClearConn, everyLimit);
			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println(everyLimit + " have error skip ");
			}
		}

		System.out.println(" use times  " + (System.currentTimeMillis() - currentTimeMillis));

	}

	public DruidDataSource get(String url, String username, String password) {
		DruidDataSource d = new DruidDataSource();
		d.setUrl(url);
		d.setUsername(username);
		d.setPassword(password);
		d.setInitialSize(5);
		d.setAsyncInit(false);
		try {
			d.init();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}

	private void initDbConnection() {

	}

	// 得到 多个分段 的 卡 数量 信息
	private List<EveryLimit> getQuerySection(Long everySectionNum, Connection conn) {
		List<EveryLimit> list = new ArrayList<EveryLimit>();
		for (int i = 0; i < cardSection.length; i++) {
			Long maxCardNo = getMaxCardNo(conn, cardSection[i]);
			Long temp = maxCardNo - everySectionNum;

			if (maxCardNo <= 0) {
				continue;
			}

			while (true) {
				if (temp < 0) {
					temp = 0L;
				}
				// TODO 注意控制 边界 <=0 > =3 的问题
				String start = addZeroForNum(temp + "", card_seria_num);
				String end = addZeroForNum(maxCardNo + "", card_seria_num);
				EveryLimit everyLimit = new EveryLimit(start, end, cardSection[i]);
				list.add(everyLimit);
				if (temp == 0L) {
					break;
				}
				maxCardNo = temp - 1;
				temp = maxCardNo - everySectionNum;
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	private Connection getConntection(String db_name, boolean debug) throws Exception {
		Connection conn = null;
		String url = "";
		Properties sysProps = System.getProperties();
		sysProps.put("user", "sa");
		sysProps.put("password", "");
		if (!debug) {
			if (DB01008_0_27.equals(db_name)) {
				url = "jdbc:sybase:Tds:sybtran1.db.bjebc.com:5000/eb_01008?charset=cp936&jconnect_version=0";
				sysProps.put("user", "eb_01008");
				sysProps.put("password", "Gome_63133177");
			} else if (eb_01007.equals(db_name)) {
				url = "jdbc:sybase:Tds:sybtran1.db.bjebc.com:5000/eb_01007?charset=cp936&jconnect_version=0";
				sysProps.put("user", "eb_01007");
				sysProps.put("password", "Gome_63133177");
			} else if (usermerch_202.equals(db_name)) {
				url = "jdbc:sybase:Tds:sybtran1.db.bjebc.com:5000/ec_clear?charset=cp936&jconnect_version=0";
				sysProps.put("user", "ec_clear");
				sysProps.put("password", "Gome_63133177");
			}
		} else {
			if (DB01008_0_27.equals(db_name)) {
				url = "jdbc:jtds:sybase://10.153.29.48:5000/db_900199";
				sysProps.put("user", "db_900199");
				sysProps.put("password", "ebc_63133063");
			} else if (usermerch_202.equals(db_name)) {
				url = "jdbc:jtds:sybase://10.153.29.48:5000/ec_clear";
				sysProps.put("user", "ec_clear");
				sysProps.put("password", "test_123456");
			}
		}
		try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, sysProps);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;			
		}
		return conn;
	}

	private Long getMaxCardNo(Connection conn, String cardBegin) {
		String sql = " select max(substring(cardid,7,7)) from pre_card_info  where optionstatus='0' and substring(cardid,1,6)=? ";
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, cardBegin);
			ResultSet executeQuery = pstmt.executeQuery();
			while (executeQuery.next()) {
				return executeQuery.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			// colseConn(conn, pstmt);
		}
		return 0L;
	}

	private void updatePreCardInfo(Connection conn, EveryLimit everyLimit) {
		if (conn == null || everyLimit == null) {
			System.out.println(" nothing to update  PreCardInfo ");
			return;
		}
		String sql = "update pre_card_info  set optionstatus='9' ,updatedate=getdate() where optionstatus='0' and substring(cardid,1,6)=?  and (substring(cardid,7,7) >=? and  substring(cardid,7,7) <= ?) ";
		PreparedStatement pstmt = null;
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			int index = 1;
			pstmt.setString(index++, everyLimit.getConnStr());
			pstmt.setString(index++, everyLimit.getStart());
			pstmt.setString(index++, everyLimit.getEnd());
			pstmt.execute();
			conn.commit();
			// TODO EVERYList 里有数量
			// System.out.println(" update preCardInfo " + list.size() + "
			// record data ");
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			colseConn(conn, pstmt);
		}
	}

	private void insertCard(Connection conn, List<TransPreCardInfo> list) throws Exception {
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
			colseConn(conn, pstmt);
		}
	}

	private void insertAccount(Connection conn, List<TransPreCardInfo> list) throws Exception {
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
					// System.out
					// .println(" accountNum[i] " + accountNum[i] + " cardid " +
					// transPreCardInfo.getCardid());
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
			colseConn(conn, pstmt);
		}
	}

	private void insertUsers(Connection conn, List<TransPreCardInfo> list) throws Exception {
		if (conn == null || list == null || list.isEmpty()) {
			System.out.println(" nothing to insert users ");
			return;
		}
		String sql = "insert into users(personid,password,type) values(?,?,?)";
		PreparedStatement pstmt = null;
		try {
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			for (TransPreCardInfo transPreCardInfo : list) {
				int index = 1;
				pstmt.setString(index++, transPreCardInfo.getCardid());
				System.out.println(" transPreCardInfo.getCardid()   " + transPreCardInfo.getCardid());
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
			colseConn(conn, pstmt);
		}
	}

	private boolean haveParmaryKeyError(String message) {
		return message.contains("PK_");
	}

	// 找到 最大那个卡号 然后 一次减 5000 分成若干 组 然后查询 得到 若干 组
	// 01007 01008 在分开
	private List<TransPreCardInfo> getTransPreCardInfo(Connection conn, EveryLimit everyLimit) {
		List<TransPreCardInfo> list = new ArrayList<TransPreCardInfo>();
		String sql = " select cardid,merchno ,  begindate     , enddate ,  	status  , 	qcpassed, rank  ,inbatchno   ,outbatchno    , isspecial     , salesman, scardid ,  ismoney ,  printflag     , serial  , type  ,pwd   ,pwd_value     , deposit , isnet ,cardmode,  currency,  optionstatus,createdate  ,	updatedate from pre_card_info  where optionstatus='0' and  substring(cardid,1,6)=?  and (substring(cardid,7,7) >=? and  substring(cardid,7,7) <= ?)  ";
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			int paramIndex = 1;
			pstmt.setString(paramIndex++, everyLimit.getConnStr());
			pstmt.setString(paramIndex++, everyLimit.getStart());
			pstmt.setString(paramIndex++, everyLimit.getEnd());
			// TODO 查 哪个号段下的 start end
			ResultSet executeQuery = pstmt.executeQuery();
			while (executeQuery.next()) {
				TransPreCardInfo transPreCardInfo = new TransPreCardInfo();
				int index = 1;
				String cardid = executeQuery.getString(index++);
				if (deBug) {
					transPreCardInfo.setCardid(cardid + card_suf_for_debug);
				} else {
					transPreCardInfo.setCardid(cardid);
				}
				String merchno = executeQuery.getString(index++);
				transPreCardInfo.setMerchno(merchno);
				Timestamp begindate = executeQuery.getTimestamp(index++);
				transPreCardInfo.setBegindate(begindate);
				Timestamp enddate = executeQuery.getTimestamp(index++);
				transPreCardInfo.setEnddate(enddate);

				String status = executeQuery.getString(index++);
				transPreCardInfo.setStatus(status);
				String qcpassed = executeQuery.getString(index++);
				transPreCardInfo.setQcpassed(qcpassed);
				String rank = executeQuery.getString(index++);
				transPreCardInfo.setRank(rank);
				String inbatchno = executeQuery.getString(index++);
				transPreCardInfo.setInbatchno(inbatchno);
				String outbatchno = executeQuery.getString(index++);
				transPreCardInfo.setOutbatchno(outbatchno);

				String isspecial = executeQuery.getString(index++);
				transPreCardInfo.setIsspecial(isspecial);
				String salesman = executeQuery.getString(index++);
				transPreCardInfo.setSalesman(salesman);
				String scardid = executeQuery.getString(index++);
				transPreCardInfo.setScardid(scardid);
				String ismoney = executeQuery.getString(index++);
				transPreCardInfo.setIsmoney(ismoney);
				String printflag = executeQuery.getString(index++);
				transPreCardInfo.setPrintflag(printflag);
				String serial = executeQuery.getString(index++);
				transPreCardInfo.setSerial(serial);
				String type = executeQuery.getString(index++);
				transPreCardInfo.setType(type);
				String pwd = executeQuery.getString(index++);
				transPreCardInfo.setPwd(pwd);
				String pwd_value = executeQuery.getString(index++);
				transPreCardInfo.setPwd_value(pwd_value);
				String deposit = executeQuery.getString(index++);
				transPreCardInfo.setDeposit(deposit);
				String isnet = executeQuery.getString(index++);
				transPreCardInfo.setIsnet(isnet);
				String cardmode = executeQuery.getString(index++);
				transPreCardInfo.setCardmode(cardmode);
				String currency = executeQuery.getString(index++);
				transPreCardInfo.setCurrency(currency);
				String optionstatus = executeQuery.getString(index++);
				transPreCardInfo.setOptionstatus(optionstatus);
				Timestamp timestamp = executeQuery.getTimestamp(index++);
				transPreCardInfo.setCreatedate(timestamp);
				Timestamp updatedate = executeQuery.getTimestamp(index++);
				transPreCardInfo.setUpdatedate(updatedate);

				list.add(transPreCardInfo);
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			colseConn(conn, pstmt);
		}
		return list;
	}

	private void colseConn(Connection conn, PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private String addZeroForNum(String str, int strLength) {
		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sbf = new StringBuffer();
				sbf.append("0").append(str);// 左补0
				// sb.append(str).append("0");//右补0
				str = sbf.toString();
				strLen = str.length();
			}
		}
		return str;
	}
}
