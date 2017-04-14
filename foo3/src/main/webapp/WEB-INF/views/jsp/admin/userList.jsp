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
 		function changeUserState(id) {
 				document.location="${pageContext.request.contextPath}/admin/changeUserState/"+id;
 		}
 		function addRole(userId) {
 			$("input[name='currUserId']").val(userId);
 			$("input[type='checkbox']").each(function(index,param){
				 $(this).prop('checked',false);
			});
 			$.ajax({ url: "${pageContext.request.contextPath}/admin/getUserRole/"+userId,
			     type:"GET",
			     async:false,
			     dataType:"json",
				 success: function(data){
					 for (var i = 0; i < data.length; i++) {
					     var tempUserId=data[i].userId;
					     var tempRoleId=data[i].roleId;
			 			$("input[type='checkbox']").each(function(index,param){
			 				if($(this).val() == tempRoleId){
// 			 					 alert("  比较值  tempUserId "+tempUserId+"  tempRoleId "+tempRoleId+"   $(this).val()  "+$(this).val());
			 					 $(this).prop('checked',true);
			 				}
		 				});
					 }
	             },
	       		error: function(e){
			        	alert(" ajax 更新 角色 失败 "+e);
	       		}
			});
 			$('#myModal').modal('show');
 		}
 		function submitUpdateRole(){
 			var userId=$("input[name='currUserId']").val();
 			var roleIds=new Array();
 			$("input[name='roleIds']:checked").each(function(index,param){
 				roleIds.push($(this).val());
 			});
 			$.ajax({ url: "${pageContext.request.contextPath}/admin/updateRole/"+userId,
 				     data:"roleIds="+roleIds,
 				     type:"POST",
 				     dataType:"text",
 				     async:false,
 					 success: function(data){
 						 if((data-0) == 1 ){
	 						 alert("更新角色成功");
 						 }else{
 							 alert("更新角色失败");
 						 }
 		             },
 		       		error: function(e){
  			        	alert(" ajax 更新 角色 失败 "+e);
 		       		}
 			});
 		}
     </script>
  </head>
<body> 
<form class="form-inline" action="${ctx}/admin/userList">
	  <div class="form-group">
	    <label class="sr-only" for="exampleInputPassword3">学号</label>
	    <input type="text" name="sno" class="form-control" value="${param.sno}" placeholder="学号">
	     <label class="sr-only" for="exampleInputPassword3">用户名</label>
	    <input type="text" name="username" class="form-control" value="${param.username}" placeholder="用户名">
	     <label class="sr-only" for="exampleInputPassword3">姓名</label>
	    <input type="text" name="sname" class="form-control" value="${param.sname}" placeholder="姓名">
	  </div>
	  <button type="submit" class="btn btn-default">搜索</button><br/>
	</form>
	<div class="table-responsive">
<!--      	<button type="button" class="btn btn-primary" id="openAccBtn">开户</button>&nbsp;&nbsp;&nbsp;&nbsp; -->
       <table class="table  table-hover table-bordered">
         <thead>
           <tr>
<!--              <th>昵称</th> -->
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
<%-- 					<td>${user.nickname}</td> --%>
                    <td>${user.username}</td>
					<td>${user.email}</td>
                    <td>${user.phone}</td>
					<td>${user.sno}</td>
                    <td>${user.sname}</td>  
                    <td>
                    	<c:choose>
						     <c:when test="${user.state eq 1 }">
						        	正常
						     </c:when>
							 <c:otherwise >
							  		锁定
							 </c:otherwise>
						 </c:choose>
                     </td>                   
					<td>
						<c:choose>
						     <c:when test="${user.state eq 1 }">
						      		<button type="button" class="btn btn-info btn-xs" onclick="changeUserState('${user.id}')">锁定</button>
						     </c:when>
							 <c:otherwise >
								  <button type="button" class="btn btn-info btn-xs" onclick="changeUserState('${user.id}')">正常</button>
							 </c:otherwise>
						 </c:choose>
						
						<button type="button" class="btn btn-info btn-xs" onclick="addRole('${user.id}')">更新角色</button>
					</td>
				</tr>
			</c:forEach>
         </tbody>
       </table>
       <!-- maxResults=steps -->
		<tag:paginate steps="${steps}" pageIndex= "${pageIndex }" count="${count}"
			uri="${ctx}/user/userList?pageIndex={0}" parms="${parms}" />
     </div>
     		<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">提示</h4>
            </div>
            <div class="modal-body">
                     	 <c:forEach items="${roleList }" var="role">
                     	    <input type="checkbox" name="roleIds"   value="${role.id}">${role.remark }&nbsp;&nbsp;
                     	 </c:forEach>
                     	 <input type="hidden" name="currUserId" />
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" onclick="submitUpdateRole();"  data-dismiss="modal">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</body>
</html>