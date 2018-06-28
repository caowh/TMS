<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"
    />
    <title>GET</title>
    <link rel="icon" href="/assets/img/get-icon.png" type="image/x-icon"/>
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"
    />
    <!--[if lt IE 9]>
    <link rel="stylesheet" type="text/css" href="/plugins/jquery-ui/jquery.ui.1.10.2.ie.css"
    />
    <![endif]-->
    <link href="/assets/css/main.css" rel="stylesheet" type="text/css" />
    <link href="/assets/css/plugins.css" rel="stylesheet" type="text/css" />
    <link href="/assets/css/responsive.css" rel="stylesheet" type="text/css"
    />
    <link href="/assets/css/icons.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="/assets/css/fontawesome/font-awesome.min.css">
    <!--[if IE 7]>
    <link rel="stylesheet" href="/assets/css/fontawesome/font-awesome-ie7.min.css">
    <![endif]-->
    <!--[if IE 8]>
    <link href="/assets/css/ie8.css" rel="stylesheet" type="text/css" />
    <![endif]-->
    <script type="text/javascript" src="/assets/js/libs/jquery-1.10.2.min.js">
    </script>
    <script type="text/javascript" src="/plugins/jquery-ui/jquery-ui-1.10.2.custom.min.js">
    </script>
    <script type="text/javascript" src="/bootstrap/js/bootstrap.min.js">
    </script>
    <script type="text/javascript" src="/assets/js/libs/lodash.compat.min.js">
    </script>
    <!--[if lt IE 9]>
    <script src="/assets/js/libs/html5shiv.js">
    </script>
    <![endif]-->
    <script type="text/javascript" src="/plugins/touchpunch/jquery.ui.touch-punch.min.js">
    </script>
    <script type="text/javascript" src="/plugins/event.swipe/jquery.event.move.js">
    </script>
    <script type="text/javascript" src="/plugins/event.swipe/jquery.event.swipe.js">
    </script>
    <script type="text/javascript" src="/assets/js/libs/breakpoints.js">
    </script>
    <script type="text/javascript" src="/plugins/respond/respond.min.js">
    </script>
    <script type="text/javascript" src="/plugins/cookie/jquery.cookie.min.js">
    </script>
    <script type="text/javascript" src="/plugins/slimscroll/jquery.slimscroll.min.js">
    </script>
    <script type="text/javascript" src="/plugins/slimscroll/jquery.slimscroll.horizontal.min.js">
    </script>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="/plugins/flot/excanvas.min.js">
    </script>
    <![endif]-->
    <script type="text/javascript" src="/plugins/sparkline/jquery.sparkline.min.js">
    </script>
    <script type="text/javascript" src="/plugins/flot/jquery.flot.min.js">
    </script>
    <script type="text/javascript" src="/plugins/flot/jquery.flot.tooltip.min.js">
    </script>
    <script type="text/javascript" src="/plugins/flot/jquery.flot.resize.min.js">
    </script>
    <script type="text/javascript" src="/plugins/flot/jquery.flot.time.min.js">
    </script>
    <script type="text/javascript" src="/plugins/flot/jquery.flot.growraf.min.js">
    </script>
    <script type="text/javascript" src="/plugins/blockui/jquery.blockUI.min.js">
    </script>
    <script type="text/javascript" src="/plugins/fullcalendar/fullcalendar.min.js">
    </script>
    <script type="text/javascript" src="/plugins/noty/jquery.noty.js">
    </script>
    <script type="text/javascript" src="/plugins/noty/layouts/top.js">
    </script>
    <script type="text/javascript" src="/plugins/noty/themes/default.js">
    </script>
    <script type="text/javascript" src="/plugins/uniform/jquery.uniform.min.js">
    </script>
    <script type="text/javascript" src="/plugins/select2/select2.min.js">
    </script>
    <script type="text/javascript" src="/assets/js/app.js">
    </script>
    <script type="text/javascript" src="/assets/js/plugins.js">
    </script>
    <script type="text/javascript" src="/assets/js/plugins.form-components.js">
    </script>
    <script>
        $(document).ready(function() {
            App.init();
            Plugins.init();
            FormComponents.init();
        });
    </script>
    <script type="text/javascript" src="/assets/js/echarts.min.js"></script>
</head>

<body>
<div class="container" style="background-color: rgba(109, 173, 189, 0.23);">
    <div class="page-header">
        <div class="page-title">
            <h3>
                监控地址：<strong></strong>
            </h3>
        </div>
        <div class="page-stats">
            地址切换为：
            <select>
                <option>192.168.4.167</option>
                <option>192.168.4.148</option>
                <option>192.168.4.172</option>
                <option>192.168.4.173</option>
            </select>
        </div>
    </div>
</div>
<div class="container">
        <div class="row row-bg">
            <div class="col-md-6">
                <div class="widget box">
                    <div class="widget-header">
                        <h4>
                            <i class="icon-reorder">
                            </i>
                            CPU使用情况
                        </h4>
                        <div class="toolbar no-padding">
                            <div class="btn-group">
                            <span class="btn btn-xs">
                                <button id="cpuPause" class="icon-pause">
                                </button>
                            </span>
                                <span class="btn btn-xs widget-collapse">
                                <i class="icon-angle-down">
                                </i>
                              </span>
                            </div>
                        </div>
                    </div>
                    <div class="widget-content">
                        <div class="chart" id="cpuUsage">
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="widget box">
                    <div class="widget-header">
                        <h4>
                            <i class="icon-reorder">
                            </i>
                            物理内存使用情况
                        </h4>
                        <div class="toolbar no-padding">
                            <div class="btn-group">
                            <span class="btn btn-xs">
                                <button id="memPause" class="icon-pause">
                                </button>
                            </span>
                                <span class="btn btn-xs widget-collapse">
                                <i class="icon-angle-down">
                                </i>
                              </span>
                            </div>
                        </div>
                    </div>
                    <div class="widget-content">
                        <div class="chart" id="memUsage">
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <div class="widget box">
                    <div class="widget-header">
                        <h4>
                            <i class="icon-reorder">
                            </i>
                            网络数据传输情况
                        </h4>
                        <div class="toolbar no-padding">
                            <div class="btn-group">
                            <span class="btn btn-xs">
                                <button id="trafficPause" class="icon-pause">
                                </button>
                            </span>
                                <span class="btn btn-xs widget-collapse">
                                <i class="icon-angle-down">
                                </i>
                              </span>
                            </div>
                        </div>
                    </div>
                    <div class="widget-content">
                        <div class="chart" id="traffic">
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="widget box">
                    <div class="widget-header">
                        <h4>
                            <i class="icon-reorder">
                            </i>
                            硬盘读写情况
                        </h4>
                        <div class="toolbar no-padding">
                            <div class="btn-group">
                            <span class="btn btn-xs">
                                <button id="diskIoPause" class="icon-pause">
                                </button>
                            </span>
                                <span class="btn btn-xs widget-collapse">
                                <i class="icon-angle-down">
                                </i>
                              </span>
                            </div>
                        </div>
                    </div>
                    <div class="widget-content">
                        <div class="chart" id="diskIo">
                        </div>
                    </div>
                </div>
            </div>
        </div>
</div>
<script>
    var address="";
    Array.prototype.pushHasMax=function(item,max) {
        if(max == undefined ||max == null ||max <= 0){
            max = 60
        }
        if(this.length>=max){
            this.shift()
        }
        this.push(item)
    }

    var cpuUsageChart = echarts.init(document.getElementById('cpuUsage'));
    var cpuUsageArr = new Array();
    function cpuUsage() {
        $.getJSON("/linuxMonitor/cpuUsage.do?url="+address,
            function (data) {
                if(data.code==1){
//                    data.result[1]['before']=data.result[1]['value']
//                    if(cpuUsageArr.length==0){
//                        data.result[1]['before']=0
//                    }else {
//                        data.result[1]['value']=data.result[1]['value']-cpuUsageArr[cpuUsageArr.length-1]['before']
//                    }
                    cpuUsageArr.pushHasMax(data.result[1])
                    cpuUsageChart.setOption(option = {
                        grid: {
                            containLabel: true,
                            y2:0
                        },
                        xAxis: {
                            type: 'time',
                            name: '时间（s）',
                            interval: 60*1000,
                            axisLine: {
                                lineStyle: {
                                    color: '#ccc'
                                }
                            },
                            nameTextStyle: {
                                color: '#333'
                            },
                            axisTick: {
                                show: false
                            }
                        },
                        toolbox: {
                            show: true,
                            feature: {
                                dataZoom: {
                                    yAxisIndex: 'none'
                                },
                                saveAsImage: {}
                            }
                        },
                        dataZoom: {
                            type: 'inside',
                            xAxisIndex: 0,
                            filterMode: 'empty',
                            start: 0,
                            end: 100
                        },
                        yAxis: {
                            name: 'CPU使用率',
                            max: 100,
                            min: 0,
                            axisLine: {
                                lineStyle: {
                                    color: '#ccc'
                                }
                            },
                            nameTextStyle: {
                                color: '#333'
                            },
                            axisLabel: {
                                formatter: '{value} %',
                                textStyle: {
                                    color: '#333'
                                }
                            },
                            interval: 25,
                            splitLine: {
                                lineStyle: {
                                    type: 'dashed',
                                    color: '#e2e6a'

                                }
                            },
                            splitArea: {
                                show: true,
                                areaStyle: {
                                    color: ['#fbfbfb', '#fbfbfb']
                                }
                            }
                        },
                        visualMap: {
                            show: false,
                            pieces: [{
                                gt: 0,
                                lte: 25,
                                color: '#096'
                            }, {
                                gt: 25,
                                lte: 50,
                                color: '#4b7cde'
                            }, {
                                gt: 50,
                                lte: 75,
                                color: '#fea621'
                            }, {
                                gt: 75,
                                lte: 100,
                                color: '#f72c2e'
                            }],
                            outOfRange: {
                                color: '#999'
                            }
                        },
                        legend: {
                            show: false
                        },
                        series: [{
                            name: 'CPU使用率',
                            type: 'line',
                            hoverAnimation: false,
                            lineStyle: {
                                normal: {
                                    width: 1
                                }
                            },
                            itemStyle: {
                                normal: {
                                    opacity: 0
                                }
                            },
                            data: cpuUsageArr.map(function(item) {
                                return {
                                    name: Math.round(item.value*100)/100,
                                    value: [item.time, Math.round(item.value*100)/100]
                                }
                            }),
                            areaStyle: {
                                normal: {
                                    color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                                        offset: 1,
                                        color: '#c5c5e1' // 0% 处的颜色
                                    }, {
                                        offset: 0,
                                        color: '#f9fafb' // 100% 处的颜色
                                    }], false),
                                    opacity: 0.4
                                }
                            }
                        }],
                        tooltip: {
                            trigger: 'axis',
                        }
                    });
                }
            }
        );
    }
    function cpuUsageHide() {
        $.getJSON("/linuxMonitor/cpuUsage.do?url="+address,
            function (data) {
                if(data.code==1){
                    cpuUsageArr.pushHasMax(data.result[1])
                }
            }
        );
    }

    var memUsageChart = echarts.init(document.getElementById('memUsage'));
    var memUsageArr = new Array();
    function memUsage() {
        $.getJSON("/linuxMonitor/memUsage.do?url="+address,
            function (data) {
                if(data.code==1){
                    var first = true;
                    if(memUsageArr.length>0){
                        first = false
                    }
                    var result=data.result;
                    var usedPercent=Math.round((result.usage/result.total)*100*100)/100;
                    var dict={}
                    dict['value']=usedPercent
                    dict['time']=result.time
                    memUsageArr.pushHasMax(dict)
                    var option;
                    if(first){
                        option = {
                            grid: {
                                containLabel: true,
                                y2:0
                            },
                            xAxis: {
                                type: 'time',
                                name: '时间（s）',
//                                interval: 60*1000,
                                axisLine: {
                                    lineStyle: {
                                        color: '#ccc'
                                    }
                                },
                                nameTextStyle: {
                                    color: '#333'
                                },
                                axisTick: {
                                    show: false
                                }
                            },
                            toolbox: {
                                show: true,
                                feature: {
                                    dataZoom: {
                                        yAxisIndex: 'none'
                                    },
                                    saveAsImage: {}
                                }
                            },
                            dataZoom: {
                                type: 'inside',
                                xAxisIndex: 0,
                                filterMode: 'empty',
                                start: 0,
                                end: 100
                            },
                            yAxis: {
                                name: '内存使用率(总量：'+result.total+'MB)',
                                max: 100,
                                min: 0,
                                axisLine: {
                                    lineStyle: {
                                        color: '#ccc'
                                    }
                                },
                                nameTextStyle: {
                                    color: '#333'
                                },
                                axisLabel: {
                                    formatter: '{value} %',
                                    textStyle: {
                                        color: '#333'
                                    }
                                },
                                interval: 25,
                                splitLine: {
                                    lineStyle: {
                                        type: 'dashed',
                                        color: '#e2e6a'

                                    }
                                },
                                splitArea: {
                                    show: true,
                                    areaStyle: {
                                        color: ['#fbfbfb', '#fbfbfb']
                                    }
                                }
                            },
                            visualMap: {
                                show: false,
                                pieces: [{
                                    gt: 0,
                                    lte: 25,
                                    color: '#096'
                                }, {
                                    gt: 25,
                                    lte: 50,
                                    color: '#4b7cde'
                                }, {
                                    gt: 50,
                                    lte: 75,
                                    color: '#fea621'
                                }, {
                                    gt: 75,
                                    lte: 100,
                                    color: '#f72c2e'
                                }],
                                outOfRange: {
                                    color: '#999'
                                }
                            },
                            legend: {
                                show: false
                            },
                            series: [{
                                name: '内存使用率',
                                type: 'line',
                                showSymbol: false,
                                hoverAnimation: false,
                                lineStyle: {
                                    normal: {
                                        width: 1
                                    }
                                },
                                itemStyle: {
                                    normal: {
                                        opacity: 0
                                    }
                                },
                                data: memUsageArr.map(function(item) {
                                    return {
                                        name: Math.round(item.value*100)/100,
                                        value: [item.time, Math.round(item.value*100)/100]
                                    }
                                }),
                                areaStyle: {
                                    normal: {
                                        color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                                            offset: 1,
                                            color: '#40e112' // 0% 处的颜色
                                        }, {
                                            offset: 0,
                                            color: '#2dfb2d' // 100% 处的颜色
                                        }], false),
                                        opacity: 0.4
                                    }
                                }
                            }],
                            tooltip: {
                                trigger: 'axis',
                            }
                        }
                    }else {
                        option = {series:[{data:memUsageArr.map(function(item) {
                            return {
                                name: Math.round(item.value*100)/100,
                                value: [item.time, Math.round(item.value*100)/100]
                            }
                        })}]}
                    }
                    memUsageChart.setOption(option);
                }
            }
        );
    }
    function memUsageHide() {
        $.getJSON("/linuxMonitor/memUsage.do?url="+address,
            function (data) {
                if(data.code==1){
                    var result=data.result;
                    var usedPercent=Math.round((result.usage/result.total)*100*100)/100;
                    var dict={}
                    dict['value']=usedPercent
                    dict['time']=result.time
                    memUsageArr.pushHasMax(dict)
                }
            }
        );
    }

    var trafficChart = echarts.init(document.getElementById('traffic'));
    var trafficOutArr = new Array();
    var trafficInArr = new Array();
    function traffic() {
        $.getJSON("/linuxMonitor/traffic.do?url="+address,
            function (data) {
                if(data.code==1){
                    var result=data.result;
                    var dict_traffic_out={}
                    dict_traffic_out['total']=result.traffic_out
                    if(trafficOutArr.length==0){
                        dict_traffic_out['value']=0
                    }else {
                        dict_traffic_out['value']=result.traffic_out-trafficOutArr[trafficOutArr.length-1]['total']
                    }
                    dict_traffic_out['time']=result.time
                    trafficOutArr.pushHasMax(dict_traffic_out)
                    var dict_traffic_in={}
                    dict_traffic_in['total']=result.traffic_in
                    if(trafficInArr.length==0){
                        dict_traffic_in['value']=0
                    }else {
                        dict_traffic_in['value']=result.traffic_in-trafficInArr[trafficInArr.length-1]['total']
                    }
                    dict_traffic_in['time']=result.time
                    trafficInArr.pushHasMax(dict_traffic_in)
                    trafficChart.setOption(option = {
                        grid: {
                            containLabel: true,
                            y2:0
                        },
                        legend: {
                            orient : 'vertical',
                            x : 'left',
                            data:['接收数据量','发送数据量']
                        },
                        xAxis: {
                            type: 'time',
                            name: '时间（s）',
                            interval: 60*1000,
                            axisLine: {
                                lineStyle: {
                                    color: '#ccc'
                                }
                            },
                            nameTextStyle: {
                                color: '#333'
                            },
                            axisTick: {
                                show: false
                            }
                        },
                        toolbox: {
                            show: true,
                            feature: {
                                dataZoom: {
                                    yAxisIndex: 'none'
                                },
                                saveAsImage: {}
                            }
                        },
                        dataZoom: {
                            type: 'inside',
                            xAxisIndex: 0,
                            filterMode: 'empty',
                            start: 0,
                            end: 100
                        },
                        yAxis: {
                            name: '数据量（bytes）',
                            max: 10000000,
                            min: 0,
                            axisLine: {
                                lineStyle: {
                                    color: '#ccc'
                                }
                            },
                            nameTextStyle: {
                                color: '#333'
                            },
                            axisLabel: {
                                textStyle: {
                                    color: '#333'
                                }
                            },
                            interval: 2500000,
                            splitLine: {
                                lineStyle: {
                                    type: 'dashed',
                                    color: '#e2e6a'

                                }
                            },
                            splitArea: {
                                show: true,
                                areaStyle: {
                                    color: ['#fbfbfb', '#fbfbfb']
                                }
                            }
                        },
                        visualMap: {
                            show: false,
                            pieces: [{
                                gt: 0,
                                lte: 2500000,
                                color: '#096'
                            }, {
                                gt: 2500000,
                                lte: 5000000,
                                color: '#4b7cde'
                            }, {
                                gt: 5000000,
                                lte: 7500000,
                                color: '#fea621'
                            }, {
                                gt: 7500000,
                                lte: 10000000,
                                color: '#f72c2e'
                            }],
                            outOfRange: {
                                color: '#999'
                            }
                        },
                        series: [
                            {
                            name: '接收数据量',
                            type: 'line',
                            hoverAnimation: false,
                            lineStyle: {
                                normal: {
                                    width: 1
                                }
                            },
                            itemStyle: {
                                normal: {
                                    opacity: 0
                                }
                            },
                            data: trafficInArr.map(function(item) {
                                return {
                                    name: Math.round(item.value*100)/100,
                                    value: [item.time, Math.round(item.value*100)/100]
                                }
                            }),
                            areaStyle: {
                                normal: {
                                    color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                                        offset: 1,
                                        color: '#c5c5e1' // 0% 处的颜色
                                    }, {
                                        offset: 0,
                                        color: '#f9fafb' // 100% 处的颜色
                                    }], false),
                                    opacity: 0.4
                                }
                            }
                        },
                            {
                                name: '发送数据量',
                                type: 'line',
                                hoverAnimation: false,
                                lineStyle: {
                                    normal: {
                                        width: 1
                                    }
                                },
                                itemStyle: {
                                    normal: {
                                        opacity: 0
                                    }
                                },
                                data: trafficOutArr.map(function(item) {
                                    return {
                                        name: Math.round(item.value*100)/100,
                                        value: [item.time, Math.round(item.value*100)/100]
                                    }
                                }),
                                areaStyle: {
                                    normal: {
                                        color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                                            offset: 1,
                                            color: '#c5c5e1' // 0% 处的颜色
                                        }, {
                                            offset: 0,
                                            color: '#f9fafb' // 100% 处的颜色
                                        }], false),
                                        opacity: 0.4
                                    }
                                }
                            }
                        ],
                        tooltip: {
                            trigger: 'axis',
                        }
                    });
                }
            }
        );
    }
    function trafficHide() {
        $.getJSON("/linuxMonitor/traffic.do?url="+address,
            function (data) {
                if(data.code==1){
                    var result=data.result;
                    var dict_traffic_out={}
                    dict_traffic_out['total']=result.traffic_out
                    if(trafficOutArr.length==0){
                        dict_traffic_out['value']=0
                    }else {
                        dict_traffic_out['value']=result.traffic_out-trafficOutArr[trafficOutArr.length-1]['total']
                    }
                    dict_traffic_out['time']=result.time
                    trafficOutArr.pushHasMax(dict_traffic_out)
                    var dict_traffic_in={}
                    dict_traffic_in['total']=result.traffic_in
                    if(trafficInArr.length==0){
                        dict_traffic_in['value']=0
                    }else {
                        dict_traffic_in['value']=result.traffic_in-trafficInArr[trafficInArr.length-1]['total']
                    }
                    dict_traffic_in['time']=result.time
                    trafficInArr.pushHasMax(dict_traffic_in)
                }
            }
        );
    }

    var diskIoChart = echarts.init(document.getElementById('diskIo'));
    var diskInArr = new Array();
    var diskOutArr = new Array();
    function diskIo() {
        $.getJSON("/linuxMonitor/diskIo.do?url="+address,
            function (data) {
                if(data.code==1){
                    var result=data.result;
                    var element=new Array();
                    element.push("all")
                    var inAll=0,outAll=0,time;
                    for(var i = 0; i < result.length; i++){
                        inAll+=parseInt(result[i][1])
                        outAll+=parseInt(result[i][2])
                        time=result[i][3]
                    }
                    element.push(inAll)
                    element.push(outAll)
                    element.push(time)
                    var dict_diskIn={}
                    dict_diskIn['total']=element[1]
                    if(diskInArr.length==0){
                        dict_diskIn['value']=0
                    }else {
                        dict_diskIn['value']=element[1]-diskInArr[diskInArr.length-1]['total']
                    }
                    dict_diskIn['time']=element[3]
                    diskInArr.pushHasMax(dict_diskIn)
                    var dict_diskOut={}
                    dict_diskOut['total']=element[2]
                    if(diskOutArr.length==0){
                        dict_diskOut['value']=0
                    }else {
                        dict_diskOut['value']=element[2]-diskOutArr[diskOutArr.length-1]['total']
                    }
                    dict_diskOut['time']=element[3]
                    diskOutArr.pushHasMax(dict_diskOut)
                    diskIoChart.setOption(option = {
                        grid: {
                            containLabel: true,
                            y2:0
                        },
                        legend: {
                            orient : 'vertical',
                            x : 'left',
                            data:['读操作','写操作']
                        },
                        xAxis: {
                            type: 'time',
                            name: '时间（s）',
                            interval: 60*1000,
                            axisLine: {
                                lineStyle: {
                                    color: '#ccc'
                                }
                            },
                            nameTextStyle: {
                                color: '#333'
                            },
                            axisTick: {
                                show: false
                            }
                        },
                        toolbox: {
                            show: true,
                            feature: {
                                dataZoom: {
                                    yAxisIndex: 'none'
                                },
                                saveAsImage: {}
                            }
                        },
                        dataZoom: {
                            type: 'inside',
                            xAxisIndex: 0,
                            filterMode: 'empty',
                            start: 0,
                            end: 100
                        },
                        yAxis: {
                            name: '操作用时（ms）',
                            max: 2000,
                            min: 0,
                            axisLine: {
                                lineStyle: {
                                    color: '#ccc'
                                }
                            },
                            nameTextStyle: {
                                color: '#333'
                            },
                            axisLabel: {
                                textStyle: {
                                    color: '#333'
                                }
                            },
                            interval: 500,
                            splitLine: {
                                lineStyle: {
                                    type: 'dashed',
                                    color: '#e2e6a'

                                }
                            },
                            splitArea: {
                                show: true,
                                areaStyle: {
                                    color: ['#fbfbfb', '#fbfbfb']
                                }
                            }
                        },
                        visualMap: {
                            show: false,
                            pieces: [{
                                gt: 0,
                                lte: 500,
                                color: '#096'
                            }, {
                                gt: 500,
                                lte: 1000,
                                color: '#4b7cde'
                            }, {
                                gt: 1000,
                                lte: 1500,
                                color: '#fea621'
                            }, {
                                gt: 1500,
                                lte: 2000,
                                color: '#f72c2e'
                            }],
                            outOfRange: {
                                color: '#999'
                            }
                        },
                        series: [
                            {
                                name: '读操作',
                                type: 'line',
                                hoverAnimation: false,
                                lineStyle: {
                                    normal: {
                                        width: 1
                                    }
                                },
                                itemStyle: {
                                    normal: {
                                        opacity: 0
                                    }
                                },
                                data: diskInArr.map(function(item) {
                                    return {
                                        name: item.value,
                                        value: [item.time, item.value]
                                    }
                                }),
                                areaStyle: {
                                    normal: {
                                        color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                                            offset: 1,
                                            color: '#c5c5e1' // 0% 处的颜色
                                        }, {
                                            offset: 0,
                                            color: '#f9fafb' // 100% 处的颜色
                                        }], false),
                                        opacity: 0.4
                                    }
                                }
                            },
                            {
                                name: '写操作',
                                type: 'line',
                                hoverAnimation: false,
                                lineStyle: {
                                    normal: {
                                        width: 1
                                    }
                                },
                                itemStyle: {
                                    normal: {
                                        opacity: 0
                                    }
                                },
                                data: diskOutArr.map(function(item) {
                                    return {
                                        name: item.value,
                                        value: [item.time,item.value]
                                    }
                                }),
                                areaStyle: {
                                    normal: {
                                        color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
                                            offset: 1,
                                            color: '#c5c5e1' // 0% 处的颜色
                                        }, {
                                            offset: 0,
                                            color: '#f9fafb' // 100% 处的颜色
                                        }], false),
                                        opacity: 0.4
                                    }
                                }
                            }
                        ],
                        tooltip: {
                            trigger: 'axis',
                        }
                    });
                }
            }
        );
    }
    function diskIoHide() {
        $.getJSON("/linuxMonitor/diskIo.do?url="+address,
            function (data) {
                if(data.code==1){
                    var result=data.result;
                    var element=new Array();
                    element.push("all")
                    var inAll=0,outAll=0,time;
                    for(var i = 0; i < result.length; i++){
                        inAll+=parseInt(result[i][1])
                        outAll+=parseInt(result[i][2])
                        time=result[i][3]
                    }
                    element.push(inAll)
                    element.push(outAll)
                    element.push(time)
                    var dict_diskIn={}
                    dict_diskIn['total']=element[1]
                    if(diskInArr.length==0){
                        dict_diskIn['value']=0
                    }else {
                        dict_diskIn['value']=element[1]-diskInArr[diskInArr.length-1]['total']
                    }
                    dict_diskIn['time']=element[3]
                    diskInArr.pushHasMax(dict_diskIn)
                    var dict_diskOut={}
                    dict_diskOut['total']=element[2]
                    if(diskOutArr.length==0){
                        dict_diskOut['value']=0
                    }else {
                        dict_diskOut['value']=element[2]-diskOutArr[diskOutArr.length-1]['total']
                    }
                    dict_diskOut['time']=element[3]
                    diskOutArr.pushHasMax(dict_diskOut)
                }
            }
        );
    }

    $(document).ready(function() {
        $('.page-stats select').change(function () {
            var ip = $(this).find("option:selected").text()
            window.location.href="/linuxMonitor/index.do?ip="+ip;
        })

        var ip = '${ip}';
        address="http://"+ip+":8000";
        $('.page-title h3 strong').text(ip)
        $('.page-stats select').find('option').each(function () {
            if($(this).text() == ip){
                $(this).attr("selected","selected")
                return false
            }
        })
        var intervalId_Cpu = setInterval(cpuUsage,1000)
        var intervalId_Mem = setInterval(memUsage,1000)
        var intervalId_Traffic = setInterval(traffic,1000)
        var intervalId_DiskIo = setInterval(diskIo,1000)
        var intervalIdHide_Cpu,intervalIdHide_Mem,intervalIdHide_Traffic,intervalIdHide_DiskIo;
        $('#cpuPause').click(function () {
            if($('#cpuPause').attr("class")=="icon-pause"){
                $('#cpuPause').removeClass("icon-pause").addClass("icon-play")
                clearInterval(intervalId_Cpu)
                intervalIdHide_Cpu = setInterval(cpuUsageHide,1000)
            }else {
                $('#cpuPause').removeClass("icon-play").addClass("icon-pause")
                clearInterval(intervalIdHide_Cpu)
                intervalId_Cpu = setInterval(cpuUsage,1000)
            }
        })
        $('#memPause').click(function () {
            if($('#memPause').attr("class")=="icon-pause"){
                $('#memPause').removeClass("icon-pause").addClass("icon-play")
                clearInterval(intervalId_Mem)
                intervalIdHide_Mem = setInterval(memUsageHide,1000)
            }else {
                $('#memPause').removeClass("icon-play").addClass("icon-pause")
                clearInterval(intervalIdHide_Mem)
                intervalId_Mem = setInterval(memUsage,1000)
            }
        })
        $('#trafficPause').click(function () {
            if($('#trafficPause').attr("class")=="icon-pause"){
                $('#trafficPause').removeClass("icon-pause").addClass("icon-play")
                clearInterval(intervalId_Traffic)
                intervalIdHide_Traffic = setInterval(trafficHide,1000)
            }else {
                $('#trafficPause').removeClass("icon-play").addClass("icon-pause")
                clearInterval(intervalIdHide_Traffic)
                intervalId_Traffic = setInterval(traffic,1000)
            }
        })
        $('#diskIoPause').click(function () {
            if($('#diskIoPause').attr("class")=="icon-pause"){
                $('#diskIoPause').removeClass("icon-pause").addClass("icon-play")
                clearInterval(intervalId_DiskIo)
                intervalIdHide_DiskIo = setInterval(diskIoHide,1000)
            }else {
                $('#diskIoPause').removeClass("icon-play").addClass("icon-pause")
                clearInterval(intervalIdHide_DiskIo)
                intervalId_DiskIo = setInterval(diskIo,1000)
            }
        })
    })

</script>
</body>

</html>


