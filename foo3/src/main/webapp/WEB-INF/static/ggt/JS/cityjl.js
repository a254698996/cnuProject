//动态获取城市、区、学校JS
$(function () {
    //动态获取区域
    $("#CityId").change(function () {
        var CityId = $("#CityId").val();
        var url ='/CmsSiteCity/GetCity.htm';
        var date = "CityId=" + CityId;
        $.ajax({
            url: url,
            data: date,
            type: 'POST',
            dataType: "json",
            success: function (date) {
                var Jsons = eval(date);
                var html = '<option value="">--请选择区域--</option>';
                for (var i = 0; i < Jsons.length; i++) {
                    var Json = Jsons[i];
                    html += '<option value="' + Json.id + '">' + Json.AreaName + '</option>';
                }
                $("#AreaId").html(html);
            }
        });
    });
    //动态获取学校
    $("#AreaId").change(function () {
        var AreaId = $("#AreaId").val();
        var url = '/CmsSiteCity/GetSchool.htm';
        var date = "id=" + AreaId;
        $.ajax({
            url: url,
            data: date,
            type: 'POST',
            dataType: "json",
            success: function (date) {
                var Jsons = eval(date);
                var html = '<option value="">--请选择学校--</option>';
                for (var i = 0; i < Jsons.length; i++) {
                    var Json = Jsons[i];
                    html += '<option value="' + Json.id + '">' + Json.SchoolName + '</option>';
                }
                $("#SchoolId").html(html);
            }
        });
    });
});