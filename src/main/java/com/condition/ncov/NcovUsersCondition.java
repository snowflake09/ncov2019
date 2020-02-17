package com.condition.ncov;

import com.dto.base.Encryption;

import java.util.Date;
import java.util.List;

public class NcovUsersCondition extends Encryption{

	private Integer page;
	private Integer rows;
	private Integer offset;
	private String openid;
	private Long REGIONID;
	private String WECHATID;
	private Integer USERTYPE;
	private String CARDNO;
	private String NAME;
	private String PHONE;
	private Integer SEX;//出行方式
	private String TEMPERATURE;
	private String startDayTime;
	private String endDayTime;
	private String REGIONADDRESS;
	private String ADDRESS;
	private Long sessionid;
	private List<Long> regionids;
	private String HEALTHY;

	public Long getSessionid() {
		return sessionid;
	}

	public void setSessionid(Long sessionid) {
		this.sessionid = sessionid;
	}

	public String getHEALTHY() {
		return HEALTHY;
	}

	public void setHEALTHY(String HEALTHY) {
		this.HEALTHY = HEALTHY;
	}

	public List<Long> getRegionids() {
		return regionids;
	}

	public void setRegionids(List<Long> regionids) {
		this.regionids = regionids;
	}


	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String ADDRESS) {
		this.ADDRESS = ADDRESS;
	}
	
	public String getREGIONADDRESS() {
		return REGIONADDRESS;
	}

	public void setREGIONADDRESS(String REGIONADDRESS) {
		this.REGIONADDRESS = REGIONADDRESS;
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

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String NAME) {
		this.NAME = NAME;
	}

	public String getPHONE() {
		return PHONE;
	}

	public void setPHONE(String PHONE) {
		this.PHONE = PHONE;
	}

	public Integer getSEX() {
		return SEX;
	}

	public void setSEX(Integer SEX) {
		this.SEX = SEX;
	}


	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getCARDNO() {
		return CARDNO;
	}

	public void setCARDNO(String CARDNO) {
		this.CARDNO = CARDNO;
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

	public String getTEMPERATURE() {
		return TEMPERATURE;
	}

	public void setTEMPERATURE(String TEMPERATURE) {
		this.TEMPERATURE = TEMPERATURE;
	}
}
