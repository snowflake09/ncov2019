package com.utils.wechatpay;

public class WeChatPayRedModel {

	private String nonce_str; //随机字符串，不长于32位
	private String sign;	//
	private String mch_billno;	//商户订单号
	private String mch_id;	//微信支付分配的商户号
	private String wxappid;	//微信分配的公众账号ID
	private String send_name;	//红包发送者名称
	private String re_openid;	//接受红包的用户用户在wxappid下的openid
	private int total_amount;	//付款金额，单位分
	private int total_num;	//红包发放总人数
	private String wishing;	//红包祝福语
	private String client_ip;	//调用接口的机器Ip地址
	private String act_name;	//活动名称
	private String remark;	//备注信息
	private String notify_way;//通知用户形式
	private String scene_id; //PRODUCT_5
	private String cre_path;
	
	public String getCre_path() {
		return cre_path;
	}
	public void setCre_path(String cre_path) {
		this.cre_path = cre_path;
	}
	public String getNotify_way() {
		return notify_way;
	}
	public void setNotify_way(String notify_way) {
		this.notify_way = notify_way;
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
	public String getMch_billno() {
		return mch_billno;
	}
	public void setMch_billno(String mch_billno) {
		this.mch_billno = mch_billno;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getWxappid() {
		return wxappid;
	}
	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}
	public String getSend_name() {
		return send_name;
	}
	public void setSend_name(String send_name) {
		this.send_name = send_name;
	}
	public String getRe_openid() {
		return re_openid;
	}
	public void setRe_openid(String re_openid) {
		this.re_openid = re_openid;
	}
	public int getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}
	public int getTotal_num() {
		return total_num;
	}
	public void setTotal_num(int total_num) {
		this.total_num = total_num;
	}
	public String getWishing() {
		return wishing;
	}
	public void setWishing(String wishing) {
		this.wishing = wishing;
	}
	public String getClient_ip() {
		return client_ip;
	}
	public void setClient_ip(String client_ip) {
		this.client_ip = client_ip;
	}
	public String getAct_name() {
		return act_name;
	}
	public void setAct_name(String act_name) {
		this.act_name = act_name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getScene_id() {
		return scene_id;
	}
	public void setScene_id(String scene_id) {
		this.scene_id = scene_id;
	}
	
}
