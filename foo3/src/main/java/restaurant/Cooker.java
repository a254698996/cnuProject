package restaurant;

import java.util.concurrent.atomic.AtomicBoolean;

@SuppressWarnings("serial")
public class Cooker extends Person {

	private int cno;
	private String name;
	private int age;

	private Restaurant restaurant;

	private AtomicBoolean isBuzy = new AtomicBoolean(false);

	public Cooker(int cno, String name, int age, Restaurant restaurant) {
		this.cno = cno;
		this.name = name;
		this.age = age;
		this.restaurant = restaurant;
		this.restaurant.getCookerSet().add(this);
	}

	// 做菜 ( 一个 厨师 ，时一时间 只能做一道菜)
	public Food cook(Food food) {
		int useTime = RandomUtil.randomName(10, 1);
		try {
			Thread.sleep(useTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		food.setCookUseTime(useTime);
		food.setCooker(this);
		food.notify();
		return food;
	}

	public AtomicBoolean getIsBuzy() {
		return isBuzy;
	}

	public void setIsBuzy(AtomicBoolean isBuzy) {
		this.isBuzy = isBuzy;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
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

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public String toString() {
		return "Cooker [cno=" + cno + ", name=" + name + ", age=" + age + "]";
	}
}
