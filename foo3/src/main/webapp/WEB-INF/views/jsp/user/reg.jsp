<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../header.jsp" %>
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
    	//后台返回提示
			var code=$('#msg').val();
			if(code!=''){
				$('#myModal').modal('show');
			}
    });
</script>
    <div class="Ioutside">
      	<form action="<%=request.getContextPath() %>/index/reg" method="post">
        <div class="ReContent">
            <table class="table2">
                <tr>
                    <td style="width: 380px;" align="right" class="title">
<span style="color: red;">*</span> 用 户 名：
                    </td>
                    <td style="width: 300px;">
                        <input type="text" name="username" id="username" />
                    </td>
                    <td>
                        <div id="UserAccTip" style="width: 181px; position: relative;">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td align="right" class="title">
<span style="color: red;">*</span>  密&nbsp;&nbsp;&nbsp;&nbsp;码：
                    </td>
                    <td>
                        <input type="password" name="password" id="password" />
                    </td>
                    <td>
                        <div id="UserPwdTip" style="width: 181px; position: relative;">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td align="right" class="title">
<span style="color: red;">*</span>  确认密码：
                    </td>
                    <td>
                        <input type="password" name="repassword" id="repassword" />
                    </td>
                    <td>
                        <div id="UserPwdsTip" style="width: 181px; position: relative;">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td align="right" class="title">
<span style="color: red;">*</span> 电子邮箱：
                    </td>
                    <td>
                        <input type="text" name="email" id="email" />
                    </td>
                    <td>
                        <div id="EmailTip" style="width: 181px; position: relative;">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td align="right" class="title">
<span style="color: red;">*</span>  电话：
                    </td>
                    <td>
                        <input type="text" name="phone" id="phone" />
                    </td>
                    <td>
                        <div id="EmailTip" style="width: 181px; position: relative;">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td align="right" class="title">
<span style="color: red;">*</span> 学号：
                    </td>
                    <td>
                        <input type="text" name="sno" id="sno" />
                    </td>
                    <td>
                        <div id="EmailTip" style="width: 181px; position: relative;">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td align="right" class="title">
<span style="color: red;">*</span> 姓名：
                    </td>
                    <td>
                        <input type="text" name="sname" id="sname" />
                    </td>
                    <td>
                        <div id="EmailTip" style="width: 181px; position: relative;">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td align="right" class="title">
<span style="color: red;">*</span> 找回密码问题：
                    </td>
                    <td>
                        <input type="text" name="passwordask" id="passwordask" />
                    </td>
                    <td>
                        <div id="EmailTip" style="width: 181px; position: relative;">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td align="right" class="title">
<span style="color: red;">*</span>  找回密码答案：
                    </td>
                    <td>
                        <input type="text" name="passwordanswer" id="passwordanswer" />
                    </td>
                    <td>
                        <div id="EmailTip" style="width: 181px; position: relative;">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td align="right" class="title">
                        &nbsp;
                    </td>
                    <td>
                        <div id="Div3" style="width: 181px">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td align="right" class="title">
                        &nbsp;
                    </td>
                    <td>
                        <input type="image" src="http://www.maliuer.com/App_Themes/UI/Images/rere_r2_c2.jpg" id="tijiao" value=""
                            style="float: left;" />
                        <input type="reset" value="" style="background-image: url(http://www.maliuer.com/App_Themes/UI/Images/re_r2_c4.jpg);
                            border: solid 0px red; height: 34px; width: 90px; float: left; margin-left: 10px;
                            cursor: pointer;" />
                    </td>
                    <td>
                        <div id="Div4" style="width: 181px">
                        </div>
                    </td>
                </tr>
            </table>
        </div>
        </form>
    </div>
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

    <div class="footer">

        <div class="copyright">
            <div class="copyright_inner">
					<img class="foot_logo" src="${ctx}/static/ggt/App_Themes/UI/images/foot_logo.jpg" width="112" height="26" alt=" " />
           </div>
             
        </div>
    </div>
<!-- footer end -->
</body>
 
</html>
 