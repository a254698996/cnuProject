<%@ page language="java" contentType="text/html; charset=utf-8" 	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/paginationTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><html>
  <head>
    <title>新增物品子分类 </title>
    <link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="${ctx}/static/bootstrap/js/jquery.js"></script>
    <script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${ctx}/static/plugins/jstree/themes/classic/style.css">  
    <script type="text/javascript" src="${ctx}/static/plugins/jstree/_lib/jquery.js"></script>  
    <script type="text/javascript"   src="${ctx}/static/plugins/jstree/_lib/jquery.cookie.js"></script>  
    <script type="text/javascript"     src="${ctx}/static/plugins/jstree/_lib/jquery.hotkeys.js"></script>  
    <script type="text/javascript" src="${ctx}/static/plugins/jstree/jquery.jstree.js"></script>  
    
     <script type="text/javascript">
     	$(document).ready(function(){
     		$("#addBut").click(function(){
     			document.location="${ctx}/goodsCategory/toAdd";
     		});
	    });
     </script>
  </head>
<body>   
	  新增物品子分类 
		<form action="<%=request.getContextPath() %>/goodsCategory/addSub" method="post">
			pcode:<input type="text" name="pcode" readonly="readonly" value="${pcode}" ></br>
			code:<input type="text" name="code"></br>
			name:<input type="text" name="name"></br>
<!-- 			isSub:<input type="text" name="isSub"></br> -->
			<input type="submit"/>
	</form>
</body>
</html>