package foo3;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.util.Assert;

import util.JSONUtils;
import web.entity.Goods;
import web.entity.GoodsCategory;
import web.entity.RolePermission;

public class TTTTTT {

	public static void main(String[] args) {
		// Map<String, Object> map = new HashMap<String, Object>(1);
		// map.put("code", 1);
		// map.put("code1", 1);
		// map.put("code32", 1);
		// map.put("code4", 1);
		// map.put("code5", 1);
		// map.put("code6", 1);
		// System.out.println(map.size());

		// List<Object> list=new ArrayList<Object>();
		// list.add("a");
		// list.add("b");
		// list.add("c");
		// list.add("d");
		// list.add("e");
		// Object[] array = list.toArray();
		// for (Object object : array) {
		// System.out.println(object);
		// }

		// goods();
		// getProperty();

		// rolePermissionSet();
		// try {
		// System.out.println(dataLenFormat(333323411, 6));
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }

		// testAssert();
		// System.out.println(File.pathSeparator);
		// System.out.println(File.pathSeparatorChar);
		// System.out.println(File.separator);
		// System.out.println(File.separatorChar);

		// List<String> list = new ArrayList<String>();
		// list.add("aa");
		// list.add("aa");
		// list.add("bb");
		// list.add("aa");
		// list.add("cc");
		// list.add("aa");
		// list2set(list);

		// test1111();
		// BigDecimal bigDecimal = new BigDecimal("54617213123289203234234");
		// System.out.println(bigDecimal.longValueExact());
		// System.out.println(bigDecimal.toString()+"");

		// long currentTimeMillis = System.currentTimeMillis();
		// try {
		// Thread.sleep(1000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// long end = System.currentTimeMillis();
		// System.out.println(" end "+end+" start "+currentTimeMillis);
		// System.out.println(" end - start "+(end-currentTimeMillis)/1000 +"
		// seconds " );

		// double aa=0.0;
		// System.out.println(aa < 0);
		// System.out.println(aa > 0);
		// System.out.println(aa <= 0);
		// System.out.println(aa >= 0);

		// testNioBuffer();

		// String tsetFinally = tsetFinally();
		// System.out.println("tsetFinally return : " + tsetFinally);

		// String limits = "00000001";;
		// System.out.println(limits.charAt(7));

		// testPage();

		// testDouble();
		// System.out.println("abcdefghij".substring(2,6));
		// testBigDecimal();

//		test0();
		
//		testMap() ;
		
		System.out.println("0100801234567890".substring(0,5));
	}

	private static void testMap() {
		Map<String, Integer> map = new HashMap<>(2);
		System.out.println("map size "+map.size());
		map.put("a", 1);
		System.out.println("map size "+map.size());
		map.put("b", 2);
		System.out.println("map size "+map.size());
		map.put("c", 3);
		System.out.println("map size "+map.size());
		map.put("d", 4);
		System.out.println("map size "+map.size());
		map.put("e", 5);
		System.out.println("map size "+map.size());
		map.put("f", 6);
		System.out.println("map size "+map.size());
		Set<Entry<String, Integer>> entrySet = map.entrySet();
		for (Entry<String, Integer> entry : entrySet) {
			System.out.println(entry.getKey() + "   " + entry.getValue());
		}
		System.out.println();
	}

	private static void test0() {
		System.out.println(0 < 0 ? "1111" : "22222");
	}

	private static void testBigDecimal() {
		BigDecimal a = new BigDecimal("34567890345678");
		System.out.println(a.toString());
	}

	private static void testDouble() {
		double aa = 3839483.00;
		System.out.println(aa + "  " + Double.MAX_VALUE);
	}

	private static void testPage() {
		int pageSize = 10;
		int currentPage = 1;
		int totalPage = 12;
		System.out.println((totalPage / pageSize) + 1);
	}

	private static String tsetFinally() {
		try {
			System.out.println("testFinally execute.....");
			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			return "998";
		} finally {
			System.out.println("testFinally finally execute ");
		}
	}

	private static void testNioBuffer() {
		ByteBuffer b = ByteBuffer.allocate(15); // 15个字节大小的缓冲区
		System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());
		for (int i = 0; i < 10; i++) {
			// 存入10个字节数据
			b.put((byte) i);
		}
		System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());
		b.flip(); // 重置position
		System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());
		for (int i = 0; i < 5; i++) {
			System.out.print(b.get());
		}
		System.out.println();
		System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());
		b.flip();
		System.out.println("limit=" + b.limit() + " capacity=" + b.capacity() + " position=" + b.position());

	}

	private static void test1111() {
		String strCardExpdate = "20201231";
		String getDoDate = "20170606";
		if (strCardExpdate.compareTo(getDoDate) < 0) {
			System.out.println("11111");
		} else {
			System.out.println("22222222");
		}

	}

	private static void test() {
		Haha h = new Haha();
		h.setSubject("3");
		if (!(h.getSubject().equals("0") || h.getSubject().equals("1"))) {
			// if (!h.getSubject().equals("0") || !h.getSubject().equals("1")) {
			System.out.println("----------------");
		} else {
			System.out.println("11111111111111111");
		}
	}

	public static void list2set(List<String> list) {
		System.out.println(list.size());
		for (Object object : list) {
			System.out.println(object);
		}
		HashSet hashSet = new HashSet(list);
		System.out.println();
		for (Object object : hashSet) {
			System.out.println(object);
		}
	}

	public static String dataLenFormat(int i, int len) throws UnsupportedEncodingException {
		StringBuffer sb = new StringBuffer();
		sb.append(i);

		while (sb.length() < len) {
			sb.insert(0, "0");
		}

		return sb.toString();
	}

	private static void rolePermissionSet() {

		Set<RolePermission> set = new HashSet<>();
		for (int i = 0; i < 5; i++) {
			RolePermission rp = new RolePermission();
			rp.setRoleId((i + 1) + "");
			rp.setPermissionId((i + 2) + "");
			rp.setId(i);
			set.add(rp);
		}
		for (RolePermission rolePermission : set) {
			System.out.println(JSONUtils.obj2json(rolePermission));
		}

	}

	private static void goods() {
		List<GoodsCategory> list = new ArrayList<GoodsCategory>();
		for (int i = 0; i < 5; i++) {
			GoodsCategory gc = new GoodsCategory();
			gc.setCode(11 + i + "");
			gc.setName("大类" + (i + 1));
			list.add(gc);
		}
		System.out.println(JSONUtils.obj2json(list));
	}

	private static void getProperty() {
		Goods g = new Goods();
		g.setId("1111");
		g.setExchangeGroupId("qwe");
		g.setName("ddd");

		Class<? extends Goods> clazz = g.getClass();
		// Field[] fields = clazz.getFields();
		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			System.out.println(field);

		}

	}

	public static void testAssert() {
		String hql = "  aaa";
		Assert.hasText(hql);
		int pageNo = 2;
		try {
			Assert.isTrue(pageNo >= 9, "pageNo should start from 1");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("qqqq-- : " + e.getMessage());
		}
		System.out.println(" testAssert ......");
	}

}

class Haha {
	private String subject;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
