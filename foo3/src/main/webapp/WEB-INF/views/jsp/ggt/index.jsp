<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../header.jsp" %>
		<!--导航底部-->
		<div class="main_banner_outter">
			<div class="main_banner">
				<div id="page">
					<div id="gamesHolder">
						<div id="games" style="overflow: hidden;">

<!-- 							<a href="http://www.maliuer.com/Home/Detail/13.htm" target="_blank"> -->
								<img src="${ctx}/upload/index/B1.jpg" class="hd"><span><b>麻溜儿网校园代理火热招募中…</b></span>
<!-- 								</a> -->

<!-- 							<a href="http://www.maliuer.com/LuckDraw/Index.htm" target="_blank"> -->
								<img src="${ctx}/upload/index/B2.jpg" class="hd"><span><b>即日起发布闲置物品，即可参与幸运大抽奖活动！轻轻点击入场吧！</b></span>
<!-- 								</a> -->


<!-- 							<a href="http://www.maliuer.com/Home/Detail/13.htm" target="_blank"> -->
<%-- 								<img src="${ctx}/upload/2012-05-03/2012050316115003848049.jpg" class="hd"><span><b>麻溜儿网校园代理火热招募中…</b></span></a> --%>

<!-- 							<a href="http://www.maliuer.com/LuckDraw/Index.htm" target="_blank"> -->
<%-- 								<img src="${ctx}/upload/2012-05-22/2012052221044338315841.jpg" class="hd"><span><b>即日起发布闲置物品，即可参与幸运大抽奖活动！轻轻点击入场吧！</b></span></a> --%>

<!-- 							<a href="http://www.maliuer.com" target="_blank"> -->
<%-- 								<img src="${ctx}/upload/2012-03-12/2012031214490142631528.jpg" class="hd"><span><b>挖掘寝室新价值，引领节约新风潮！</b></span></a> --%>

<!-- 							<a href="http://www.maliuer.com" target="_blank"> -->
<%-- 								<img src="${ctx}/upload/2012-03-12/2012031214490144202186.jpg" class="hd"><span><b>麻溜儿网正式开放测试，欢迎您提出宝贵意见，我们将虚心聆听！</b></span></a> --%>

<!-- 							<a href="http://www.maliuer.com/Login/Login.htm" target="_blank"> -->
<%-- 								<img src="${ctx}/upload/2012-03-12/2012031214490150453772.jpg" class="hd"><span><b>身边的人都已经加入麻溜儿啦！身为大学生一员的你还在等什么？快来加入吧！</b></span></a> --%>

<!-- 							<a href="http://mall.maliuer.com" target="_blank"> -->
<%-- 								<img src="${ctx}/upload/2012-03-12/2012031214503639874276.jpg" class="hd"><span><b>麻溜儿商城，打造大学生自己的精品商城，现在下单即有精美礼品赠送哦！</b></span></a> --%>

<!-- 							<a href="http://www.maliuer.com/Login/Login.htm" target="_blank"> -->
<%-- 								<img src="${ctx}/upload/2012-03-12/2012031214552472234842.jpg" class="hd"><span><b>2012登船集结号！赶快卖了闲置物品，攒钱买船票吧！</b></span></a> --%>

<!-- 							<a href="http://www.maliuer.com" target="_blank"> -->
<%-- 								<img src="${ctx}/upload/2012-03-12/2012031214560295829234.jpg" class="hd"><span><b>挖掘寝室新价值，引领节约新风潮！</b></span></a> --%>

						</div>
					</div>
				</div>
			</div>
			<!--main_banner-->
			<div class="main_banner_r">
				<ul>
				<% List<Object> noticeList =SysInit.noticeList;  
					pageContext.setAttribute("noticeList", noticeList);
				%>
                     <c:forEach items="${noticeList }" var="notice">
	                     <li> <a class="ggao" href="${ctx }/user/indexNotice/${notice.id }">
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
   			<% List<Object> goodsList = SysInit.goodsList;  
					pageContext.setAttribute("goodsList", goodsList); 	%>
                     <c:forEach items="${goodsList }" var="goods">
	                    <li>
							<a href="Commodity/Detail/593.htm" class="rec_tu">
<!-- 								<img src="http://pic.maliuer.com/UploadFolder/2012-05-14/2012051416253736584636_150_180.jpg?a=1" /></a> -->
								<img width="150" height="180" src="${ctx}/upload/${goods.titleUrl}" /></a>
							<h2>
                            <a href="Commodity/Detail/593.htm">
                                		${goods.name }</a>
                        </h2>
							<span class="rec_date">发布日期:[${goods.sendDate }]</span>
<!-- 							<span class="rec_prince"> -->
<!--                             		￥10 -->
<!--                         </span> -->
						</li>
                     </c:forEach>
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
					<% List<Object> activityList = SysInit.activityList;  
						pageContext.setAttribute("activityList", activityList);
					%>
                          <c:forEach items="${activityList}" var="active" varStatus="status">  
								<li>
									<div class="hot_tu">
										<a href="${ctx }/user/indexNotice/${active.id }" target="_blank">
											<img src="${ctx}/upload/${active.imgUrl }" width="100" height="100" />
										</a>	
									</div>
									<span class="hot_num">Hot EVENTS.0${ status.index + 1}</span>
									<h2>
		                            <a href="${ctx }/user/indexNotice/${active.id }" target="_blank">${active.name }</a>
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