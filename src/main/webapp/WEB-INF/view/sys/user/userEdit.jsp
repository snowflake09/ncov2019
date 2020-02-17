<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<script type="text/javascript">
	$(function() {

		$('#uuserEditForm').form({
			url : '${ctx}/sys/manageuser/edit',
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
					parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为schedule.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
		
		$("#roleId").combobox({
			panelHeight : '200',
			value : '${dto.roleId}'
		});

	});

</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden; padding: 3px;">
		<form id="uuserEditForm" method="post">
		<input type="hidden" name="id" value="${dto.id }" />
			<table class="grid">
<tr>
					<td align="right">姓名：</td>
					<td align="left"><input name="username" class="easyui-validatebox" value="${dto.username }"
						type="text" placeholder=""
						style="width: 200px; height: 20px;" data-options="required:true"
						maxlength="20" /></td>
				</tr>
				<tr>
					<td align="right">账号：</td>
					<td align="left"><input name="loginName" type="text" class="easyui-validatebox" value="${dto.loginName }"
						placeholder="" style="width: 200px; height: 20px;"
						data-options="required:true" maxlength="200" /></td>
				</tr>
				
				<tr>
					<td align="right">手机：</td>
					<td align="left"><input name="phone" type="text" class="easyui-validatebox" value="${dto.phone }"
						placeholder="" style="width: 200px; height: 20px;"
						maxlength="11" /></td>
				</tr>
				<tr>
					<td align="right">授权：</td>
					<td align="left"><select name="status" class="easyui-combobox" value="${dto.status }"
						data-options="width:200,height:25,editable:false,panelHeight:'auto'">
							<option value="1">有效</option>
							<option value="0">禁止登录</option>
					</select></td>
				</tr>
				<tr>
					<td align="right">角色：</td>
					<td align="left">
						<select id="roleId" name="roleId" class="easyui-combobox" data-options="width:200,height:25,required:true,editable:false">
							<c:forEach items="${roleDtos}" var="roleDto">
								<option value="${roleDto.id}">${roleDto.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>