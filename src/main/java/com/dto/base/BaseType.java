package com.dto.base;

public class BaseType {
	
	private String value;
	
	private String label;
	
	private String icon;
	
	public BaseType() {
		super();
	}

	public BaseType(String value, String label) {
		super();
		this.value = value;
		this.label = label;
	}
	
	public BaseType(String value, String label,String icon) {
		super();
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
	
}
