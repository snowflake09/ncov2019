package com.converter;

/**
 * 数据转换处理
 * @param <T>
 * @author LiQiang
 * @date 2017年3月2日
 */
public interface MessageConverterHandler<T> {
	
	/**
	 * 签名验证
	 * @param body
	 * @author LiQiang
	 * @date 2017年3月25日
	 */
	public void readAfter(Object obj);
}
