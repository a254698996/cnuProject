package jvm;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

public interface Sorter {

	public <T extends Comparable<T>> void sort(T[] list);

	public <T> void sort(T[] list, Comparator<T> comp);
	
	public AtomicInteger getCount();

}
