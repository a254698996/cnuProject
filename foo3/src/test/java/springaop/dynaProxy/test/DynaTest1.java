package springaop.dynaProxy.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import springaop.dynaProxy.DLogger;
import springaop.dynaProxy.DynaProxyHello1;
import springaop.staticaop.Hello;
import springaop.staticaop.IHello;

public class DynaTest1 {
	public static void main(String[] args) {
		IHello hello = (IHello) new DynaProxyHello1().bind(new Hello(), new DLogger());// 如果我们需要日志功能，则使用代理类
		// IHello hello = new Hello();//如果我们不需要日志功能则使用目标类
		hello.sayHello("明天");
		// Hashtable<String, Integer> obj;
		//
		// ConcurrentHashMap<Object, Object> map;
		// SimpleDateFormat sdf;
		//
		// Callable<String> call;

		// testSimpleDateFormat();

		testSimpleDateFormat2();
	}

	private static void testSimpleDateFormat() {
		ExecutorService executor = Executors.newCachedThreadPool();
		for (int i = 0; i < 30; i++) {

			// A 会sleep 2s 后开始执行sdf.parse()
			executor.execute(new DateFormatTest("A", "1991-09-13", true));
			// B 打了断点,会卡在方法中间
			executor.execute(new DateFormatTest("B", "2013-09-13", false));

		}
		executor.shutdown();
	}

	private static void testSimpleDateFormat2() {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		for (int i = 0; i < 30; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println(
								Thread.currentThread().getName() + "  " + DateUtil.parse("2013-09-13", "yyyy-MM-dd"));
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			});

		}
		executor.shutdown();
	}

}

class DateFormatTest extends Thread {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	ThreadLocal<SimpleDateFormat> sdff = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd");
		}
	};

	private String name;
	private String dateStr;
	private boolean sleep;

	public DateFormatTest(String name, String dateStr, boolean sleep) {
		this.name = name;
		this.dateStr = dateStr;
		this.sleep = sleep;
	}

	@Override
	public void run() {

		Date date = null;

		if (sleep) {
			try {
				TimeUnit.MILLISECONDS.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		try {
			date = sdff.get().parse(dateStr);
			// date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println(name + " : date: " + date);
	}

	// public static void main(String[] args) throws InterruptedException {
	//
	// ExecutorService executor = Executors.newCachedThreadPool();
	//
	// // A 会sleep 2s 后开始执行sdf.parse()
	// executor.execute(new DateFormatTest("A", "1991-09-13", true));
	// // B 打了断点,会卡在方法中间
	// executor.execute(new DateFormatTest("B", "2013-09-13", false));
	//
	// executor.shutdown();
	// }
}

class DateUtil {

	/** 锁对象 */
	private static final Object lockObj = new Object();
	private static final Lock lock = new ReentrantLock();

	/** 存放不同的日期模板格式的sdf的Map */
	private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

	/**
	 * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
	 * 
	 * @param pattern
	 * @return
	 */
	private static SimpleDateFormat getSdf(final String pattern) {
		ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);

		// 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
		if (tl == null) {
			synchronized (lockObj) {
				// try {
				// boolean tryLock = lock.tryLock(100, TimeUnit.MILLISECONDS);
				// if (tryLock) {
				tl = sdfMap.get(pattern);
				if (tl == null) {
					// 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
					System.out.println("put new sdf of pattern " + pattern + " to map ");
					System.out.println("tl  " + tl);

					// 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new
					// SimpleDateFormat
					tl = new ThreadLocal<SimpleDateFormat>() {

						@Override
						protected SimpleDateFormat initialValue() {
							System.out.println("thread: " + Thread.currentThread() + " init pattern: " + pattern);
							return new SimpleDateFormat(pattern);
						}
					};
					sdfMap.put(pattern, tl);
				}
				// } else {
				// System.out.println("没有得到 lock");
				// }
				// } catch (Exception e) {
				// e.printStackTrace();
				// } finally {
				// lock.unlock();
				// }

			}
		} else

		{
			System.out.println("t1 exist");
		}
		return tl.get();
	}

	/**
	 * 是用ThreadLocal<SimpleDateFormat>来获取SimpleDateFormat,这样每个线程只会有一个SimpleDateFormat
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		return getSdf(pattern).format(date);
	}

	public static Date parse(String dateStr, String pattern) throws ParseException {
		return getSdf(pattern).parse(dateStr);
	}

}
