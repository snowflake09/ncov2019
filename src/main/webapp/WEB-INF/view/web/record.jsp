<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>社区疫情防控系统</title>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport" />
<link rel="icon" href="${ctx }/image/icon/logo.ico" type="image/x-icon" />
<link rel="shortcut icon" href="${ctx }/image/icon/logo.ico" />
<!-- CSS -->
<link rel="stylesheet" href="${ctx }/js/ncov/style.css" />
<!-- Javascript -->
<script src="${ctx }/js/jquery/jquery.min.js"></script>
<script src="${ctx }/js/common/MD5_UTF8.js"></script>
<script src="${ctx }/js/common/layer/layer.js"></script>
<script>
	//如果登录窗口不在主窗口，在跳转到主窗口
	if (window != top){
		top.location.href = "login";
	}
	jQuery(document)
			.ready(
					function() {
						//回车事件绑定
						document.onkeydown = function(event) {
							var e = event || window.event
									|| arguments.callee.caller.arguments[0];
							if (e && e.keyCode == 13) {
								$('#buttonno').click();
							}
						};

						//登录操作
						$('#buttonno')
								.click(
										function() {
											layer.msg('请先登记外来人员！')
										});
					});
</script>
<style>
canvas {
	position: fixed;
	top: 0px;
	left: 0px;
}
</style>
</head>

<body id="body">
	<div class="page-container">
		<h1>社区疫情防控系统</h1>
		<div class="cardin">
			<h2>${name}出入证</h2>
			<div class="txtleft"><span>姓名：<b>张三</b></span><span style="margin-left: 15px">住址：<b>B1-1-1101</b><span</div>
			<div class="txtleft">身份证号：<b>513401111111111111 </b> </div>
			<div class="txtleft">办证日期：<b>2020-02-02</b></div>
		</div>
	</div>
		<form id="_form" action="" method="post">
			<div style="text-align: left; margin-left: 10px;"></div>
<%--

			<a type="button" href="inadd?openid=oHtMx5pqAYxEP4md9pV1MPsilyiQ&regionid=1" style="width: 100%">返京人员登记</a>
			<a type="button" href="add?openid=oHtMx5pqAYxEP4md9pV1MPsilyiQ&regionid=1" style="width: 100%">日常出入登记</a>
			<a type="button" href="outadd?openid=oHtMx5pqAYxEP4md9pV1MPsilyiQ&regionid=1" style="width: 100%">来访人员登记</a>
			<a type="button" href="healthadd?openid=oHtMx5pqAYxEP4md9pV1MPsilyiQ&regionid=1" style="width: 100%">人员健康汇报</a>

--%>

			<a type="button" href="inadd?openid=${openid}&regionid=${regionid}" style="width: 100%">返京人员登记</a>
			<a type="button" href="add?openid=${openid}&regionid=${regionid}" style="width: 100%">日常出入登记</a>
			<a type="button" href="outadd?openid=${openid}&regionid=${regionid}" style="width: 100%">访客信息登记</a>
			<c:choose>
				<c:when test="${count=='0'}">
					<a type="buttonno"  id ="buttonno" href="#" style="width: 100%">人员健康汇报</a>
				</c:when>
				<c:otherwise>
					<a type="button" href="healthadd?openid=${openid}&regionid=${regionid}" style="width: 100%">人员健康汇报</a>
				</c:otherwise>
			</c:choose>

		</form>
	</div>
	</div>
</body>

</html>