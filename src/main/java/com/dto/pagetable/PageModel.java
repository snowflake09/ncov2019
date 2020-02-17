package com.dto.pagetable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LiQiang
 * @since 2016年10月22日
 */
public class PageModel implements Serializable {

	private static final long serialVersionUID = -3542668843268550832L;

	public static final String asc = "ASC";

	public static final String desc = "DESC";

	/**
	 * 总记录数
	 */
	private Long total;

	/**
	 * 当前页
	 */
	private int page;

	/**
	 * 每页显示记录数
	 */

	private int pageSize;

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

	public PageModel() {
	}

	public PageModel(String sort, String order) {
		this.sort = sort;
		this.order = order;
	}

	public PageModel(int page, int pageSize, String sort, String order) {
		super();
		this.page = page;
		this.pageSize = pageSize;
		this.sort = sort;
		this.order = order;
	}

	public Long getTotal() {
		if (this.total == null) {
			return new Long(0);
		}
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
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
		if (rows == null) {
			rows = new ArrayList<>();
		}
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public int getStart() {
		if (page == 0) {
			page = 1;
		}
		return (page - 1) * pageSize;
	}

	public void orderBy(String sortField, String orderType) {
		this.sort = sortField;
		this.order = orderType;
	}

}
