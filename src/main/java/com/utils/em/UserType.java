package com.utils.em;

import java.util.ArrayList;
import java.util.List;

import com.dto.base.BaseType;

public enum UserType {
	
	BUSINESS(1,"代理"),
	SHOP(2,"门店"),
	CUSTOMER(3,"顾客");
	
	private int value;
    private String label;

    private UserType(int value, String label) {
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
    
    public static List<BaseType> getTypeList() {
        List<BaseType> list = new ArrayList<>();
        list.add(new BaseType(UserType.BUSINESS.getValue()+"", UserType.BUSINESS.getLabel()));
        list.add(new BaseType(UserType.SHOP.getValue()+"", UserType.SHOP.getLabel()));
        list.add(new BaseType(UserType.CUSTOMER.getValue()+"", UserType.CUSTOMER.getLabel()));
        return list;
    }

	public static String getLabel(int value) {
		if (BUSINESS.getValue()==value) {
			return BUSINESS.getLabel();
		}else if(SHOP.getValue()==value){
			return SHOP.getLabel();
		}else if(CUSTOMER.getValue()==value){
			return CUSTOMER.getLabel();
		}else {
			return "";
		}
	}
}
