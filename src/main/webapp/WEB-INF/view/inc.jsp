<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css"
	href="${ctx}/js/jquery/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${ctx}/js/jquery/themes/icon.css">
	<link rel="stylesheet" type="text/css"
		  href="${ctx}/js/ncov/index.css">
<script type="text/javascript" src="${ctx}/js/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${ctx}/js/jquery/jquery.easyui.extend.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
	<script type="text/javascript" src="${ctx}/js/echarts/echarts.min.js" charset="utf-8"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
a,a:hover{ text-decoration:none;}
/** 查询页面样式 **/
.divContent0 {
	width: 100%;
	margin-top: 10px;
	margin-bottom: 10px;
	overflow: hidden;
	background-color: #fff;
	/* 	text-align: center; */
}

.tabContent0 {
	width: 90%;
	height: 100%;
	text-align: center;
}

/**  **/
.divContent1 {
	width: 95%;
	margin-right: auto;
	margin-left: auto;
}

.divContent2 {
	overflow-y: auto;
	overflow-x: hidden;
	padding: 3px;
}

/**标题样式 **/
.tdTitle {
	text-align: right;
	font-size: 14px;
	color: #000000;
	width: 15%;
	text-shadow: 1px 1px 1px #FFF;
	
}
/** 内容样式 **/
.tdContent1 {
	text-align: left;
	font-size: 14px;
	color: #000000;
	width: 85%;
}

.tdContent2 {
	text-align: left;
	font-size: 14px;
	color: #000000;
	width: 35%;
}

.divBtn {
	text-align: center;
	margin-top: 20px
}

.input1 {
	width: 150px;
	height: 25px;
}
</style>
</head>
</html>