package com.utils.easyui;

public class Json implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8722126593920041253L;

	private boolean success = false;

	private String msg = "";

	private Object obj = null;
	
	private String text;
	
	private String value;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
	
}
