package com.dto.pagetable;

import java.math.BigDecimal;
import java.util.List;

public class MobilePageModel {

	/**
	 * 当前条数
	 */
	private Integer page;
	
	/**
	 * 每页显示记录数
	 */
	private Integer pageSize;
	
	/**
	 * 排序字段
	 */
	private String sort;
	
	/**
	 * asc/desc
	 */
	private String order;
	
	/**
	 * 查询结果集
	 */
	private List<?> rows;
	
	private BigDecimal totalPrice;
	
	private Integer totalNum;
	
	private String day;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}
	
}
