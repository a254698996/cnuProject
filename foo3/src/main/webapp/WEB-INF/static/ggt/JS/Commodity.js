$(function () {
    //全部Checkbox功能
    $("#SelectAllIds").toggle(function () {
        $(":checkbox[name='ids']").attr("checked", true);
        $(this).attr("checked", true);
    }, function () {
        $(":checkbox[name='ids']").attr("checked", false);
        $(this).attr("checked", false);
    });
});

function IsConfirm() {
    if (window.confirm("你确定要执行操作吗?"))
        return true;
    else
        return false;
}

//删除数据

function DeleteId(id) {//删除一条数据
    if (IsConfirm()) DeleteIdsForAjax(new Array(id));
}
function DeleteSelectAllIds(obj) {//删除选中的数据
    if (IsConfirm()) {
        var ids = new Array();
        $(":checkbox[name='ids']:checked").each(function (i, v)
        { ids[i] = $(v).val(); });
        DeleteIdsForAjax(ids);
    }
}
var DeleteUrl = "/Commodity/DeleteCommodity.htm"; //删除数据地址默认为空
function DeleteIdsForAjax(ids) {//ajax执行删除数据功能
    var can = ids.join(",");
    if (can == "")
    { $("body").showMessage("未选择任何内容"); return; }
    if (DeleteUrl == "")
    { $("body").showMessage("删除失败, 路径为空"); return; }

    $.ajax({
        url: DeleteUrl,
        type: "POST",
        data: "ids=" + can,
        success: function (msg) {
            if (msg == "true") {
                for (var i = 0; i < ids.length; i++) {
                    $(":checkbox[name='ids'][value='" + ids[i] + "']").parent().parent().remove();
                }
                $("body").showMessage("删除成功");
            }
            else
                $("body").showMessage("删除失败");
        }
    });
}

//设置完成
function FinishId(id) {//设置一条数据
    if (IsConfirm()) FinishIdsForAjax(new Array(id));
}
function FinishSelectAllIds(obj) {//设置选中的数据
    if (IsConfirm()) {
        var ids = new Array();
        $(":checkbox[name='ids']:checked").each(function (i, v)
        { ids[i] = $(v).val(); });
        FinishIdsForAjax(ids);
    }
}
var FinishUrl = "/Commodity/FinishCommodity.htm"; //设置数据地址默认为空
function FinishIdsForAjax(ids) {//ajax执行设置数据功能
    var can = ids.join(",");
    if (can == "")
    { $("body").showMessage("未选择任何内容"); return; }
    if (FinishUrl == "")
    { $("body").showMessage("设置失败, 路径为空"); return; }

    $.ajax({
        url: FinishUrl,
        type: "POST",
        data: "ids=" + can,
        success: function (msg) {
            if (msg.length == 0) {
                for (var i = 0; i < ids.length; i++) {
                    $(":checkbox[name='ids'][value='" + ids[i] + "']").parent().parent().remove();
                }
                $("body").showMessage(msg);
            }
            else
                $("body").showMessage(msg);
        }
    });
}

//刷新商品时间
function RefreshId(id) {//设置一条数据
    if (IsConfirm()) RefreshIdsForAjax(new Array(id));
}
function RefreshSelectAllIds(obj) {//设置选中的数据
    if (IsConfirm()) {
        var ids = new Array();
        $(":checkbox[name='ids']:checked").each(function (i, v)
        { ids[i] = $(v).val(); });
        RefreshIdsForAjax(ids);
    }
}
var RefreshUrl = "/Commodity/RefreshCommodity.htm"; //设置数据地址默认为空
function RefreshIdsForAjax(ids) {//ajax执行设置数据功能
    var can = ids.join(",");
    if (can == "")
    { $("body").showMessage("未选择任何内容"); return; }
    if (RefreshUrl == "")
    { $("body").showMessage("刷新失败, 路径为空"); return; }

    $.ajax({
        url: RefreshUrl,
        type: "POST",
        data: "ids=" + can,
        success: function (msg) {
            if (msg.length==0) {
                for (var i = 0; i < ids.length; i++) {
                    $(":checkbox[name='ids'][value='" + ids[i] + "']").parent().parent().remove();
                }
                $("body").showMessage(msg);
            }
            else
                $("body").showMessage(msg);
        }
    });
}

