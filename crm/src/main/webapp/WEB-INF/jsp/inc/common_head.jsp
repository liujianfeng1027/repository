<%@ page contentType="text/html; charset=UTF-8" %>
<meta charset="UTF-8">
<link href="/static/jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="/static/bs_pagination/jquery.bs_pagination.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/static/jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="/static/jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/static/jquery/jquery-md5.js"></script>
<script type="text/javascript" src="/static/bs_pagination/en.js"></script>
<script type="text/javascript" src="/static/bs_pagination/jquery.bs_pagination.min.js"></script>

<%--日历控件--%>
<link href="/static/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="/static/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="/static/bootstrap-datetimepicker/locale/bootstrap-datetimepicker.zh-CN.js"></script>
<script>
    jQuery(function ($) {
        $("input[date]").datetimepicker({
            language: "zh-CN",
            format: "yyyy-mm-dd",//显示格式
            minView: "month", // 日期时间选择器所能够提供的最精确的时间选择视图。
            initialDate: new Date(),//初始化当前日期
            autoclose: true, //选中自动关闭
            pickerPosition: "bottom-right"
        });

        $("input[datetime]").datetimepicker({
            language: "zh-CN",
            format: "yyyy-mm-dd hh:ii:ss",//显示格式
            minView: "hour", // 日期时间选择器所能够提供的最精确的时间选择视图。
            initialDate: new Date(),//初始化当前日期
            autoclose: true, //选中自动关闭
            pickerPosition: "bottom-right"
        });

        // 按需加载
        if($("select[owner]").size()) {
            $.ajax({
                url: "/user/owners.json",
                success(data) {
                    // data: ["10000|张三", ...]
                    var html = "";
                    $(data).each(function () {
                        html += "<option>"+this+"</option>"
                    })
                    $("select[owner]").html(html);
                }
            })
        }

        // 从缓存中加载下拉选项
        if($("select[options]").size()) {

            var types = [];
            $("select[options]").each(function () {
                var type = $(this).attr("options");
                // 数组的indexOf方法：判断数组中是否存在指定元素的下标，不存在返回-1
                if(types.indexOf(type) == -1) {
                    types.push(type); // push: 向数组中添加元素
                    $.ajax({
                        url: "/cache/options.json?type="+type,
                        success(data) {
                            // data: ["虚假线索","将来联系",...]
                            var html = "";
                            $(data).each(function () {
                                html += "<option>"+this+"</option>";
                            });
                            $("select[options="+type+"]").append(html);
                        }
                    })
                }
            });

        }

    })
</script>
