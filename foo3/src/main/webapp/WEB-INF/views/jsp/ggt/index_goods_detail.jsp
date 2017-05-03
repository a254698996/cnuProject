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
    <script type="text/javascript">
        //控制菜单方法
        function GetMenu(id) {
            $(".nav_inner li").each(function (a, b) {
                if (a == id) {
                    $(b).find("a").addClass("nav_active");
                }else {
                    $(b).find("a").removeClass("nav_active");
                }
            });
        }
        $(function () {

            //图片延迟加载
            $("img").not($(".hd")).lazyload({ 
                effect : "fadeIn"
            }); 

            $(".k").each(function (a, b) {
                $(b).click(function () {
                    $("#CommTitle").val($(this).text());
                });
                if (a % 2 == 1) {
                    $(b).css("color", "#FF7800");
                }
            });
            $("#sousuo").click(function () {
                var title = $("#CommTitle").val();
                if (title.length != 0 && title != '物物交换一下你想要的^_^') {
                    $("#sform").submit();
                }
            });
            $(".dh").hover(function () {
                $(this).removeClass("dh").addClass("dhhover");
                $(".xiala").show();
            }, function () {
                $(this).removeClass("dhhover").addClass("dh");
                $(".xiala").hide();
            });
        });
    </script>
    
    
    <script src="http://www.maliuer.com/JS/jquery.ad-gallery.js" type="text/javascript"></script>
    <link href="http://www.maliuer.com/JS/jquery.ad-gallery.css" rel="stylesheet" type="text/css" />
    <script src="http://www.maliuer.com/JS/Copy/ZeroClipboard.js" type="text/javascript"></script>
    <link href="http://www.maliuer.com/App_Themes/UI/css/CDetail.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript">
        //幻灯片方法
        function Hd() {
          
                $('img.image1').data('ad-desc', '');
                $('img.image1').data('ad-title', '');
                $('img.image4').data('ad-desc', '');
                $('img.image5').data('ad-desc', '');
                var galleries = $('.ad-gallery').adGallery();
                $('#switch-effect').change(
                function () {
                    galleries[0].settings.effect = $(this).val();
                    return false;
                }
            );
                $('#toggle-slideshow').click(
                function () {
                    galleries[0].slideshow.toggle();
                    return false;
                }
            );
                $('#toggle-description').click(
                function () {
                    if (!galleries[0].settings.description_wrapper) {
                        galleries[0].settings.description_wrapper = $('#descriptions');
                    } else {
                        galleries[0].settings.description_wrapper = false;
                    }
                    return false;
                }
            );
        }

        $(function () {
            //复制
            $('.copyID').zclip({
                path: '/JS/Copy/ZeroClipboard.swf',
                copy: function () {
                    return $(this).prev().html();
                }
            });

            //调用幻灯片方法
            Hd();

            //鉴定开始
            $(".boxlefttwotitleb").css({ "visibility": "hidden" });

            $(".jianding_biao").mousemove(function () {
                $(".boxlefttwotitleb").css({ "visibility": "visible" });
                $('.copyID').zclip({
                    path: '/JS/Copy/ZeroClipboard.swf',
                    copy: function () {
                        return $(this).prev().html();
                    }
                });
            });

            $(".boxlefttwotitleb").mouseleave(function () {
                $(".boxlefttwotitleb").css({ "visibility": "hidden" });
            });
            //鉴定结束

            $("#jform").submit(function () {
                var type = 0;
                $("#Types > input").each(function (a, b) {
                    if ($(b).attr("checked")) {
                        type = 1;
                    }
                });
                if (type == 0) {
                    alert("请选择投诉类型!");
                    return false;
                }
                if ($("#RContent").val().length == 0 || $("#RContent").val() == "请您填写投诉的理由！") {
                    alert("请填写投诉内容!");
                    return false;
                }
                if ($("#Tell").val() == "请填写联系电话") {
                    $("#Tell").val("");
                }
                return true;
            });

            // 提交留言内容 Start
            $("#lyform").submit(function () {
                if ($("#Remarks").val().length == 0 || $("#Remarks").val() == "请填写您的留言内容！") {
                    alert("留言内容不能为空额！");
                    return false;
                }
                return true;
            });
            // 提交留言内容 End

            //发送手机短信
            $("#Send").click(function () {
                var id = $("#fdid").val();
                var phone = $("#phone").val();
                var usercode = $("#usercode").val();
                var reg = /^1[3|5|8][0-9]{9}$/;

                if (phone.length < 0) {
                    alert("请输入手机号码！");
                    return;
                }
                else if (!reg.test(phone)) {
                    alert("手机格式错误！");
                    return;
                }
                if (usercode.length < 0) {
                    alert("请输入验证码！");
                    return;
                }

                var url = '/Commodity/SendMessage.htm';
                var date = "id=" + id + "&phone=" + phone + "&usercode=" + usercode;
                $.ajax({
                    url: url,
                    data: date,
                    type: 'POST',
                    success: function (msg) {
                        if (msg == "发送成功！") {
                            $("#fss").hide();
                            $("#fse").show();
                        } else {
                            alert(msg);
                        }
                    }
                });
            });
            //评论
            $("#plform").submit(function () {
                if ($(".message_txt").val() == "文明上网，理性发言") {
                    alert("请输入评论内容,谢谢！")
                    return false;
                } else {
                    return true;
                }
            });
            //回复
            $(".tijiao").each(function (a, b) {
                $(b).click(function () {
                    if ($("#Reply" + a).val().length <= 0) {
                        alert("请输入回复内容！");
                    } else {
                        $("#hfform" + a).submit();
                    }

                });
            });

        });
        //举报方法
        function Jubao() {
            Dialog("#jubao", "举报该信息", function () { }, false);
        }

        //点出留言框
        function Liuyan() {
            Dialog("#liuyan", "给Ta留言", function () { }, false);
        }

        //点出发短信框
        function Duanxin() {
            Dialog("#duanxin","把该信息发送到手机", function () { },false);
        }

        //点击出现回复框
        function huifu(aa) {
            $(".hfbox").each(function (a, b) {
                if (aa == a) {
                    $(b).show();
                }
            });
        }
        //刷新
        function Refresh(id) {
            var url = '/Commodity/RefreshCommodity.htm';
            var date = "ids=" + id;
            $.ajax({
                url: url,
                data: date,
                type: 'POST',
                success: function (msg) {
                    alert(msg);
                }
            });
        }
       
    </script>
	  
        <div class="outer_body">
        <div class="inner_left">
            <div class="place">
                <a href="${ctx }/index/userIndex">
                大学生物品交换平台</a>
            
            > <a  >
              ${goods.goodsCategoryName}</a>
            
            > <a  >
                ${goods.goodsCategorySubName}</a>
            > <a >
                ${goods.name}</a>
            
            </div>
            <!--网站位置-->
            <div class="shop_inner"> 
                <div class="shop_jianjie">
                    <ul>
                        <li>
                            <span class="jianjie_tit">发布时间：</span><span class="jianjie_con">${goods.sendDate }</span>
                        </li>
                        <li>
                            <span class="jianjie_tit">联系人：</span>
                            <span class="jianjie_con">${goods.user.username }</span>
                            <span class="jianjie_tit">联系电话：</span>
                              <span class="jianjie_con">${goods.user.phone }</span>
                            
                        </li>
                        <li>
                            <span class="jianjie_tit">交易地点：</span>
                            <span class="jianjie_con">${goods.exchangeAddress }</span>
                        </li>
                        <li>
                            <span class="jianjie_tit">交易状态：</span>
                            <span class="jianjie_con"> 
	                            <c:choose>
							     <c:when test="${goods.deal eq 0 }">
							      	未成交
							     </c:when>
								 <c:otherwise >
									 已成交
								 </c:otherwise>
							 </c:choose>
                            </span>
                        </li>
                        <c:if test="${goods.deal eq 0 }">
	                        <li>
	                            <span class="jianjie_tit"><a href="${ctx }/user/toExchange/${goods.id}">点击此处交换</a></span> 
	                        </li>
                        </c:if>
                    </ul>
                </div>
                <!--shop_jianjie-->
                <p class="shop_zisu">
                    &nbsp;&nbsp;&nbsp;  ${goods.goodsDesc}
                    <br /><br />
                    联系我时，请说是在物物交换上看到的，谢谢！
                </p>
                <!--shop_zisu-->
                <div class="kuangqie_tu">
                    <div id="gallery" class="ad-gallery">
                        <div class="ad-image-wrapper">
                        </div>
                        <div class="ad-controls">
                        </div>
                        <div class="ad-nav">
                            <div class="ad-thumbs">
                                <ul class="ad-thumb-list">
                                    <c:forEach items="${goods.goodsPicList }" var="goodsPic">
                                    	<li><a href="${ctx }/upload/${goodsPic.url}">
                                        <img src="${ctx }/upload/${goodsPic.url}" class="image0" alt="" height="74"; width="74"/></a></li>
                                    </c:forEach>
                                    
                                </ul>
                            </div>
                        </div>
                    </div>
                    <!-- 结束 -->
                </div>
                <!--kuangqie_tu-->
                  <li>
                      <span class="jianjie_tit"><b>以下物品想要同该物品进行交换</b></span>
                  </li>
               <div class="shop_jianjie">
                    <ul>
                     <c:forEach items="${goodsList }" var="userGoods">
                        <li>
                            <span class="jianjie_tit">物品所有人：</span><span class="jianjie_con">${userGoods.eo.user.username }</span>
                            <span class="jianjie_tit">物品名称：</span><span class="jianjie_con">${userGoods.name }</span>
                            <span class="jianjie_tit">大类：</span><span class="jianjie_con">${userGoods.goodsCategoryName }</span>
                            <span class="jianjie_tit">细类：</span><span class="jianjie_con">${userGoods.goodsCategorySubName }</span>
                        </li>
                     </c:forEach>
                    </ul>
                </div>
                
            </div>
            <!--shop_inner-->
        </div>
        <!--内页左边-->
        <div class="myclear">
        </div>
    </div>
     <style type="text/css">
        .fbox{ width:600px; height:auto; display:none;}
        /*发送手机短信*/
        .fbox .fdb{width:528px; height:auto; margin:0px auto;}
        .fbox .fdb .fdbr{ width:175px;height: 225px; float:left;background-image:Url("/Content/images/duanxin_bg.gif");}
        .fbox .fdb .fdbr h2 {color: #666666;font-size: 12px;font-weight: normal;padding: 9px 10px;list-style: none outside none; margin:0px; border:solid 0px red;}
        .fbox .fdb .fdbr p{color: #FFFFFF;font-size: 13px;line-height: 22px;padding: 0 15px;}
        .fbox .fdb .fdbl{ width:320px;height: auto; float:left; padding-left:30px;}
        .fbox .fdb .fdbl .fdblt{width:320px; height:26px; line-height:26px;letter-spacing: 1px; padding-bottom:40px;}
        .fbox .fdb .fdbl .fdbls{width:320px; padding: 40px 0;background:Url("/Content/images/right-img.gif") no-repeat 80px center; color:#169B00; font-size:16px; text-align:center; font-weight:bold;}
        .fbox .fdb .fdbl p{text-align:center;}
    </style>
		 
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

<style>
    .fixedBtn
    {
        position: fixed;
        right: 0;
        top: 230px;
        width: 36px;
        height: 178px;
    }
    * html .fixedBtn
    {
        position: absolute;
        bottom: auto;
        top: expression(eval(document.documentElement.scrollTop)+230);
    }
    .fixedBtn .feedback
    {
        display: inline-block;
        text-indent: -2000px;
        background: url('/App_Themes/UI/images/fankui.png');
        width: 31px;
        height: 104px;
        position: absolute;
        left: 0;
        top: 0;
    }
    .fixedBtn .feedback:hover
    {
        background-position: -31px 0;
    }
    .fixedBtn .tops
    {
        text-indent: -2000px;
        background: url('/App_Themes/UI/images/celan_top.png');
        width: 31px;
        height: 74px;
        position: absolute;
        left: 0;
        bottom: 0;
    }
    .fixedBtn .top:hover
    {
        background-position: -31px 0;
    }
</style>
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