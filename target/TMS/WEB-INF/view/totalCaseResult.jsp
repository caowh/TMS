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
    <style>
        .light{
            float: left;
            width: 38px;
            height: 35px;
            background: url("/assets/img/base.png") no-repeat center;
        }
        .light p{
            width: 100%;
            height: 100%;
        }
        .light-alarm{
            float: left;
            width: 38px;
            height: 35px;
            background: url("/assets/img/red.png") no-repeat center;
        }
        .light-normal{
            float: left;
            width: 38px;
            height: 35px;
            background: url("/assets/img/green.png") no-repeat center;
        }

        @-webkit-keyframes light {
            from{
                opacity: 1;
            }
            50%{
                opacity: 1;
            }
            to{
                opacity: 0;
            }
        }
        .light-alarm{
            animation:light 1s ease  infinite;
        }
        @keyframes light {
            from{
                opacity: 1;
            }
            50%{
                opacity: 1;
            }
            to{
                opacity: 0;
            }
        }

    </style>
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
            var planName="${planName}";
            totalCaseResult.init(planName)
        });
    </script>
    <script type="text/javascript" src="/assets/js/echarts.min.js"></script>
    <script type="text/javascript" src="/assets/js/data.js"></script>
    <script type="text/javascript" src="/assets/js/totalCaseResult.js"></script>
</head>

<body>
<div class="container" style="background-color: rgba(109, 173, 189, 0.23);">
    <div class="page-header">
        <div class="page-title">
            <h3>
                <img src="/assets/img/get-logo.png"  style="height: 40px;width: 118px">SKY EYES ON TEST
            </h3>
            <a href="/login.jsp">
                hello, guest，do you want broaden your horizons？just click!
            </a>
        </div>
        <ul class="page-stats">
                <li>
                    <div class="summary">
                  <span>
                    用例总数
                  </span>
                        <h3>
                            ${executeCount}
                        </h3>
                    </div>
                </li>
                <li>
                    <div class="summary">
                  <span>
                    问题总数
                  </span>
                        <h3>
                            ${severityCount}
                        </h3>
                    </div>
                </li>
            </ul>
    </div>
</div>
<div class="container">
        <div class="row row-bg">
            <div class="col-md-3">
                <div class="statbox widget box box-shadow">
                    <div class="widget-content">
                        <div class="visual blue">
                            <i class="icon-camera">
                            </i>
                        </div>
                        <div class="title">
                            模块
                        </div>
                        <div class="value">
                            ${planName}
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-3">
                <div class="statbox widget box box-shadow">
                    <div class="widget-content">
                        <div class="visual green">
                            <i class="icon-globe">
                            </i>
                        </div>
                        <div class="title">
                            被测件
                        </div>
                        <div class="value">
                            ${planMessage.get("uut")}
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-3">
                <div class="statbox widget box box-shadow">
                    <div class="widget-content">
                        <div class="visual yellow">
                            <i class="icon-desktop">
                            </i>
                        </div>
                        <div class="title">
                            环境
                        </div>
                        <div class="value">
                            ${planMessage.get("environment")}
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-3">
                <div class="statbox widget box box-shadow">
                    <div class="widget-content">
                        <div class="visual red">
                            <i class="icon-user">
                            </i>
                        </div>
                        <div class="title">
                            负责人
                        </div>
                        <div class="value">
                            ${planMessage.get("leader")}
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
                            问题严重情况分布图
                        </h4>
                        <div class="light"><p id="severity-light"></p></div>
                        <div class="toolbar no-padding">
                            <div class="btn-group">
                      <span class="btn btn-xs widget-collapse">
                        <i class="icon-angle-down">
                        </i>
                      </span>
                            </div>
                        </div>
                    </div>
                    <div class="widget-content">
                        <div class="chart" id="severity">
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
                            问题严重情况变化趋势图
                        </h4>
                        <div class="light"><p id="severityCompare-light"></p></div>
                        <div class="toolbar no-padding">
                            <div class="btn-group">
                      <span class="btn btn-xs widget-collapse">
                        <i class="icon-angle-down">
                        </i>
                      </span>
                            </div>
                        </div>
                    </div>
                    <div class="widget-content">
                        <div class="chart" id="severityCompare">

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
                            子模块执行用例数与问题数及比值统计图
                        </h4>
                        <div class="light"><p id="caseBugCount-light"></p></div>
                        <div class="toolbar no-padding">
                            <div class="btn-group">
                      <span class="btn btn-xs widget-collapse">
                        <i class="icon-angle-down">
                        </i>
                      </span>
                            </div>
                        </div>
                    </div>
                    <div class="widget-content">
                        <div class="chart" id="caseBugCount">
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
                            问题数变化趋势图
                        </h4>
                        <div class="light"><p id="caseBugRatioCompare-light"></p></div>
                        <div class="toolbar no-padding">
                            <div class="btn-group">
                      <span class="btn btn-xs widget-collapse">
                        <i class="icon-angle-down">
                        </i>
                      </span>
                            </div>
                        </div>
                    </div>
                    <div class="widget-content">
                        <div class="chart" id="caseBugRatioCompare">

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>


