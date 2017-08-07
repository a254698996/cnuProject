package test.restaurant;

import java.util.HashSet;
import java.util.Set;

public class FoodOrder {

	private Customer customer;

	private Waiter waiter;

	private Set<Food> foodSet = new HashSet<>();

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<Food> getFoodSet() {
		return foodSet;
	}

	public void setFoodSet(Set<Food> foodSet) {
		this.foodSet = foodSet;
	}

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}

}
