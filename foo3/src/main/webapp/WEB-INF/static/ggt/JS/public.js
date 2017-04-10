//加入收藏
function AddFavorite(sURL, sTitle)
{
    try
    {
        window.external.addFavorite(sURL, sTitle);
    }
    catch (e)
    {
        try
        {
            window.sidebar.addPanel(sTitle, sURL, "");
        }
        catch (e)
        {
            alert("加入收藏失败，请使用Ctrl+D进行添加");
        }
    }
}
//设为首页
function SetHome(obj, vrl)
{
    try
    {
        obj.style.behavior = 'url(#default#homepage)'; obj.setHomePage(vrl);
    }
    catch (e)
    {
        if (window.netscape)
        {
            try
            {
                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
            }
            catch (e)
            {
                alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。");
            }
//            var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
//            prefs.setCharPref('browser.startup.homepage', vrl);
        }
    }
}
//input='text'默认提示文字
function TextDefaultValue()
{
    $("input[type='text']").each(function (i, v)
    {
        if ($(v).attr("Jtips") != undefined && $(v).attr("Jtips") != null)
        {
            var Jtips = $(v).attr("Jtips");
            $(v).focus(function ()
            {
                var val = $.trim($(this).val());
                if (val == Jtips)
                    $(this).val("");
            });
            $(v).blur(function ()
            {
                var val = $.trim($(this).val());
                if (val == "")
                    $(this).val(Jtips);
            });
            $(v).blur();
        }
    });
}
$(function ()
{
    TextDefaultValue();
});

//关注开始
function guanzu(cid) {
    var id = cid;
    var url = '/Commodity/AddAttention.htm';
    var date = "id=" + id;
    $.ajax({
        url: url,
        data: date,
        type: 'POST',
        success: function (msg) {
            alert(msg);
        }
    });
}
//关注结束

function IsConfirm() {
    if (window.confirm("你确定要执行操作吗?"))
        return true;
    else
        return false;
}



