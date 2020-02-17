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
<title>模块管理</title>
<script type="text/javascript">
	var treeGrid;
	$(function() {
		treeGrid = $('#treeGrid')
				.treegrid(
						{
							url : '${ctx}/sys/resource/treeGrid?type=all',
							idField : 'id',
							treeField : 'name',
							parentField : 'pid',
							fit : true,
							fitColumns : false,
							border : false,
							frozenColumns : [ [ {
								title : '编号',
								field : 'id',
								width : 40,
								hidden : true
							} ] ],
							columns : [ [
									{
										field : 'name',
										title : '模块名称',
										width : 150
									},
									{
										field : 'url',
										title : '模块路径',
										width : 300
									},
									{
										field : 'resourceType',
										title : '模块类型',
										width : 80,
										align : "center",
										formatter : function(value, row, index) {
											switch (value) {
											case 0:
												return '菜单';
											case 1:
												return '按钮';
											case 2:
												return '链接';
											case 3:
												return '页面';
											}
										}
									},
									{
										field : 'pid',
										title : '上级模块ID',
										width : 150,
										hidden : true
									},
									{
										field : 'status',
										title : '状态',
										width : 40,
										align : "center",
										hidden : true
									},
									{
										field : 'seq',
										title : '排序',
										width : 40,
										align : "center"
									},
									{
										field : 'statusLabel',
										title : '状态',
										width : 60,
										align : "center",
										styler : function(value, row, index) {
											// console.log(value);
											if (value == '禁用') {
												return "color:red;";
											} else {
												return "color:green;";
											}
										}
									},
									{
										field : 'action',
										title : '操作',
										width : 160,
										align : "center",
										formatter : function(value, row, index) {
											var str = '&nbsp;';
											str += $
													.formatString(
															'<a href="javascript:void(0)" onclick="editFun(\'{0}\');" >修改</a>',
															row.id);
											str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
											str += $
													.formatString(
															'<a href="javascript:void(0)" onclick="deleteFun(\'{0}\');" >删除</a>',
															row.id);
											return str;
										}
									} ] ],
							toolbar : '#toolbar'
						});
	});

	function addFun() {
		parent.$.modalDialog({
			title : '新建模块',
			width : 500,
			height : 300,
			href : "${ctx}/sys/resource/addPage",
			buttons : [
					{
						text : '保存',
						handler : function() {
							parent.$.modalDialog.openner_treeGrid = treeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
							var f = parent.$.modalDialog.handler
									.find('#resourceAddForm');
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
			title : '修改模块',
			width : 500,
			height : 300,
			href : "${ctx}/sys/resource/editPage?id=" + id,
			buttons : [
					{
						text : '保存',
						handler : function() {
							parent.$.modalDialog.openner_treeGrid = treeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
							var f = parent.$.modalDialog.handler
									.find('#resourceEditForm');
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
		parent.$.messager.confirm('询问', '您是否要删除当前模块？删除当前模块会连同子模块一起删除!',
				function(b) {
					if (b) {
						progressLoad();
						$.post('${ctx}/sys/resource/delete', {
							id : id
						}, function(result) {
							if (result.success) {
								parent.$.messager.alert('提示', result.msg,
										'info');
								treeGrid.treegrid('reload');
							}
							progressClose();
						}, 'JSON');
					}
				});
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'center',border:false"
			style="overflow: hidden;">
			<table id="treeGrid"></table>
		</div>
	</div>
	<div id="toolbar" style="display: none;">
		<a onclick="addFun();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon-add'">新建</a>
	</div>
</body>
</html>