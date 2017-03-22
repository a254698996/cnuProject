<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
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
</body>
</html>