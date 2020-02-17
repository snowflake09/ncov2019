<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登录</title>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport" />
<link rel="icon" href="${ctx }/image/icon/logo.ico" type="image/x-icon" />
<link rel="shortcut icon" href="${ctx }/image/icon/logo.ico" />
<!-- CSS -->
<link rel="stylesheet" href="${ctx }/js/login/style.css" />
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
								$('#login').click();
							}
						};

						//登录操作
						$('#login')
								.click(
										function() {

											var username = $('.username').val();
											var password = $('.password').val();
											if (username == '') {
												$('.error').fadeOut(
														'fast',
														function() {
															$('.error').css(
																	'top',
																	'27px')
																	.show();
														});
												$('.error').fadeIn(
														'fast',
														function() {
															$('.username')
																	.focus();
														});
												return false;
											}
											if (password == '') {
												$('.error').fadeOut(
														'fast',
														function() {
															$('.error').css(
																	'top',
																	'96px')
																	.show();
														});
												$(this).find('.error').fadeIn(
														'fast',
														function() {
															$('.password')
																	.focus();
														});
												return false;
											}
											var pswd = md5(password);
											var data = {
												password : pswd,
												loginName : username
											};
											var load = layer.load();
											$.post(
															"${ctx}/submitLogin",
															data,
															function(result) {
																layer
																		.close(load);
																if (result
																		&& result.status != 200) {
																	layer
																			.msg(
																					result.message,
																					function() {
																					});
																	$(
																			'.password')
																			.val(
																					'');
																	return;
																} else {
																	layer
																			.msg('登录成功！');
																	setTimeout(
																			function() {
																				//登录返回
																				window.location.href = '${ctx}'
																						+ result.back_url
																						|| "/";
																			},
																			1000)
																}
															}, "json");

										});
						$(
								'.page-container form .username, .page-container form .password')
								.keyup(
										function() {
											$(this).parent().find('.error')
													.fadeOut('fast');
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
		<h1>Login</h1>
		<form id="_form" action="" method="post">
			<input type="text" name="username" class="username"
				placeholder="Account"> <input type="password"
				name="password" class="password" placeholder="Password">
			<div style="text-align: left; margin-left: 10px;"></div>
			<button type="button" id="login" style="width: 100%">登录</button>
			<div class="connect"></div>

			<div class="error">
				<span>+</span>
			</div>
		</form>
	</div>
</body>

</html>