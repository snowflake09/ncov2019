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
<title>访客管理</title>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${ctx}/ncov/outuser/dataGrid?usertype=2',
							striped : true,
							rownumbers : true,
							fit : true,
							pagination : true,
							singleSelect : true,
							remoteSort:false,
							idField : 'id',
							pageSize : 50,
							pageList : [ 10, 20, 30, 40, 50, 100, 200, 300,
									400, 500 ],
							columns : [ [
									{
										width : '80',
										title : '姓名',
										field : 'name',
										sortable:true,
										align : "center"
									},
									{
										width : '120',
										title : '身份证',
										align : "center",
										sortable:true,
										field : 'cardno'
									},
								{
									width : '80',
									title : '联系方式',
									align : "center",
									sortable:true,
									field : 'phone'
								},
								{
									width : '120',
									title : '事由',
									align : "center",
									sortable:true,
									field : 'reason'
								},
								{
									width : '120',
									title : '拜访对象信息',
									align : "center",
									sortable:true,
									field : 'regionaddress'
								},
								{
									width : '60',
									title : '车牌号',
									align : "center",
									sortable:true,
									field : 'carno'
								},
								{
									width : '50',
									title : '体温',
									align : "center",
									sortable:true,
									field : 'temperature',
									formatter : function(value, row, index) {
										if (value > 37.3) {
											var str = '&nbsp;';
											str += $.formatString(
													"<b style='color: red'>{0}<b>",
													value);
											return str;
										} else {
											var str = '&nbsp;';
											str += $.formatString(
													"<b>{0}<b>",
													value);
											return str;
										}
									}
								},
								{
									width : '120',
									title : '登记时间',
									align : "center",
									sortable:true,
									field : 'createdate'
								},
								{
										width : '60',
										title : '状态',
										field : 'status',
									    sortable:true,
										align : "center",
										formatter : function(value, row, index) {
											if(value=='1'){
												return "删除";
											}else{
												return "有效";
											}
										}
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
								}] ],
							toolbar : '#toolbar'
						});
		dataGrid.datagrid({
			rowStyler:function(index,row){
				if (row.temperature>37.3){
					return 'background-color:#F5DEB3;color:blue;font-weight:bold;';
				}
			}
		});
	});
	function deleteFun(id) {
		parent.$.messager.confirm('询问', '您是否要删除当前记录信息？',
				function(b) {
					if (b) {
						progressLoad();
						$.post('${ctx}/ncov/user/delete', {
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
		dataGrid.datagrid({
			rowStyler:function(index,row){
				if (row.temperature>37.3){
					return 'background-color:#F5DEB3;color:blue;font-weight:bold;';
				}
			}
		});
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
			<input id="USERTYPE" name="USERTYPE"  type="hidden" value="0"/>
			<form id="searchForm">
				<table class="tabContent0">
					<tr>
						<th width="8%" align="right">姓名:</th>
						<td width="10%" align="left"><input id="NAME" name="NAME"
															style="width: 150px; height: 25px" maxlength="5"/></td>
						<th width="8%" align="right">身份证:</th>
						<td width="10%" align="left"><input id="CARDNO" name="CARDNO"
															style="width: 150px; height: 25px" maxlength="18"/></td>
						<th width="10%" align="right">联系电话:</th>
						<td width="10%" align="left"><input id="PHONE" name="PHONE"
															style="width: 150px; height: 25px" maxlength="11"/></td>
						<th width="8%" align="right">体温大于:</th>
						<td width="5%" align="left"><input type="number" id="TEMPERATURE" name="TEMPERATURE"
														   style="width: 150px; height: 25px" min="34.0" max="43.0" step="0.1" maxlength="4"/></td>
					</tr>
					<tr>
						<th width="10%" align="right">登记日期:</th>
						<td width="10%" align="left">
							<input name="startDayTime" type="text" class="easyui-datebox" data-options="width:150,editable:false"></input>
							~
							<input name="endDayTime" type="text" class="easyui-datebox" data-options="width:150,editable:false"></input>
						</td>
						<th width="10%" align="right">楼号:</th>
						<td style="text-align: left;">
							<select id="REGIONADDRESS" name="REGIONADDRESS" class="easyui-combobox" data-options="width:150,height:25,editable:false">
								<option value="" selected>请选择查询楼号</option>
								<c:forEach items="${Buildings}" var="Building">
									<option value="${Building.BUILDINGNAME}">${Building.BUILDINGNAME}</option>
								</c:forEach>
							</select>
						</td>
						<th width="10%" align="right">住址:</th>
						<td width="10%" align="left"><input id="ADDRESS" name="ADDRESS"
															style="width: 150px; height: 25px" maxlength="11"/></td>
						<th width="10%" align="right"></th>
						<td width="10%" align="left"><a href="javascript:void(0);"
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