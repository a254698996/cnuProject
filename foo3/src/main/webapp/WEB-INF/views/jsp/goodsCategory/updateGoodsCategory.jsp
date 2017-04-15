<%@ page language="java" contentType="text/html; charset=utf-8" 	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/paginationTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><html>
  <head>
    <title>更新物品分类</title>
    <link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="${ctx}/static/bootstrap/js/jquery.js"></script>
    <script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${ctx}/static/plugins/jstree/themes/classic/style.css">  
    <script type="text/javascript" src="${ctx}/static/plugins/jstree/_lib/jquery.js"></script>  
    <script type="text/javascript"   src="${ctx}/static/plugins/jstree/_lib/jquery.cookie.js"></script>  
    <script type="text/javascript"     src="${ctx}/static/plugins/jstree/_lib/jquery.hotkeys.js"></script>  
    <script type="text/javascript" src="${ctx}/static/plugins/jstree/jquery.jstree.js"></script>  
    
  </head>
<body>   
	  更新物品分类 
		<form action="<%=request.getContextPath() %>/goodsCategory/update" method="post">
		<input type="hidden" name="id" value="${goodsCategory.id}">
		编码:<input type="text" name="code" value="${goodsCategory.code}"></br>
		名称:<input type="text" name="name"  value="${goodsCategory.name}"></br>
		英文名称:<input type="text" name="ename"  value="${goodsCategory.ename}"></br>
<!-- 		isSub:<input type="text" name="isSub"></br> -->
		<input type="submit" value="提交"/>
	</form>
</body>
</html>