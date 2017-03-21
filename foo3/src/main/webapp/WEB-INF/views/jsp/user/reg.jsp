<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
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