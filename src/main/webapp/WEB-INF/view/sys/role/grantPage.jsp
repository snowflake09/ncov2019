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
<title>授权管理</title>
<script type="text/javascript">
	var treeGrid;
	var resourceTree;
	$(function() {
		resourceTree = $('#treeGrid').tree({
			url : '${ctx}/sys/role/selectPermissionList?id=${param.id}',
			parentField : 'pid',
			lines : true,
			checkbox : true,
			onCheck : function(node, checked) {
				if(checked){
					//alert(node.id);
				}
			},
			onLoadSuccess : function(node, data) {
				progressLoad();
				initNode(data);
				
				/*
				$.post( '${ctx}/sys/role/get', {
					id : '${role.id}'
				}, function(result) {
					var ids;
					var uncheckedIds;
					if (result.id != undefined && result.resourceIds!= undefined) {
						ids = $.stringToList(result.resourceIds);
						uncheckedIds = $.stringToList(result.uncheckedResourceIds);
					}
					if (ids.length > 0) {
						for ( var i = 0; i < ids.length; i++) {
							if (resourceTree.tree('find', ids[i])) {
								resourceTree.tree('check', resourceTree.tree('find', ids[i]).target);
							}
						}
						for (var i = 0; i < uncheckedIds.length; i++) {
							if (resourceTree.tree('find', uncheckedIds[i])) {
								resourceTree.tree('uncheck', resourceTree.tree('find', uncheckedIds[i]).target);
							}
						}
					}
				}, 'json');
				*/
				progressClose();
			},
			cascadeCheck : true
		});
		
	});
	function checkAll() {
		var nodes = resourceTree.tree('getChecked', 'unchecked');
		if (nodes && nodes.length > 0) {
			for ( var i = 0; i < nodes.length; i++) {
				resourceTree.tree('check', nodes[i].target);
			}
		}
	}
	function uncheckAll() {
		var nodes = resourceTree.tree('getChecked');
		if (nodes && nodes.length > 0) {
			for ( var i = 0; i < nodes.length; i++) {
				resourceTree.tree('uncheck', nodes[i].target);
			}
		}
	}
	function checkInverse() {
		var unchecknodes = resourceTree.tree('getChecked', 'unchecked');
		var checknodes = resourceTree.tree('getChecked');
		if (unchecknodes && unchecknodes.length > 0) {
			for ( var i = 0; i < unchecknodes.length; i++) {
				resourceTree.tree('check', unchecknodes[i].target);
			}
		}
		if (checknodes && checknodes.length > 0) {
			for ( var i = 0; i < checknodes.length; i++) {
				resourceTree.tree('uncheck', checknodes[i].target);
			}
		}
	}
	var ids=[];
	function save(){
		ids=[];
		ids.push(0);
		var checknodes = resourceTree.tree('getChecked', ['checked', 'indeterminate']);
		if (checknodes && checknodes.length > 0) {
			for ( var i = 0; i < checknodes.length; i++) {
				ids.push(checknodes[i].id);
			}
		}
		progressLoad();
		$.post('${ctx}/sys/role/grant', {
			roleId : '${param.id}',
			permissionIds:ids.toString()
		}, function(result) {
			if (result.success) {
				parent.$.messager.alert('提示', result.msg,'info');
				self.parent.closeTab('${param.text}', "${param.title}");
			}else{
				parent.$.messager.alert('提示', result.msg,'info');
			}
			progressClose();
		}, 'JSON');
	}
	
	function initNode(rows){
		if(rows.length != 0){
			for(var i=0;i<rows.length;i++){
				var node = rows[i];
					if(node.flag){
						resourceTree.tree('check', resourceTree.tree('find', node.id).target);
					}else{
						resourceTree.tree('uncheck', resourceTree.tree('find',  node.id).target);
					}
					if(node.children!=undefined){
						initNode(node.children);
					}

			}
		}
	}
	
</script>
</head>
<body>
<div id="roleGrantLayout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'west'" title="系统资源" style="width: 300px; padding: 1px;">
		<div data-options="region:'center',border:false"
			style="overflow: hidden;">
			<table id="treeGrid"></table>
		</div>
	</div>
	<div  data-options="region:'center'" title="" style="overflow: hidden; padding: 10px;">
		<a onclick="checkAll();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon-ok'">全选</a> <a
			onclick="uncheckAll();" href="javascript:void(0);"
			class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-cancel'">清除</a>
		<a onclick="save();" href="javascript:void(0);"
			class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'">保存</a>
	</div>
	</div>
</body>
</html>