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
<title>用户管理</title>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${ctx}/sys/manageuser/dataGrid',
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
										width : '100',
										title : '姓名',
										field : 'username',
										align : "center"
									},
									{
										width : '180',
										title : '账号',
										align : "center",
										field : 'loginName'
									},
									{
										width : '100',
										title : '授权状态',
										field : 'status',
										align : "center",
										formatter : function(value, row, index) {
											if(value=='1'){
												return "有效";
											}else{
												return "禁止登录";
											}
										}
									},
									{
										field :'action',
										width : '100',
										title : '操作',
										align : "center",
										formatter : function(value, row, index) {
											var str = '';
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
			title : '新建用户',
			width : 500,
			height : 400,
			href : "${ctx}/sys/manageuser/addPage",
			buttons : [
					{
						text : '保存',
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
							var f = parent.$.modalDialog.handler
									.find('#uuserAddForm');
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
			title : '修改用户',
			width : 500,
			height : 400,
			href : "${ctx}/sys/manageuser/editPage?id=" + id,
			buttons : [
					{
						text : '保存',
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
							var f = parent.$.modalDialog.handler
									.find('#uuserEditForm');
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
		parent.$.messager.confirm('询问', '您是否要删除当前用户!',
				function(b) {
					if (b) {
						progressLoad();
						$.post('${ctx}/sys/manageuser/delete', {
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
	
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
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