<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>ECharts</title>
    <!-- 引入刚刚下载的 ECharts 文件 -->
    <script src="/static/echarts.min.js"></script>
    <script src="/static/jquery/jquery-1.11.1-min.js"></script>

</head>
<body>
<!-- 为 ECharts 准备一个定义了宽高的 DOM -->
<div id="main" style="width: 1500px;height:700px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据

    var option2 = {
        tooltip: {
            trigger: 'item'
        },
        legend: {
            top: '5%',
                left: 'center'
        },
        series: [
            {
                name: 'Access From',
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                itemStyle: {
                    borderRadius: 10,
                    borderColor: '#fff',
                    borderWidth: 2
                },
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: '40',
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                //data: [{"name":"01资质审查","value":1},{"name":"02需求分析","value":6},{"name":"03价值建议","value":5},{"name":"04确定决策者","value":6},{"name":"05提案/报价","value":3},{"name":"06谈判/复审","value":1},{"name":"07成交","value":3},{"name":"08丢失的线索","value":3},{"name":"09因竞争丢失关闭","value":2}]
            }
        ]
    };

    var option = {
        xAxis: {
            type: 'category',
            data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                data: [120, 200, 150, 80, 70, 110, 130],
                type: 'bar',
                showBackground: true,
                backgroundStyle: {
                    color: 'rgba(180, 180, 180, 0.2)'
                }
            }
        ]
    };

    jQuery(function ($) {
        $.ajax({
            url: "/tran/charts.json",
            success(data) {
                // 替换真实数据
                //option.series[0].data = data;
                option.xAxis.data = $(data).map(function () {
                    return this.name
                }).get();
                option.series[0].data = $(data).map(function () {
                    return this.value;
                }).get();
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        })
    })
</script>
</body>
</html>
