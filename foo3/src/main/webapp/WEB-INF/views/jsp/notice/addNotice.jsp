<%@ page language="java" contentType="text/html; charset=utf-8" 	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/paginationTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"><html>
  <head>
    <title>新增公告 </title>
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
		initFileInput("file-Portrait1", "${ctx}/goods/addGoodsPic","${ctx}/goods/deleteGoodsPic")
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
			multiple : false,
			previewFileIcon : "<i class='glyphicon glyphicon-king'></i>",
			msgFilesTooMany : "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
		}).on("filebatchselected", function(event, files) {  
            $(this).fileinput("upload");  
        }).on("fileuploaded", function (e, data, previewId, index) {
			var res =  data.response;
	        if (res.state == '1') {
                 var temp= previewId.split("-");
	        	$("#myForm").append("<input type='text' name='imgUrl' picUrl='"+res.url+"' id= '"+temp[1]+"' value='"+res.url+"' />");
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
	 </script>
<script type="text/javascript">
     <script type="text/javascript">
     	$(document).ready(function(){
     		$("#addBut").click(function(){
     			document.location="${ctx}/notice/toAdd";
     		});
	    });
     </script>
  </head>
<body>   
	  新增公告或活动
		<form id="myForm" action="<%=request.getContextPath() %>/notice/add" method="post" >
			名称:<input type="text" name="name"  ></br>
			内容: 
			<textarea rows="3" cols="50" name="conent"></textarea></br>
			发部时间:<input type="text" name="sendDate"></br>
			结束时间:<input type="text" name="endDate"></br>
			公告:<input type="radio" name="type" value="1" checked="checked"> 
			活动:<input type="radio" name="type" value="2"></br>
			发布:<input type="radio" name="state" value="1" checked="checked"></br>
			不发布:<input type="radio" name="state" value="0"></br>
                              图片: <div class="row" style="height: 280px">
					<input id="file-Portrait1"  name="files"  type="file">
				</div><br>
			<input type="submit"/>
	</form>
</body>
</html>