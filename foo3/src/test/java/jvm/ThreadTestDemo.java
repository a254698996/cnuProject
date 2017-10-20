package jvm;

public class ThreadTestDemo {

	public synchronized void print() {
		int i = 5;
		while (i-- > 0) {
			System.out.println(Thread.currentThread().getName() + ":" + i);

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static synchronized void printStatic() {
		int i = 5;
		while (i-- > 0) {
			System.out.println(Thread.currentThread().getName() + ":" + i);

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static synchronized void printStaticAA() {
		int i = 5;
		while (i-- > 0) {
			System.out.println(Thread.currentThread().getName() + ":" + i);

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		final ThreadTestDemo t = new ThreadTestDemo();
		Thread test1 = new Thread(new Runnable() {
			public void run() {
				t.print();
			}
		}, "test1");
		Thread test2 = new Thread(new Runnable() {
			public void run() {
				ThreadTestDemo.printStatic();
			}
		}, "test2");
		// 后加的 test3 test3与test2 中用到的锁是同一个锁 ThreadTestDemo的类锁 有竞争关系
		Thread test3 = new Thread(new Runnable() {
			public void run() {
				ThreadTestDemo.printStaticAA();
			}
		}, "test3");
		// 线程 锁 面试题
		// test1.start();
		// test2.start();
		// test3.start();
		// -- 结果为
		// test2:4
		// test1:4
		// test1:3
		// test2:3
		// test1:2
		// test2:2
		// test2:1
		// test1:1
		// test2:0
		// test1:0
		// 因为 test1 test2 取得的不是一 同一把锁
		// test1 取得的是当前对像锁
		// test2 取的是 ThreadTestDemo.class 的锁
		// 不是同一把锁 没有竞争 关系 ,得到上面的结果的原因是 Thread.sleep(500)的存在
		// test1 test2 进行了各自的睡眠状态 但 并不没有释放 当前线所拥有的锁,
		// 看起来 test1 test2 交替 打印是 因为 spleep(500)的存在 ,
		// 并不是两个线程有锁竞争关系

		Integer a = new Integer(3);
		Integer b = 3;
		int c = 3;
		System.out.println(a == b);
		System.out.println(a == c);
		// 结果为 ,第一个 false 为 引用对象 比较所以为 false
		// 第二个 为 int 类型 0 - 127 之间的 值 比较 所以为 true
		// false
		// true

		short s1 = 1;
		// 下面语句编译不通过, 因为 1 为整型 ,整型 与 s1 相加 会向上转型为整型，
		// 整型是不能赋值给 short型 short占4位 int 占8位
		// s1 = s1 + 1;
		// 下面的可以正常运行 += 会做 向下类型转换 ,所以 可以赋值给 s1
		s1 += 1;
		System.out.println("s1  " + s1);

		String q1 = "abc";
		String q2 = "abc";
		String q3 = new String("abc");
		System.out.println("q1 == q2  " + (q1 == q2));
		System.out.println("q1 == q3  " + (q1 == q3));

		String w1 = new String("java");
		String w2 = new String("java");

		System.out.println("w1==w2 " + (w1 == w2)); // false
		System.out.println("w1equals(w2) " + w1.equals(w2));

	}
}
