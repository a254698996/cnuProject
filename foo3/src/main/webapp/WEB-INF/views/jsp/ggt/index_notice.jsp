<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../header.jsp" %>

 <link href="http://www.maliuer.com/Content/Style/Commodity.css" rel="stylesheet" type="text/css" />
 <link href="http://www.maliuer.com/Content/Style/help.css" rel="stylesheet" type="text/css" />
	  <div class="help">
        <div class="map">

        </div>
        <div class="helpleft">
            <div class="title">
                <a href="13.htm">
                 ${notice.name }</a>
            </div>
            <div class="title2">
                发布时间:
               ${notice.sendDate }
               
            </div>
            <div class="title3">
            </div>
            <div class="leftContent">
                <p>
	<img alt="" height="348" src="${ctx }/upload/${notice.imgUrl}" width="679" /></p>
<p>
	<span style="font-size:18px;">物物交换校园代理正式开始招募啦~想创业？想锻炼自己？想让日后找工作的简历多些分量？那就快来看看吧！</span></p>
<p>
	<span style="font-size:18px;">${notice.conent}
	</span></p>
 
            </div>
        </div>
        
    </div>
		<div class="footer">

			<div class="copyright">
				<div class="copyright_inner">
					<img class="foot_logo" src="${ctx}/static/ggt/App_Themes/UI/images/foot_logo.jpg" width="112" height="26" alt=" " />
<!-- 					<div class="sec_nav"> -->
<!-- 						<a href="Help/Detail/2.htm" target="_blank">关于物物交换</a> | -->
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