package com.utils;

/**
 * 
 * @date 2018年5月30日
 *
 * @author LiQiang
 *
 * @description 全局常量配置
 * 
 *
 */
public final class CommonContants {

	/**
	 * 佣金比例
	 */
	public static final double BROKERAGE = 0.1;
	/**
	 * 原图
	 */
	public static final String PIC_NAME_SAVE = "original";
	/**
	 * 压缩过的图
	 */
	public static final String PIC_NAME_BIG = "big.jpg";
	/**
	 * X-Token 参数命名
	 */
	public static final String DEFAULT_TOKEN_NAME = "userToken";
	/**
	 * X-Type 参数命名
	 */
	public static final String DEFAULT_TYPE_NAME = "userType";
	/**
	 * session 参数命名
	 */
	public static final String SESSION_INFO = "sessionInfo";

	/**
	 * 进账
	 */
	public static final int PAYMENT_OPERATION_INCOME = 0;
	/**
	 * 出账
	 */
	public static final int PAYMENT_OPERATION_PUT = 1;
}
