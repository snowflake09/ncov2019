<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../../inc.jsp"></jsp:include>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>数据字典管理</title>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		
		document.onkeydown=function(event){
			var e = event || window.event || arguments.callee.caller.arguments[0];
			if(e && e.keyCode==13){ 
				searchFun();
			}
		};
		
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${ctx}/sys/dictionary/dataGrid',
							striped : true,
							rownumbers : true,
							fit : true,
							pagination : true,
							singleSelect : true,
							idField : 'id',
							pageSize : 50,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							columns : [ [
									{
										width : '150',
										title : '键值',
										field : 'value',
										align : 'center'
									},
									{
										width : '150',
										title : '标签名',
										field : 'label',
										align : 'center'
									},
									{
										width : '150',
										title : '类型',
										field : 'typeLabel',
										align : 'center'
									},
									{
										width : '200',
										title : '描述',
										field : 'description',
										align : 'center'
									},
									{
 										width : '100',
 										title : '排序号',
 										field : 'seq',
 										align : 'center'
 									},
									{
										field :'action',
										width : '180',
										title : '操作',
										align : "center",
										formatter : function(value, row, index) {
											var str = '&nbsp;';
											str += $
											.formatString(
													'<a href="javascript:void(0)" onclick="editFun(\'{0}\');" >修改</a>',
													row.id);
											return str;
										}
									} ] ],
							toolbar : '#toolbar'
						});
	});

	function addFun() {
		parent.$.modalDialog({
			title : '新建字典',
			width : 500,
			height : 400,
			href : "${ctx}/sys/dictionary/addPage",
			buttons : [
					{
						text : '保存',
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
							var f = parent.$.modalDialog.handler
									.find('#dictionaryAddForm');
							f.submit();
						}
					}, {
						text : '取消',
						handler : function() {
							parent.$.modalDialog.handler.dialog('close');
						}
					} ]
		});
	}

	function editFun(id) {
		parent.$.modalDialog({
			title : '修改字典',
			width : 500,
			height : 400,
			href : "${ctx}/sys/dictionary/editPage?id=" + id,
			buttons : [
					{
						text : '保存',
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
							var f = parent.$.modalDialog.handler
									.find('#dictionaryEditForm');
							f.submit();
						}
					}, {
						text : '取消',
						handler : function() {
							parent.$.modalDialog.handler.dialog('close');
						}
					} ]
		});
	}

	function deleteFun(id) {
		parent.$.messager.confirm('询问', '您是否要删除当前资源？删除当前资源会连同子资源一起删除!',
				function(b) {
					if (b) {
						progressLoad();
						$.post('${ctx}/sys/dictionary/delete', {
							id : id
						}, function(result) {
							if (result.success) {
								parent.$.messager.alert('提示', result.msg,
										'info');
								dataGrid.datagrid('reload');
							}
							progressClose();
						}, 'JSON');
					}
				});
	}
	
	
	function searchFun() {

		dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
	}
	function cleanFun() {
		$('#searchForm input').val('');
		dataGrid.datagrid('load', {});
	}
</script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" class="divContent0">
		<form id="searchForm">
			<table class="tabContent0">
				<tr>
					<th width="10%" align="right">键值:</th>
					<td width="20%" align="left"><input name="value"
						placeholder="" style="width:150px;"></td>
					<th width="10%" align="right">标签名:</th>
					<td width="20%" align="left"><input name="label"
						placeholder="" style="width:150px;"></td>


				</tr>
				<tr>
					<th width="10%" align="right">类型:</th>
					<td style="text-align: left;">
						<select id="type" name="type" class="easyui-combobox" data-options="width:150,height:25,editable:false">
							<option value="">全部</option>
							<c:forEach items="${dto}" var="baseType">
								<option value="${baseType.value}">${baseType.label}</option>
							</c:forEach>
						</select>
					</td>
					<th width="10%" align="right"></th>
					<td width="20%" align="left"><a href="javascript:void(0);"
						class="easyui-linkbutton"
						data-options="iconCls:'icon-search',plain:true"
						onclick="searchFun();">查询</a> <a href="javascript:void(0);"
						class="easyui-linkbutton"
						data-options="iconCls:'icon-cancel',plain:true"
						onclick="cleanFun();">清空</a></td>
				</tr>
			</table>
			<input id="page" name="page" type="hidden" /> <input id="rows"
				name="rows" type="hidden" />
		</form>
	</div>
	
		<div data-options="region:'center',border:false"
			style="overflow: hidden;">
			<table id="dataGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<a onclick="addFun();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon-add'">新建</a>
	</div>
</body>
</html>