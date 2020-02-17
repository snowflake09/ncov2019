package com.condition.ncov;

import com.dto.base.Encryption;


public class NcovBuildingCondition extends Encryption{

	private Integer regionid;
	private String jsCode;
	private String name;
	private String managename;
	private String manage_id;
	private Integer page;
	private Integer rows;
	private Integer offset;
	private Long sessionid;

	public Long getSessionid() {
		return sessionid;
	}

	public void setSessionid(Long sessionid) {
		this.sessionid = sessionid;
	}

	public String getManagename() {
		return managename;
	}

	public void setManagename(String managename) {
		this.managename = managename;
	}
	public Integer getRegionid() {
		return regionid;
	}

	public void setRegionid(Integer regionid) {
		this.regionid = regionid;
	}
	public String getJsCode() {
		return jsCode;
	}

	public void setJsCode(String jsCode) {
		this.jsCode = jsCode;
	}

	public String getManage_id() {
		return manage_id;
	}

	public void setManage_id(String manage_id) {
		this.manage_id = manage_id;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
