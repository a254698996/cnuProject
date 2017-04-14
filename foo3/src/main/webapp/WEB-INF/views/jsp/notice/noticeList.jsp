<%@ page language="java" contentType="text/html; charset=utf-8" 	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/paginationTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><html>
  <head>
    <title>公告活动列表</title>
    <link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${ctx}/static/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <script src="${ctx}/static/bootstrap/js/jquery.js"></script>
    <script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${ctx}/static/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
	<script type="text/javascript" src="${ctx}/static/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
     <script type="text/javascript">
     	$(document).ready(function(){
     		$("#addBtn").click(function(){
     			document.location="${pageContext.request.contextPath}/notice/toAdd";
     		});
	    });
 		function del(id) {
 			if (confirm("确认删除吗？")) {
 				document.location="${pageContext.request.contextPath}/user/delete/"+id;
 			}
 		}
 		function changeState(id) {
 				document.location="${pageContext.request.contextPath}/notice/changeState/"+id;
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
<form class="form-inline" action="${ctx}/notice/list" method="get">
	  <div class="form-group">
	    <label class="sr-only" for="exampleInputPassword3">名称</label>
	    <input type="text" name="name" class="form-control" value="${param.name}" placeholder="名称">
	     <label class="sr-only" for="exampleInputPassword3">内容</label>
	    <input type="text" name="conent" class="form-control" value="${param.conent}" placeholder="内容">
             <div class="input-group date form_date col-md-5" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                 <input class="form-control" size="16" type="text" value="${param.sendDateParam}" readonly>
                 <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
             </div>
			<input type="hidden" id="dtp_input2" name="sendDateParam" value="${param.sendDateParam}" /><br/>
	  </div>
	  <button type="submit" class="btn btn-default">搜索</button><br/>
	</form>
	<div class="table-responsive">
     	<button type="button" class="btn btn-primary" id="addBtn">新增</button>&nbsp;&nbsp;&nbsp;&nbsp;
       <table class="table  table-hover table-bordered">
         <thead>
           <tr>
             <th>名称</th>
             <th>内容</th>
             <th>开始时间</th>
             <th>结束时间</th>
             <th>类型</th>
             <th>状态</th>
             <th>操作</th>
           </tr>
         </thead>
         <tbody>  
           <c:forEach items="${noticeList}" var="notice" varStatus="itr">
				<tr>
					<td>${notice.name}</td>
                    <td>${notice.conent}</td>
				    <td>${notice.sendDate}</td>
				    <td>${notice.endDate}</td>
				    <td>
				     <c:choose>
						     <c:when test="${notice.type eq 1 }">
						        	公告
						     </c:when>
							 <c:otherwise >
							  		活动
							 </c:otherwise>
						 </c:choose>
				    </td>
				    <td>
				    <c:choose>
						     <c:when test="${notice.state eq 1 }">
						        	启用
						     </c:when>
							 <c:otherwise >
							  		禁用
							 </c:otherwise>
						 </c:choose>
			       </td>
					<td>
						<c:choose>
						     <c:when test="${notice.state eq 1 }">
						      		<button type="button" class="btn btn-info btn-xs" onclick="changeState('${notice.id}')">禁用</button>
						     </c:when>
							 <c:otherwise >
								  <button type="button" class="btn btn-info btn-xs" onclick="changeState('${notice.id}')">启用</button>
							 </c:otherwise>
						 </c:choose>
					</td>
				</tr>
			</c:forEach>
         </tbody>
       </table>
       <!-- maxResults=steps -->
		<tag:paginate steps="${steps}" pageIndex= "${pageIndex }" count="${count}"
			uri="${ctx}/notice/list?pageIndex={0}" parms="${parms}" />
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
    
    <script type="text/javascript">
   
		$('.form_date').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
	    });
		 
    </script>
</body>
</html>