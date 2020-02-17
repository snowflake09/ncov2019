package com.utils.wechatpay;

/**
 * 公众号签名订单实体
 * 
 * @author LiQiang
 * @date 2016年12月27日
 */
public class WeChatPayAppParamModel {

	/**
	 * 公众账号ID
	 */
	private String appId;
	/**
	 * 商户号
	 */
	private String partnerId;
	/**
	 * 交易单号
	 */
	private String prepayId;
	/**
	 * 随机字符串
	 */
	private String nonceStr;
	/**
	 * 时间戳
	 */
	private String timeStamp;
	/**
	 * 订单详情扩展字符串，例：prepay_id=123456789
	 */
	private String packAge;
	/**
	 * 签名
	 */
	private String sign;
	
	private String paymentNo;
	
	public String getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getPrepayId() {
		return prepayId;
	}
	public void setPrepayId(String prepayId) {
		this.prepayId = prepayId;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getPackAge() {
		return packAge;
	}
	public void setPackAge(String packAge) {
		this.packAge = packAge;
	}
	
}
