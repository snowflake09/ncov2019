package com.utils.wechatpay;

/**
 * 支付请求参数实体
 * 
 * @author LiQiang
 * @date 2016年12月27日
 */
public class WeChatPayBizModel {

	/**
	 * 微信开放平台审核通过的应用APPID
	 */
	private String appid;
	/**
	 * 微信支付分配的商户号
	 */
	private String mch_id;
	/**
	 * 随机字符串，不长于32位
	 */
	private String nonce_str;
	/**
	 * 签名
	 */
	private String sign;
	/**
	 * 商品描述交易字段格式根据不同的应用场景按照以下格式：APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值。
	 */
	private String body;
	/**
	 * 商户系统内部的订单号,32个字符内、可包含字母, 
	 */
	private String out_trade_no;
	/**
	 * 商户系统内部的订单号,32个字符内、可包含字母, 
	 */
	private int total_fee;
	/**
	 * 用户端实际ip
	 */
	private String spbill_create_ip;
	/**
	 * 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
	 */
	private String notify_url;
	/**
	 * 支付类型:APP
	 */
	private String trade_type;
	/**
	 * 用户openid
	 */
	private String openid;
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
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
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	
}
