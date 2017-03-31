<%@ page language="java" contentType="text/html; charset=utf-8" 	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/paginationTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><html>
  <head>
    <title>用户列表</title>
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
<form class="form-inline" action="${ctx}/admin/userList">
	  <div class="form-group">
	    <label class="sr-only" for="exampleInputPassword3">用户名</label>
	    <input type="text" name="sno" class="form-control" value="${param.sno}" placeholder="用户名">
	  </div>
	  <button type="submit" class="btn btn-default">搜索</button><br/>
	</form>
	<div class="table-responsive">
<!--      	<button type="button" class="btn btn-primary" id="openAccBtn">开户</button>&nbsp;&nbsp;&nbsp;&nbsp; -->
       <table class="table  table-hover table-bordered">
         <thead>
           <tr>
             <th>昵称</th>
             <th>用户名</th>
             <th>email</th>
             <th>电话</th>
             <th>学号</th>
             <th>姓名</th>
             <th>状态</th>
             <th>操作</th>
           </tr>
         </thead>
         <tbody>  
           <c:forEach items="${userList}" var="user" varStatus="itr">
				<tr>
					<td>${user.nickname}</td>
                    <td>${user.username}</td>
					<td>${user.email}</td>
                    <td>${user.phone}</td>
					<td>${user.sno}</td>
                    <td>${user.sname}</td>  
                    <td>${user.state}</td>                   
					<td>
						<button type="button" class="btn btn-info btn-xs" onclick="recharge('${user.username}')">禁用</button>
					</td>
				</tr>
			</c:forEach>
         </tbody>
       </table>
       <!-- maxResults=steps -->
		<tag:paginate steps="${steps}" pageIndex= "${pageIndex }" count="${count}"
			uri="${ctx}/user/userList?pageIndex={0}" parms="${parms}" />
     </div>
</body>
</html>