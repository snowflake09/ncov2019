package com.condition.ncov;

import com.dto.base.Encryption;

import java.util.List;

public class NcovUserRecordCondition extends Encryption{

	private Integer page;
	private Integer rows;
	private Integer offset;
	private String openid;
	private Long REGIONID;
	private String WECHATID;
	private Integer USERTYPE;
	private String USERNAME;
	private String TEMPERATURE;
	private Integer HEALTHY;
	private String HEALTHNOTE;
	private Long REPORTUSERID;
	private String startDayTime;
	private String endDayTime;
	private String CARDNO;
	private String PHONE;
	private String REGIONADDRESS;
	private String ADDRESS;
	private Long sessionid;

	public Long getSessionid() {
		return sessionid;
	}

	public void setSessionid(Long sessionid) {
		this.sessionid = sessionid;
	}

	public String getREGIONADDRESS() {
		return REGIONADDRESS;
	}

	public void setREGIONADDRESS(String REGIONADDRESS) {
		this.REGIONADDRESS = REGIONADDRESS;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String ADDRESS) {
		this.ADDRESS = ADDRESS;
	}

	public String getStartDayTime() {
		return startDayTime;
	}

	public void setStartDayTime(String startDayTime) {
		this.startDayTime = startDayTime;
	}

	public String getEndDayTime() {
		return endDayTime;
	}

	public void setEndDayTime(String endDayTime) {
		this.endDayTime = endDayTime;
	}

	public String getCARDNO() {
		return CARDNO;
	}

	public void setCARDNO(String CARDNO) {
		this.CARDNO = CARDNO;
	}

	public String getPHONE() {
		return PHONE;
	}

	public void setPHONE(String PHONE) {
		this.PHONE = PHONE;
	}

	public Long getREPORTUSERID() {
		return REPORTUSERID;
	}

	public void setREPORTUSERID(Long REPORTUSERID) {
		this.REPORTUSERID = REPORTUSERID;
	}

	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String USERNAME) {
		this.USERNAME = USERNAME;
	}

	public String getTEMPERATURE() {
		return TEMPERATURE;
	}

	public void setTEMPERATURE(String TEMPERATURE) {
		this.TEMPERATURE = TEMPERATURE;
	}

	public Integer getHEALTHY() {
		return HEALTHY;
	}

	public void setHEALTHY(Integer HEALTHY) {
		this.HEALTHY = HEALTHY;
	}

	public String getHEALTHNOTE() {
		return HEALTHNOTE;
	}

	public void setHEALTHNOTE(String HEALTHNOTE) {
		this.HEALTHNOTE = HEALTHNOTE;
	}
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Long getREGIONID() {
		return REGIONID;
	}

	public void setREGIONID(Long REGIONID) {
		this.REGIONID = REGIONID;
	}

	public String getWECHATID() {
		return WECHATID;
	}

	public void setWECHATID(String WECHATID) {
		this.WECHATID = WECHATID;
	}

	public Integer getUSERTYPE() {
		return USERTYPE;
	}

	public void setUSERTYPE(Integer USERTYPE) {
		this.USERTYPE = USERTYPE;
	}

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
