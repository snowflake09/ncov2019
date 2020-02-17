<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>人员健康登记</title>
<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport" />
<link rel="icon" href="${ctx }/image/icon/logo.ico" type="image/x-icon" />
<link rel="shortcut icon" href="${ctx }/image/icon/logo.ico" />
<!-- CSS -->
	<link rel="stylesheet" href="${ctx }/js/ncov/health.css"/>
<!-- Javascript -->
<script src="${ctx }/js/jquery/jquery.min.js"></script>
<script src="${ctx }/js/common/MD5_UTF8.js"></script>
<script src="${ctx }/js/common/layer/layer.js"></script>
<script>
	//如果登录窗口不在主窗口，在跳转到主窗口
	if (window != top){
		top.location.href = "login";
	}

	function editFun(offset,page) {
		var current = Number(page)-Number(1) ;
		if (current == "0"){
			layer.msg('所有人员上报完成！');
			return
		}
		$('.page').val(current);
		$('.error').fadeOut();
		$(".healthitem").first().remove();
		$(".healthitem").first().show();
		$(".healthitem").first().addClass("current");
		if (current == "1") {
			$('#submit').text("完成");
		}
		else {

			$('#submit').text("下一位(" + current + ")");
		}
		console.log(current)
	}
	jQuery(document)
			.ready(
					function() {
						//回车事件绑定
						document.onkeydown = function(event) {
							var e = event || window.event
									|| arguments.callee.caller.arguments[0];
							if (e && e.keyCode == 13) {
								$('#submit').click();
							}
						};

						//登录操作
						$('#submit')
								.click(
										function() {
											var openid = $('.openid').val();
											var regionid = $('.regionid').val();
											var page = $('.page').val();
											var offset = $('.offset').val();
											var username = $('.username').val();
											var userid = $('.userid').val();
											var cardno = $('.cardno').val();
											var temperature = $('.temperature').val();
											var healthy = $('.healthy').val();
											var health_note = $('.health_note').val();
											if (username == '') {
												$('.error').fadeOut(
														'fast',
														function() {
															$('.error').css(
																	'top',
																	'20px')
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
											if (temperature == '' || temperature < 36.0 || temperature > 45.0) {
												$('.error').fadeOut(
														'fast',
														function() {
															$('.error').css(
																	'top',
																	'145px')
																	.show();
														});
												$(this).find('.error').fadeIn(
														'fast',
														function() {
															$('.temperature')
																	.focus();
														});
												return false;
											}
											if (healthy == '') {
												$('.error').fadeOut(
														'fast',
														function() {
															$('.error').css(
																	'top',
																	'210px')
																	.show();
														});
												$(this).find('.error').fadeIn(
														'fast',
														function() {
															$('.health_note')
																	.focus();
														});
												return false;
											}
											if (health_note == '') {
												$('.error').fadeOut(
														'fast',
														function() {
															$('.error').css(
																	'top',
																	'270px')
																	.show();
														});
												$(this).find('.error').fadeIn(
														'fast',
														function() {
															$('.health_note')
																	.focus();
														});
												return false;
											}
											var data = {
												openid : openid,
												REGIONID : regionid,
												page : page,
												offset : offset,
												CARDNO : cardno,
												TEMPERATURE : temperature,
												HEALTHY : healthy,
												HEALTHNOTE : health_note,
												REPORTUSERID:userid,
												USERNAME : username
											};
											var load = layer.load();
											$.post(
															"${ctx}/ncov/web/addhealth",
															data,
															function(result) {
																layer.close(load);
																if (result.success
																		&& result.status != 200) {
																	if (page == "1"){
																		layer.msg('所有人员此次上报完成。');
																		return;
																	}
																	layer.msg('感谢您的配合，您上报的信息为，姓名：'+data.USERNAME+'，身份证：'+data.CARDNO+'，体温：'+data.TEMPERATURE+'度', {
																				time: 200000,
																				area:['auto', 'auto'],
																				anim:0,
																				shade:[0.5, '#393D49'],
																		        btn: ['继续']
																			},
																					function() {
																						editFun(offset,page);
																					});
																	$('.temperature').val('');
																	$('.error').fadeOut();
																	return;
																} else {
																	layer.msg('添加失败！');
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
		<h1>人员健康汇报</h1>
		<div>${now}</div>
		<form id="_form" action="" method="post">
			<input name="openid" class="openid" type="hidden" value="${openid}">
			<input name="regionid" class="regionid" type="hidden" value="${regionid}">
			<input name="page" class="page" type="hidden" value="${count}">
			<input name="offset" class="offset" type="hidden" value="${current}">
			<c:forEach items="${users }" var="user" varStatus="id">
				<c:choose>
				<c:when test="${current == id.index}">
					<div class="healthitem id current flag">
				</c:when>
				<c:otherwise>
					<div class="healthitem ${id.index} hidden">
				</c:otherwise>
				</c:choose>
					<input name="userid" class="userid" type="hidden" value="${user.ID}">
					<input type="text" name="username" class="username" placeholder="姓名" maxlength="10"
						   value="${user.NAME}" disabled="disabled">
					<input type="text" name="cardno" class="cardno" placeholder="身份证" maxlength="10"
						   value="${user.CARDNO}" disabled="disabled">
					<input type="number" name="temperature" class="temperature" placeholder="请输入体温，如：36.5" min="36.0" max="45.0"
						   step="0.1" maxlength="6">
					<select name="healthy" class="healthy">
						<option value="" selected>身体是否健康</option>
						<option value="0">是</option>
						<option value="1">否</option>
					</select>
					<input type="text" name="health_note" class="health_note" placeholder="身体症状，如：发热、乏力、干咳、鼻塞和流涕等">
				</div>
			</c:forEach>
			<c:choose>
				<c:when test="${offset=='1'}">
					<button type="button" id="submit" style="width: 100%">完成(0)</button>
				</c:when>
				<c:otherwise>
					<button type="button" id="submit" style="width: 100%">下一位(${count})</button>
				</c:otherwise>
			</c:choose>
			<div class="error">
				<span>+</span>
			</div>
		</form>
	</div>
</body>

</html>