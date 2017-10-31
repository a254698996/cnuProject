package jvm;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public class SorterImpl implements Sorter {

	private AtomicInteger count = new AtomicInteger(0);

	@Override
	// 加入 swapped 后循环变少的原因是:
	// 当 swapped == true 恰巧 是 当前 元素 移动到 最后一个位置 list.length - i
	// 就是说 当前 元素 刚好是 移动到了 可移动的最后，就是 最后那个位置 刚好是排好序的
	// 所以 当 i++ 一直加到 list.length - i 时这个位置已经排好序了，所以就不用再 进行一次循环换位了
	public <T extends Comparable<T>> void sort(T[] list) {
		int totalCount = 0;
		boolean swapped = true;
		for (int i = 1; i < list.length && swapped; i++) {
			swapped = false;
			System.out.println("out .. ");
			for (int j = 0; j < list.length - i; j++) {
				if (list[j].compareTo(list[j + 1]) > 0) {
					T temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
					swapped = true;
				}
				totalCount++;
				System.out.println("in ....");
				count.incrementAndGet();
			}
		}
		System.out.println("totalCount " + totalCount);
	}

	@Override
	public <T> void sort(T[] list, Comparator<T> comp) {
		boolean swapped = true;
		for (int i = 1, len = list.length; i < len && swapped; ++i) {
			swapped = false;
			for (int j = 0; j < len - i; ++j) {
				if (comp.compare(list[j], list[j + 1]) > 0) {
					T temp = list[j];
					list[j] = list[j + 1];
					list[j + 1] = temp;
					swapped = true;
				}
			}
		}
	}

	public AtomicInteger getCount() {
		return count;
	}

}
