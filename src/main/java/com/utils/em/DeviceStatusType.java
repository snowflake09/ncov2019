package com.utils.em;

import java.util.ArrayList;
import java.util.List;

import com.dto.base.BaseType;

public enum DeviceStatusType {

	DISABLE(0,"离线"),
	ENABLE(1,"在线"),
	ING(2,"运行中");
	
	private int value;
    private String label;

    private DeviceStatusType(int value, String label) {
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
        list.add(new BaseType(DeviceStatusType.DISABLE.getValue()+"", DeviceStatusType.DISABLE.getLabel()));
        list.add(new BaseType(DeviceStatusType.ENABLE.getValue()+"", DeviceStatusType.ENABLE.getLabel()));
        list.add(new BaseType(DeviceStatusType.ING.getValue()+"", DeviceStatusType.ING.getLabel()));
        return list;
    }

	public static String getLabel(int value) {
		if (DISABLE.getValue()==value) {
			return DISABLE.getLabel();
		}else if(ENABLE.getValue()==value){
			return ENABLE.getLabel();
		}else if(ING.getValue()==value){
			return ING.getLabel();
		}else {
			return "";
		}
	}
	
}
