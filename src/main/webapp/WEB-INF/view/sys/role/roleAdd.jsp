<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<script type="text/javascript">
	$(function() {

		$('#uroleAddForm').form({
			url : '${ctx}/sys/role/save',
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
		<form id="uroleAddForm" method="post">
		<input type="hidden" name="jobstatus" value="0"/>
			<table class="grid">
				<tr>
					<td align="right">角色名称：</td>
					<td align="left"><input name="name" class="easyui-validatebox"
						type="text" placeholder=""
						style="width: 300px; height: 20px;" data-options="required:true"
						maxlength="20" /></td>
				</tr>
				<tr>
					<td align="right">描述：</td>
					<td align="left"><textarea name="discription" class="easyui-validatebox"
						data-options="required:true" maxlength="200" style="width:298px;height:68px;"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
</div>