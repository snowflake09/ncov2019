package com.wechat;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.utils.CodecUtil;
import com.utils.HttpsRequestUtil;
import com.utils.wechatpay.WeChatPay;

public class WechatUtils {
	
	private static final String GRANT_TYPE = "client_credential";
	private static final String BASE_URL = "https://api.weixin.qq.com/cgi-bin";
	private static final String GET_OPENID = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
	
    public static boolean checkSignature(String signature, String token, String nonce, String timestamp) {
    	String[] paramArr =  {token, nonce, timestamp};
    	Arrays.sort(paramArr);
    	String paramStr = StringUtils.join(paramArr);
    	return signature.equals(CodecUtil.encryptSHA(paramStr));
    }
	
    /**
     * 获取公众号token
     * @return
     * @author LiQiang
     * @date 2017年3月21日
     */
	public static JSONObject getAccessToken() {
		String urlStr = String.format("%s/token?grant_type=%s&appid=%s&secret=%s", BASE_URL, GRANT_TYPE, WeChatPay.APP_ID, WeChatPay.APP_SECRET);
		return JSONObject.parseObject(HttpsRequestUtil.sendGet(urlStr));
	}

	/**
	 * 获取jsApi
	 * @param accessToken
	 * @return
	 * @author LiQiang
	 * @date 2017年3月21日
	 */
	public static JSONObject getJsApiTicket(String accessToken) {
		String urlStr = String.format("%s/ticket/getticket?access_token=%s&type=jsapi", BASE_URL, accessToken);
		return JSONObject.parseObject(HttpsRequestUtil.sendGet(urlStr));
	}
	
	/**
	 * 获取关注用户信息
	 * @param accessToken
	 * @param openId
	 * @return
	 * @author LiQiang
	 * @date 2017年3月21日
	 */
	public static JSONObject getUserInfo(String accessToken,String openId) {
		String urlStr = String.format("%s/user/info?access_token=%s&openid=%s&lang=zh_CN", BASE_URL, accessToken,openId);
		return JSONObject.parseObject(HttpsRequestUtil.sendGet(urlStr));
	}

	/**
	 * 获取关注用户openid
	 * @param code
	 * @return
	 * @author LiQiang
	 * @date 2017年3月21日
	 */
	public static JSONObject getOpenId(String code) {
		String urlStr = String.format(GET_OPENID,WeChatPay.APP_ID,WeChatPay.APP_SECRET,code);
		return JSONObject.parseObject(HttpsRequestUtil.sendGet(urlStr));
	}
	
	public static String getSignature(String jsapiTicket, String nonceStr, String timestamp, String url) {
		String string1 = String.format("jsapi_ticket=%s&noncestr=%s&timestamp=%s&url=%s", jsapiTicket, nonceStr, timestamp, url);
		return CodecUtil.encryptSHA(string1);
	}
	
	public static void main(String[] args) {
		System.out.println(getAccessToken());
	}
}
