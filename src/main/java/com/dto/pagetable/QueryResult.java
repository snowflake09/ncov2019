package com.dto.pagetable;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页结果集封装类
 * 
 * @author 
 * @since 2019年11月20日
 */

public class QueryResult
{
	
	/**
	 * 总记录数
	 */
	private long total;

	/**
	 * 查询结果集
	 */
	private List<?> list = new ArrayList<>();
	
	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

}
