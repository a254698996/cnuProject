package test.restaurant;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@SuppressWarnings("serial")
public class Customer extends Person {

	private String name;
	private int age;

	public Customer(String name, int age) {
		this.name = name;
		this.age = age;
	}

	// 食客 随机点餐(随机 生成 点几道菜，第道菜点几份)
	public Order order(Order order, Menu menu) {
		Map<Food, Integer> foodOrder = order.getFoodList();
		// 随机 点 1到5道 菜
		int count = RandomUtil.randomName(5, 1);
		for (int i = 0; i < count; i++) {
			// 随机点 1 到 10 号 菜品
			int randmodFoodId = RandomUtil.randomName(10, 1);
			Food chooseFood = menu.choose(randmodFoodId);
			// 每道 菜 随机点 1 到 3份
			int chooseCount = RandomUtil.randomName(3, 1);
			if (chooseCount == 1) {
				foodOrder.put(chooseFood, 1);
			} else {
				for (int j = 1; j <= chooseCount; j++) {
					try {
						Food clone = (Food) chooseFood.clone();
						clone.setEatSeqNo(j);
						foodOrder.put(clone, 1);
					} catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return order;
	}

	public void eat(Order order) {
		Map<Food, Integer> foodList = order.getFoodList();
		Set<Entry<Food, Integer>> entrySet = foodList.entrySet();
		Iterator<Entry<Food, Integer>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<Food, Integer> next = iterator.next();
			Food food = next.getKey();
			// 吃的时间 数量就都 是 1
			// Integer value = next.getValue();
			eat(food);
		}
	}

	public void eat(Food food) {
		while (food.getCookUseTime() == 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int eatUseTime = RandomUtil.randomName(10, 2);
		food.setEatTime(eatUseTime);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", age=" + age + "]";
	}

}
