package com.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * http工具类
 */
public class HttpsRequestUtil {

	/**
	 * https get请求
	 * 
	 * @param urlStr
	 * @return
	 * @author LiQiang
	 * @date 2016年12月29日
	 */
	public static String sendGet(String urlStr) {
		HttpsURLConnection httpsUrlConn = null;
		StringBuffer buffer = new StringBuffer();
		try {
			httpsUrlConn = (HttpsURLConnection) (new URL(urlStr)).openConnection();
			httpsUrlConn.setDoOutput(true);
			httpsUrlConn.setDoInput(true);
			httpsUrlConn.setUseCaches(false);
			httpsUrlConn.setRequestMethod("GET");
			httpsUrlConn.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(httpsUrlConn.getInputStream(),"UTF-8"));
			String line = null;
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
			in.close();
			httpsUrlConn.disconnect();
		} catch (Exception e) {
			e.getStackTrace();
		}
		return buffer.toString();
	}

	public static String sendPost(String url, String param) {
		HttpsURLConnection httpsUrlConn = null;
		StringBuffer buffer = new StringBuffer();
		try {
			httpsUrlConn = (HttpsURLConnection) (new URL(url)).openConnection();
			httpsUrlConn.setDoInput(true);
			httpsUrlConn.setDoOutput(true);
			httpsUrlConn.setRequestMethod("POST");
			httpsUrlConn.setRequestProperty("Accept", "application/json");
			httpsUrlConn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			httpsUrlConn.setRequestProperty("Content-Length", String.valueOf(256));
			httpsUrlConn.setUseCaches(false);
			httpsUrlConn.getOutputStream().write(param.getBytes());
			httpsUrlConn.getOutputStream().flush();
			httpsUrlConn.getOutputStream().close();
			BufferedReader in = new BufferedReader(new InputStreamReader(httpsUrlConn.getInputStream()));
			String line = null;
			while ((line = in.readLine()) != null) {
				buffer.append(line);
			}
			in.close();
			httpsUrlConn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	public static void main(String[] args) {
	}
}
