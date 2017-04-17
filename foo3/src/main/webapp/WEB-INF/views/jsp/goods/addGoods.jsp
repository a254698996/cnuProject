<%@page import="web.conf.SysInit"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../header.jsp" %>
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
	        	$("#myForm").append("<input type='hidden' name='goodsPicIds' picUrl='"+res.url+"' id= '"+temp[1]+"' value='"+res.id+"' />");
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
// 				alert("上传出错: " + e);
			}
		});
	}
</script>
		 <label class="control-lable">新增物品 </label>
	<form id="myForm" action="<%=request.getContextPath()%>/goods/addGoods" method="post"  enctype="multipart/form-data">
	   <label class="control-lable">名称</label><input type="text" name="name" ><br>
	   <label class="control-lable">交易地点</label><input type="text" name="exchangeAddress" ><br>
	   <label class="control-lable">物品描述</label><br>
	   <textarea rows="3" cols="80"name="goodsDesc" ></textarea><br>
		<label class="control-lable">大类</label>
		  <select onchange="selectCode(this)" name="goodsCategoryCode">
			<option>请选择</option>
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
		<br><br><br><br>
		<input type="submit" id="submitBut" />
	</form>

		<div class="footer">

			<div class="copyright">
				<div class="copyright_inner">
					<img class="foot_logo" src="${ctx}/static/ggt/App_Themes/UI/images/foot_logo.jpg" width="112" height="26" alt=" " />
<!-- 					<div class="sec_nav"> -->
<!-- 						<a href="Help/Detail/2.htm" target="_blank">关于麻溜儿</a> | -->
<!-- 						<a href="Help/Detail/3.htm" target="_blank">联系我们</a> | -->
<!-- 						<a href="Help/Detail/4.htm" target="_blank">商家合作</a> | -->
<!-- 						<a href="Help/JianYi.htm" target="_blank">意见反馈</a> | -->
<!-- 						<a href="Help/index.htm" target="_blank">帮助中心</a> -->
<!-- 					</div> -->
				</div>
<!-- 				<p style=" text-align:center;"> -->
<!-- 					<script src="http://s5.cnzz.com/stat.php?id=3696939&web_id=3696939&show=pic" language="JavaScript"></script> -->
<!-- 				</p> -->
			</div>
		</div>

		<style>
			.fixedBtn {
				position: fixed;
				right: 0;
				top: 230px;
				width: 36px;
				height: 178px;
			}
			
			* html .fixedBtn {
				position: absolute;
				bottom: auto;
				top: expression(eval(document.documentElement.scrollTop)+230);
			}
			
			.fixedBtn .feedback {
				display: inline-block;
				text-indent: -2000px;
				background: url('${ctx}/static/ggt/App_Themes/UI/images/fankui.png');
				width: 31px;
				height: 104px;
				position: absolute;
				left: 0;
				top: 0;
			}
			
			.fixedBtn .feedback:hover {
				background-position: -31px 0;
			}
			
			.fixedBtn .tops {
				text-indent: -2000px;
				background: url('${ctx}/static/ggt/App_Themes/UI/images/celan_top.png');
				width: 31px;
				height: 74px;
				position: absolute;
				left: 0;
				bottom: 0;
			}
			
			.fixedBtn .top:hover {
				background-position: -31px 0;
			}
		</style>
		<script type="text/javascript">
			$(function() {
				$("#toptop").hide();
				$(window).scroll(function() {
					if(parseFloat($(window).scrollTop()) > 200) {
						$("#toptop").show();
					} else {
						$("#toptop").hide();
					}
				});
			});
		</script>
		<!-- footer end -->

	</body>

	<script type="text/javascript">
		function setTValue(key, value) {
			$("input[name='" + key + "']").attr("value", value);
		}

		function setRadioValue(key, value) {
			$("input[name='" + key + "'][value='" + value + "']").attr("checked", 'checked');
		}

		function setSelectValue(key, value) {
			$("select[name='" + key + "'] option:[value='" + value + "']").attr("selected", "selected");
		}
		//function setTextAValue(key, value) { $("textarea[name='" + key + "']").html(value); }
		var myValues = new Array();
		var EvalString = "";

		eval(EvalString);
		$(
			function() {
				for(var i = 0; i < myValues.length; i++) {
					var key = myValues[i][0];
					var value = myValues[i][1];
					value = value.replace(/@_@/g, "'");
					value = value.replace(/#_#/g, "\"");
					value = value.replace(/&_&/g, "\\");

					try {
						var type = $("input[name='" + key + "']").attr("type");
						switch(type.toLowerCase()) {
							case "text":
								setTValue(key, value);
								break;
							case "hidden":
								setTValue(key, value);
								break;
							case "password":
								setTValue(key, value);
								break;
							case "file":
								setTValue(key, value);
								break;
							case "radio":
								setRadioValue(key, value);
								break;
							case "checkbox":
								setRadioValue(key, value);
								break;
						}
					} catch(ce) {
						try {
							setSelectValue(key, value);
						} catch(cee) {}
					}
				}
			}
		);
	</script>

</html>

<!--提示信息-->
<script type="text/javascript">
	var msg = '';
	if(msg == "") {
		msg += '';
	}
	if(msg != "") {
		$("body").showMessage(msg);
	}
</script>
<!--防止重复提交-->
<script type="text/javascript">
	$(function() {
		var R_hidd = '';
		if(R_hidd != "") {
			$("form").each(function(i, v) {
				if($(v).attr("target") != "_blank") {
					var hidd = "<input type='hidden' notsavevalue='true'  name='___R_hidd' value='" + R_hidd + "' />";
					$(v).append(hidd);
				}
			});
		}
	});
</script>