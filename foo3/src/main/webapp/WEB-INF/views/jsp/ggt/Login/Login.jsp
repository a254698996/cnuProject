<%@page import="web.conf.SysInit"%>
<%@page import="java.util.List"%>
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
    <title>登录-物物交换-大学生闲置物品交易平台</title>
    <meta name="keywords" content="二手货|闲置物品|校园二手|大学生二手交易|大学生闲置物品交易" />
    <meta name="description" content="服务于大学生群体的闲置物品交易平台，在物物交换，您可以便捷、自主、高效的处理身边的闲置物品。" />
    <meta name="copyright" content="物物交换 " />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta property="qc:admins" content="6202377777651415526375" />
    <meta property="wb:webmaster" content="44e7216ce6f6da21" />
    <script src="${ctx}/static/bootstrap/js/jquery.js"></script>
<%--     <script src="${ctx}/static/ggt/JS/jquery-1.5.1.min.js" type="text/javascript"></script> --%>
    <script src="${ctx}/static/ggt/JS/public.js" type="text/javascript"></script>
    <script src="${ctx}/static/ggt/JS/sM.js" type="text/javascript"></script>
    <link href="${ctx}/static/ggt/App_Themes/Public/PagerStyle.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/static/ggt/JS/Dialog.js" type="text/javascript"></script>
    <link href="${ctx}/static/ggt/App_Themes/Public/Dialog.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/ggt/App_Themes/UI/css/Common.css" rel="stylesheet" type="text/css" />
    <link type="image/x-icon" href="${ctx}/static/ggt/App_Themes/UI/images/favicon.ico" rel="Shortcut Icon">
    <script src="${ctx}/static/ggt/JS/jquery.lazyload.js" type="text/javascript"></script>
    <link href="${ctx}/static/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <script src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
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
     <script type="text/javascript">
     	$(document).ready(function(){
     		$("#toGetPasswordBut").click(function(){
     			var username=$("input[name='username']").val();
     			if(username==''){
     				$("#pageMsg").html("没有输入用户名");
     				$('#myModal').modal('show');
     				return ;
     			}
     			$.ajax({ url: "${pageContext.request.contextPath}/index/userExist?username="+username, 
     				     type:"post",async:false ,dataType:"json",
     				     success: function(data){
     				    	var dataObj=eval("("+data+")");
     				    	 if(dataObj.exist==true){
     				    		var url="${pageContext.request.contextPath}/index/toGetPassword?username="+username;
     			     			document.location=url;
     				    	 }else{
     				    		$("#pageMsg").html("用户名不正确");
     		     				$('#myModal').modal('show');
     		     				return ;
     				    	 }
     				    	 return ;
     				},
     				error:function(e){
     					 alert("有错误... "+e);
     				}
     			});
     		});
     		
     		//后台返回提示
   			var code=$('#msg').val();
   			if(code!=''){
   				$('#myModal').modal('show');
   			}
	    });
     </script>
    <link href="http://www.maliuer.com/Content/Style/Login.css" rel="stylesheet" type="text/css" />
    <script src="http://www.maliuer.com/FormValidator/formValidator-4.1.1.js" type="text/javascript"></script>
    <script src="http://www.maliuer.com/FormValidator/formValidatorRegex.js" type="text/javascript"></script>
    <style type="text/css">
        table tr
        {
            height: 60px;
        }
        table tr .title{color: #636563;font-size: 14px;font-weight: bold;}
        table tr a
        {
            color:#6D7182;
        }
        .input_public
        {
            width: 180px;
        }
    </style>
 </head>
<!--网站前台总模板页面-->
<body>
    <div class="status">
        <div class="status_inner">
            <div class="area">
            </div>
            <!--选择地区-->
          <div class="login" style=" text-align:right;">
				<%   if(SecurityUtils.getSubject().getPrincipal()== null){ %>
                   <a href="${ctx}/index/toLogin"><img src="${ctx}/static/ggt/App_Themes/UI/images/login_bg.jpg" /></a>
                  <% }else{ %>
                	  欢迎[<%=SecurityUtils.getSubject().getPrincipal() %>]登录，<a href="${pageContext.request.contextPath}/user/userLoginOut">退出</a> 
                	  <% } %> 
					<a href="${ctx}/index/toReg"><img src="${ctx}/static/ggt/App_Themes/UI/images/reg_bg.jpg" /></a>
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
						<img src="${ctx}/static/ggt/App_Themes/UI/images/foot_logo.jpg" width="263" height="58" alt="物物交换" /></a>
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
					<a href="${ctx}/index/userIndex" title="首页"><span class="yinwen">Home</span><span class="zhongwen">首页</span></a>
				</li>
				<%  List<Object> goodsCategoryList = SysInit.goodsCategoryList; 
					pageContext.setAttribute("goodsCategoryList", goodsCategoryList);
				%>
				<c:forEach items="${goodsCategoryList }" var="gc" >
				  	 <li>
						<a href="${ctx}/index/indexList/${gc.id}" title="${gc.ename }"><span class="yinwen">${gc.ename }</span><span class="zhongwen">${gc.name }</span></a>
					</li>
				</c:forEach>
				</ul>
			</div>
		</div>
    <!--导航-->
    <div class="nav_sub"></div>
    
    
    <div class="Ioutside">
        <div class="Lotitle">
<!--             用户登录&nbsp;已有<font color="red">778</font>位可爱的童鞋们成为物物交换的用户啦！ -->
        </div>
        <div class="Loconten">
            <input type="hidden" name="ToUrls" value="http://www.maliuer.com/"/>
            <div class="Loleft">
              <form method="post"  action="<%=request.getContextPath()%>/index/userLogin"  >
                <table style="width: 100%; height: 100%">
                    <tr>
                        <td style="width: 150px;" align="right" class="title">
                            用户名：
                        </td>
                        <td>
                            <input type="text" name="username" id="username"/>
                        </td>
                        <td>
                            <div id="UserNameTip" style="width: 181px; position:relative;">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td align="right" class="title">
                            密&nbsp;&nbsp;码：
                        </td>
                        <td>
                            <input type="password" name="password" id="password"/>
                        </td>
                        <td>
                            <div id="UserPwdTip" style="width: 181px;position:relative;">
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            &nbsp;
                        </td>
                        <td>
                            <label><input name="userType" type="radio" value="user" checked="checked" />用户 </label> 
							<label><input name="userType" type="radio" value="admin" />管理员</label> 
<!--                             <span>30天内自动登录</span> -->
 				      		 <label id ="toGetPasswordBut" style="color: red">忘记密码？</label> 
                        </td>
                        <td>
                            &nbsp;
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" align="center">
                            <input type="image" src="http://www.maliuer.com/App_Themes/UI/Images/Login_r12_c8.jpg" />
                        </td>
                    </tr>
                </table>
                </form>
            </div>
            <div class="Loright">
                <div class="LoPic">
                </div>
                <a href="${ctx }/index/toReg"></a>
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
<script type="text/javascript">
    $(function () {
        $("#toptop").hide();
        $(window).scroll(function () {
            if (parseFloat($(window).scrollTop()) > 200) {
                $("#toptop").show();
            } else {
                $("#toptop").hide();
            }
        });
    });
</script>

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

<!-- footer end -->
<div class="fixedBtn">
    <a href="http://www.maliuer.com/Help/JianYi.htm" class="feedback" title="欢迎反馈物物交换的使用问题、Bug和建议～" target="_blank">意见反馈</a> 
    <a href="Login.htm#" class="tops" id="toptop" title="回到顶部">回到顶部</a>
</div>

</body>

<script type="text/javascript">
    function setTValue(key, value) { $("input[name='" + key + "']").attr("value", value); }
    function setRadioValue(key, value) { $("input[name='" + key + "'][value='" + value + "']").attr("checked", 'checked'); }
    function setSelectValue(key, value) { $("select[name='" + key + "'] option:[value='" + value + "']").attr("selected", "selected"); }
    //function setTextAValue(key, value) { $("textarea[name='" + key + "']").html(value); }
    var myValues = new Array();
    var EvalString="";

eval(EvalString);
    $(
                function()
                {
                    for (var i = 0; i < myValues.length; i++)
                    {
                        var key = myValues[i][0];
                        var value = myValues[i][1];
                        value = value.replace(/@_@/g,"'");
                        value = value.replace(/#_#/g,"\"");
                        value = value.replace(/&_&/g,"\\");
                        
                        try
                        {
                            var type = $("input[name='" + key + "']").attr("type");
                            switch (type.toLowerCase())
                            {
                                case "text": setTValue(key, value); break;
                                case "hidden": setTValue(key, value); break;
                                case "password": setTValue(key, value); break;
                                case "file": setTValue(key, value); break;
                                case "radio": setRadioValue(key, value); break;
                                case "checkbox": setRadioValue(key, value); break;
                            }
                        }
                        catch (ce)
                        {
                            try
                            {
                                setSelectValue(key, value);
                            }
                            catch (cee) { }
                        }
                    }
                }
            );
</script>

</html>
<!--防止重复提交-->
<script type="text/javascript">
    $(function () {
        var R_hidd = '1891';
        if (R_hidd != "") {
            $("form").each(function (i, v) { if ($(v).attr("target") != "_blank") { var hidd = "<input type='hidden' notsavevalue='true'  name='___R_hidd' value='" + R_hidd + "' />"; $(v).append(hidd); } });
        }
    });
</script>