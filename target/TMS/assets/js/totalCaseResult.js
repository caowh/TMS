/**
 * Created by user on 2017/8/29.
 */
function drawSeverity(res) {
    if(res.code==1){
        var myChart = echarts.init(document.getElementById('severity'));
        var option = {
            title : {
                text: '问题严重级别分布情况',
                subtext:res.time,
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient : 'vertical',
                x : 'left',
                data:["致命问题","严重问题","一般问题","建议问题"]
            },
            toolbox: {
                feature : {
                    dataView : {show: true, readOnly: true},
                    saveAsImage : {show: true}
                }
            },
            series : [
                {
                    name:'问题严重级别分布情况',
                    type:'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:JSON.parse(res.result),
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(255, 255, 255, 0.3)'
                        }
                    },
                    itemStyle: {
                        normal: {
                            label: {
                                show: true,
                                formatter: '{b} : {c} ({d}%)'
                            }
                        },
                        labelLine: {show: true}
                    }
                }
            ]
        };
        myChart.setOption(option);
        if(res.pass){
            $('#severity-light').addClass('light-normal');
        }else {
            $('#severity-light').addClass('light-alarm');
        }
    }else {
        $('#severity').html(res.message)
    }
}
function drawSeverityCompare(res) {
    if(res.code==1){
        var myChart = echarts.init(document.getElementById('severityCompare'));
        var myData = ['致命问题', '严重问题', '一般问题', '建议问题','问题总数'];
        var databeast = res.result;
        var versions = res.versions;
        var option;
        if(versions.length==2){
            var max=0;
            $.each(databeast,function (index,item) {
                $.each(item,function (index1,item1) {
                    var number=parseInt(item1)
                    if(number>max){
                        max=number
                    }
                });
            });
            $.each(databeast,function (index,item) {
                item.push(max)
            });
            option = {
                baseOption: {
                    title: {
                        text:'问题严重级别情况版本对比',
                        subtext: res.time,
                        x:'center'
                    },
                    legend: {
                        orient : 'vertical',
                        x : 'left',
                        data:versions
                    },
                    tooltip: {
                        show: true,
                        trigger: 'axis',
                        formatter: '{b}<br/>{a}: {c}个',
                        axisPointer: {
                            type: 'shadow',
                        }
                    },

                    toolbox:{
                        feature:{
                            dataView: {show: true, readOnly: true},
                            saveAsImage: {}
                        }
                    },

                    grid: [{
                        show: false,
                        left: '4%',
                        top: 60,
                        bottom: 0,
                        containLabel: true,
                        width: '46%',
                    }, {
                        show: false,
                        left: '50.5%',
                        top: 80,
                        bottom: 0,
                        width: '0%',
                    }, {
                        show: false,
                        right: '4%',
                        top: 60,
                        bottom: 0,
                        containLabel: true,
                        width: '46%',
                    }],

                    xAxis: [
                        {
                            type: 'value',
                            inverse: true,
                            axisLine: {
                                show: false,
                            },
                            axisTick: {
                                show: false,
                            },
                            position: 'top',
                            axisLabel: {
                                show: true,
                                textStyle: {
                                    color: '#B2B2B2',
                                    fontSize: 12,
                                },
                            },
                            splitLine: {
                                show: true,
                                lineStyle: {
                                    color: '#1F2022',
                                    width: 1,
                                    type: 'solid',
                                },
                            },
                        }, {
                            gridIndex: 1,
                            show: false,
                        }, {
                            gridIndex: 2,
                            type: 'value',
                            axisLine: {
                                show: false,
                            },
                            axisTick: {
                                show: false,
                            },
                            position: 'top',
                            axisLabel: {
                                show: true,
                                textStyle: {
                                    color: '#B2B2B2',
                                    fontSize: 12,
                                },
                            },
                            splitLine: {
                                show: true,
                                lineStyle: {
                                    color: '#1F2022',
                                    width: 1,
                                    type: 'solid',
                                },
                            },
                        }, ],
                    yAxis: [{
                        type: 'category',
                        inverse: true,
                        position: 'right',
                        axisLine: {
                            show: false
                        },
                        axisTick: {
                            show: false
                        },
                        axisLabel: {
                            show: false,
                            margin: 8,
                            textStyle: {
                                color: '#9D9EA0',
                                fontSize: 12,
                            },

                        },
                        data: myData
                    }, {
                        gridIndex: 1,
                        type: 'category',
                        inverse: true,
                        position: 'left',
                        axisLine: {
                            show: false
                        },
                        axisTick: {
                            show: false
                        },
                        axisLabel: {
                            show: true,
                            textStyle: {
                                color: '#9D9EA0',
                                fontSize: 12,
                            }
                        },
                        data: myData.map(function(value) {
                            return {
                                value: value,
                                textStyle: {
                                    align: 'center',
                                }
                            }
                        }),
                    }, {
                        gridIndex: 2,
                        type: 'category',
                        inverse: true,
                        position: 'left',
                        axisLine: {
                            show: false
                        },
                        axisTick: {
                            show: false
                        },
                        axisLabel: {
                            show: false,
                            textStyle: {
                                color: '#9D9EA0',
                                fontSize: 12,
                            },

                        },
                        data: myData,
                    }, ],
                    series: [{
                        name: versions[0],
                        type: 'bar',
                        barGap: 20,
                        barWidth: 20,
                        label: {
                            normal: {
                                show: false,
                            },
                            emphasis: {
                                show: true,
                                position: 'left',
                                offset: [0, 0],
                                textStyle: {
                                    color: '#fff',
                                    fontSize: 14,
                                },
                            },
                        },
                        itemStyle: {
                            normal: {
                                color: '#659F83',
                            },
                            emphasis: {
                                color: '#08C7AE',
                            }
                        },
                        data: databeast[0]
                    },


                        {
                            name: versions[1],
                            type: 'bar',
                            barGap: 20,
                            barWidth: 20,
                            xAxisIndex: 2,
                            yAxisIndex: 2,
                            label: {
                                normal: {
                                    show: false,
                                },
                                emphasis: {
                                    show: true,
                                    position: 'right',
                                    offset: [0, 0],
                                    textStyle: {
                                        color: '#fff',
                                        fontSize: 14,
                                    },
                                },
                            },
                            itemStyle: {
                                normal: {
                                    color: '#F68989',
                                },
                                emphasis: {
                                    color: '#F94646',
                                },
                            },
                            data:  databeast[1]
                        }
                    ]

                }
            };
        }else {
            option = {
                title: {
                    text: '问题严重级别情况版本对比',
                    subtext:res.time,
                    x:'center'
                },
                tooltip: {
                    trigger: 'axis'
                },
                dataZoom:
                    {
                        type: 'inside',
                        xAxisIndex: 0,
                        filterMode: 'empty',
                        start: 0,
                        end: 100
                    },
                grid: {
                    containLabel: true,
                    y2:0
                },
                legend: {
                    orient : 'vertical',
                    x : 'left',
                    data:res.versions
                },
                toolbox: {
                    show: true,
                    feature: {
                        dataView: {readOnly: true},
                        magicType: {type: ['line', 'bar']},
                        saveAsImage: {}
                    }
                },
                xAxis:  {
                    type: 'category',
                    boundaryGap: false,
                    data: myData
                },
                yAxis: {
                    type: 'value',
                    name: '问题个数',
                    nameLocation:"middle",
                    nameGap:50,
                    axisLabel: {
                        formatter: '{value} %'
                    }
                },
                series: []
            };
            $.each(res.versions,function (index,element) {
                option.series.push({
                    name:element,
                    type:'line',
                    data:databeast[index]
                })
            })
        }
        myChart.setOption(option);
        if(res.pass){
            $('#severityCompare-light').addClass('light-normal');
        }else {
            $('#severityCompare-light').addClass('light-alarm');
        }
    }else {
        $('#severityCompare').html(res.message)
    }
}
function drawCaseBugCount(res) {
    if(res.code==1){
        var myChart = echarts.init(document.getElementById('caseBugCount'));
        var option = {
            title : {
                text: '子模块用例问题分布情况',
                subtext:res.time,
                x:'center'
            },
            tooltip: {
                trigger: 'axis'
            },
            dataZoom:
                {
                    type: 'inside',
                    xAxisIndex: 0,
                    filterMode: 'empty',
                    start: 0,
                    end: 100
                },
            toolbox: {
                feature: {
                    dataView: {
                        show: true,
                        readOnly: true
                    },
                    saveAsImage: {
                        show: true
                    }
                }
            },
            grid: {
                containLabel: true,
                y2:0
            },
            legend: {
                orient : 'vertical',
                x : 'left',
                data:["问题数/用例数","用例数","问题数"]
            },
            xAxis: [{
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                data: res.suiteList
            }],
            yAxis: [{
                type: 'value',
                name: '百分比',
                position: 'right',
                axisLabel: {
                    formatter: '{value} %'
                }
            }, {
                type: 'value',
                name: '数量',
                position: 'left'
            }],
            series: [{
                name: '问题数/用例数',
                type: 'line',
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                    }
                },
                lineStyle: {
                    normal: {
                        width: 3,
                        shadowColor: 'rgba(0,0,0,0.4)',
                        shadowBlur: 10,
                        shadowOffsetY: 10
                    }
                },
                data: res.ratioList
            }, {
                name: '用例数',
                type: 'bar',
                yAxisIndex: 1,
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                data: res.caseCountList
            }, {
                name: '问题数',
                type: 'bar',
                yAxisIndex: 1,
                label: {
                    normal: {
                        show: true,
                        position: 'top'
                    }
                },
                data: res.bugCountList
            }]
        };
        myChart.setOption(option);
        if(res.pass){
            $('#caseBugCount-light').addClass('light-normal');
        }else {
            $('#caseBugCount-light').addClass('light-alarm');
        }
    }else {
        $('#caseBugCount').html(res.message)
    }
}
function drawCaseBugRatioCompare(res) {
    if(res.code==1){
        var myChart = echarts.init(document.getElementById('caseBugRatioCompare'));
        var option = {
            title: {
                text: '子模块用例问题分布版本对比',
                subtext:res.time,
                x:'center'
            },
            tooltip: {
                trigger: 'axis'
            },
            dataZoom:
                {
                    type: 'inside',
                    xAxisIndex: 0,
                    filterMode: 'empty',
                    start: 0,
                    end: 100
                },
            grid: {
                containLabel: true,
                y2:0
            },
            legend: {
                orient : 'vertical',
                x : 'left',
                data:res.versions
            },
            toolbox: {
                show: true,
                feature: {
                    dataView: {readOnly: true},
                    magicType: {type: ['line', 'bar']},
                    saveAsImage: {}
                }
            },
            xAxis:  {
                type: 'category',
                boundaryGap: false,
                data: res.suiteList
            },
            yAxis: {
                type: 'value',
                name: '问题数/用例数',
                nameLocation:"middle",
                nameGap:50,
                axisLabel: {
                    formatter: '{value} %'
                }
            },
            series: []
        };
        $.each(res.versions,function (index,element) {
            option.series.push({
                name:element,
                type:'line',
                data:res.result[index],
                markPoint: {
                    data: [
                        {type: 'max', name: '最大值'},
                        {type: 'min', name: '最小值'}
                    ]
                },
                markLine: {
                    data: [
                        {type: 'average', name: '平均值'}
                    ]
                }
            })
        })
        myChart.setOption(option);
        if(res.pass){
            $('#caseBugRatioCompare-light').addClass('light-normal');
        }else {
            $('#caseBugRatioCompare-light').addClass('light-alarm');
        }
    }else {
        $('#caseBugRatioCompare').html(res.message)
    }
}

var totalCaseResult={
    init:function (planName) {
        /**左一图*/
        var severityData={
            planName:planName,
            type:"severity",
            node:"0"
        }
        getCaseAnalyseResult(severityData,drawSeverity)
        /**右一图*/
        var severityCompareData={
            planName:planName,
            type:"severityCompare",
            node:"0"
        }
        getCaseAnalyseResult(severityCompareData,drawSeverityCompare)

        /**左二图*/
        var caseBugCountData={
            planName:planName,
            type:"caseBugCount",
            node:"0"
        }
        getCaseAnalyseResult(caseBugCountData,drawCaseBugCount)


        /**右二图*/
        var caseBugRatioCompareData={
            planName:planName,
            type:"caseBugRatioCompare",
            node:"0"
        }
        getCaseAnalyseResult(caseBugRatioCompareData,drawCaseBugRatioCompare)

    }
}




