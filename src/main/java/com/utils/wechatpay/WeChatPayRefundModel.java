package com.utils.wechatpay;

/**
 * 微信退款请求实体参数
 * 
 * @author LiQiang
 * @date 2017年1月5日
 */
public class WeChatPayRefundModel {

	private String appid;
	private String mch_id;
	private String device_info;
	private String nonce_str;
	private String sign;
	private String sign_type;
	private String transaction_id;
	private String out_trade_no;
	private String out_refund_no;
	private int total_fee;
	private int refund_fee;
	private String refund_fee_type;
	private String op_user_id;
	private String refund_account;
	private String cre_path;
	
	public String getCre_path() {
		return cre_path;
	}
	public void setCre_path(String cre_path) {
		this.cre_path = cre_path;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getOut_refund_no() {
		return out_refund_no;
	}
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public int getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(int refund_fee) {
		this.refund_fee = refund_fee;
	}
	public String getRefund_fee_type() {
		return refund_fee_type;
	}
	public void setRefund_fee_type(String refund_fee_type) {
		this.refund_fee_type = refund_fee_type;
	}
	public String getOp_user_id() {
		return op_user_id;
	}
	public void setOp_user_id(String op_user_id) {
		this.op_user_id = op_user_id;
	}
	public String getRefund_account() {
		return refund_account;
	}
	public void setRefund_account(String refund_account) {
		this.refund_account = refund_account;
	}
	
}
