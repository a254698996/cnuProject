package test.restaurant;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Menu {

	private TreeSet<Food> foodSet = new TreeSet<>();

	public Set<Food> getFoodSet() {
		return foodSet;
	}

	public void setFoodSet(TreeSet<Food> foodSet) {
		this.foodSet = foodSet;
	}

	public Food choose(int no) {
		Iterator<Food> iterator = foodSet.iterator();
		while (iterator.hasNext()) {
			Food next = iterator.next();
			if (next.getFid() == no) {
				return next;
			}
		}
		return null;
	}
}
