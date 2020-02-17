package com.utils.alipay;

import java.math.BigDecimal;

import com.alipay.api.AlipayObject;
/**
 * 业务参数
 * 
 * @author LiQiang
 * @date 2016年12月12日
 */
public class AliPayBizModel extends AlipayObject{

	private static final long serialVersionUID = -717857083789476499L;
	/**
	 * 对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body,非必填
	 */
	private String body;
	/**
	 * 订单标题,必填
	 */
	private String subject;
	/**
	 * 商户网站唯一订单号,必填
	 */
	private String out_trade_no;
	/**
	 * 该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。,非必填
	 */
	private String timeout_express;
	/**
	 * 该笔订单的资金总额，单位为RMB-Yuan。取值范围为[0.01，100000000.00]，精确到小数点后两位。,必填
	 */
	private BigDecimal total_amount;
	/**
	 * 收款支付宝账号对应的支付宝唯一用户号。 以2088开头的纯16位数字,必填
	 */
	private String seller_id;
	
	/**
	 * 销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY,必填
	 */
	private String product_code;
	/**
	 * 商品主类型：0—虚拟类商品，1—实物类商品注：虚拟类商品不支持使用花呗渠道,非必填
	 */
	private String goods_type;
	/**
	 * 公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。支付宝会在异步通知时将该参数原样返回。本参数必须进行UrlEncode之后才可以发送给支付宝,非必填
	 */
	private String passback_params;
	/**
	 * 优惠参数注：仅与支付宝协商后可用,非必填
	 */
	private String promo_params;
	/**
	 * 业务扩展参数，详见下面的“业务扩展参数说明”,非必填
	 */
	private String extend_params;
	/**
	 * 可用渠道，用户只能在指定渠道范围内支付当有多个渠道时用“,”分隔注：与disable_pay_channels互斥,非必填
	 */
	private String enable_pay_channels;
	/**
	 * 禁用渠道，用户不可用指定渠道支付当有多个渠道时用“,”分隔注：与enable_pay_channels互斥,非必填
	 */
	private String disable_pay_channels;
	/**
	 * 添加该参数后在h5支付收银台会出现返回按钮，可用于用户付款中途退出并返回到该参数指定的商户网站地址。注：该参数对支付宝钱包标准收银台下的跳转不生效。
	 */
	private String quit_url;
	
	public String getQuit_url() {
		return quit_url;
	}
	public void setQuit_url(String quit_url) {
		this.quit_url = quit_url;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTimeout_express() {
		return timeout_express;
	}
	public void setTimeout_express(String timeout_express) {
		this.timeout_express = timeout_express;
	}
	public String getGoods_type() {
		return goods_type;
	}
	public void setGoods_type(String goods_type) {
		this.goods_type = goods_type;
	}
	public String getPassback_params() {
		return passback_params;
	}
	public void setPassback_params(String passback_params) {
		this.passback_params = passback_params;
	}
	public String getPromo_params() {
		return promo_params;
	}
	public void setPromo_params(String promo_params) {
		this.promo_params = promo_params;
	}
	public String getExtend_params() {
		return extend_params;
	}
	public void setExtend_params(String extend_params) {
		this.extend_params = extend_params;
	}
	public String getEnable_pay_channels() {
		return enable_pay_channels;
	}
	public void setEnable_pay_channels(String enable_pay_channels) {
		this.enable_pay_channels = enable_pay_channels;
	}
	public String getDisable_pay_channels() {
		return disable_pay_channels;
	}
	public void setDisable_pay_channels(String disable_pay_channels) {
		this.disable_pay_channels = disable_pay_channels;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getProduct_code() {
		return product_code;
	}
	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public BigDecimal getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(BigDecimal total_amount) {
		this.total_amount = total_amount;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	
}
