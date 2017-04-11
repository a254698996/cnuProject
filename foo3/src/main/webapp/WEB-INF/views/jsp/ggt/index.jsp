<%@page import="org.apache.shiro.session.Session"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tag" uri="/WEB-INF/taglibs/paginationTaglib.tld"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

	<head>
		<title>GGT</title>
		<meta name="keywords" content="二手货|闲置物品|校园二手|大学生二手交易|大学生闲置物品交易" />
		<meta name="description" content="服务于大学生群体的闲置物品交易平台，在麻溜儿网，您可以便捷、自主、高效的处理身边的闲置物品。" />
		<meta name="copyright" content="麻溜儿 " />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script src="${ctx}/static/ggt/JS/jquery-1.5.1.min.js" type="text/javascript"></script>
		<script src="${ctx}/static/ggt/JS/public.js" type="text/javascript"></script>
		<script src="${ctx}/static/ggt/JS/sM.js" type="text/javascript"></script>
		<link href="${ctx}/static/ggt/App_Themes/Public/PagerStyle.css" rel="stylesheet" type="text/css" />
		<script src="${ctx}/static/ggt/JS/Dialog.js" type="text/javascript"></script>
		<link href="${ctx}/static/ggt/App_Themes/Public/Dialog.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/static/ggt/App_Themes/UI/css/Common.css" rel="stylesheet" type="text/css" />
		<link type="image/x-icon" href="${ctx}/static/ggt/App_Themes/UI/images/favicon.ico" rel="Shortcut Icon">
		<script src="${ctx}/static/ggt/JS/jquery.lazyload.js" type="text/javascript"></script>
		<script type="text/javascript">
			//控制菜单方法
			function GetMenu(id) {
				$(".nav_inner li").each(function(a, b) {
					if(a == id) {
						$(b).find("a").addClass("nav_active");
					} else {
						$(b).find("a").removeClass("nav_active");
					}
				});
			}
			$(function() {

				//图片延迟加载
				$("img").not($(".hd")).lazyload({
					effect: "fadeIn"
				});

				$(".k").each(function(a, b) {
					$(b).click(function() {
						$("#CommTitle").val($(this).text());
					});
					if(a % 2 == 1) {
						$(b).css("color", "#FF7800");
					}
				});
				$("#sousuo").click(function() {
					var title = $("#CommTitle").val();
					if(title.length != 0 && title != '麻溜儿一下你想要的^_^') {
						$("#sform").submit();
					}
				});
				$(".dh").hover(function() {
					$(this).removeClass("dh").addClass("dhhover");
					$(".xiala").show();
				}, function() {
					$(this).removeClass("dhhover").addClass("dh");
					$(".xiala").hide();
				});
			});
		</script>

		<link href="${ctx}/static/ggt/App_Themes/UI/css/index.css" rel="stylesheet" type="text/css" />
		<script src="${ctx}/static/ggt/App_Themes/UI/js/s.js" type="text/javascript"></script>
		<style type="text/css">
			.demo_menu {
				font-size: 14px;
				margin: 10px 0px 0px;
				color: #999;
				line-height: 180%;
				text-align: center;
			}
			
			.demo_menu B {
				color: #333;
			}
			
			.demo_menu A {
				font-size: 14px;
				color: #f00;
			}
			
			.demo_menu A:visited {
				font-size: 14px;
				color: #f00;
			}
			
			.demo_menu A:hover {
				font-size: 14px;
				color: #090;
			}
		</style>
		<script src="${ctx}/static/ggt/App_Themes/UI/js/coin-slider.min.js" type="text/javascript"></script>
		<link href="${ctx}/static/ggt/App_Themes/UI/css/main_banner.css" type="text/css" rel="stylesheet" />
		<link href="${ctx}/static/ggt/App_Themes/UI/css/coin-slider-styles.css" type="text/css" rel="stylesheet" />
		<!--[if lte IE 6]>
    <script type="text/javascript" src="${ctx}/static/ggt/App_Themes/UI/js/PNG.js"></script>
    <![endif]-->
		<script type="text/javascript">
			$(function() {
				//控制菜单JS
				GetMenu(0);
				//首页焦点广告
				$('#games').coinslider({
					hoverPause: false
				});
			});
		</script>

	</head>

	<body>
		<div class="status">
			<div class="status_inner">
				<div class="login" style=" text-align:right;">
				<%   if(SecurityUtils.getSubject().getPrincipal()== null){ %>
                   <a href="${ctx}/user/toLogin"><img src="${ctx}/static/ggt/App_Themes/UI/images/login_bg.jpg" /></a>
                  <% }else{ %>
                	  欢迎[<%=SecurityUtils.getSubject().getPrincipal() %>]登录，<a href="${pageContext.request.contextPath}/user/userLoginOut">退出</a> 
                	  <% } %> 
					<a href="${ctx}/user/toReg"><img src="${ctx}/static/ggt/App_Themes/UI/images/reg_bg.jpg" /></a>
					<div class="myclear">
					</div>
				</div>
				<!--登陆栏目-->

				<!--设置首页-->
				<div class="myclear">
				</div>
			</div>
			<!--status_inner-->
		</div>
		<!--顶部状态栏-->
		<div class="top">
			<div class="top_inner">
				<div class="logo">
					<a href="">
						<img src="${ctx}/static/ggt/App_Themes/UI/images/logo.gif" width="263" height="58" alt="麻溜儿" /></a>
				</div>
				<div class="logo_r">
					<div class="guanzhu">
						<span style="float:right;overflow:hidden; height:22px;">
                       
                    </span>
						<span style="float:right;overflow:hidden;height:22px;">
                    </span>
					</div>
					
					<div class="search">
						<div class="search_bar">
							<form action="Commodity/SearchList.htm" name="sform" id="sform">
								<input class="search_bar_input" name="CommTitle" type="text" value="你想要的^_^" onblur="if(!value){value=defaultValue;}" onfocus="if(value==defaultValue){value='';}" id="CommTitle" />
								<input class="search_btn" type="button" value="" id="sousuo" />
							</form>
						</div>
<!-- 						<div class="biaoqian"> -->

<!-- 							<a href="#" class="k">英语四级</a> -->

<!-- 							<a href="#" class="k">牛仔裤</a> -->

<!-- 							<a href="#" class="k">苹果</a> -->

<!-- 							<a href="#" class="k">墨镜</a> -->

<!-- 							<a href="#" class="k">面膜</a> -->

<!-- 							<a href="#" class="k">影集</a> -->

<!-- 							<a href="#" class="k">数字油画</a> -->

<!-- 						</div> -->
					</div>
				</div>
				<!--logo右部分-->
				<div class="myclear">
				</div>
			</div>
		</div>
		<!--顶部-->
		<div class="nav">
			<div class="nav_inner">
				<ul>
			  	 <li>
					<a href="${ctx}/user/userIndex" title="首页"><span class="yinwen">Home</span><span class="zhongwen">首页</span></a>
				</li>
				<c:forEach items="${goodsCategoryList }" var="gc">
				  	 <li>
						<a href="${ctx}/user/userIndex" title="${gc.ename }"><span class="yinwen">${gc.ename }</span><span class="zhongwen">${gc.name }</span></a>
					</li>
				</c:forEach>
<!-- 					<li class="last_nav_li" title="生活超市"> -->
<!-- 						<a href="Commodity/List/12.htm"><span class="yinwen">Supermarket</span><span class="zhongwen">生活超市</span></a> -->
<!-- 					</li> -->
				</ul>
<!-- 				<a class="shangchen" href="http://mall.maliuer.com/" title="商城"></a> -->
			</div>
		</div>
		<!--导航-->
		<div class="nav_sub"></div>

		<!--导航底部-->
		<div class="main_banner_outter">
			<div class="main_banner">
				<div id="page">
					<div id="gamesHolder">
						<div id="games" style="overflow: hidden;">

							<a href="http://www.maliuer.com/Home/Detail/13.htm" target="_blank">
								<img src="${ctx}/upload/2012-05-03/2012050316115003848049.jpg" class="hd"><span><b>麻溜儿网校园代理火热招募中…</b></span></a>

							<a href="http://www.maliuer.com/LuckDraw/Index.htm" target="_blank">
								<img src="${ctx}/upload/2012-05-22/2012052221044338315841.jpg" class="hd"><span><b>即日起发布闲置物品，即可参与幸运大抽奖活动！轻轻点击入场吧！</b></span></a>

							<a href="http://www.maliuer.com" target="_blank">
								<img src="${ctx}/upload/2012-03-12/2012031214490142631528.jpg" class="hd"><span><b>挖掘寝室新价值，引领节约新风潮！</b></span></a>

							<a href="http://www.maliuer.com" target="_blank">
								<img src="${ctx}/upload/2012-03-12/2012031214490144202186.jpg" class="hd"><span><b>麻溜儿网正式开放测试，欢迎您提出宝贵意见，我们将虚心聆听！</b></span></a>

							<a href="http://www.maliuer.com/Login/Login.htm" target="_blank">
								<img src="${ctx}/upload/2012-03-12/2012031214490150453772.jpg" class="hd"><span><b>身边的人都已经加入麻溜儿啦！身为大学生一员的你还在等什么？快来加入吧！</b></span></a>

							<a href="http://mall.maliuer.com" target="_blank">
								<img src="${ctx}/upload/2012-03-12/2012031214503639874276.jpg" class="hd"><span><b>麻溜儿商城，打造大学生自己的精品商城，现在下单即有精美礼品赠送哦！</b></span></a>

							<a href="http://www.maliuer.com/Login/Login.htm" target="_blank">
								<img src="${ctx}/upload/2012-03-12/2012031214552472234842.jpg" class="hd"><span><b>2012登船集结号！赶快卖了闲置物品，攒钱买船票吧！</b></span></a>

							<a href="http://www.maliuer.com" target="_blank">
								<img src="${ctx}/upload/2012-03-12/2012031214560295829234.jpg" class="hd"><span><b>挖掘寝室新价值，引领节约新风潮！</b></span></a>

						</div>
					</div>
				</div>
			</div>
			<!--main_banner-->
			<div class="main_banner_r">
				<ul>
                     <c:forEach items="${noticeList }" var="notice">
	                     <li> <a class="ggao" href="Home/Detail/13.htm">
								${notice.conent }</a><span class="gonggao_date">
								[ <fmt:formatDate type="date"  value="${notice.sendDate }" />]
								</span></li>
						 <li>
                     </c:forEach>
<!-- 					<li> -->
<!-- 						<a class="ggao" href="Home/Detail/13.htm"> -->
<!-- 							校园代理招募活动火热启动</a><span class="gonggao_date">[2012-05-03]</span></li> -->

<!-- 					<li> -->
<!-- 						<a class="ggao" href="Home/Detail/10.htm"> -->
<!-- 							麻溜儿商城强势回归</a><span class="gonggao_date">[2012-04-25]</span></li> -->

<!-- 					<li> -->
<!-- 						<a class="ggao" href="Home/Detail/5.htm"> -->
<!-- 							麻溜儿网正式上线开放测试</a><span class="gonggao_date">[2012-03-12]</span></li> -->

				</ul>
				<div class="new_person">
					<a href="Help/index.htm" target="_blank">
					 </a>
				</div>
			</div>
			<!--main_banner_r-->
			<div class="myclear">
			</div>
		</div>
		<!--主banner外体-->
		<div class="main_banner_outter_sub">
		</div>
		<!--主banner底部-->
		<div class="outer_body">
			<!--精品推荐开始-->
			<div class="recommend">
				<h1>
                <a class="rec_more">
                    <img src="${ctx}/static/ggt/App_Themes/UI/images/rec_more.jpg" width="100" height="88" alt="more" /></a>
            </h1>
				<div class="rec_b">
					<ul>

						<li>
							<a href="Commodity/Detail/593.htm" class="rec_tu">
								<img src="http://pic.maliuer.com/UploadFolder/2012-05-14/2012051416253736584636_150_180.jpg?a=1" /></a>
							<h2>
                            <a href="Commodity/Detail/593.htm">
                                时尚流苏两用包</a>
                        </h2>
							<span class="rec_date">发布日期:[2012-05-14]</span>
							<span class="rec_prince">
                            ￥10
                        </span>
						</li>

						<li>
							<a href="Commodity/Detail/595.htm" class="rec_tu">
								<img src="http://pic.maliuer.com/UploadFolder/2012-05-14/2012051416344079294068_150_180.jpg?a=1" /></a>
							<h2>
                            <a href="Commodity/Detail/595.htm">
                                青春 韩寒原著</a>
                        </h2>
							<span class="rec_date">发布日期:[2012-05-14]</span>
							<span class="rec_prince">
                            ￥10
                        </span>
						</li>

						<li>
							<a href="Commodity/Detail/598.htm" class="rec_tu">
								<img src="http://pic.maliuer.com/UploadFolder/2012-05-15/2012051516390481531590_150_180.jpg?a=1" /></a>
							<h2>
                            <a href="Commodity/Detail/598.htm">
                                EOS香草豆蔻米色唇...</a>
                        </h2>
							<span class="rec_date">发布日期:[2012-05-15]</span>
							<span class="rec_prince">
                            ￥29
                        </span>
						</li>

						<li>
							<a href="Commodity/Detail/498.htm" class="rec_tu">
								<img src="http://pic.maliuer.com/UploadFolder/2012-04-13/2012041319592044865001_150_180.jpg?a=1" /></a>
							<h2>
                            <a href="Commodity/Detail/498.htm">
                                sgirl正品春款糖果...</a>
                        </h2>
							<span class="rec_date">发布日期:[2012-04-13]</span>
							<span class="rec_prince">
                            ￥170
                        </span>
						</li>

						<div class="myclear"></div>
						<div class="xuxian"></div>

						<li>
							<a href="Commodity/Detail/561.htm" class="rec_tu">
								<img src="http://pic.maliuer.com/UploadFolder/2012-05-05/2012050523011063552304_150_180.JPG?a=1" /></a>
							<h2>
                            <a href="Commodity/Detail/561.htm">
                                夏普sh81iuc 智能...</a>
                        </h2>
							<span class="rec_date">发布日期:[2012-05-05]</span>
							<span class="rec_prince">
                            ￥750
                        </span>
						</li>

						<li>
							<a href="Commodity/Detail/501.htm" class="rec_tu">
								<img src="http://pic.maliuer.com/UploadFolder/2012-04-15/2012041515071630352239_150_180.jpg?a=1" /></a>
							<h2>
                            <a href="Commodity/Detail/501.htm">
                                薰衣草星星玻璃罐</a>
                        </h2>
							<span class="rec_date">发布日期:[2012-09-06]</span>
							<span class="rec_prince">
                            ￥5
                        </span>
						</li>

						<li>
							<a href="Commodity/Detail/560.htm" class="rec_tu">
								<img src="http://pic.maliuer.com/UploadFolder/2012-05-19/2012051909213128417560_150_180.JPG?a=1" /></a>
							<h2>
                            <a href="Commodity/Detail/560.htm">
                                正品opi悦诗风吟爱...</a>
                        </h2>
							<span class="rec_date">发布日期:[2012-06-20]</span>
							<span class="rec_prince">
                            ￥25
                        </span>
						</li>

						<li>
							<a href="Commodity/Detail/142.htm" class="rec_tu">
								<img src="http://pic.maliuer.com/UploadFolder/2012-03-14/2012031418241314332530_150_180.jpg?a=1" /></a>
							<h2>
                            <a href="Commodity/Detail/142.htm">
                                ups路由 猫  后备...</a>
                        </h2>
							<span class="rec_date">发布日期:[2012-03-18]</span>
							<span class="rec_prince">
                            ￥120
                        </span>
						</li>

					</ul>
					<div class="myclear">
					</div>
				</div>
			</div>
			<!--精品推荐结束-->
			<div class="recommend_r">
				<!--热点活动开始-->
				<div class="hotevent">
					<h1>
                    <a>
                        <img src="${ctx}/static/ggt/App_Themes/UI/images/hot_h_bg.jpg" width="307" height="88" alt="热点活动" /></a>
                </h1>
					<ul>
                          <c:forEach items="${activityList}" var="active" varStatus="status">  
								<li>
									<div class="hot_tu">
										<a href="Home/Detail/14.htm" target="_blank">
											<img src="${ctx}/upload/${active.imgUrl }" width="100" height="100" />
										</a>	
									</div>
									<span class="hot_num">Hot EVENTS.0${ status.index + 1}</span>
									<h2>
		                            <a href="Home/Detail/14.htm" target="_blank">${active.name }</a>
		                        </h2>
									<span class="hot_date">[ <fmt:formatDate type="date"  value="${active.sendDate }" />]</span>
								</li>
						 </c:forEach>
					</ul>
				</div>
				<!--热点活动结束-->
				 
			</div>
			<!--recommend_r-->
			<div class="myclear">
			</div>
			<div class="sub_ad1">


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