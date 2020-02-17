package com.utils.em;

import java.util.ArrayList;
import java.util.List;

import com.dto.base.BaseType;

public enum PaymentType {

	/**
	 * 支付宝
	 */
	ALIPAY("alipay","支付宝","/ymkj/css/icon/alipay.png"),
	/**
	 * 微信
	 */
	WECHAT("wechat","微信","/ymkj/css/icon/wechat.png"),
	/**
	 * 银行卡
	 */
	CARD("card","银行卡","/ymkj/css/icon/card.png");
	
	private String value;
    private String label;
    private String icon;
    
    private PaymentType(String value, String label,String icon) {
		this.value = value;
		this.label = label;
		this.icon = icon;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}

	public static List<BaseType> getPaymentTypeList() {
        List<BaseType> list = new ArrayList<>();
        list.add(new BaseType(PaymentType.ALIPAY.getValue(),PaymentType.ALIPAY.getLabel(),PaymentType.ALIPAY.getIcon()));
        list.add(new BaseType(PaymentType.WECHAT.getValue(),PaymentType.WECHAT.getLabel(),PaymentType.WECHAT.getIcon()));
        list.add(new BaseType(PaymentType.CARD.getValue(),PaymentType.CARD.getLabel(),PaymentType.CARD.getIcon()));
        return list;
    }

	public static String getLabel(String value) {
		if (ALIPAY.getValue().equals(value)) {
			return ALIPAY.getLabel();
		}else if(WECHAT.getValue().equals(value)){
			return WECHAT.getLabel();
		}else if(CARD.getValue().equals(value)){
			return CARD.getLabel();
		}else{
			return "";
		}
	}
	
	public static String getIcon(String value) {
		if (ALIPAY.getValue().equals(value)) {
			return ALIPAY.getIcon();
		}else if(WECHAT.getValue().equals(value)){
			return WECHAT.getIcon();
		}else if(CARD.getValue().equals(value)){
			return CARD.getIcon();
		}else{
			return "";
		}
	}
	
}
