<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="inc.jsp"></jsp:include>
<title>社区人员健康管理系统</title>
<style type="text/css">

</style>

<script type="text/javascript">
	$(function() {

	});
	function refreshNavi() {
		$('#naviPanel').panel('refresh', '${ctx}/sys/resource/navigation');
	}
	function closeTab(closeTabName, refreshTabName) {
		//var currTab =  self.parent.$('#tabs').tabs('getSelected'); //获得当前tab
		var currTab = $('#index_tabs').tabs('getTab', refreshTabName);
		var url = $(currTab.panel('options').content).attr('src');
		$('#index_tabs')
				.tabs(
						'update',
						{
							tab : currTab,
							options : {
								content : '<iframe src="'
										+ url
										+ '" frameborder="0" style="border:0;width:100%;height:99.5%;"></iframe>'
							}
						});
		$("#index_tabs").tabs('close', closeTabName);
		$("#index_tabs").tabs('select', refreshTabName);
	}

	function refershParentTab(closeTabName, refreshTabName) {
		//var currTab =  self.parent.$('#tabs').tabs('getSelected'); //获得当前tab
		var currTab = $('#index_tabs').tabs('getTab', refreshTabName);
		var url = $(currTab.panel('options').content).attr('src');
		$('#index_tabs')
				.tabs(
						'update',
						{
							tab : currTab,
							options : {
								content : '<iframe src="'
										+ url
										+ '" frameborder="0" style="border:0;width:100%;height:99.5%;"></iframe>'
							}
						});
		//         $("#index_tabs").tabs('close', closeTabName);
		//         $("#index_tabs").tabs('select', refreshTabName);
	}

	function getCurrentTab() {
		var currTab = $('#index_tabs').tabs('getSelected');
		var title = currTab.panel('options').title;
		return title;
	}

	function onlyCloseTab(closeTabName) {
		$("#index_tabs").tabs('close', closeTabName);
	}

	function addTab(params) {
		var iframe = '<iframe src="'
				+ params.url
				+ '" frameborder="0" style="border:0;width:100%;height:99.5%;"></iframe>';
		var t = $('#index_tabs');
		var opts = {
			title : params.title,
			closable : true,
			iconCls : params.iconCls,
			content : iframe,
			border : false,
			fit : true
		};
		if (t.tabs('exists', opts.title)) {
			//modify by qianwei
			//t.tabs('select', opts.title);
			t.tabs('close', opts.title);
			t.tabs('add', opts);
		} else {
			t.tabs('add', opts);
		}
	}
</script>
</head>

<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height: 60px; background: #B3DFDA; padding: 10px;font-size: 18px">疫情社区健康防控系统</div>

	<div id="naviPanel"
		data-options="region:'west',split:true,title:'导航菜单',href:'${ctx}/sys/resource/navigation',tools:'#tb'"
		style="width: 150px"></div>
	<div
		data-options="region:'east',split:true,collapsed:true,title:'East'"
		style="width: 100px; padding: 10px;">技术支持，微信13661207021</div>
	<div data-options="region:'south',border:false"
		style="height: 20px; background: #A9FACD; padding: 2px;font-size: 12px">北京西奈山科技有限公司 © 版权所有2019-2022</div>
	<div data-options="region:'center'">
		<div class="easyui-tabs" id="index_tabs"
			data-options="fit:true,border:false,plain:true">
			<div title="介绍" data-options="" style="padding: 10px">
				<div style="margin: 10px auto;text-align: center">
					<div><b>返京人员</b>
						总数：89人 ，新增数：10人 ，以解除隔离：4人，正在隔离：50人，身体异样：10人</div>
					<div><b>访客人员</b>
						总数：89人 ，新增数：10人 ,身体异样：10人</div>
					<div><b>日常出入</b>
						总数：89人 ，新增数：10人 ,身体异样：10人</div>
				</div>
				<table class="tabContent0">
					<tr>
						<td width="50%"><div id="chart1" style="" class="chart"></div></td>
						<td width="50%"><div id="chart2" style="" class="chart"></div></td>
					</tr>
					<tr>
						<td width="50%"><div id="chart3" style="" class="chart"></div></td>
						<td width="50%"><div id="chart4" style="" class="chart"></div></td>
					</tr>
				</table>
				</div>
			</div>
		</div>
	</div>
	<!-- <div id="tb" style="display: none;">
		<a href="#" class="icon-reload" onclick="refreshNavi()"></a>
	</div> -->
	<script type="text/javascript">
		// 基于准备好的dom，初始化echarts实例
		var myChart1 = echarts.init(document.getElementById('chart1'));
		var myChart2 = echarts.init(document.getElementById('chart2'));
		var myChart3 = echarts.init(document.getElementById('chart3'));

		// 指定图表的配置项和数据
		var option1 = {
			title: {
				text: '外来人员'
			},
			tooltip: {},
			legend: {
				data:['人数']
			},
			xAxis: {
				data: ["A区","B区","C区","D区","E区","F区"]
			},
			yAxis: {},
			series: [{
				name: '人数',
				type: 'bar',
				data: [5, 20, 36, 10, 10, 20]
			}]
		};
		var option2 = {
			title: {
				text: '人员构成'
			},
			series : [
				{
					name: '人员来源',
					type: 'pie',
					radius: '55%',
					data:[
						{value:235, name:'北京'},
						{value:274, name:'河北'},
						{value:310, name:'河南'},
						{value:335, name:'天津'},
						{value:400, name:'山东'}
					]
				}
			]
		};
		function randomData() {
			now = new Date(+now + oneDay);
			value = value + Math.random() * 21 - 10;
			return {
				name: now.toString(),
				value: [
					[now.getFullYear(), now.getMonth() + 1, now.getDate()].join('/'),
					Math.round(value)
				]
			};
		}

		var data = [];
		var now = +new Date(2019, 12, 30);
		var oneDay = 24 * 3600 * 1000;
		var value = Math.random() * 1000;
		for (var i = 0; i < 1000; i++) {
			data.push(randomData());
		}

		var option3 = {
			title: {
				text: '人员流动'
			},
			tooltip: {
				trigger: 'axis',
				formatter: function (params) {
					params = params[0];
					var date = new Date(params.name);
					return date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear() + ' : ' + params.value[1];
				},
				axisPointer: {
					animation: false
				}
			},
			xAxis: {
				type: 'time',
				splitLine: {
					show: false
				}
			},
			yAxis: {
				type: 'value',
				boundaryGap: [0, '100%'],
				splitLine: {
					show: false
				}
			},
			series: [{
				name: '模拟数据',
				type: 'line',
				showSymbol: false,
				hoverAnimation: false,
				data: data
			}]
		};

		setInterval(function () {

			for (var i = 0; i < 5; i++) {
				data.shift();
				data.push(randomData());
			}

			myChart3.setOption({
				series: [{
					data: data
				}]
			});
		}, 1000);
		// 使用刚指定的配置项和数据显示图表。
		myChart1.setOption(option1);
		myChart2.setOption(option2);
		myChart3.setOption(option3);

	</script>
	<script type="text/javascript">
		var myChart4 = echarts.init(document.getElementById('chart4'));
		option = {
			tooltip: {
				trigger: 'item',
				formatter: '{a} <br/>{b}: {c} ({d}%)'
			},
			legend: {
				orient: 'vertical',
				left: 10,
				data: ['外来访问', '身体异常', '解除隔离', '隔离中', '身体正常']
			},
			series: [
				{
					name: '人员动态',
					type: 'pie',
					radius: ['50%', '70%'],
					avoidLabelOverlap: false,
					label: {
						normal: {
							show: false,
							position: 'center'
						},
						emphasis: {
							show: true,
							textStyle: {
								fontSize: '30',
								fontWeight: 'bold'
							}
						}
					},
					labelLine: {
						normal: {
							show: false
						}
					},
					data: [
						{value: 335, name: '外来访问'},
						{value: 310, name: '身体异常'},
						{value: 234, name: '解除隔离'},
						{value: 135, name: '隔离中'},
						{value: 1548, name: '身体正常'}
					]
				}
			]
		};

		myChart4.setOption(option);
	</script>
</body>


</html>