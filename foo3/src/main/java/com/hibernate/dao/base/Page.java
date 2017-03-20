package com.hibernate.dao.base;

import java.util.List;

import org.hibernate.Criteria;

public class Page {
	public final static int defaultStartIndex = 1;
	public final static int defaultPageSize = 10;

	private int startIndex, pageSize;
	private long totalCount;
	private List<Object> list;

	public Page() {

	}

	public Page(int startIndex, long totalCount, int pageSize, List<Object> list) {
		super();
		this.startIndex = startIndex;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.list = list;
	}

	public Page(Criteria criteria, int pageNo, int pageSize) {

	}

	public Page(String hql, int start, int pageSize, Object... values) {

	}

	public static int getStartOfPage(int pageNo, int pageSize) {
		return 0;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

}
