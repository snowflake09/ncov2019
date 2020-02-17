package com.utils.wechatpay;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.alipay.api.internal.util.AlipaySignature;
import com.utils.MD5;
import com.utils.MapConvert;
import com.utils.OkhttpRequest;
import com.utils.RecordNo;
import com.utils.XmlConvert;

/**
 * 微信支付
 * 
 * @author LiQiang
 * @date 2016年12月12日
 */
public class WeChatPay {
	
	/**
	 * 返回状态[成功]
	 */
	public static final String SUCCESS = "SUCCESS";
	/**
	 * 返回状态[失败]
	 */
	public static final String FAIL = "FAIL";
	/**
	 * 商户号密钥
	 */
	/*private static final String API_KEY = "";*/
	private static final String API_KEY = "";
	/**
	 * 商户号
	 */
	/*public static final String MCH_ID = "";*/
	public static final String MCH_ID = "";

	/**
	 * 应用appid
	 */
	public static final String APP_ID = "";
	public static final String APP_SECRET = "";
	
	/**
	 * app支付类型
	 */
	public static final String APP_TRADE_TYPE = "APP";

	/**
	 * 公众号支付类型
	 */
	public static final String WECHAT_TRADE_TYPE = "JSAPI";
	/**
	 * 微信支付服务统一下单地址
	 */
	private static final String SERVER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	/**
	 * 微信支付查询订单地址
	 */
	private static final String SELECT_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
	/**
	 * 微信支付退款地址
	 */
	private static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	
	/**
	 * 小程序红包发放
	 */
	private static final String MINI_RED_PARKET_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendminiprogramhb";
	
	/**
	 * 公众号红包发放
	 */
	private static final String WECHAT_RED_PARKET_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
	

	/**
	 * 回调通知地址
	 */
	public static final String NOTIFY_URL = "/webService/customer/biz/pay/weChatPayNotify";
	
	/**
	 * 商户证书地址
	 */
	public static final String MCH_CRE_PATH = "D:/home/wdm_apiclient_cert.p12";
	
	/**
	 * 创建微信支付订单
	 * @param weChatPayCreateOrderModel
	 * @return
	 * @author LiQiang
	 * @date 2018年6月4日
	 */
	public static String createPrepayOrder(WeChatPayCreateOrderModel weChatPayCreateOrderModel) {
		WeChatPayBizModel weChatPayBizModel = new WeChatPayBizModel();
		weChatPayBizModel.setAppid(weChatPayCreateOrderModel.getAppId());
		weChatPayBizModel.setMch_id(weChatPayCreateOrderModel.getMchId());
		weChatPayBizModel.setTrade_type(weChatPayCreateOrderModel.getTradeType());
		weChatPayBizModel.setNotify_url(weChatPayCreateOrderModel.getBaseUrl());
		weChatPayBizModel.setNonce_str(UUID.randomUUID().toString().toUpperCase().replace("-", ""));
		weChatPayBizModel.setSpbill_create_ip(weChatPayCreateOrderModel.getIp());
		weChatPayBizModel.setOpenid(weChatPayCreateOrderModel.getOpenId());
		weChatPayBizModel.setOut_trade_no(weChatPayCreateOrderModel.getOrderNo());
		weChatPayBizModel.setBody(weChatPayCreateOrderModel.getOrderName());
		weChatPayBizModel.setTotal_fee(weChatPayCreateOrderModel.getOrderPrice().multiply(new BigDecimal(100)).intValue());
		Map<String, String> map = MapConvert.beanToMapString(weChatPayBizModel);
		String content = AlipaySignature.getSignContent(map);
		weChatPayBizModel.setSign(MD5.toMD5(content + "&key=" + API_KEY).toUpperCase());
		String xml = XmlConvert.beanToXml(weChatPayBizModel, false);
		return OkhttpRequest.post(SERVER_URL, OkhttpRequest.TYPE_JSON, xml);
	}
	
	/**
	 * 创建订单签名
	 * @param weChatPayOrderSignModel
	 * @return
	 * @author LiQiang
	 * @date 2018年6月4日
	 */
	public static WeChatPayAppParamModel createOrderSign(WeChatPayOrderSignModel weChatPayOrderSignModel) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appId", weChatPayOrderSignModel.getAppId());
		map.put("nonceStr", UUID.randomUUID().toString().toUpperCase().replace("-", ""));
		String timeStamp = String.valueOf(System.currentTimeMillis()).toString().substring(0, 10);
		map.put("timeStamp", timeStamp);
		map.put("package", "prepay_id=" + weChatPayOrderSignModel.getPrepayId());
		map.put("signType", "MD5");
		String content = AlipaySignature.getSignContent(map);
		map.put("sign", MD5.toMD5(content + "&key=" + API_KEY).toUpperCase());
		// package属于java关键字，不能作为参数，生成签名的时候是用package，转对象参数packAge
		map.put("packAge", "prepay_id=" + weChatPayOrderSignModel.getPrepayId());
		return (WeChatPayAppParamModel) MapConvert.convertMap(WeChatPayAppParamModel.class, map);
	}

	/**
	 * 查询付款订单（暂未使用）
	 * 
	 * @author LiQiang
	 * @date 2016年12月30日
	 */
	public static void searchOrder() {
		WeChatPaySearchOrderModel weChatPaySearchOrderModel = new WeChatPaySearchOrderModel();
		weChatPaySearchOrderModel.setAppid(APP_ID);
		weChatPaySearchOrderModel.setMch_id(MCH_ID);
		weChatPaySearchOrderModel.setNonce_str(UUID.randomUUID().toString().toUpperCase().replace("-", ""));
		weChatPaySearchOrderModel.setOut_trade_no("113774026730366671");
		Map<String, String> map = MapConvert.beanToMapString(weChatPaySearchOrderModel);
		String content = AlipaySignature.getSignContent(map);
		weChatPaySearchOrderModel.setSign(MD5.toMD5(content + "&key=" + API_KEY).toUpperCase());
		String xml = XmlConvert.beanToXml(weChatPaySearchOrderModel, false);
		System.out.println(xml);
		String result = OkhttpRequest.post(SELECT_URL, OkhttpRequest.TYPE_XML, xml);
		System.out.println(result);
	}
	
	/**
	 * 退款
	 * 
	 * @author LiQiang
	 * @date 2016年12月30日
	 */
	public static String refund(WeChatPayRefundModel refundModel) {
		WeChatPayRefundModel weChatPayRefundModel = new WeChatPayRefundModel();
		weChatPayRefundModel.setTransaction_id(refundModel.getTransaction_id());
		weChatPayRefundModel.setTotal_fee(refundModel.getTotal_fee());
		weChatPayRefundModel.setRefund_fee(refundModel.getRefund_fee());
		weChatPayRefundModel.setAppid(refundModel.getAppid());
		weChatPayRefundModel.setMch_id(refundModel.getMch_id());
		weChatPayRefundModel.setSign_type("MD5");
		weChatPayRefundModel.setNonce_str(UUID.randomUUID().toString().toUpperCase().replace("-", ""));
		weChatPayRefundModel.setOut_refund_no(RecordNo.create("TP",RecordNo.YEAR_MONTH+RecordNo.DAY_HOUR));
		weChatPayRefundModel.setOp_user_id(refundModel.getMch_id());
		Map<String, String> map = MapConvert.beanToMapString(weChatPayRefundModel);
		String content = AlipaySignature.getSignContent(map);
		weChatPayRefundModel.setSign(MD5.toMD5(content + "&key=" + API_KEY).toUpperCase());
		String xml = XmlConvert.beanToXml(weChatPayRefundModel, false);
		String result = OkhttpRequest.postSSL(REFUND_URL, OkhttpRequest.TYPE_XML, xml,refundModel.getCre_path(), refundModel.getMch_id());
		return result;
	}
	
	/**
	 * 公众号发红包
	 * 
	 * @author LiQiang
	 * @date 2016年12月30日
	 */
	public static String wechatSendRedPacket(WeChatPayRedModel redModel) {
		WeChatPayRedModel weChatPayRedModel = new WeChatPayRedModel();
		weChatPayRedModel.setNonce_str(UUID.randomUUID().toString().toUpperCase().replace("-", ""));
		weChatPayRedModel.setMch_billno(redModel.getMch_billno());
		weChatPayRedModel.setMch_id(redModel.getMch_id());
		weChatPayRedModel.setWxappid(redModel.getWxappid());
		weChatPayRedModel.setSend_name(redModel.getSend_name());
		weChatPayRedModel.setRe_openid(redModel.getRe_openid());
		weChatPayRedModel.setTotal_amount(redModel.getTotal_amount());
		weChatPayRedModel.setTotal_num(redModel.getTotal_num());
		weChatPayRedModel.setWishing(redModel.getWishing());
		weChatPayRedModel.setAct_name(redModel.getAct_name());
		weChatPayRedModel.setRemark(redModel.getRemark());
		Map<String, String> map = MapConvert.beanToMapString(weChatPayRedModel);
		String content = AlipaySignature.getSignContent(map);
		weChatPayRedModel.setSign(MD5.toMD5(content + "&key=" + API_KEY).toUpperCase());
		String xml = XmlConvert.beanToXml(weChatPayRedModel, false);
		String result = OkhttpRequest.postSSL(WECHAT_RED_PARKET_URL, OkhttpRequest.TYPE_XML, xml,redModel.getCre_path(), redModel.getMch_id());
		return result;
	}
	
	/**
	 * 小程序发红包
	 * 
	 * @author LiQiang
	 * @date 2016年12月30日
	 */
	public static String miniAppSendRedPacket(WeChatPayRedModel redModel) {
		WeChatPayRedModel weChatPayRedModel = new WeChatPayRedModel();
		weChatPayRedModel.setNonce_str(UUID.randomUUID().toString().toUpperCase().replace("-", ""));
		weChatPayRedModel.setMch_billno(redModel.getMch_billno());
		weChatPayRedModel.setMch_id(redModel.getMch_id());
		weChatPayRedModel.setWxappid(redModel.getWxappid());
		weChatPayRedModel.setSend_name(redModel.getSend_name());
		weChatPayRedModel.setRe_openid(redModel.getRe_openid());
		weChatPayRedModel.setTotal_amount(redModel.getTotal_amount());
		weChatPayRedModel.setTotal_num(redModel.getTotal_num());
		weChatPayRedModel.setWishing(redModel.getWishing());
		weChatPayRedModel.setAct_name(redModel.getAct_name());
		weChatPayRedModel.setRemark(redModel.getRemark());
		weChatPayRedModel.setNotify_way("MINI_PROGRAM_JSAPI");
		Map<String, String> map = MapConvert.beanToMapString(weChatPayRedModel);
		String content = AlipaySignature.getSignContent(map);
		weChatPayRedModel.setSign(MD5.toMD5(content + "&key=" + API_KEY).toUpperCase());
		String xml = XmlConvert.beanToXml(weChatPayRedModel, false);
		String result = OkhttpRequest.postSSL(MINI_RED_PARKET_URL, OkhttpRequest.TYPE_XML, xml,redModel.getCre_path(), redModel.getMch_id());
		return result;
	}
	
	/**
	 * 创建红包签名
	 * @param weChatPayOrderSignModel
	 * @return
	 * @author LiQiang
	 * @date 2018年6月4日
	 */
	public static WeChatPayAppParamModel createRedSign(WeChatPayOrderSignModel weChatPayOrderSignModel) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appId", weChatPayOrderSignModel.getAppId());
		map.put("nonceStr", UUID.randomUUID().toString().toUpperCase().replace("-", ""));
		String timeStamp = String.valueOf(System.currentTimeMillis()).toString().substring(0, 10);
		map.put("timeStamp", timeStamp);
		String pack = "";
		try {
			pack = URLEncoder.encode(weChatPayOrderSignModel.getPrepayId(),"GBK");
			map.put("package",pack);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String content = AlipaySignature.getSignContent(map);
		map.put("sign", MD5.toMD5(content + "&key=" + API_KEY).toUpperCase());
		// package属于java关键字，不能作为参数，生成签名的时候是用package，转对象参数packAge
		map.put("packAge",pack);
		return (WeChatPayAppParamModel) MapConvert.convertMap(WeChatPayAppParamModel.class, map);
	}
	
	/**
	 * 验证返回参数签名
	 * 
	 * @param params
	 * @return
	 * @author LiQiang
	 * @date 2016年12月15日
	 */
	public static boolean checkSign(Map<String, String> params) {
		String sign = params.get("sign");
		params.remove("sign");
		String content = AlipaySignature.getSignContent(params);
		content = content + "&key=" + API_KEY;
		if (sign.equals(MD5.toMD5(content).toUpperCase())) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		WeChatPayCreateOrderModel weChatPayCreateOrderModel = new WeChatPayCreateOrderModel();
		weChatPayCreateOrderModel.setIp("192.168.0.32");
		weChatPayCreateOrderModel.setBaseUrl("www.iumer.vip" + WeChatPay.NOTIFY_URL);
		weChatPayCreateOrderModel.setAppId(WeChatPay.APP_ID);
		weChatPayCreateOrderModel.setMchId(WeChatPay.MCH_ID);
		weChatPayCreateOrderModel.setTradeType(WeChatPay.WECHAT_TRADE_TYPE);
		weChatPayCreateOrderModel.setOpenId("oshfn0eRdjhCjl3dlikgpW017kVQ");
		String paymentNo = RecordNo.create("HD", RecordNo.YEAR_MONTH + RecordNo.DAY_HOUR);
		weChatPayCreateOrderModel.setOrderNo(paymentNo);
		weChatPayCreateOrderModel.setOrderName("购买套餐");
		weChatPayCreateOrderModel.setOrderPrice(new BigDecimal(0.01));
		String result = WeChatPay.createPrepayOrder(weChatPayCreateOrderModel);
		System.out.println(result);
	}

}
