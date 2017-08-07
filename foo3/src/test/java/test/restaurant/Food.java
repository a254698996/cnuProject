package test.restaurant;

public class Food implements Comparable<Food>, Cloneable {

	private int fid;
	private String name;
	private double price;
	private Cooker cooker;
	private int cookUseTime = 0;
	private int eatSeqNo;
	private int eatTime;

	public Food(int fid, String name, double price) {

		super();
		this.fid = fid;
		this.name = name;
		this.price = price;
	}

	@Override
	public int compareTo(Food o) {
		if (this.getFid() > o.getFid()) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public String toString() {
		return "Food [fid=" + fid + ", name=" + name + ", price=" + price + "]";
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Cooker getCooker() {
		return cooker;
	}

	public void setCooker(Cooker cooker) {
		this.cooker = cooker;
	}

	public int getCookUseTime() {
		return cookUseTime;
	}

	public void setCookUseTime(int cookUseTime) {
		this.cookUseTime = cookUseTime;
	}

	public int getEatSeqNo() {
		return eatSeqNo;
	}

	public void setEatSeqNo(int eatSeqNo) {
		this.eatSeqNo = eatSeqNo;
	}

	public int getEatTime() {
		return eatTime;
	}

	public void setEatTime(int eatTime) {
		this.eatTime = eatTime;
	}

}
