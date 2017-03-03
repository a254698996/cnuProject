//package foo2.test;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//import org.apache.commons.lang.StringUtils;
//import org.apache.log4j.Logger;
//import org.json.JSONObject;
//import org.junit.Assert;
//import org.junit.Test;
//
//import com.yst.m2.sdk.M2;
//import com.yst.m2.sdk.ReturnObj;
//import com.yst.m2.sdk.util.JsonUtil;
//
//public class M2Test {
//	private static final Logger logger = Logger.getLogger(M2Test.class);
//	@Test
//	public void testSend() { 
//		Assert.assertEquals("小天", "小天");
//	}
//
////	@Test
////	public void testSend() {
////		String userCode = "8008010007740655";
////		String userName = getUserName(userCode);
////		Assert.assertEquals("小天", userName);
////	 }
//
//	public String getUserName(String userCode) {
//		Map<String, Object> request = new HashMap<String, Object>();
//		request.put("UserUniqueCode", userCode);
//		request.put("AppCode", "1001000000018134");
//		request.put("Sign", "");
//
//		request.put("login_token", "");
//
//		logger.info("调用获得用户信息(GetUserInfoByUserCode)接口传入参数：" + request.toString());
//		ReturnObj result = M2.send("usercenter_api@GetUserInfoByUserCode", JsonUtil.to_json(request));
//		logger.info("调用获得用户信息(GetUserInfoByUserCode)接口返回参数：" + JsonUtil.to_json(result));
//
//		if (StringUtils.isNotBlank(result.get("err_text"))) {
//			logger.info("调用获得用户信息(get_user_info) 成功 ，但 大概是参数 有问题");
//			return "问题用户" + UUID.randomUUID().toString();
//		}
//		String get_data = result.get_data();
//		logger.info("get_data " + get_data);
//		Map<String, Object> to_map = JsonUtil.to_map(get_data);
//		Object object = to_map.get("UserInfoModel");
//		String to_json = JsonUtil.to_json(object);
//		JSONObject json = new JSONObject(to_json);
//		Object RealName = json.get("RealName");
//		logger.info(" 用户 RealName ： " + RealName);
//
//		return RealName.toString();
//	}
//
//}
