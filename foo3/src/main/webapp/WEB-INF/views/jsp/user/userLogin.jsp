<%@ page language="java" contentType="text/html; charset=utf-8" 	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/paginationTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><html>
  <head>
    <title>用户登陆</title>
    <link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="${ctx}/static/bootstrap/js/jquery.js"></script>
    <script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript">
     	$(document).ready(function(){
     		$("#toGetPasswordBut").click(function(){
     			var username=$("input[name='username']").val();
     			if(username==''){
     				$('.popover-show').attr('data-content','没有输入用户名');
     				$('.popover-show').popover('show');
     				return ;
     			}
     			$.ajax({ url: "${pageContext.request.contextPath}/user/userExist?username="+username, 
     				     type:"post",async:false ,dataType:"json",
     				     success: function(data){
     				    	var dataObj=eval("("+data+")");
     				    	 if(dataObj.exist==true){
     				    		var url="${pageContext.request.contextPath}/user/toGetPassword?username="+username;
     			     			document.location=url;
     				    	 }else{
     				    		$('.popover-show').attr('data-content','用户名不正确');
     		     				$('.popover-show').popover('show');
     				    	 }
     				    	 return ;
     				},
     				error:function(e){
     					 alert(e);
     				}
     			});
     		});
	    });
     </script>
  </head>
<body> 
<form class="form-horizontal" action="<%=request.getContextPath() %>/user/userLogin" method="post" role="form">
  <div class="form-group">
    <label for="firstname" class="col-md-3 control-label">用户名</label>
    <div class="col-md-5">
      <input type="text" name="username" class="form-control" id="firstname" placeholder="请输入用户名">
    </div>
  </div>
  <div class="form-group">
    <label for="lastname" class="col-md-3 control-label">密码</label>
    <div class="col-md-5">
      <input type="text" name="password" class="form-control" id="lastname" placeholder="请输入密码">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox">请记住我
        </label>
      </div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-10">
      <button type="submit" class="btn btn-default">登录</button>
	    <button type="button" class="btn btn-primary popover-show"
	            title="错误提示" data-container="body"
	            data-toggle="popover" 
	            data-content="没有输入密码"
	            id="toGetPasswordBut"  >
	  		          找回密码
	    </button>
     </div>
  </div>
</form>
	
</body>
</html>