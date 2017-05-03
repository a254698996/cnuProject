<%@ page language="java" contentType="text/html; charset=utf-8" 	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/paginationTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><html>
  <head>
    <title>角色列表</title>
    <link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="${ctx}/static/bootstrap/js/jquery.js"></script>
    <script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
     <script type="text/javascript">
    	$(document).ready(function(){
     		$("#addRoleBtn").click(function(){
     			document.location="${pageContext.request.contextPath}/permission/toAddRole";
     		});
     		$("#addPermissionBtn").click(function(){
     			document.location="${pageContext.request.contextPath}/permission/toAddPermission";
     		});
	    });
 		function deleteRole(id) {
 			 if(confirm("确定要清空数据吗？")) {
 				document.location="${pageContext.request.contextPath}/permission/deleteRole/"+id;
			  } 
 		}
 		function addRole(userId) {
 			$("input[name='currUserId']").val(userId);
 			$.ajax({ url: "${pageContext.request.contextPath}/admin/getUserRole/"+userId,
			     type:"GET",
			     async:false,
			     dataType:"json",
				 success: function(data){
					
					 for (var i = 0; i < data.length; i++) {
// 						 alert("userId "+data[i].userId+"  roleId "+data[i].roleId);
					     var tempUserId=data[i].userId;
					     var tempRoleId=data[i].roleId;
			 			$("input[name='roleIds']").each(function(index,param){
			 				if($(this).val() == tempRoleId){
			 					$(this).attr("checked","checked");
			 				}
// 		 					roleIds.push($(this).val());
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
//  				alert(index+"  "+$(this).val());
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
 		function updatePermission(roleId){
 			var permissions=new Array();
 			$("input[name='permission_"+roleId+"']:checked").each(function(index,param){
 				permissions.push($(this).val());
 			});
 			$.ajax({ url: "${pageContext.request.contextPath}/permission/updatePermission/"+roleId,
			     data:"permissions="+permissions,
			     type:"POST",
			     dataType:"text",
			     async:false,
				 success: function(data){
					 if((data-0) == 1 ){
						 alert("更新权限成功");
					 }else{
						 alert("更新权限失败");
					 }
	             },
	       		error: function(e){
			        	alert(" ajax 更新 权限 失败 "+e);
	       		}
		});
 		}
     </script>
  </head>
<body> 
	<div class="table-responsive">
		<shiro:user> 
<!--      		<button type="button" class="btn btn-primary" id="openAccBtn">开户</button>&nbsp;&nbsp;&nbsp;&nbsp; -->
     	</shiro:user>
<shiro:hasRole name="admin">
	     	<button type="button" class="btn btn-primary" id="addRoleBtn">新增角色</button>&nbsp;&nbsp;&nbsp;&nbsp;
<!-- 	     	<button type="button" class="btn btn-primary" id="addPermissionBtn">新增权限</button>&nbsp;&nbsp;&nbsp;&nbsp; -->
 </shiro:hasRole>
       <table class="table  table-hover table-bordered">
         <thead>
           <tr>
             <th>名称</th>
             <th>描述</th>
             <th>权限</th>
             <th>操作</th>
           </tr>
         </thead>
         <tbody>  
           <c:forEach items="${roleList}" var="role">
				<tr>
					<td>${role.name}</td>
                    <td>${role.remark}</td>
                    <td>
                     <c:forEach items="${pAllSet }" var="pAll">
	                       	 <input type="checkbox"  name="permission_${role.id}"  value="${pAll.id}"
	                       	     <c:forEach items="${rpSet }" var="rp">
					                      <c:if test="${rp.roleId eq role.id}">
					                           <c:if test="${rp.permissionId eq pAll.id}">
					                      			checked="checked"
					                           </c:if>
					                      </c:if>
				                 </c:forEach>
	                       	   >${pAll.remark }&nbsp;&nbsp;
                       </c:forEach>
                    </td>
					<td>
						<button type="button" class="btn btn-info btn-xs" onclick="deleteRole('${role.id}')">删除</button>
						<button type="button" class="btn btn-info btn-xs" onclick="updatePermission('${role.id}')">更新权限</button>
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
                     	    <input type="checkbox" name="roleIds"   value="${role.id}">${role.name }&nbsp;&nbsp;
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