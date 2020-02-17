package com.utils.em;

/**
 * 接口状态码
 * 
 * @author LiQiang
 * @date 2017年1月5日
 */
public enum CodeType {
	
	ILLEGAL_VISIT(-1000,"不合法的访问"),
	NO_AUTH(-1001,"没有访问权限"),
	TOKEN_INVALID(302,"登录信息已过期"),
	AUTHCODE_FAIL(-1002,"验证码错误"),
	PAY_SUBMIT_FAIL(-1003,"付款请求失败"),
	NO_OPENID(-1004,"请使用微信登录"),
	REGISTER_FAIL(-1005,"手机号码已被注册"),
	NO_BUSINESS(-1006,"未注册的邀请人"),
	NO_PASSWORD(-1007,"用户名或密码错误"),
	DISABLE_USER(-1008,"审核中,请等待客服审核"),
	NO_USER(-1009,"用户信息不存在"),
	EXIST_CARD(-1010,"已添加过该银行卡"),
	NO_PRICE(-1011,"余额不足"),
	NO_DEVICE(-1012,"设备信息不存在"),
	STOP_DEIVCE_SET(-1013,"套餐已下架"),
	DEVICE_INFO_ERROR(-1014,"连接设备出错"),
	DEVICE_NO_ONLINE(-1015,"设备不在线"),
	NO_NUM(-1016,"可用次数不足"),
	DEVICE_ING(-1017,"设备运行中"),
	START_DEVICE_ERROR(-1018,"设备启动失败"),
	FAIL(0,"操作失败"),
	SUCCESS(200,"操作成功");

	private int value;
    private String label;

    private CodeType(int value, String label) {
        this.value = value;
        this.label = label;
    }
   
    public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
	
}
