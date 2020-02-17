package com.utils.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.utils.DateUtil;

/**
 * 支付宝
 * 
 * @author LiQiang
 * @date 2016年12月12日
 */
public class AliPay {


	//
	public static final String APP_ID = "";
	private static final String ALIPAY_PUBLIC_KEY = "";
	private static final String APP_PRIVATE_KEY = "";
	private static final String APP_PUBLIC_KEY = "";
	private static final String CHARSET = "UTF-8";
	private static final String FORMAT = "json";
	private static final String SERVER_URL = "https://openapi.alipay.com/gateway.do";

	private static final AlipayClient alipayClient = new DefaultAlipayClient(SERVER_URL, APP_ID, APP_PRIVATE_KEY,
			FORMAT, CHARSET, ALIPAY_PUBLIC_KEY,"RSA2");

	public static final String PROJECT_NOTIFY_URL = "";

	/**
	 * 交易创建，等待买家付款
	 */
	public static final String WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
	/**
	 * 未付款交易超时关闭，或支付完成后全额退款
	 */
	public static final String TRADE_CLOSED = "TRADE_CLOSED";
	/**
	 * 交易支付成功
	 */
	public static final String TRADE_SUCCESS = "TRADE_SUCCESS";
	/**
	 * 交易结束，不可退款
	 */
	public static final String TRADE_FINISHED = "TRADE_FINISHED";

	/**
	 * 移动端网站支付
	 * 
	 * @param baseUrl
	 * @return
	 * @author LiQiang
	 * @date 2016年12月13日
	 */
	public static String PhoneWeb(String notifyUrl,String returnUrl,AliPayBizModel bizModel) {
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();// 创建API对应的request
		alipayRequest.setNotifyUrl(notifyUrl);
		alipayRequest.setReturnUrl(returnUrl);
		// bizModel.setSeller_id(SELLER_ID);
		alipayRequest.setBizContent(JSON.toJSONString(bizModel));
		String form = "";
		try {
			form = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return form;
	}
	
	/**
	 * 授权用户信息
	 * @param code
	 * @return
	 * @author LiQiang
	 * @date 2019年11月26日
	 */
	public static AliPayOauthReturnModel getOauth(String code){
		AlipaySystemOauthTokenRequest requests = new AlipaySystemOauthTokenRequest();
		requests.setCode(code);
        requests.setGrantType("authorization_code");
        AliPayOauthReturnModel aliPayOauthReturnModel = new AliPayOauthReturnModel();
		try {
			AlipaySystemOauthTokenResponse response = alipayClient.execute(requests);
			String form = response.getBody();
			JSONObject json = new JSONObject();
			json = JSONObject.parseObject(form);
			aliPayOauthReturnModel = JSONObject.parseObject(json.getString("alipay_system_oauth_token_response"), AliPayOauthReturnModel.class);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return aliPayOauthReturnModel;
	}

	/**
	 * 获取信息签名
	 * 
	 * @return
	 * @author LiQiang
	 * @date 2016年12月14日
	 */
	public static String getSign(String notifyUrl, AliPayBizModel bizModel) {
		Map<String, String> paramsMap = new HashMap<>();
		try {
			paramsMap.put("app_id", APP_ID);
			paramsMap.put("method", "alipay.trade.app.pay");
			paramsMap.put("format", FORMAT);
			paramsMap.put("charset", CHARSET);
			paramsMap.put("sign_type", "RSA");
			paramsMap.put("timestamp", DateUtil.dateToString(new Date(), DateUtil.FORMAT_DATE_TIME));
			paramsMap.put("version", "1.0");
			paramsMap.put("notify_url",notifyUrl);
			// bizModel.setSeller_id(SELLER_ID);
			bizModel.setProduct_code("QUICK_MSECURITY_PAY");
			paramsMap.put("biz_content", JSON.toJSONString(bizModel));
			
			String signContent = buildOrderParam(paramsMap);
			signContent = signContent + "&"
					+ buildKeyValue("sign", AlipaySignature.rsaSign(paramsMap, APP_PRIVATE_KEY, CHARSET), true);
			return signContent;
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 退款
	 * 
	 * @param bizModel
	 * @return
	 * @author LiQiang
	 * @date 2017年8月10日
	 */
	public static AliPayRefundReturnModel aliPayRefund(AliPayRefundBizModel bizModel) {
		AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
		request.setBizContent(JSON.toJSONString(bizModel));
		AliPayRefundReturnModel aliPayReturnModel = new AliPayRefundReturnModel();
		try {
			String form = alipayClient.execute(request).getBody();
			JSONObject json = new JSONObject();
			json = JSONObject.parseObject(form);
			aliPayReturnModel = JSONObject.parseObject(json.getString("alipay_trade_refund_response"), AliPayRefundReturnModel.class);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return aliPayReturnModel;
	}

	public static String buildOrderParam(Map<String, String> map) {
		List<String> keys = new ArrayList<String>(map.keySet());
		Collections.sort(keys);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = map.get(key);
			sb.append(buildKeyValue(key, value, true));
			sb.append("&");
		}

		String tailKey = keys.get(keys.size() - 1);
		String tailValue = map.get(tailKey);
		sb.append(buildKeyValue(tailKey, tailValue, true));

		return sb.toString();
	}

	/**
	 * 拼接键值对
	 * 
	 * @param key
	 * @param value
	 * @param isEncode
	 * @return
	 */
	private static String buildKeyValue(String key, String value, boolean isEncode) {
		StringBuilder sb = new StringBuilder();
		sb.append(key);
		sb.append("=");
		if (isEncode) {
			try {
				sb.append(URLEncoder.encode(value, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				sb.append(value);
			}
		} else {
			sb.append(value);
		}
		return sb.toString();
	}

	/**
	 * 验证异步返回签名
	 * 
	 * @param paramsMap
	 * @return
	 * @author LiQiang
	 * @date 2016年12月13日
	 */
	public static boolean rsaCheck(Map<String, String> paramsMap) {
		try {
			return AlipaySignature.rsaCheckV1(paramsMap,ALIPAY_PUBLIC_KEY, CHARSET,"RSA2");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) throws AlipayApiException {
	}

}
