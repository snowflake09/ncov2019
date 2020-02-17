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
<title>楼号管理</title>
<script type="text/javascript">
	var dataGrid;
	var userid;
	$(function() {
		userid();
		userid = $('#userid').val();
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${ctx}/ncov/building/dataGrid',
							striped : true,
							rownumbers : true,
							fit : true,
							pagination : true,
							singleSelect : true,
							idField : 'ID',
							pageSize : 50,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							columns : [ [
								{
									width : '120',
									title : '社区名称',
									field : 'name',
									align : "center"
								},
									{
										width : '80',
										title : '楼号名称',
										field : 'buildingname',
										align : "center"
									},
									{
										width : '180',
										title : '管理员名称',
										align : "center",
										field : 'managername'
									},
									{
										width : '180',
										title : '管理员电话',
										align : "center",
										field : 'managerphone'
									},
								{
									width : '180',
									title : '状态',
									align : "center",
									field : 'status',
									formatter : function(value, row, index) {
										if(value=='1'){
											return "停用";
										}else{
											return "启用";
										}
									}
								},
								{
									width : '180',
									title : '添加日期',
									align : "center",
									field : 'createtime'
								},
									{
										field : 'action',
										width : '180',
										title : '操作',
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
							toolbar : '#toolbar',
							onLoadSuccess:function(result){
								if(result.success!=undefined && !result.success){
									parent.$.messager.alert('错误', result.msg, 'error');
								}
							}
						});
	});
	function userid(){
		$.post('${ctx}/ncov/web/getuserid', function(result) {
			if (result.code == "200") {
				$('#userid').val(result.data);
			}else{
				$('#userid').val("0");
			}
		}, 'JSON');
	}

	function addFun() {
		var userid
		userid = $('#userid').val();
		parent.$.modalDialog({
			title : '新建楼号',
			width : 500,
			height : 400,
			href : "${ctx}/ncov/building/addPage",
			buttons : [
				{
					text : '保存',
					handler : function() {
						parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
						var f = parent.$.modalDialog.handler
								.find('#buildingAddForm');
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
			title : '修改楼号',
			width : 500,
			height : 400,
			href : "${ctx}/ncov/building/editPage?id=" + id,
			buttons : [
				{
					text : '保存',
					handler : function() {
						parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
						var f = parent.$.modalDialog.handler
								.find('#buildingEditForm');
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
		parent.$.messager.confirm('询问', '您是否要删除当前小区信息？',
				function(b) {
					if (b) {
						progressLoad();
						$.post('${ctx}/ncov/building/delete', {
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
			<input type="hidden" id="userid" name="userid" />
			<form id="searchForm">
				<table class="tabContent0">
					<tr>
						<th width="10%" align="right">楼号名称:</th>

						<td width="10%" style="text-align: left;"><input id ="name" name="name" placeholder="楼号名称" style="width: 150px;"></td>
						</td>
						<th width="10%" align="right">管理员:</th>
						<td width="10%" style="text-align: left;"><input id ="managename" name="managename"
																		 placeholder="管理员名称" style="width: 150px;"></td>
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