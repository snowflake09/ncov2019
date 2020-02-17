<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<script type="text/javascript">
	$(function() {
		$('#pid').combotree({
			url : '${ctx}/sys/resource/comboTree',
			parentField : 'pid',
			lines : true,
			value:"${resourceDto.pid }"
		});

		$('#resourceEditForm').form({
			url : '${ctx}/sys/resource/edit',
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
					parent.$.modalDialog.openner_treeGrid.treegrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为resource.jsp页面预定义好了
					parent.$.modalDialog.handler.dialog('close');
				} else {
					parent.$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
		
		$("#resourceType").combobox({
			panelHeight : '200',
			value : '${resourceDto.resourceType}'
		});
		
		$("#status").combobox({
			panelHeight : '200',
			value : '${resourceDto.status}'
		});
		$("#iconcls").combobox({
			panelHeight : '200',
			value : '${resourceDto.iconcls}'
		});

	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title=""
		style="overflow: hidden; padding: 3px;">
		<form id="resourceEditForm" method="post">
		<input type="hidden" name="id" value="${resourceDto.id }" />
			<table class="grid">
				<tr>
					<td align="right">模块名称：</td>
					<td align="left"><input name="name" class="easyui-validatebox" value="${resourceDto.name }"
						type="text" placeholder="请输入模块名称,限20字符"
						style="width: 150px; height: 20px;" data-options="required:true"
						maxlength="20" /></td>
				</tr>
				<tr>
					<td align="right">模块路径：</td>
					<td align="left"><input name="url" type="text" value="${resourceDto.url }"
						placeholder="请输入模块路径,限200字符" style="width: 150px; height: 20px;"
						data-options="required:true" maxlength="200" /></td>
				</tr>
				<tr>
					<td align="right">模块类型：</td>
					<td align="left"><select name="resourceType" id="resourceType"
						class="easyui-combobox"
						data-options="width:150,height:25,editable:false,panelHeight:'auto'">
							<option value="0">菜单</option>
							<option value="1">其它</option>
					</select></td>
				</tr>
				<tr>
					<td align="right">图标：</td>
					<td align="left"><select id="iconcls" name="iconcls"
						class="easyui-combobox"
						data-options="width:150,height:25,editable:false,panelHeight:'auto'">
							<option value="">无</option>
							<c:forEach items="${dto}" var="baseType">
								<option value="${baseType.value}">${baseType.label}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td align="right">状态：</td>
					<td align="left"><select id="status" name="status" class="easyui-combobox" value="${resourceDto.status }"
						data-options="width:150,height:25,editable:false,panelHeight:'auto'">
							<option value="1">启用</option>
							<option value="0">停用</option>
					</select></td>
				</tr>
				<tr>
					<td align="right">上级模块</td>
					<td align="left"><select id="pid" name="pid"
						style="width: 150px; height: 25px;" data-options="editable:false"></select>
						<a class="easyui-linkbutton" href="javascript:void(0)"
						onclick="$('#pid').combotree('clear');">清空</a></td>
				</tr>
				<tr>
					<td align="right">排序</td>
					<td align="left">
						<input name="seq" class="easyui-numberbox" value="${resourceDto.seq }"
						type="text" placeholder=""
						style="width: 150px; height: 20px;" data-options="required:true"
						maxlength="2" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>