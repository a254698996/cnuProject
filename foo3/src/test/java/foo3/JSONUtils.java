package foo3;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import foo3.TransAccbaseDto.TransAccbaseReq;
import foo3.TransAccbaseDto.TransAccbaseResp;
 

public class JSONUtils {

	public static void main(String[] args) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TransAccbaseDto t = new TransAccbaseDto();
		TransAccbaseReq req = t.getReq();
		req.setAccNo("21312123312132");
		TransAccbaseResp resp = t.getResp();
		resp.setAcc_no("21312123312132");
		Date date = new Date();
		System.out.println(simpleDateFormat.format(date));
		long time = (date).getTime();
		Timestamp timestamp = new Timestamp(time);

		resp.setBegin_time(timestamp);
		resp.setCurrency("CNY");
		
		TransAccbaseDto t2 = new TransAccbaseDto();
		TransAccbaseReq req2 = t2.getReq();
		req2.setAccNo("21312123312132");
		TransAccbaseResp resp2 = t2.getResp();
		resp2.setAcc_no("21312123312132");
		Date date2 = new Date();
		System.out.println(simpleDateFormat.format(date2));
		long time2 = (date2).getTime();
		Timestamp timestamp2 = new Timestamp(time2);

		resp2.setBegin_time(timestamp2);
		resp2.setCurrency("CNY");
		
		List<TransAccbaseDto> list=new ArrayList<TransAccbaseDto>();
		list.add(t);
		list.add(t2);
		String obj2json =  obj2json(list);
		 
		System.out.println(obj2json);
		
//		
//		String obj2json = JsonUtils.obj2json(t);
//		TransAccbaseDto json2pojo = JsonUtils.json2pojo(obj2json, TransAccbaseDto.class);
//		System.out.println(obj2json);
//		Timestamp begin_time = json2pojo.getResp().getBegin_time();
//		System.out.println(simpleDateFormat.format(begin_time));
	}

	private final static ObjectMapper objectMapper = new ObjectMapper();

	private JSONUtils() {

	}

	public static ObjectMapper getInstance() {

		return objectMapper;
	}

	/**
	 * javaBean,list,array convert to json string
	 */
	public static String obj2json(Object obj) throws Exception {
		return objectMapper.writeValueAsString(obj);
	}

	/**
	 * json string convert to javaBean
	 */
	public static <T> T json2pojo(String jsonStr, Class<T> clazz) throws Exception {
		return objectMapper.readValue(jsonStr, clazz);
	}

	/**
	 * json string convert to map
	 */
	public static <T> Map<String, Object> json2map(String jsonStr) throws Exception {
		return objectMapper.readValue(jsonStr, Map.class);
	}

	/**
	 * json string convert to map with javaBean
	 */
	public static <T> Map<String, T> json2map(String jsonStr, Class<T> clazz) throws Exception {
		Map<String, Map<String, Object>> map = objectMapper.readValue(jsonStr, new TypeReference<Map<String, T>>() {
		});
		Map<String, T> result = new HashMap<String, T>();
		for (Entry<String, Map<String, Object>> entry : map.entrySet()) {
			result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
		}
		return result;
	}

	/**
	 * json array string convert to list with javaBean
	 */
	public static <T> List<T> json2list(String jsonArrayStr, Class<T> clazz) throws Exception {
		List<Map<String, Object>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {
		});
		List<T> result = new ArrayList<T>();
		for (Map<String, Object> map : list) {
			result.add(map2pojo(map, clazz));
		}
		return result;
	}

	/**
	 * map convert to javaBean
	 */
	public static <T> T map2pojo(Map map, Class<T> clazz) {
		return objectMapper.convertValue(map, clazz);
	}
}