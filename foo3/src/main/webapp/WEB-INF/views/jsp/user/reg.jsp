<%@ page language="java" contentType="text/html; charset=utf-8" 	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/paginationTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><html>
  <head>
    <title>用户注册</title>
    <link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="${ctx}/static/bootstrap/js/jquery.js"></script>
    <script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
     	$(document).ready(function(){
     		$("#openAccBtn").click(function(){
     			document.location="${pageContext.request.contextPath}/accManager/openAccReq";
     		});
	    });
 		function del(id) {
 			if (confirm("确认删除吗？")) {
 				document.location="${pageContext.request.contextPath}/user/delete/"+id;
 			}
 		}
     </script>
  </head>
<body>
	<form action="<%=request.getContextPath() %>/user/reg" method="post">
		nickname:<input type="text" name="nickname"></br>
		username:<input type="text" name="username"></br>
		password:<input type="text" name="password"></br>
		email:<input type="text" name="email"></br>
		phone:<input type="text" name="phone"></br>
		sno:<input type="text" name="sno"></br>
		sname:<input type="text" name="sname"></br>
		passwordask:<input type="text" name="passwordask"></br>
	    passwordanswer:<input type="text" name="passwordanswer"></br>
		memo:<input type="text" name="memo"></br>
		<input type="submit"/>
	</form>
</body>
</html>