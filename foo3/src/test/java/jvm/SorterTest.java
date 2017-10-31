package jvm;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class SorterTest {

	public static void main(String[] args) {
		Integer[] arr = new Integer[] { 1, 5, 2, 378, 99, 9473, 4, 78 };
		Sorter sorter = new SorterImpl();
		sorter.sort(arr);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ",");
		}

		sorter.sort(arr, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				if (o1 < o2) {
					return 1;
				} else if (o1 > o2) {
					return -1;
				}
				return 0;
			}
		});
		AtomicInteger count = sorter.getCount();
		System.out.println("count  " + count);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ",");
		}
	}

}
