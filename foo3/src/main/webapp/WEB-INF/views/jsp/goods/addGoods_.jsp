<%@page import="web.conf.SysInit"%>
<%@page import="java.util.List"%>
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
		initFileInput("file-Portrait1", "${ctx}/goods/addGoodsPic","${ctx}/goods/deleteGoodsPic");
 		//后台返回提示
			var code=$('#msg').val();
			if(code!=''){
				$('#myModal').modal('show');
			}
	});
	function initFileInput(ctrlName, uploadUrl,deleteUrl) {
		var control = $('#' + ctrlName);

		control.fileinput({
			language : 'zh', //设置语言
			uploadUrl : uploadUrl, //上传的地址
			deleteUrl :deleteUrl,
			allowedFileExtensions : [ 'jpg', 'png', 'gif' ],//接收的文件后缀
			showUpload : false, //是否显示上传按钮
			showCaption : false,//是否显示标题
			browseClass : "btn btn-primary", //按钮样式         
			multiple : true,
			previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
			msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
		}).on("filebatchselected", function(event, files) {  
            $(this).fileinput("upload");  
        }).on("fileuploaded", function (e, data, previewId, index) {
			var res =  data.response;
	        if (res.state == '1') {
                 var temp= previewId.split("-");
	        	$("#myForm").append("<input type='text' name='goodsPicIds' picUrl='"+res.url+"' id= '"+temp[1]+"' value='"+res.id+"' />");
	        }
	        else {
	            alert('上传失败')
	        }
// 	    	alert("上传 previewId  : "+previewId+",   index  : "+index);
	    }).on("filesuccessremove", function(event, data, previewId, index) {
	        var temp= data.split("-");
	        var goodsPicObj =$('#' + temp[1]);
	    	var goodsPicId =goodsPicObj.val();
	    	var picUrl=$(goodsPicObj).attr("picUrl");
// 	    	alert(" goodsPicId "+goodsPicId+"  , picUrl  "+picUrl +"  , goodsPicObj  "+$(goodsPicObj).val());
	    	
	    	$.ajax({ url: "${ctx}/goods/deleteGoodsPic", data:"picUrl="+picUrl+"&key="+goodsPicId ,  type:"POST", async:true,
	    		success: function(data){
	    			goodsPicObj.remove();
	          },
	          error:function(e){
	        	  alert(e);
	          }
	    	});
	    });
	}
	
	function selectCode(param) {
		var code = $(param).val();
		if (code != '请选择') {
			getSubCodeSelect(code);
		} else {
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
				$(data).each(
					function(index, element) {
						option = "<option value='"+element.code+"'>" + element.name + "</option>";
						$("#subSelect").append(option);
					});
			},
			error : function(e) {
				alert("上传出错: " + e);
			}
		});
	}
</script>
</head>
<body>
	新增物品 
	<form id="myForm" action="<%=request.getContextPath()%>/goods/addGoods" method="post"  enctype="multipart/form-data">
	   名称<input type="text" name="name" ><br>
		<label class="control-lable">大类</label>
		  <select onchange="selectCode(this)" name="goodsCategoryCode">
			<option>请选择</option>
			   <% List<Object> goodsCategoryList = SysInit.goodsCategoryList;  
					pageContext.setAttribute("goodsCategoryList", goodsCategoryList); 	%>
			<c:forEach items="${goodsCategoryList }" var="goodsCategory">
				 <option value="<c:out value='${goodsCategory.code}'/>">
					<c:out value='${goodsCategory.name}' />
			     </option>
			</c:forEach>
		  </select>
	  <label class="control-lable">细类</label> 
		 <select id="subSelect" name="goodsCategorySubCode">
			<option>请选择</option>
		</select>
		<div class="row" style="height: 300px">
			<input id="file-Portrait1"  name="files"  type="file" multiple>
		</div>
		<input type="submit" id="submitBut" />
	</form>
	<!-- 模态框（Modal） -->
<input type="hidden" value="${msg}" id="msg">
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">提示</h4>
            </div>
            <div class="modal-body" id="pageMsg" >${msg}</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>