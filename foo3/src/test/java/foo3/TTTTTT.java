package foo3;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
//		getProperty();
		
		rolePermissionSet();
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

}
