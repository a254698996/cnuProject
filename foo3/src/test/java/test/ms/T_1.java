package test.ms;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.json.JSONObject;

public class T_1 {
	public static void main(String[] args) {
		String jsonStr = "{ \"a\": 1, \"b\": { \"c\": 2, \"d\": [3,4] } }";
		Map<String, String> jsonToMap = jsonToMap(null, jsonStr);
		System.out.println("--------------");
		printMap(jsonToMap);
	}

	private static Map<String, String> jsonToMap(String keyStr, String jsonStr) {
		if (jsonStr == null || jsonStr.trim().length() == 0) {
			return null;
		}
		Map<String, String> jsonToMap = new HashMap<String, String>();
		JSONObject json = new JSONObject(jsonStr);
		Set<String> keySet = json.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String next = iterator.next();
			Object object = json.get(next);
			try {
				JSONObject jsonObject = new JSONObject(object.toString());
				System.out.println("key  " + next + " jsonObject  " + jsonObject + "   object " + object);
				jsonToMap.putAll(jsonToMap(next, jsonObject.toString()));
				continue;
			} catch (Exception e) {
				// e.printStackTrace();
			}
			jsonToMap.put(keyStr == null ? next : keyStr + "." + next, object.toString());
		}
		return jsonToMap;
	}

	private static void printMap(Map jsonToMap) {
		Set<Entry<String, Object>> entrySet = jsonToMap.entrySet();
		Iterator<Entry<String, Object>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> next = iterator.next();
			System.out.println(next);
		}
	}
}
