package restaurant;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class Restaurant {

	private RestaurantSate isOpen;
	private AtomicInteger waiterCount = new AtomicInteger(0);

	private Set<Waiter> waiterSet = new HashSet<>();
	private Set<Cooker> cookerSet = new HashSet<>();
	private Set<Customer> customerSet = new HashSet<>();

	private Menu menu;

	public void open() {
		this.setIsOpen(RestaurantSate.OPEND);
	}

	public Waiter getWaiter() {
		if (waiterCount.get() > 0) {
			Iterator<Waiter> iterator = waiterSet.iterator();
			while (iterator.hasNext()) {
				Waiter next = iterator.next();
				if (!next.isBuzy()) {
					next.setBuzy(true);
					return next;
				}
			}
		}
		return null;
	}

	public RestaurantSate getIsOpen() {
		return isOpen;
	}

	public void setIsOpen(RestaurantSate isOpen) {
		this.isOpen = isOpen;
	}

	public Set<Waiter> getWaiterSet() {
		return waiterSet;
	}

	public void setWaiterSet(Set<Waiter> waiterSet) {
		this.waiterSet = waiterSet;
		waiterCount.incrementAndGet();
	}

	public Set<Cooker> getCookerSet() {
		return cookerSet;
	}

	public void setCookerSet(Set<Cooker> cookerSet) {
		this.cookerSet = cookerSet;
	}

	public Set<Customer> getCustomerSet() {
		return customerSet;
	}

	public void setCustomerSet(Set<Customer> customerSet) {
		this.customerSet = customerSet;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

}

enum RestaurantSate {
	OPEND, CLOSE;
}
