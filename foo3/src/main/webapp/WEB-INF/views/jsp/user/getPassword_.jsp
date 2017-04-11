<%@ page language="java" contentType="text/html; charset=utf-8" 	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/paginationTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><html>
  <head>
    <title>找回密码</title>
    <link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="${ctx}/static/bootstrap/js/jquery.js"></script>
    <script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
     	$(document).ready(function(){
     		//后台返回提示
   			var code=$('#msg').val();
   			if(code!=''){
   				$('#myModal').modal('show');
   			}
     			
	    });
     </script>
  </head>
<body> 
	找回密码
	</br>
	<form action="<%=request.getContextPath() %>/user/getPassword" method="post">
		问题: ${user.passwordask} ?<input  type="hidden" name="id" value="${user.id}"></br>
		回答: <input text="text"name="passwordanswer"></br> 
		新密码:<input text="text"name="newPassword"></br> 
	        重复新密码:<input text="text"name="reNewPassword"></br> 
			<input type="submit" />
	</form>
	
<!-- 模态框（Modal） -->
<input type="hidden" value="${msg }" id="msg">
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">提示</h4>
            </div>
            <div class="modal-body">${msg }</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
	
</body>
</html>