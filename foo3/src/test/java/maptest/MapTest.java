package maptest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

// http://www.cnblogs.com/ITtangtang/p/3948406.html
// 说的很对
public class MapTest {
	public static void main(String[] args) {
		// testHashMap(1999999);
		// testHash();
		testToString();
	}

	private static void testToString() {
		S s = new S();
		int hashCode = s.hashCode();
		System.out.println("s hashcode " + s.hashCode());
		String hexString = Integer.toHexString(hashCode);
		System.out.println("Integer.toHexString(hashCode)  " + hexString);
		System.out.println("s toString " + s.toString());
	}

	private static void testHash() {
		HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
		T t1 = new T(12, "张三");
		T t2 = new T(28, "张三");
		T t3 = new T(108, "张三");
		T t4 = new T(140, "张三");
		System.out.println("t1.hashCode()  " + t1.hashCode());
		System.out.println("t2.hashCode()  " + t2.hashCode());
		System.out.println("t3.hashCode()  " + t3.hashCode());
		System.out.println("t4.hashCode()  " + t4.hashCode());
		System.out.println("t1.equals(t2)" + t1.equals(t2));
		System.out.println("t1.equals(t3)" + t1.equals(t3));
		System.out.println("t1.equals(t4)" + t1.equals(t4));
		System.out.println("t2.equals(t3)" + t2.equals(t3));
		System.out.println("t2.hashCode() == t1.hashCode() " + (t2.hashCode() == t1.hashCode()));
		hashMap.put(t1, "2");
		hashMap.put(t2, "3");
		hashMap.put(t3, "4");
		hashMap.put(t4, "5");

		System.out.println(hashMap.size());

		Set<Entry<Object, Object>> entrySet = hashMap.entrySet();
		Iterator<Entry<Object, Object>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<Object, Object> next = iterator.next();
		}
		for (Entry<Object, Object> entry : entrySet) {
			T t = (T) entry.getKey();
			System.out.println(t.toString() + "..... " + entry.getValue());
		}
	}

	private static void testHashMap(int count) {
		HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
		for (int i = 0; i < count; i++) {
			hashMap.put(i, i);
		}
		long currentTimeMillis = System.currentTimeMillis();
		Set<Object> keySet = hashMap.keySet();
		for (Object object : keySet) {
			hashMap.get(object);
		}
		System.out.println(" use keySet for " + (System.currentTimeMillis() - currentTimeMillis));

		long currentTimeMillis_2 = System.currentTimeMillis();
		Set<Entry<Object, Object>> entrySet = hashMap.entrySet();
		for (Entry<Object, Object> entry : entrySet) {
			entry.getValue();
			entry.getKey();
		}
		System.out.println(" use entrySet for " + (System.currentTimeMillis() - currentTimeMillis_2));
	}
}

class S {

}

class T {
	private int no;
	private String name;

	public T(int no, String name) {
		super();
		this.no = no;
		this.name = name;
	}

	@Override
	public int hashCode() {
		return no % 16;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		T other = (T) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (no != other.no)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "T [no=" + no + ", name=" + name + "]";
	}
}
