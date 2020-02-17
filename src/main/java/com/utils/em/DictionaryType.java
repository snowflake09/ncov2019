package com.utils.em;

import java.util.ArrayList;
import java.util.List;

import com.dto.base.BaseType;

public enum DictionaryType {
	
	RESOURCE_ICON("RESOURCE_ICON","资源图片"),
	COMMENT_LABEL("COMMENT_LABEL","评论标签"),
	URL_TYPE("URL_TYPE","外部链接类型");
	
    private String value;
    private String label;

    private DictionaryType(String value, String label) {
        this.value = value;
        this.label = label;
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

    public static List<BaseType> getDictTypeList() {
        List<BaseType> list = new ArrayList<>();
        list.add(new BaseType(DictionaryType.RESOURCE_ICON.getValue(), DictionaryType.RESOURCE_ICON.getLabel()));
        list.add(new BaseType(DictionaryType.COMMENT_LABEL.getValue(), DictionaryType.COMMENT_LABEL.getLabel()));
        list.add(new BaseType(DictionaryType.URL_TYPE.getValue(), DictionaryType.URL_TYPE.getLabel()));
        return list;
    }

	public static String getLabel(String value) {
		if (RESOURCE_ICON.getValue().equals(value)) {
			return RESOURCE_ICON.getLabel();
		}else if(COMMENT_LABEL.getValue().equals(value)){
			return COMMENT_LABEL.getLabel();
		}else if(URL_TYPE.getValue().equals(value)){
			return URL_TYPE.getLabel();
		}else {
			return "";
		}
	}
}
