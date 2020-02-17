<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<script type="text/javascript">

	$(function() {

		$('#buildingEditForm').form({
			url : '${ctx}/ncov/building/edit',
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
		<form id="buildingEditForm" method="post">
		<input type="hidden" name="ID" value="${ncovBuildingDto.ID }" />
			<table class="grid">
				<tr>
					<td align="right">楼号名称：</td>
					<td align="left"><input name="BUILDINGNAME" class="easyui-validatebox" value="${ncovBuildingDto.BUILDINGNAME }"
						type="text" placeholder=""
						style="width: 300px; height: 20px;" data-options="required:true"
						maxlength="20" /></td>
				</tr>
				<tr>
					<td align="right">管理员姓名：</td>
					<td align="left"><input name="MANAGERNAME" class="easyui-validatebox"
						data-options="required:true" maxlength="200" value="${ncovBuildingDto.MANAGERNAME}"/></td>
				</tr>
				<tr>
					<td align="right">管理员手机号：</td>
					<td align="left"><input name="MANAGERPHONE" class="easyui-validatebox"
											data-options="required:true" maxlength="200" value="${ncovBuildingDto.MANAGERPHONE}"/></td>

				</tr>
				<tr>
					<td align="right">所属社区：</td>
					<td align="left"><select name="REGIONID" class="easyui-combobox"
											 data-options="width:200,height:25,editable:false,panelHeight:'auto'">
						<c:forEach items="${regionDtos}" var="regionDto">
							<option value="${regionDto.ID}">${regionDto.NAME}</option>
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