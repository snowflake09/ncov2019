<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<script type="text/javascript">
	$(function() {

		$('#regionAddForm').form({
			url : '${ctx}/ncov/region/save',
			onSubmit : function() {
				progressLoad();
				var isValid = $(this).form('validate');
				if (!isValid) {
					progressClose();
				}
				return isValid;
			},
			success : function(result) {
				progressClose();
				result = $.parseJSON(result);
				if (result.success) {
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为urole.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});

	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden; padding: 3px;">
		<form id="regionAddForm" method="post">
			<table class="grid">
				<tr>
					<td align="right">社区名称：</td>
					<td align="left"><input name="NAME" class="easyui-validatebox"
						type="text" placeholder="社区名称"
						style="width: 300px; height: 20px;" data-options="required:true"
						maxlength="20" /></td>
				</tr>
				<tr>
					<td align="right">社区地址：</td>
					<td align="left"><input name="ADDRESS" class="easyui-validatebox"
											type="text" placeholder="省份地市区"
											style="width: 300px; height: 20px;" data-options="required:true"
											maxlength="20" /></td>
				</tr>
				<tr>
					<td align="right">管理员：</td>
					<td align="left"><select name="MANAGEID" class="easyui-combobox"
											 data-options="width:200,height:25,editable:false,panelHeight:'auto'">
						<c:forEach items="${userDtos}" var="userDto">
							<option value="${userDto.id}">${userDto.loginName}</option>
						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td align="right">状态：</td>
					<td align="left"><select name="STATUS" class="easyui-combobox"
											 data-options="width:200,height:25,editable:false,panelHeight:'auto'">
						<option value="0">有效</option>
						<option value="1">禁止</option>
					</select></td>
				</tr>

			</table>
		</form>
	</div>
</div>