package test.restaurant.test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import test.restaurant.Cooker;
import test.restaurant.Customer;
import test.restaurant.Food;
import test.restaurant.Menu;
import test.restaurant.Order;
import test.restaurant.Restaurant;
import test.restaurant.Waiter;
 

public class RestaurantTest {
	public static AtomicInteger orderNo = new AtomicInteger(1);

	public static void main(String[] args) {
		try {
			Restaurant restaurant = restaurantReady();
			// randomName();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static char randomName() {
		int max = 90;
		int min = 65;
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		return ((char) s);
	}

	private static int randomAge() {
		int max = 55;
		int min = 20;
		Random random = new Random();
		return random.nextInt(max) % (max - min + 1) + min;
	}

	private static void restaurantBusiness(Restaurant restaurant, int customerCount) throws Exception {
		if (restaurant == null) {
			throw new Exception("restaurant is not ready ");
		}

		Set<Customer> customerSet = customerComming(customerCount);

		order(restaurant, customerSet);
	}

	private static synchronized void order(Restaurant restaurant, Set<Customer> customerSet) throws Exception {
		Waiter waiter = restaurant.getWaiter();
		for (Customer customer : customerSet) {
			Order order = new Order();
			order.setCustomer(customer);
			order.setWaiter(waiter);
			order.setOrderNo(orderNo.incrementAndGet());
			// 食客点餐
			Order returnOrder = customer.order(order, restaurant.getMenu());
		}
		waiter.setBuzy(false);
	}

	private static Set<Customer> customerComming(int customerCount) throws Exception {
		if (customerCount <= 0) {
			throw new Exception("no customer comming");
		}
		Set<Customer> customerSet = new HashSet<>();
		for (int i = 0; i < customerCount; i++) {
			customerSet.add(new Customer(randomName() + "", randomAge()));
		}
		return customerSet;
	}

	private static Restaurant restaurantReady() throws Exception {
		// 有一间 餐馆
		Restaurant restaurant = new Restaurant();
		// 招募 厨师
		new Cooker(11, "厨-张三", 25, restaurant);
		new Cooker(12, "厨-李四", 29, restaurant);
		new Cooker(18, "厨-王五", 45, restaurant);

		for (Cooker c : restaurant.getCookerSet()) {
			System.out.println(c.toString());
		}
		// 招募 服务员
		new Waiter(42, "服务员-make", 21, restaurant);
		new Waiter(48, "服务员-paul", 18, restaurant);
		new Waiter(46, "服务员-scott", 32, restaurant);

		for (Waiter c : restaurant.getWaiterSet()) {
			System.out.println(c.toString());
		}
		// 制做 菜单
		Menu menu = new Menu();
		menu.getFoodSet().add(new Food(5, "羊肉串", 20.0));
		menu.getFoodSet().add(new Food(6, "溜肉段", 20.0));
		menu.getFoodSet().add(new Food(7, "大肘子", 45.0));
		menu.getFoodSet().add(new Food(8, "蛋花汤", 26.0));
		menu.getFoodSet().add(new Food(9, "木须肉", 20.0));
		menu.getFoodSet().add(new Food(10, "炸丸子", 22.0));
		menu.getFoodSet().add(new Food(1, "鱼香肉丝", 20.0));
		menu.getFoodSet().add(new Food(2, "宫保鸡丁", 18.0));
		menu.getFoodSet().add(new Food(3, "老虎菜", 10.0));
		menu.getFoodSet().add(new Food(4, "糖醋里脊", 30.0));

		restaurant.setMenu(menu);
		Menu tempMenu = restaurant.getMenu();

		for (Food c : tempMenu.getFoodSet()) {
			System.out.println(c.toString());
		}
		restaurant.open();
		return restaurant;
	}
}
