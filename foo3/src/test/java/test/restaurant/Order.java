package test.restaurant;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class Order implements Serializable {

	private int orderNo;
	private Customer customer;
	private Waiter waiter;

	private Map<Food, Integer> foodList = new HashMap<>();

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}

	public Map<Food, Integer> getFoodList() {
		return foodList;
	}

	public void setFoodList(Map<Food, Integer> foodList) {
		this.foodList = foodList;
	}

}
