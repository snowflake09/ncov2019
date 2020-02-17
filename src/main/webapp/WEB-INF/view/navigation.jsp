<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<title>导航菜单</title>

<script type="text/javascript">
	
</script>
</head>

<body class="easyui-layout">
	<div id="navigation" class="easyui-accordion"
		style="background-color: #fff;"></div>
	<script>
		$("#navigation").accordion({ //初始化accordion
			fillSpace : true,
			fit : true,
			border : false,
			animate : false
		});
		$.post('${ctx}/sys/resource/getChildResource', {
			pid : 0
		}, function(data) {
			$.each(data, function(i, e) {
				var id = e.id;
				$('#navigation').accordion('add', {
					title : e.text,
					content : "<ul id='tree"+id+"' ></ul>",
					selected : true,
					iconCls : e.iconCls
				});
				//$.parser.parse();
				/* $.post('${ctx}/sys/resource/getChildResource', {
					pid : id
				}, function(data) {
						$("#tree" + id).tree({
							data:data,
							onBeforeExpand:function(node,param){ 
							$("#tree" + id).tree('options').url = "${ctx}/sys/resource/getChildResource?pid=" + node.id;
							}, onLoadSuccess:function(node,data){
								console.log(data);
								var t = $(this);
								if(data){
							       $(data).each(function(index,d){
								   if(d.state == 'closed'){
									   //t.tree('expandAll');
									   alert(23);
								   }});
							   }
							},
							onClick : function(node){ 
								if (node.state == 'closed'){ 
									$(this).tree('expand', node.target); 
								}else if (node.state == 'open'){ 
									$(this).tree('collapse', node.target); 
								}else{
									alert(node.text);
									var tabTitle = node.text;
									var url = "../../" + node.attributes;
									var icon = node.iconcls;
									alert(icon);
									//addTab(tabTitle, url, icon);
								}
							}
						});
				}, 'JSON'); */
						$("#tree" + id).tree({
							url:"${ctx}/sys/resource/getChildResource?pid="+id,
							animate:true,
							onBeforeExpand:function(node,param){ 
							$("#tree" + id).tree('options').url = "${ctx}/sys/resource/getChildResource?pid=" + node.id;
							}, onLoadSuccess:function(node,data){
								var t = $(this);
								if(data){
							       $(data).each(function(index,d){
								   if(d.state == 'closed'){
									   t.tree('expandAll');
								   }});
							   }
							},
							onClick : function(node){ 
									if (node && node.attributes) {
										var url = '${ctx}' + node.attributes + "?title=" + node.text;
										addTab({
											url : url,
											title : node.text,
											iconCls : node.iconCls
										});
									}
							}
						});
			});
		}, 'JSON');
		
	
		function addTab(params) {
			var iframe = '<iframe src="' + params.url
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
	
</body>
</html>