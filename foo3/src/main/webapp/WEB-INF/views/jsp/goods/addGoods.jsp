<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/paginationTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>新增物品</title>
<link href="${ctx}/static/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<script src="${ctx}/static/bootstrap/js/jquery.js"></script>
<script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="${ctx}/static/plugins/jstree/themes/classic/style.css">
<script type="text/javascript"
	src="${ctx}/static/plugins/jstree/_lib/jquery.js"></script>
<script type="text/javascript"
	src="${ctx}/static/plugins/jstree/_lib/jquery.cookie.js"></script>
<script type="text/javascript"
	src="${ctx}/static/plugins/jstree/_lib/jquery.hotkeys.js"></script>
<script type="text/javascript"
	src="${ctx}/static/plugins/jstree/jquery.jstree.js"></script>
<script type="text/javascript"
	src="${ctx}/static/bootstrap-fileinput/js/fileinput.min.js"></script>
<link rel="stylesheet"
	href="${ctx}/static/bootstrap-fileinput/css/fileinput.min.css">
<script type="text/javascript">
	$(document).ready(function() {
		$("#addBut").click(function() {
			document.location = "${ctx}/goodsCategory/toAdd";
		});
		initFileInput("file-Portrait1", "${ctx}/goodsCategory/toAdd")
	});
	function initFileInput(ctrlName, uploadUrl) {
		var control = $('#' + ctrlName);

		control.fileinput({
			language : 'zh', //设置语言
			uploadUrl : uploadUrl, //上传的地址
			allowedFileExtensions : [ 'jpg', 'png', 'gif' ],//接收的文件后缀
			showUpload : false, //是否显示上传按钮
			showCaption : false,//是否显示标题
			browseClass : "btn btn-primary", //按钮样式         
			multiple : true,
			previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
			msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
		});
	}
	function selectCode(param) {
		var code = $(param).val();
		// 		alert("code  "+code);
		if (code != '请选择') {
			getSubCodeSelect(code);
		}else{
			$("#subSelect").html("<option>请选择</option>");
		}
	}

	function getSubCodeSelect(pcode) {
		$.ajax({
			url : "${ctx}/goods/getSubGoodsCategoryList/" + pcode,
			dataType : "json",
			type : "GET",
			async : false,
			success : function(data) {
				// 	 alert(data);
				$("#subSelect").html("");
				var option = "<option>请选择</option>";
				$("#subSelect").append(option);
				$(data).each( function(index, element) {
					 option = "<option value='"+element.code+"'>" + element.name + "</option>";
					 $("#subSelect").append(option);
				 });
			},
			error : function(e) {
				alert("sssss" + e);
			}
		});
	}
</script>
</head>
<body>
	新增物品
	<label class="control-lable">选择文件</label> 大类
	<select onchange="selectCode(this)">
		<option>请选择</option>
		<c:forEach items="${pList }" var="goodsCategory">
			<option value="<c:out value='${goodsCategory.code}'/>"><c:out
					value='${goodsCategory.name}' /></option>
		</c:forEach>
	</select> 细类
	<select id="subSelect">
		<option>请选择</option>
	</select>
	<div class="row" style="height: 300px">
		<input id="file-Portrait1" name="files" type="file" multiple>
	</div>
	<form action="<%=request.getContextPath()%>/goodsCategory/add"
		method="post"></form>
</body>
</html>