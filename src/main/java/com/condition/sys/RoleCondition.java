package com.condition.sys;

import com.dto.base.Encryption;

public class RoleCondition extends Encryption{

	private Integer page;
	private Integer rows;
	private Integer offset;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	
	
}
