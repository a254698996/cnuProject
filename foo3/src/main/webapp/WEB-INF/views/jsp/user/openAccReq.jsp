<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${ctx}/static/plugins/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<script src="${ctx}/static/plugins/jquery/jquery.min.js"></script>
<script src="${ctx}/static/plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(function () { 
	
	var code=$('#msg').val();
	if(code!=''){
		$('#myModal').modal('show');
	}
	
});
</script>
</head>
<body>
<input type="hidden" value="${msg }" id="msg">
	<div class="container">
		</br>
		<form:form id="inputForm" commandName="acc" action="${ctx}/accManager/openAcc" method="post">
			<div class="row form-group">
				<div class="form-horizontal">
					<label class="col-md-3 control-label text-left">开户行号</label>
					<div class="col-md-5">
						<form:input path="issueno" class="form-control required" />
						<form:errors path="issueno" cssClass="error" />
					</div>
				</div>
			</div>
			<div class="row form-group">
				<div class="form-horizontal">
					<label class="col-md-3 control-label text-left">开户类型号</label>
					<div class="col-md-5">
						<form:input path="acctypeno" class="form-control required" />
						<form:errors path="acctypeno" cssClass="error" />
					</div>
				</div>
			</div>
			<div class="row form-group">
				<div class="form-horizontal">
					<label class="col-md-3 control-label text-left">用户ID</label>
					<div class="col-md-5">
						<form:input path="userid" class="form-control required" />
						<form:errors path="userid" cssClass="error" />
					</div>
				</div>
			</div>
			<div class="row form-group">
				<div class="form-horizontal">
					<label class="col-md-3 control-label text-left">用户名</label>
					<div class="col-md-5">
						<form:input path="username" class="form-control required" />
						<form:errors path="username" cssClass="error" />
					</div>
				</div>
			</div>
			<div class="row form-group">
				<div class="form-horizontal">
					<label class="col-md-3 control-label text-left">身份证号</label>
					<div class="col-md-5">
						<form:input path="useridno" class="form-control required" />
						<form:errors path="useridno" cssClass="error" />
					</div>
				</div>
			</div>
			<div class="row form-group">
				<div class="form-horizontal">
					<div class="col-md-offset-5 col-md-4">
						<button type="submit" class="btn btn-danger" name="opAction"
							value="create">开户</button>
					</div>
				</div>
			</div>
		</form:form>
	</div>
	
			<!-- 模态框（Modal） -->
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