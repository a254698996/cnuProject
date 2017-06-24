package restaurant;

public class Waiter extends Person {

	private static final long serialVersionUID = 1L;

	private int wno;
	private String name;
	private int age;

	private boolean isBuzy;

	private Restaurant restaurant;

	public Waiter(int wno, String name, int age, Restaurant restaurant) {
		this.wno = wno;
		this.name = name;
		this.age = age;
		this.restaurant = restaurant;
		this.restaurant.getWaiterSet().add(this);
	}

	public synchronized boolean isBuzy() {
		return isBuzy;
	}

	public synchronized void setBuzy(boolean isBuzy) {
		this.isBuzy = isBuzy;
	}

	public int getWno() {
		return wno;
	}

	public void setWno(int wno) {
		this.wno = wno;
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
		return "Waiter [wno=" + wno + ", name=" + name + ", age=" + age + "]";
	}

}
