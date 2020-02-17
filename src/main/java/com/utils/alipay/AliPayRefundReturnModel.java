package com.utils.alipay;

import java.math.BigDecimal;
import java.util.Date;

public class AliPayRefundReturnModel {

	private String code;
	private String msg;
	private String buyer_logon_id;
	private String trade_no;
	private String out_trade_no;
	private String fund_change;
	private BigDecimal refund_fee;
	private Date gmt_refund_pay;
	private String store_name;
	private String buyer_user_id;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getBuyer_logon_id() {
		return buyer_logon_id;
	}
	public void setBuyer_logon_id(String buyer_logon_id) {
		this.buyer_logon_id = buyer_logon_id;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getFund_change() {
		return fund_change;
	}
	public void setFund_change(String fund_change) {
		this.fund_change = fund_change;
	}
	public BigDecimal getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(BigDecimal refund_fee) {
		this.refund_fee = refund_fee;
	}
	public Date getGmt_refund_pay() {
		return gmt_refund_pay;
	}
	public void setGmt_refund_pay(Date gmt_refund_pay) {
		this.gmt_refund_pay = gmt_refund_pay;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getBuyer_user_id() {
		return buyer_user_id;
	}
	public void setBuyer_user_id(String buyer_user_id) {
		this.buyer_user_id = buyer_user_id;
	}
	
}
