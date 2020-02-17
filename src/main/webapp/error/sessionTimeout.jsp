<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" href="${ctx }/image/icon/logo.ico" type="image/x-icon" />
<link rel="shortcut icon" href="${ctx }/image/icon/logo.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<script>
	function timedMsg() {
		parent.location.assign('${ctx}/login');
	}
	setTimeout(timedMsg,10)
</script>
</body>
</html>