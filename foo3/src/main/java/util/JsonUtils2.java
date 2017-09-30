package util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
/**
 * json工具类
 * @author:zuoys
 * @date:2017年3月7日下午3:58:18
 * @version:V1.0
 */
public class JsonUtils2 {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	private static XmlMapper xmlMapper = new XmlMapper();
	
	
	static{
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	/**
	 * javaBean,list,array convert to json string
	 */
	public static String obj2json(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
		    return null;
		}
	}
	
	public static <T> T json2pojo(String jsonStr,Class<T> clazz) throws Exception{
		return objectMapper.readValue(jsonStr, clazz);
	}
	
	public static String obj2json_camel2ul(Object obj) throws JsonProcessingException {
	    objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
	    return objectMapper.writeValueAsString(obj);
    }
	public static <T> T json2obj_ul2camel(String jsonStr,Class<T> clazz) throws Exception{
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        return objectMapper.readValue(jsonStr, clazz);
    }
	public static <T> T json2pojo_ul2camel(String jsonStr,Class<T> clazz) throws Exception{
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        return objectMapper.readValue(jsonStr, clazz);
    }
	/**
	 * json string convert to map
	 */
	
	public static Map json2map(String jsonStr){
		try {
			return objectMapper.readValue(jsonStr, Map.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/** map2json */
	public static String map2json(Map map) {
		String json = null;
		try {
			json = objectMapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return json;
	}
	
	/**
	 * json string convert to map with javaBean
	 */
	public static <T> Map<String,T> json2map(String jsonStr,Class<T> clazz)throws Exception{
		Map<String,Map<String,Object>> map =  objectMapper.readValue(jsonStr, new TypeReference<Map<String,T>>() {
		});
		Map<String,T> result = new HashMap<String, T>();
		for (Entry<String, Map<String,Object>> entry : map.entrySet()) {
			result.put(entry.getKey(), map2pojo(entry.getValue(), clazz));
		}
		return result;
	}
	
	/**
	 * json array string convert to list with javaBean
	 */
	public static <T> List<T> json2list(String jsonArrayStr,Class<T> clazz)throws Exception{
		List<Map<String,Object>> list = objectMapper.readValue(jsonArrayStr, new TypeReference<List<T>>() {
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
	public static <T> T map2pojo(Map map,Class<T> clazz){
		return objectMapper.convertValue(map, clazz);
	}
	
	/**
	 * json string convert to xml string
	 */
	public static String json2xml(String jsonStr)throws Exception{
		JsonNode root = objectMapper.readTree(jsonStr);
		String xml = xmlMapper.writeValueAsString(root);
		return xml;
	}
	
	/**
	 * xml string convert to json string
	 */
	public static String xml2json(String xml)throws Exception{
		StringWriter w = new StringWriter();
        JsonParser jp = xmlMapper.getFactory().createParser(xml);
        JsonGenerator jg = objectMapper.getFactory().createGenerator(w);
        while (jp.nextToken() != null) {
            jg.copyCurrentEvent(jp);
        }
        jp.close();
        jg.close();
        return w.toString();
	}
	
	/**
	 * xml string convert to json string
	 */
	public static Map xml2map(String xml){
		try {
			return xmlMapper.readValue(xml, Map.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * java pojo convert to map
	 */
	public static Map pojo2map(Object obj){
		return objectMapper.convertValue(obj, Map.class);
	}
}