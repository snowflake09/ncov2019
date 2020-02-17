package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.conn.ssl.SSLContexts;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class OkhttpRequest {

	public static final String AK = "R9g2Gh5xxbxupk7z2t7QFyhL0leLBQo6";
	public static final String GET_DIS_URL = "http://api.map.baidu.com/routematrix/v2/driving?output=json&origins=ORIGINS&destinations=DESTINATIONS&ak="
			+ AK;

	public static final MediaType TYPE_JSON = MediaType.parse("application/json;charset=utf-8");
	public static final MediaType TYPE_XML = MediaType.parse("text/xml;charset=utf-8");

	/**
	 * 批量获取距离
	 * 
	 * @param origins
	 * @param destinations
	 * @return
	 * @author LiQiang
	 * @date 2016年12月1日
	 */
	public static List<String> batchGetDis(String origins, String destinations) {
		String url = GET_DIS_URL.replace("ORIGINS", origins).replace("DESTINATIONS", destinations);
		String result = get(url);
		List<String> list = new ArrayList<>();
		if (!StringUtils.isEmpty(result)) {
			JSONObject json = JSON.parseObject(result);
			if (json.getInteger("status") == 0) {
				JSONArray arr = json.getJSONArray("result");
				for (int i = 0; i < arr.size(); i++) {
					JSONObject j = arr.getJSONObject(i);
					JSONObject distance = (JSONObject) j.get("distance");
					list.add(distance.getString("text"));
				}
			}
		}
		return list;
	}

	/**
	 * 同步get请求
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 * @author LiQiang
	 * @date 2016年12月1日
	 */
	public static String get(String url) {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).get().build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				return response.body().string();
			} else {
				throw new IOException("服务器端错误:" + response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 同步post请求
	 * 
	 * @param url
	 * @param json
	 * @return
	 * @throws IOException
	 * @author LiQiang
	 * @date 2016年12月1日
	 */
	public static String post(String url, MediaType type, String json) {
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(type, json);
		Request request = new Request.Builder().url(url).post(body).build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				return response.body().string();
			} else {
				throw new IOException("服务器端错误:" + response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 同步post请求
	 * 
	 * @param url
	 * @param json
	 * @return
	 * @throws IOException
	 * @author LiQiang
	 * @date 2016年12月1日
	 */
	public static byte[] postBytes(String url, MediaType type, String json) {
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(type, json);
		Request request = new Request.Builder().url(url).post(body).build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				return response.body().bytes();
			} else {
				throw new IOException("服务器端错误:" + response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 同步post请求
	 * @param url
	 * @param type
	 * @param json
	 * @param headers
	 * @return
	 * @author LiQiang
	 * @date 2017年3月29日
	 */
	public static String postHeaders(String url, MediaType type, String json,Headers headers) {
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(type, json);
		Request request = new Request.Builder().url(url).post(body).headers(headers).build();
		try {
			Response response = client.newCall(request).execute();
			if (response.isSuccessful()) {
				return response.body().string();
			} else {
				throw new IOException("服务器端错误:" + response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 同步post请求
	 * 
	 * @param url
	 * @param json
	 * @return
	 * @throws IOException
	 * @author LiQiang
	 * @date 2016年12月1日
	 */
	public static String postSSL(String url, MediaType type, String json, String filePath, String password) {
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(type, json);
		Request request = new Request.Builder().url(url).post(body).build();
		try {
			File file = new File(filePath);
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			FileInputStream instream = new FileInputStream(file);
			keyStore.load(instream, password.toCharArray());
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, password.toCharArray()).build();
			Response response = client.setSslSocketFactory(sslcontext.getSocketFactory()).newCall(request).execute();
			if (response.isSuccessful()) {
				return response.body().string();
			} else {
				throw new IOException("服务器端错误:" + response);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 异步get请求
	 * 
	 * @param url
	 * @author LiQiang
	 * @date 2016年12月1日
	 */
	public static void asyGet(String url) {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).get().build();
		client.newCall(request).enqueue(new Callback() {
			@Override
			public void onResponse(Response response) throws IOException {
				if (response.isSuccessful()) {
					response.body().string();
				} else {
					throw new IOException("服务器端错误: " + response);
				}
			}

			@Override
			public void onFailure(Request request, IOException e) {
				e.printStackTrace();
			}
		});
	}

	/**
	 * 获取url返回的所有参数
	 * 
	 * @param request
	 * @return
	 * @author LiQiang
	 * @date 2016年12月14日
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> getParameter(HttpServletRequest request) {
		Map<String, String> params = new HashMap<String, String>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}
		return params;
	}

	public static void main(String[] args) throws IOException {

	}

}
