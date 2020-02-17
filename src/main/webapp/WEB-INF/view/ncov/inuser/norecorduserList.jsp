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
<title>返京人员管理</title>
<script type="text/javascript">
	var dataGrid;
	$(function() {
		dataGrid = $('#dataGrid')
				.datagrid(
						{
							url : '${ctx}/ncov/inuser/dataNoGrid?usertype=3',
							striped : true,
							rownumbers : true,
							fit : true,
							striped:true,
							pagination : true,
							singleSelect : true,
							idField : 'id',
							pageSize : 50,
							remoteSort:false,
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
									title : '户口所在地',
									align : "center",
									sortable:true,
									field : 'provincename'
								},
								{
									width : '120',
									title : '现居住地',
									align : "center",
									sortable:true,
									field : 'regionaddress'
								},
								{
									width : '80',
									title : '返京时间',
									align : "center",
									sortable:true,
									field : 'cityname'
								},
								{
									width : '80',
									title : '返京出发地',
									align : "center",
									sortable:true,
									field : 'areaname'
								},
								{
									width : '60',
									title : '出行方式',
									align : "center",
									sortable:true,
									field : 'sex',
									formatter : function(value, row, index) {
										if(value=='0'){
											return "自驾";
										}else if (value=='1'){
											return "火车";
										}
										else if (value=='2'){
											return "飞机";
										}
										else
										{
											return "其他";
										}
									}
								},
								{
									width : '60',
									title : '车次',
									align : "center",
									sortable:true,
									field : 'tripno'
								},
								{
									width : '60',
									title : '接触湖北地区人',
									align : "center",
									sortable:true,
									field : 'healthy',
									formatter : function(value, row, index) {
										if(value=='0'){
											return "<b style='color: red'>是<b>";
										}
										else if (value=='1'){
											return "否";
										}
										else{
											return "<b style='color: #FF6103'>未知<b>";
										}
									}
								},
								{
									width : '60',
									title : '车牌号',
									align : "center",
									sortable:true,
									field : 'carno'
								},
								{
									width : '120',
									title : '登记时间',
									align : "center",
									sortable:true,
									resizable:true,
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
									}] ],
							toolbar : '#toolbar'
						}
		);
		dataGrid.datagrid({
			rowStyler:function(index,row){
				if (row.temperature>37.3|| row.healthy != 1){
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
				if (row.temperature>37.3|| row.healthy != 1){
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
						<th width="8%" align="right">联系电话:</th>
						<td width="10%" align="left"><input id="PHONE" name="PHONE"
															style="width: 150px; height: 25px" maxlength="11"/></td>
						<th width="10%" align="right">是否健康:</th>
						<td style="text-align: left;">
							<select id="HEALTHY" name="HEALTHY" class="easyui-combobox" data-options="width:150,height:25,editable:false">
								<option value="">请选择</option>
								<option value="0">是</option>
								<option value="1">否</option>
							</select>
						</td>
					</tr>
					<tr>
						<th width="8%" align="right">出行方式:</th>
						<td width="5%" style="text-align: left;">
							<select name="SEX" class="easyui-combobox"
									data-options="width:150,height:25,editable:false,panelHeight:'auto'">
								<option value="">请选择</option>
								<option value="0">自驾</option>
								<option value="1">火车</option>
								<option value="2">飞机</option>
								<option value="3">其他</option>
							</select>
						</td>
						<th width="10%" align="right">未上报日期:</th>
						<td width="10%" align="left">
							<input name="startDayTime" type="text" class="easyui-datebox" data-options="width:150,editable:false"></input>
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
						</tr>
					<tr>
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