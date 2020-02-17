package com.utils;

import java.security.MessageDigest;
import java.util.Map;
import java.util.UUID;

import com.alipay.api.internal.util.AlipaySignature;

public class MD5 {
	
	public static final String MD5_KEY = "A3C49D4D35194EBFB6632D10C58C0A19";
	
	public static String toMD5(String plainText) {
		try {
			// 生成实现指定摘要算法的 MessageDigest 对象。
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 使用指定的字节数组更新摘要。
			md.update(plainText.getBytes("utf-8"));
			// 通过执行诸如填充之类的最终操作完成哈希计算。
			byte b[] = md.digest();
			// 生成具体的md5密码到buf数组
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			//System.out.println("32位: " + buf.toString());// 32位的加密
			//System.out.println("16位: " + buf.toString().substring(8, 24));// 16位的加密，其实就是32位加密后的截取
			return buf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return plainText;

	}
	
	/**
	 * 加密验证
	 * @param params
	 * @return
	 * @author LiQiang
	 * @date 2017年3月25日
	 */
	public static boolean checkSign(Map<String, String> params) {
		String sign = params.get("sign");
		params.remove("sign");
		String content = AlipaySignature.getSignContent(params);
		content = content + "&key=" + MD5_KEY;
		if (MD5.toMD5(content).toUpperCase().equals(sign)) {
			return true;
		}
		return false;
	}

	
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().toUpperCase().replace("-", ""));
	}
}
