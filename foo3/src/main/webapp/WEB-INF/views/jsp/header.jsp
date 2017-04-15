<%@page import="java.util.List"%>
<%@page import="web.conf.SysInit"%>
<%@page import="org.apache.shiro.session.Session"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/paginationTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<title>大学生物品交换平台</title>
		<meta name="keywords" content="二手货|闲置物品|校园二手|大学生二手交易|大学生闲置物品交易" />
		<meta name="description" content="服务于大学生群体的闲置物品交易平台，在麻溜儿网，您可以便捷、自主、高效的处理身边的闲置物品。" />
		<meta name="copyright" content="麻溜儿 " />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	    <script src="${ctx}/static/bootstrap/js/jquery.js"></script>
<%-- 		<script src="${ctx}/static/ggt/JS/jquery-1.5.1.min.js" type="text/javascript"></script> --%>
		<script src="${ctx}/static/ggt/JS/public.js" type="text/javascript"></script>
		<script src="${ctx}/static/ggt/JS/sM.js" type="text/javascript"></script>
		<link href="${ctx}/static/ggt/App_Themes/Public/PagerStyle.css" rel="stylesheet" type="text/css" />
		<script src="${ctx}/static/ggt/JS/Dialog.js" type="text/javascript"></script>
		<link href="${ctx}/static/ggt/App_Themes/Public/Dialog.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/static/ggt/App_Themes/UI/css/Common.css" rel="stylesheet" type="text/css" />
		<link type="image/x-icon" href="${ctx}/static/ggt/App_Themes/UI/images/favicon.ico" rel="Shortcut Icon">
		<script src="${ctx}/static/ggt/JS/jquery.lazyload.js" type="text/javascript"></script>
	    <link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
   		 <script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript">
			//控制菜单方法
			function GetMenu(id) {
				$(".nav_inner li").each(function(a, b) {
					if(a == id) {
						$(b).find("a").addClass("nav_active");
					} else {
						$(b).find("a").removeClass("nav_active");
					}
				});
			}
			$(function() {

				//图片延迟加载
				$("img").not($(".hd")).lazyload({
					effect: "fadeIn"
				});

				$(".k").each(function(a, b) {
					$(b).click(function() {
						$("#CommTitle").val($(this).text());
					});
					if(a % 2 == 1) {
						$(b).css("color", "#FF7800");
					}
				});
				$("#sousuo").click(function() {
					var title = $("#CommTitle").val();
					if(title.length != 0 && title != '麻溜儿一下你想要的^_^') {
						$("#sform").submit();
					}
				});
				$(".dh").hover(function() {
					$(this).removeClass("dh").addClass("dhhover");
					$(".xiala").show();
				}, function() {
					$(this).removeClass("dhhover").addClass("dh");
					$(".xiala").hide();
				});
			});
		</script>

		<link href="${ctx}/static/ggt/App_Themes/UI/css/index.css" rel="stylesheet" type="text/css" />
		<script src="${ctx}/static/ggt/App_Themes/UI/js/s.js" type="text/javascript"></script>
		<style type="text/css">
			.demo_menu {
				font-size: 14px;
				margin: 10px 0px 0px;
				color: #999;
				line-height: 180%;
				text-align: center;
			}
			
			.demo_menu B {
				color: #333;
			}
			
			.demo_menu A {
				font-size: 14px;
				color: #f00;
			}
			
			.demo_menu A:visited {
				font-size: 14px;
				color: #f00;
			}
			
			.demo_menu A:hover {
				font-size: 14px;
				color: #090;
			}
		</style>
		<script src="${ctx}/static/ggt/App_Themes/UI/js/coin-slider.min.js" type="text/javascript"></script>
		<link href="${ctx}/static/ggt/App_Themes/UI/css/main_banner.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/static/ggt/App_Themes/UI/css/coin-slider-styles.css" type="text/css" rel="stylesheet" />
		<!--[if lte IE 6]>
    <script type="text/javascript" src="${ctx}/static/ggt/App_Themes/UI/js/PNG.js"></script>
    <![endif]-->
		<script type="text/javascript">
			$(function() {
				//控制菜单JS
				GetMenu(0);
				//首页焦点广告
				$('#games').coinslider({
					hoverPause: false
				});
			});
		</script>

	</head>

	<body>
		<div class="status">
			<div class="status_inner">
				<div class="login" style=" text-align:right;">
				<%   if(SecurityUtils.getSubject().getPrincipal()== null){ %>
                   <a href="${ctx}/index/toLogin"><img src="${ctx}/static/ggt/App_Themes/UI/images/login_bg.jpg" /></a>
                  <% }else{ %>
                	  欢迎[<a href="${ctx}/user/owner"><%=SecurityUtils.getSubject().getPrincipal() %></a>] ，<a href="${pageContext.request.contextPath}/user/userLoginOut">退出</a> 
                	  <% } %> 
					<a href="${ctx}/index/toReg"><img src="${ctx}/static/ggt/App_Themes/UI/images/reg_bg.jpg" /></a>
					<div class="myclear">
					</div>
				</div>
				<!--登陆栏目-->

				<!--设置首页-->
				<div class="myclear">
				</div>
			</div>
			<!--status_inner-->
		</div>
		<!--顶部状态栏-->
		<div class="top">
			<div class="top_inner">
				<div class="logo">
					<a href="">
						<img  src="${ctx}/static/ggt/App_Themes/UI/images/foot_logo.jpg" width="263" height="58" alt="麻溜儿" /></a>
				</div>
				<div class="logo_r">
					<div class="guanzhu">
						<span style="float:right;overflow:hidden; height:22px;">
                       
                    </span>
						<span style="float:right;overflow:hidden;height:22px;">
                    </span>
					</div>
					
					<div class="search">
						<div class="search_bar">
							<form action="Commodity/SearchList.htm" name="sform" id="sform">
								<input class="search_bar_input" name="CommTitle" type="text" value="你想要的^_^" onblur="if(!value){value=defaultValue;}" onfocus="if(value==defaultValue){value='';}" id="CommTitle" />
								<input class="search_btn" type="button" value="" id="sousuo" />
							</form>
						</div>
<!-- 						<div class="biaoqian"> -->

<!-- 							<a href="#" class="k">英语四级</a> -->

<!-- 							<a href="#" class="k">牛仔裤</a> -->
 

<!-- 						</div> -->
					</div>
				</div>
				<!--logo右部分-->
				<div class="myclear">
				</div>
			</div>
		</div>
		<!--顶部-->
		<div class="nav">
			<div class="nav_inner">
				<ul>
			  	 <li>
					<a href="${ctx}/index/userIndex" title="首页"><span class="yinwen">Home</span><span class="zhongwen">首页</span></a>
				</li>
				<%  List<Object> goodsCategoryList = SysInit.goodsCategoryList; 
					pageContext.setAttribute("goodsCategoryList", goodsCategoryList);
				%>
				<c:forEach items="${goodsCategoryList }" var="gc" >
				  	 <li>
						<a href="${ctx}/index/indexList/${gc.id}" title="${gc.ename }"><span class="yinwen">${gc.ename }</span><span class="zhongwen">${gc.name }</span></a>
					</li>
				</c:forEach>
<!-- 					<li class="last_nav_li" title="生活超市"> -->
<!-- 						<a href="Commodity/List/12.htm"><span class="yinwen">Supermarket</span><span class="zhongwen">生活超市</span></a> -->
<!-- 					</li> -->
				</ul>
<!-- 				<a class="shangchen" href="http://mall.maliuer.com/" title="商城"></a> -->
			</div>
		</div>
		<!--导航-->
		<div class="nav_sub"></div>