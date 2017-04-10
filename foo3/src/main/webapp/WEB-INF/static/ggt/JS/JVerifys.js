////////////////////////////////////////////////
//////调用本页面前，请先引用jquery.js文件/////////
//////js表单验证集合/////////////////////////////
//////2010-4-12/////////////////////////////////
////////////////////////////////////////////////
//////////JVerify.js////////////////////////////

/*
Class 说明[可以任意组合] 
所有应用要生效,上级form表单必须Class需要应用:FormClassName[默认为var FormClassName = ".form"; //form表单样式]

.J -- 正常(什么都不验证), 目的让<input type="text" name="a1" class="J" min="2" max="8" />生效(不是必填,但又控制了min和max生效)
.req -- 必填  
.email -- 邮箱
.tel -- 座机
.ascii -- 字母 数字、“_”、“.”的字符串 字母开头
.cn -- 只能输入中文
.en -- 不能包含英文以外的字符
.trueacct -- 银行卡号格式
.phone -- 手机格式
.phoneortel -- 手机或者座机
.idcardno -- 身份证
.postnum -- 邮编
.int -- 整数(不带小数点)
.float -- 数字

.Sreq -- 必选 <针对于select标签>
.Rreq -- 必选 <针对于Radio标签>
.Creq -- 必选 <针对于Checkbox标签>

.notips -- 隐藏正常提示信息

min 最小值  max 最大值
<input type="text" name="a1" class="req" min="2" max="8" />//必填长度在2-8
<input type="text" name="a1" class="req int" min="2" max="8" />//必填范围在2-8
<input type="text" name="a1" class="req int" min="2" max="8" msg="自定义错误提示信息" tips="自定义正常提示信息" />//必填范围在2-8

<input type="radio" name="rad" value="1" />1
<input type="radio" name="rad" value="2" />2
<input type="radio" name="rad" value="3" />3<span name="rad" class="Rreq"></span> //必选

<input type="checkbox" name="che" value="1" />1
<input type="checkbox" name="che" value="2" />2
<input type="checkbox" name="che" value="3" />3
<input type="checkbox" name="che" value="4" />4<span name="che" class="Creq" min="2" max="3"></span> //必选范围在2-3个

<select name="s1" class="Sreq"> //必选
<option value="">--</option>
<option value="1">1</option>
<option value="2">2</option>
<option value="3">3</option>
</select>
*/

var FormClassName = ".form"; //form表单样式

var NormalBorderColor = "#CCCCCC #CCCCCC #CCCCCC #CCCCCC"; //input边框
var NormalColor = "#666666"; //input字体颜色
var ErrorColor = "Red"; //错误提示颜色
var FocusColor = "Green"; //正常提示颜色

var reqTips = ""; //必填
var reqMsg = ""; //必填

var form_VerifyList = new Array();
form_VerifyList.push(new Array("verify_J", "", ""));
form_VerifyList.push(new Array("verify_req", reqTips, reqMsg));
form_VerifyList.push(new Array("verify_email", "", ""));
form_VerifyList.push(new Array("verify_tel", "", ""));
form_VerifyList.push(new Array("verify_ascii", "", ""));
form_VerifyList.push(new Array("verify_cn", "", ""));
form_VerifyList.push(new Array("verify_en", "", ""));
form_VerifyList.push(new Array("verify_trueacct", "", ""));
form_VerifyList.push(new Array("verify_phone", "", ""));
form_VerifyList.push(new Array("verify_phoneortel", "", ""));
form_VerifyList.push(new Array("verify_idcardno", "", ""));
form_VerifyList.push(new Array("verify_postnum", "", ""));
form_VerifyList.push(new Array("verify_int", "", ""));
form_VerifyList.push(new Array("verify_float", "", ""));

form_VerifyList.push(new Array("verify_Sreq", "", "*"));
form_VerifyList.push(new Array("verify_Rreq", "", "*", false));
form_VerifyList.push(new Array("verify_Creq", "", "*", false));


//正常,目的让<input type="text" name="a1" class="J" min="2" max="8" />生效(不是必填,但又控制了min和max生效)
function verify_J(obj, tips, msg) {
    set_flag_verify(obj, msg, tips, true);
}
//必填
function verify_req(obj, tips, msg) {
    if ($(obj).val().replace(/(^\s*)|(\s*$)/g, "").length == 0) set_flag_verify(obj, msg, tips, false);
    else set_flag_verify(obj, msg, tips, true);
}
//邮件
function verify_email(obj, tips, msg) {
    var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;

    if (!reg.test($(obj).val())) set_flag_verify(obj, msg, tips, false);
    else set_flag_verify(obj, msg, tips, true);
}
//座机
function verify_tel(obj, tips, msg) {
    var reg = /^((\(\d{3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}$/;

    if (!reg.test($(obj).val())) set_flag_verify(obj, msg, tips, false);
    else set_flag_verify(obj, msg, tips, true);
}
//可带 字母 数字、“_”、“.”的字符串
function verify_ascii(obj, tips, msg) {
    var reg = /^[a-zA-Z]{1}([a-zA-Z0-9]|[._])+$/;

    if (!reg.test($(obj).val())) set_flag_verify(obj, msg, tips, false);
    else set_flag_verify(obj, msg, tips, true);
}
//只能为中文
function verify_cn(obj, tips, msg) {
    var reg = /^[\u4e00-\u9fa5]+$/;

    if (!reg.test($(obj).val())) set_flag_verify(obj, msg, tips, false);
    else set_flag_verify(obj, msg, tips, true);
}
//不能包含英文以外的字符
function verify_en(obj, tips, msg) {
    var reg = /^[a-zA-Z]+$/;

    if (!reg.test($(obj).val())) set_flag_verify(obj, msg, tips, false);
    else set_flag_verify(obj, msg, tips, true);
}
//验证银行卡：卡号必须为15位或16位或19位数字
function verify_trueacct(obj, tips, msg) {
    var reg = /^(\d{15}|\d{16}|\d{19})$/;

    if (!reg.test($(obj).val())) set_flag_verify(obj, msg, tips, false);
    else set_flag_verify(obj, msg, tips, true);
}
//验证手机号码：13、15、18
function verify_phone(obj, tips, msg) {
    var reg = /^1[3|5|8][0-9]{9}$/;

    if (!reg.test($(obj).val())) set_flag_verify(obj, msg, tips, false);
    else set_flag_verify(obj, msg, tips, true);
}
//验证手机或者座机
function verify_phoneortel(obj, tips, msg) {
    var reg = /^1[3|5|8][0-9]{9}$/; //手机
    var reg2 = /^((\(\d{3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}$/; //座机

    if (!reg.test($(obj).val()) && !reg2.test($(obj).val())) set_flag_verify(obj, msg, tips, false);
    else set_flag_verify(obj, msg, tips, true);
}
//身份证验证
function verify_idcardno(obj, tips, msg) {
    var reg = /(^\d{15}$)|(^\d{17}([0-9]|X)$)/;

    if (!reg.test($(obj).val())) set_flag_verify(obj, msg, tips, false);
    else set_flag_verify(obj, msg, tips, true);
}
//邮编
function verify_postnum(obj, tips, msg) {
    var reg = /^[1-9][0-9]{5}$/;

    if (!reg.test($(obj).val())) set_flag_verify(obj, msg, tips, false);
    else set_flag_verify(obj, msg, tips, true);
}
//必须为整数
function verify_int(obj, tips, msg) {
    var reg = /^-?\d+$/; //^[0-9]+$

    if (!reg.test($(obj).val())) set_flag_verify(obj, msg, tips, false);
    else set_flag_verify(obj, msg, tips, true);
}
//必须为数字
function verify_float(obj, tips, msg) {
    var reg = /^-?\d+(\.\d+)?$/; //^[0-9]+(\.)?([0-9])?([0-9])?$

    if (!reg.test($(obj).val())) set_flag_verify(obj, msg, tips, false);
    else set_flag_verify(obj, msg, tips, true);
}
//radio必选
function verify_Rreq(obj, tips, msg) {
    try {
        var name = $(obj).attr("name");
        var P = $(obj).parents(FormClassName);
        //var AllCont = $(":radio[name='" + name + "']").length;
        if ($(P).find(":radio[name='" + name + "']:checked").length == 0) set_flag_verify(obj, msg, tips, false);
        else set_flag_verify(obj, msg, tips, true);
    } catch (e) { }
}
//checkbox必选
function verify_Creq(obj, tips, msg) {
    try {
        var name = $(obj).attr("name");
        var P = $(obj).parents(FormClassName);
        //var AllCont = $(":checkbox[name='" + name + "']").length;
        if ($(P).find(":checkbox[name='" + name + "']:checked").length == 0) set_flag_verify(obj, msg, tips, false);
        else set_flag_verify(obj, msg, tips, true);
    } catch (e) { }
}
//select必选
function verify_Sreq(obj, tips, msg) {
    try {
        if ($(obj).val() == null || $(obj).val().replace(/(^\s*)|(\s*$)/g, "") == "") set_flag_verify(obj, msg, tips, false);
        else set_flag_verify(obj, msg, tips, true);
    } catch (e) { }
}

//验证函数
function set_flag_verify(obj, msg, tips, value)
{ set_flag_verify(obj, msg, tips, value, false); }
//验证函数
function set_flag_verify(obj, msg, tips, value, first) {

    msg = getInputMsg(obj, msg);
    tips = getInputTips(obj, tips);

    var arr = checkMinMax(obj, value);
    if (!first) { value = arr[0]; }
    msg = msg + arr[1];
    tips = tips + arr[1];

    if ($(obj).val() != null && $(obj).val().replace(/(^\s*)|(\s*$)/g, "").length == 0 && !$(obj).hasClass("req") && !$(obj).hasClass("Sreq")) { value = true; }

    removeMsgTips(obj);

    if (value == false) {
        addErrorSpan(obj, msg);
        $(obj).addClass("inputError");
    }
    else {
        addTipsSpan(obj, tips);
        $(obj).removeClass("inputError");
    }
}

//获取自定义错误提示信息
function getInputMsg(obj, msg) {
    if ($(obj).attr('msg') != undefined && $(obj).attr('msg') != null && $(obj).attr('msg').length > 0)
        msg = msg + " " + $(obj).attr('msg');

    return msg;
}
//获取自定义正常提示信息
function getInputTips(obj, tips) {
    if ($(obj).attr('tips') != undefined && $(obj).attr('tips') != null && $(obj).attr('tips').length > 0)
        tips = tips + " " + $(obj).attr('tips');

    return tips;
}
//获取焦点
function inputFocus(obj, tips) {
    tips = getInputTips(obj, tips);

    var arr = checkMinMax(obj, true);
    tips = tips + arr[1];

    removeMsgTips(obj);
    addTipsSpan(obj, tips);

    $(obj).css("border", "1px solid " + FocusColor);
    $(obj).css("color", FocusColor);
}
//移除提示span
function removeMsgTips(obj) {
    var P = $(obj).parents(FormClassName);
    $(P).find("span[id='msg_" + $(obj).attr('name') + "']").remove();
    $(P).find("span[id='tips_" + $(obj).attr('name') + "']").remove();
}
//增加错误提示span
function addErrorSpan(obj, msg) {
    if ($(obj).hasClass("req")) {
        if (msg.indexOf(reqMsg) < 0)
        { msg = reqMsg + " " + msg; }
    }
    $(obj).after("<span style='color:" + ErrorColor + "' id='msg_" + $(obj).attr('name') + "'> " + msg + "</span>");

    $(obj).css("border", "1px solid " + ErrorColor);
    $(obj).css("color", NormalColor);
}
//增加正常提示span
function addTipsSpan(obj, tips) {
    if ($(obj).hasClass("req")) {
        if (tips.indexOf(reqTips) < 0)
        { tips = reqTips + " " + tips; }
    }
    var hidden = $(obj).hasClass("notips") ? "display:none;" : "";
    $(obj).after("<span style='" + hidden + "color:" + FocusColor + "' id='tips_" + $(obj).attr('name') + "'> " + tips + "</span>");
    $(obj).css("border-width", "1px");
    $(obj).css("border-style", "solid");
    $(obj).css("border-color", NormalBorderColor);
    $(obj).css("color", NormalColor);
}
//验证字符串长度 或者 数值大小
function checkMinMax(obj, value) {
    var t = "";
    var s = value;

    var val = $(obj).val();
    if ($(obj).attr('min') != undefined && $(obj).attr('min') != null && $(obj).attr('min').length > 0) {
        var min = parseFloat($(obj).attr('min'));
        if ($(obj).hasClass("int") || $(obj).hasClass("float")) {
            val = parseFloat(val);
            if (min > val) { s = false; }
            //t = t + " " + "值不小于" + $(obj).attr('min');
        }
        else if ($(obj).hasClass("Creq")) {
            var name = $(obj).attr("name");
            var P = $(obj).parents(FormClassName);
            var CheckCount = parseFloat($(P).find(":checkbox[name='" + name + "']:checked").length);
            if (min > CheckCount) { s = false; }
            //t = t + " " + "个数不小于" + $(obj).attr('min');
        }
        else {
            if (min > C_ASIIC(val).length) { s = false; }
            //t = t + " " + "长度不小于" + $(obj).attr('min');
        }
    }
    if ($(obj).attr('max') != undefined && $(obj).attr('max') != null && $(obj).attr('max').length > 0) {
        var max = parseFloat($(obj).attr('max'));
        if ($(obj).hasClass("int") || $(obj).hasClass("float")) {
            val = parseFloat(val);
            if (max < val) { s = false; }
            //t = t + " " + "值不大于" + $(obj).attr('max');
        }
        else if ($(obj).hasClass("Creq")) {
            var name = $(obj).attr("name");
            var P = $(obj).parents(FormClassName);
            var CheckCount = parseFloat($(P).find(":checkbox[name='" + name + "']:checked").length);
            if (max < CheckCount) { s = false; }
            //t = t + " " + "个数不大于" + $(obj).attr('max');
        }
        else {
            if (max < C_ASIIC(val).length) { s = false; }
            //t = t + " " + "长度不大于" + $(obj).attr('max');
        }
    }
    return new Array(s, t);
}
//把一个双字节转换为ww
function C_ASIIC(val) {
    return val.replace(/[^\x00-\xff]/g, "ww");
}

//表单submit时验证
function verfy_submit(_obj) {
    //var obj = FormClassName;
    var obj = $(_obj);

    var output = "";
    for (var i = 0; i < form_VerifyList.length; i++) {
        var tips = form_VerifyList[i][1];
        var msg = form_VerifyList[i][2];
        var functionName = form_VerifyList[i][0];
        var className = "";
        try {
            className = functionName.split("_")[1];
        } catch (ce) { continue; }
        if (className == "") { continue; }

        output += "$(obj).find(\"." + className + "\").each(function(u, v){" + functionName + "($(v),'" + tips + "','" + msg + "');});";
    }
    eval(output);

    return $(obj).find(".inputError").length == 0 ? true : false;
}

//初始验证函数
$(function () {
    //    eval("$(\":text\").css(\"border-width\", \"1px\");");
    //    eval("$(\":text\").css(\"border-style\", \"solid\");");
    //    eval("$(\":text\").css(\"border-color\", \"" + NormalBorderColor + "\");");
    //    eval("$(\":text\").css(\"color\", \"" + NormalColor + "\");");

    $(FormClassName).each(function (fi, fv) {
        for (var i = 0; i < form_VerifyList.length; i++) {
            var tips = form_VerifyList[i][1];
            var msg = form_VerifyList[i][2];
            var functionName = form_VerifyList[i][0];
            var className = "";
            try {
                className = functionName.split("_")[1];
            } catch (ce) { continue; }
            if (className == "") { continue; }

            //        eval("$(\"." + className + "\").blur(function(){" + functionName + "($(this),'" + tips + "','" + msg + "');});");

            //        eval("$(\"." + className + "\").focus(function(){inputFocus($(this),'" + tips + "');});");
            eval("$(fv).find(\"." + className + "\").blur(function(){if(!$(this).hasClass('noBlur')){" + functionName + "($(this),'" + tips + "','" + msg + "'); } });");
            eval("$(fv).find(\"." + className + "\").focus(function(){if(!$(this).hasClass('noFocus')){ inputFocus($(this),'" + tips + "'); } });");

            $(fv).find("." + className).each(function (ui, vi) {
                eval("set_flag_verify($(this), '" + msg + "', '" + tips + "', true, true);");
            });

            var isRes = true;
            if (form_VerifyList[i][3] != undefined) { isRes = form_VerifyList[i][3]; }
            if (!isRes) { $(fv).find("." + className).hide(); $(fv).find("." + className).val("&nbsp;"); }

        }

        //增加submit方法
        //$(FormClassName).submit(function () { return verfy_submit(); });

        $(fv).submit(function () { return verfy_submit($(this)); });
    });
});


