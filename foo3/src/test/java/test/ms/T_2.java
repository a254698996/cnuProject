package test.ms;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class T_2 {
	public static void main(String[] args) {
		String text = "key1=value1;key2=value2\nkeyA=valueA\n";
		Map<String, String>[] load = load(text);
		for (Map<String, String> map : load) {
			Set<Entry<String, String>> entrySet = map.entrySet();
			Iterator<Entry<String, String>> iterator = entrySet.iterator();
			while (iterator.hasNext()) {
				Entry<String, String> next = iterator.next();
				System.out.println(next);
			}
		}
		String sore = sore(load);
		System.out.println(sore);
	}

	private static String sore(Map<String, String>[] strArray) {
		if (strArray == null || strArray.length == 0) {
			return null;
		}
		StringBuilder sbl = new StringBuilder();
		for (Map<String, String> map : strArray) {
			Set<Entry<String, String>> entrySet = map.entrySet();
			Iterator<Entry<String, String>> iterator = entrySet.iterator();
			StringBuilder sbl2 = new StringBuilder();
			while (iterator.hasNext()) {
				Entry<String, String> next = iterator.next();
				String key = next.getKey();
				String value = next.getValue();
				sbl2.append(key).append("=").append(value).append(";");
			}
			sbl.append(sbl2.subSequence(0, sbl2.length() - 1)).append("\\n");
		}
		return sbl.substring(0, sbl.length() - 2);
	}

	private static Map[] load(String text) {
		if (text == null || text.length() == 0) {
			return null;
		}
		int index = 0;
		String[] split = text.split("\\n");
		Map[] map = new Map[split.length];
		for (String string : split) {
			String[] split2 = string.split(";");
			Map<String, String> hashMap = new HashMap<String, String>();
			for (String string2 : split2) {
				String[] split3 = string2.split("=");
				hashMap.put(split3[0], split3[1]);
			}
			map[index] = hashMap;
			index++;
		}
		return map;
	}
}
