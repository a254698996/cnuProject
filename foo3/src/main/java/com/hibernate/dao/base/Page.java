package com.hibernate.dao.base;

import java.util.List;

import org.hibernate.Criteria;

public class Page {
	private int startIndex, pageSize;
	private long totalCount;
	private List list;

	public Page() {

	}
	
	public Page(int startIndex, long totalCount, int pageSize, List list) {
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
	
	public static int getStartOfPage(int pageNo,int pageSize ){
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

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}