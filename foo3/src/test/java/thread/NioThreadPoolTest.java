package thread;

import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioThreadPoolTest {

	private static FileOutputStream fos;

	public static void main(String[] args) throws Exception {
		long currentTimeMillis = System.currentTimeMillis();
		makeAccountFile("D:/1.txt");
		System.out.println(System.currentTimeMillis() - currentTimeMillis);
	}

	private static void makeAccountFile(String filePath) throws Exception {
		fos = new FileOutputStream(filePath);
		FileChannel fc = fos.getChannel();
		// 创建一个buffer并把准备写的数据填充进去;
		ByteBuffer bb = ByteBuffer.allocate(1024);
		for (int i = 1; i <= 100; i++) {
			// 数据源
			byte[] message = new Content(99992992 + i, "张三" + i, "2017-10-20", 5, "+", "消费").toString().getBytes("GBK");
			bb.put(message);
			if (i % 25 == 0) {
				bb.flip();
				fc.write(bb);
				bb.clear();
			}
		}
	}
}

class Content {
	private long accountNo;
	private String name;
	private String date;
	private double money;
	private String mark;
	private String desc;

	public Content(long accountNo, String name, String date, double money, String mark, String desc) {
		super();
		this.accountNo = accountNo;
		this.name = name;
		this.date = date;
		this.money = money;
		this.mark = mark;
		this.desc = desc;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return accountNo + "," + name + "," + date + "" + money + "," + mark + "," + desc + "\r\n";
	}

}
