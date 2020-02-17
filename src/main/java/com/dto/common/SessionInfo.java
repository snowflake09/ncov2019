package com.dto.common;

import java.util.List;

public class SessionInfo implements java.io.Serializable {

	private static final long serialVersionUID = 868479274763641841L;

	private Long id;// 用户ID
	private String loginName;// 登录名
	private String username;// 姓名
	private List<String> resourceList;// 用户可以访问的资源地址列表
	
	public List<String> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<String> resourceList) {
		this.resourceList = resourceList;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

}
