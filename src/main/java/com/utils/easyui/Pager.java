package com.utils.easyui;

import java.util.List;

public class Pager {

	private Long total;
	
	private Integer pageCount;
	
	private Integer currentPage;
	
	private List<?> rows;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	
	public static Integer getPageCount(Long count, Integer rows) {
		if (count % rows == 0) {
			return (int) (count/rows);
		}else {
			return (int) (count/rows)+1;
		}
	}
}
