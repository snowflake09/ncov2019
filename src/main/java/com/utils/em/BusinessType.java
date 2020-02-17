package com.utils.em;

import java.util.ArrayList;
import java.util.List;

import com.dto.base.BaseType;

public enum BusinessType {

	ONE_LEVEL(1,"合伙人"),
	TWO_LEVEL(2,"服务商"),
	SHOP_LEVEL(3,"店家");
	
	private int value;
    private String label;

    private BusinessType(int value, String label) {
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
        list.add(new BaseType(BusinessType.ONE_LEVEL.getValue()+"", BusinessType.ONE_LEVEL.getLabel()));
        list.add(new BaseType(BusinessType.TWO_LEVEL.getValue()+"", BusinessType.TWO_LEVEL.getLabel()));
        return list;
    }

	public static String getLabel(int value) {
		if (ONE_LEVEL.getValue()==value) {
			return ONE_LEVEL.getLabel();
		}else if(TWO_LEVEL.getValue()==value){
			return TWO_LEVEL.getLabel();
		}else {
			return "";
		}
	}
	
}
