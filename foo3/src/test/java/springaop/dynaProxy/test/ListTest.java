package springaop.dynaProxy.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ListTest {

	public static void main(String[] args) {
		// int count = 1000_000;
		// System.out.println(count);
		// System.out.println(1000_000);
		// testArrayinitSizeSet(count);

		// testMapinitSizeSet(count);

		testCall();
	}

	private static void testArrayinitSizeSet(int count) {
		long currentTimeMillis = System.currentTimeMillis();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			list.add(i);
		}
		System.out.println("time  " + (System.currentTimeMillis() - currentTimeMillis));

		long currentTimeMillis2 = System.currentTimeMillis();
		List<Integer> list2 = new ArrayList<>(count);
		for (int i = 0; i < count; i++) {
			list2.add(i);
		}
		System.out.println("time  " + (System.currentTimeMillis() - currentTimeMillis2));
	}

	private static void testMapinitSizeSet(int count) {
		long currentTimeMillis = System.currentTimeMillis();
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < count; i++) {
			map.put(i, i);
		}
		System.out.println("time  " + (System.currentTimeMillis() - currentTimeMillis));

		long currentTimeMillis2 = System.currentTimeMillis();
		Map<Integer, Integer> map2 = new HashMap<>(count);
		for (int i = 0; i < count; i++) {
			map2.put(i, i);
		}
		System.out.println("time  " + (System.currentTimeMillis() - currentTimeMillis2));
	}

	private static void testCall() {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(20);

		List<Future<Integer>> futureList = new ArrayList<>(10);
		for (int i = 0; i < 10; i++) {
			Future<Integer> submit = newFixedThreadPool.submit(new MyCall());
			futureList.add(submit);
		}
		newFixedThreadPool.shutdown();
		for (Future<Integer> future : futureList) {
			try {
				System.out.println("get Future  nextInt:" + future.get());
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("futureList size " + futureList.size());
	}
}

class MyCall implements Callable<Integer> {
	@Override
	public Integer call() throws Exception {
		int nextInt = new Random().nextInt(998);
		System.out.println("execute MyCall  nextInt:" + nextInt);
		return nextInt;
	}
}
