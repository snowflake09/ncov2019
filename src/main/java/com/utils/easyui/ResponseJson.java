package com.utils.easyui;

public class ResponseJson {

	private static final int SUCCESS = 200;

	private int code;

	private String desc;

	private Object data;

	public ResponseJson() {
		super();
	}

	public ResponseJson(int code, String desc) {
		super();
		this.code = code;
		this.desc = desc;
	}

	public ResponseJson success(Object data, String message) {
		this.desc = message;
		this.code = SUCCESS;
		this.data = data;
		return this;
	}

	public ResponseJson failure(int code, String message) {
		this.desc = message;
		this.code = code;
		return this;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
