package qingfeng;

public class EveryLimit {
	private String start;
	private String end;
	private String connStr;

	public EveryLimit() {

	}

	public EveryLimit(String start, String end, String connStr) {
		super();
		this.start = start;
		this.end = end;
		this.connStr = connStr;
	}

	@Override
	public String toString() {
		return "EveryLimit [start=" + start + ", end=" + end + ", connStr=" + connStr + "]";
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getConnStr() {
		return connStr;
	}

	public void setConnStr(String connStr) {
		this.connStr = connStr;
	}

}
