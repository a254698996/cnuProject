<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/paginationTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>GGT后台管理</title>
<link href="${ctx}/static/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<script src="${ctx}/static/bootstrap/js/jquery.js"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<style type="text/css">
/* Custom Styles */
ul.nav-tabs {
	width: 140px;
	margin-top: 20px;
	border-radius: 4px;
	border: 1px solid #ddd;
	box-shadow: 0 1px 4px rgba(0, 0, 0, 0.067);
}

ul.nav-tabs li {
	margin: 0;
	border-top: 1px solid #ddd;
}

ul.nav-tabs li:first-child {
	border-top: none;
}

ul.nav-tabs li a {
	margin: 0;
	padding: 8px 16px;
	border-radius: 0;
}

ul.nav-tabs li.active a, ul.nav-tabs li.active a:hover {
	color: #fff;
	background: #0088cc;
	border: 1px solid #0088cc;
}

ul.nav-tabs li:first-child a {
	border-radius: 4px 4px 0 0;
}

ul.nav-tabs li:last-child a {
	border-radius: 0 0 4px 4px;
}

ul.nav-tabs.affix {
	top: 30px; /* Set the top position of pinned element */
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#myNav").affix({
			offset : {
				top : 125
			}
		});
	});
	function toUser(){
		$("#adminIframe").attr("src","${ctx}/admin/userList");
	}
	function toNotice(){
// 		$("#adminIframe").attr("src","${ctx}/admin/userList");
	}
	function toActity(){
// 		$("#adminIframe").attr("src","${ctx}/admin/userList");
	}
	function toRole(){
		$("#adminIframe").attr("src","${ctx}/permission/roleList");
	}
	function toGoods(){
		$("#adminIframe").attr("src","${ctx}/goods/list");
	}
</script>
</head>
<body data-spy="scroll" data-target="#myScrollspy">
	<div class="container">
		<div class="jumbotron">
			<h2>GGT后台管理</h2>
		</div>
		<div class="row">
			<div class="col-xs-3" id="myScrollspy">
				<ul class="nav nav-tabs nav-stacked" id="myNav">
					<li><a href="#" onclick="toUser();">用户管理</a></li>
					<li><a href="#" onclick="toNotice();">公告管理</a></li>
					<li><a href="#" onclick="toActity();">活动管理</a></li>
					<li><a href="#" onclick="toRole();">角色管理</a></li>
					<li><a href="#" onclick="toGoods();">物品管理</a></li>
				</ul>
			</div>
			<div class="col-xs-9">
				 <iframe src="${ctx}/admin/userList" id="adminIframe" width="850" height="400">
				 
				 </iframe>
			</div>
		</div>
	</div>
</body>
</html>