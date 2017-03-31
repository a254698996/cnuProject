package foo3;

import java.util.ArrayList;
import java.util.List;

public class TTTTTT {

	public static void main(String[] args) {
//		Map<String, Object> map = new HashMap<String, Object>(1);
//		map.put("code", 1);
//		map.put("code1", 1);
//		map.put("code32", 1);
//		map.put("code4", 1);
//		map.put("code5", 1);
//		map.put("code6", 1);
//		System.out.println(map.size());
		
		List<Object> list=new ArrayList<Object>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		Object[] array = list.toArray();
		for (Object object : array) {
			System.out.println(object);
		}

	}

}
