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
<title>角色管理</title>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${ctx}/sys/role/dataGrid',
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
										title : '角色名称',
										field : 'name',
										align : "center"
									},
									{
										width : '180',
										title : '描述',
										align : "center",
										field : 'discription'
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
															'<a href="javascript:void(0)" onclick="grant(\'{0}\',\'{1}\');" >授权</a>',
															row.id,row.name);
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

	function addFun() {
		parent.$.modalDialog({
			title : '新建角色',
			width : 500,
			height : 400,
			href : "${ctx}/sys/role/addPage",
			buttons : [ {
				text : '保存',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#uroleAddForm');
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
			title : '修改角色',
			width : 500,
			height : 400,
			href : "${ctx}/sys/role/editPage?id=" + id,
			buttons : [
					{
						text : '保存',
						handler : function() {
							parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
							var f = parent.$.modalDialog.handler
									.find('#uroleEditForm');
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

	function setIcon() {

		var id = dataGrid.datagrid('getSelected');
		if (id == undefined) {
			parent.$.messager.alert('提示', "请选择角色", 'info');
			return false;
		}
		parent.$.modalDialog({
			title : '选择图标',
			width : 500,
			height : 300,
			href : "${ctx}/permission/urole/iconPage?id=" + id.id,
			buttons : [ {
				text : '保存',
				handler : function() {
					parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
					var f = parent.$.modalDialog.handler.find('#iconAddForm');
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
		parent.$.messager.confirm('询问', '您是否要删除当前角色？删除当前角色会连同子角色一起删除!',
				function(b) {
					if (b) {
						progressLoad();
						$.post('${ctx}/sys/role/delete', {
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

	
	function grant(id,name){
		var text = name+"-角色授权"
		self.parent.addTab({
            url: "${ctx}/sys/role/grantPage?change=" + Date.parse(new Date()) + "&title=${param.title}&id="+id+"&text="+text,
            title: text,
            iconCls: "icon-add"
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