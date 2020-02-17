package com.utils.wechatpay;

/**
 * 接收通知返回给微信的实体
 * 
 * @author LiQiang
 * @date 2016年12月27日
 */
public class WeChatPayNotifyReturnModel {

	/**
	 * 返回状态码
	 */
	private String return_code;
	/**
	 * 返回信息
	 */
	private String return_msg;
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	
}
