<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../header.jsp" %>

 <link href="http://www.maliuer.com/Content/Style/Commodity.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
        $(function () {
            //菜单栏JS
            var menu = '6';
            GetMenu(menu-5)
           
            $(".oneboxoneright").each(function (a, b) {
                $(b).find("a").each(function (c, d) {
                    if ($(this).attr("date") == $(b).find("input").val()) {
                        $(this).css({ "font-weight": "bold", "color": "#5c9a00" });
                    }
                });
                $(b).find("a").click(function () {
                    $(b).find("input").val($(this).attr("date"));
                    if (a == 0) {
                        $(".oneboxoneright").each(function (e, f) {
                            if (e != 0 && e != $(".oneboxoneright").length - 1) {
                                $(f).find("input").val("");
                            }
                        });
                    } else if (a == 1) {
                        $(".oneboxoneright").each(function (e, f) {
                            if (e == 2) {
                                $(f).find("input").val("");
                            }
                        });
                    }
                    $("form").submit();
                });
            });
            $(".oneboxonefour > a").each(function (a, b) {
                if ($(this).attr("date") == $(".oneboxonefour > input").val()) {
                    $(this).css({ "font-weight": "bold", "color": "#5c9a00" });
                }
                $(b).click(function () {
                    $(".oneboxonefour > input").val($(this).attr("date"));
                    $("form").submit();
                });
            });
            $("#gongqiu >a").each(function (a, b) {
                if ($(b).attr("date") == $("#gongqiu > input").val()) {
                    $(b).css({ "font-weight": "bold", "color": "#5c9a00" });
                }
                $(b).click(function () {
                    $("#gongqiu > input").val($(this).attr("date"));
                    $("form").submit();
                });
            });
            $("#jiage > a").each(function (a, b) {
                if ($(b).attr("min") == $("#minprice").val() && $(b).attr("max") == $("#maxprice").val()) {
                    $(b).css({ "font-weight": "bold", "color": "#5c9a00" });
                }
                $(b).click(function () {
                    $("#one").val("");
                    $("#two").val("");
                    $("#minprice").val($(this).attr("min"));
                    $("#maxprice").val($(this).attr("max"));
                    $("form").submit();
                });
            });
            $("#one").change(function () {
                var reg = /^-?\d+(\.\d+)?$/;
                if (!reg.test($("#one").val())) {
                    $("body").showMessage("您输入的格式错误！");
                    $("#one").val("");
                } else {
                    $("#minprice").val($(this).val());
                }
            });
            $("#two").change(function () {
                var reg = /^-?\d+(\.\d+)?$/;
                if (!reg.test($("#two").val())) {
                    $("body").showMessage("您输入的格式错误！");
                    $("#two").val("");
                } else {
                    $("#maxprice").val($(this).val());
                }
            });
            $("#saixuan").click(function () {
                if ($("#one").val() != "" || $("#two").val() != "") {
                    $("form").submit();
                }
                else {
                    $("body").showMessage("您没有输入筛选条件,请输入筛选条件！");
                }
            });
            $(".titleone").each(function (a, b) {
                if ($(".boxtwotitle >input").val() == $(b).attr("date")) {
                    $(b).removeClass("titleone").addClass("titletwo");
                }
                $(b).click(function () {
                    $(".boxtwotitle >input").val($(this).attr("date"));
                    $("form").submit();
                });
            });
            //图文切换
            $("#tupian").click(function () {
                $("#tuwen").val($(this).attr("date"));
                $("form").submit();
            });
            $("#tw").click(function () {
                $("#tuwen").val($(this).attr("date"));
                $("form").submit();
            });
        });
    </script>
	 <form method="get">
    
    <div class="box">
        <div class="boxone">
            <div class="onebox"> 
            <form action="${ctx }/index/indexList/${categoryId}" method="get"></form>
                <div class="oneboxtwo">
                    <input type="text" name="_SCH_name" class="inputt">
                    <input type="image" src="http://www.maliuer.com/Content/images/search.gif"></div>
                    
            </div>
        </div>
        <div class="boxtwo">
            <div class="boxtwotitle">
<!--                 <input type="hidden" value="0" -->
<!--                     name="ShangJia" /> -->
<!--                 <div class="titleone" date="0"> -->
<!--                     全部</div> -->
<!--                 <div class="titleone" date="1"> -->
<!--                     个人</div> -->
<!--                 <div class="titleone" date="2"> -->
<!--                     商家</div> -->
<!--                 <div class="titlethree"> -->
<!--                     <a href="http://www.maliuer.com/Commodity/SelectXSort.htm?Cid=6">免费发布学习资料信息</a></div> -->
            </div>
        </div>
        <div class="boxthree">
            <div class="boxthreetop">
                <input type="hidden" name="tuwen" id="tuwen" value="0" />
                <div class="boxthreetopleft">
                    <span>搜索结果</span>
                    
<!--                     <img src="http://www.maliuer.com/Content/images/kt.gif" date="1" id="tupian"> -->
<!--                     <img src="http://www.maliuer.com/Content/images/_tw.gif" date="0" id="tw"> -->
                    
                </div>
<!--                 <div class="boxthreetopright"> -->
<!--                     想让信息靠前显示，请<span>置顶</span></div> -->
            </div>
            <!--顶部赞助商广告-->
            <div class="boxthreead"> </div>
            		<c:forEach items="${goodsList }" var="goods">
		                <div class="boxthreetwo">
			                <div class="twoone">
<!-- 			                  	  【供应】  -->
			                    <a href="http://www.maliuer.com/Commodity/Detail/401.htm" target="_blank">
			                      	  ${goods.name }</a> (${goods.goodsCategoryName }-${goods.goodsCategorySubName })<span>(个人)</span>
			                    &nbsp;&nbsp; 
			                </div>
			                <div class="twotwo">
<!-- 			                      <span>   5</span>&nbsp;元 -->
			                </div>
			                <div class="twothree"> ${goods.daysBetween }天前</div>
		            </div>
	           	</c:forEach>    
            <div class="boxthreead"> </div>
            
                    <!-- maxResults=steps -->
		  <tag:paginate steps="${steps}" pageIndex= "${pageIndex }" count="${count}" uri="${ctx}/index/indexList/${categoryId}?pageIndex={0}" parms="${parms}" />
        </div>
    </div>
    </form>
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