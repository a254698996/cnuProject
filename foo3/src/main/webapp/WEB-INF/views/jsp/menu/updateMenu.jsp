<%@ page language="java" contentType="text/html; charset=utf-8" 	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/paginationTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><html>
  <head>
    <title>菜单更新</title>
    <link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="${ctx}/static/bootstrap/js/jquery.js"></script>
    <script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
  </head>
<body> 
<form class="form-inline" action="${ctx}/menu/update" method="POST">
	  <div class="form-group">
	    <label class="sr-only" for="exampleInputPassword3">名称</label>
	    <input type="text" name="name" class="form-control" value="${menu.name }"  >
	      <label class="sr-only" for="exampleInputPassword3">URL</label>
	   	 <input type="text" name="url" class="form-control"  value="${menu.url }"  >
	    <input type="hidden" name="satte" value="${menu.state }"  >
	     <input type="hidden" name="id" value="${menu.id }"  >
	  </div>
	  <button type="submit" class="btn btn-default">提交</button><br/>
	</form>
</body>
</html>