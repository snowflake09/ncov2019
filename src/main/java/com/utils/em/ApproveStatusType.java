package com.utils.em;

import java.util.ArrayList;
import java.util.List;

import com.dto.base.BaseType;

public enum ApproveStatusType {
	
	DISABLE(0,"禁用"),
	ENABLE(1,"启用"),
	AUDIT(2,"审核中"),
	ING(3,"运行中");
	
	private int value;
    private String label;

    private ApproveStatusType(int value, String label) {
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
        list.add(new BaseType(ApproveStatusType.DISABLE.getValue()+"", ApproveStatusType.DISABLE.getLabel()));
        list.add(new BaseType(ApproveStatusType.ENABLE.getValue()+"", ApproveStatusType.ENABLE.getLabel()));
        list.add(new BaseType(ApproveStatusType.AUDIT.getValue()+"", ApproveStatusType.AUDIT.getLabel()));
        return list;
    }

	public static String getLabel(int value) {
		if (DISABLE.getValue()==value) {
			return DISABLE.getLabel();
		}else if(ENABLE.getValue()==value){
			return ENABLE.getLabel();
		}else if(AUDIT.getValue()==value){
			return AUDIT.getLabel();
		}else {
			return "";
		}
	}
}
