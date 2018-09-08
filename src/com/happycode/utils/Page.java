package com.happycode.utils;

import java.util.List;

public class Page<T> {
    
	// 总记录数
	private int total;
	// 当前页号
	private int page;
	// 页面大小
	private int size;
	// 数据
    private List<T> rows;
    
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
    
	
    
}
