<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String base = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bootstrap 模板</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 引入 Bootstrap -->
<link
	href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">

<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
<!--[if lt IE 9]>
         <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
         <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
      <![endif]-->
<!-- jQuery (Bootstrap 的 JavaScript 插件需要引入 jQuery) -->
<script src="<%=base%>/js/jquery.js"></script>
<!-- 包括所有已编译的插件 -->
<script src="<%=base%>/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#hehe").click(function() {
			$(this).hide();
		});
		function haha(){
			alert("sssss");
			
		}
	});
</script>
</head>
<body>
	<h1>Hello, world!</h1>

	<p  onclick="haha();">If you click on me, I will disappear.</p>
	<button id="hehe" >走你 </button>
</body>
</html>