package com.wechat;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.utils.CacheUtils;
import com.utils.HttpsRequestUtil;
import com.utils.OkhttpRequest;

public class MiniAppsUtils {
	
	private static final String GRANT_TYPE = "client_credential";
	private static final String BASE_URL = "https://api.weixin.qq.com/cgi-bin";
	private static final String GET_OPENID = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
	private static final String GET_SHARE_QR = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=%s";
	public static final String CACHE_YMP_TOKEN = "ymp_miniapp_access_token";
	
	/**
     * 获取小程序token
     * @return
     * @author LiQiang
     * @date 2017年3月21日
     */
	public static JSONObject getAccessToken(String appid,String secret) {
		String urlStr = String.format("%s/token?grant_type=%s&appid=%s&secret=%s", BASE_URL, GRANT_TYPE, appid, secret);
		return JSONObject.parseObject(HttpsRequestUtil.sendGet(urlStr));
	}
	
	/**
	 * 获取小程序OPENID
	 * @param code
	 * @return
	 * @author LiQiang
	 * @date 2017年5月3日
	 */
	public static JSONObject getOpenId(String appid,String secret,String code) {
		String urlStr = String.format(GET_OPENID,appid,secret,code);
		return JSONObject.parseObject(HttpsRequestUtil.sendGet(urlStr));
	}

	/**
	 * 获取小程序分享二维码
	 * @param path
	 * @param width
	 * @return
	 * @author LiQiang
	 * @throws IOException 
	 * @date 2017年5月3日
	 */
	public static byte[] getShareQR(String path, Integer width){
		String urlStr = String.format(GET_SHARE_QR,CacheUtils.get(CacheUtils.MINIAPP_TOKEN_CACHE,CACHE_YMP_TOKEN));
		JSONObject json = new JSONObject();
		json.put("path", path);
		json.put("width", width);
		return OkhttpRequest.postBytes(urlStr, OkhttpRequest.TYPE_JSON, json.toJSONString());
	}

}
