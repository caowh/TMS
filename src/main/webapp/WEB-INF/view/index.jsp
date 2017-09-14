<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"
    />
    <title>GET主页</title>
    <link rel="icon" href="/assets/img/get-icon.png" type="image/x-icon"/>
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"
    />
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
    <!--[if lt IE 9]>
    <link rel="stylesheet" type="text/css" href="/plugins/jquery-ui/jquery.ui.1.10.2.ie.css"
    />
    <![endif]-->
    <link href="/assets/css/main.css?_MM" rel="stylesheet" type="text/css" />
    <link href="/assets/css/plugins.css" rel="stylesheet" type="text/css" />
    <link href="/assets/css/responsive.css" rel="stylesheet" type="text/css"/>
    <link href="/assets/css/icons.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="/assets/css/fontawesome/font-awesome.min.css">
    <link rel="stylesheet" href="/assets/css/zTreeStyle/zTreeStyle.css">
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
    <script type="text/javascript" src="/plugins/easy-pie-chart/jquery.easy-pie-chart.min.js">
    </script>
    <script type="text/javascript" src="/plugins/daterangepicker/moment.min.js">
    </script>
    <script type="text/javascript" src="/plugins/daterangepicker/daterangepicker.js">
    </script>
    <script type="text/javascript" src="/plugins/blockui/jquery.blockUI.min.js">
    </script>
    <script type="text/javascript" src="/plugins/fullcalendar/fullcalendar.min.js">
    </script>
    <script type="text/javascript" src="/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js">
    </script>
    <script type="text/javascript" src="/plugins/noty/jquery.noty.js">
    </script>
    <script type="text/javascript" src="/plugins/noty/layouts/top.js">
    </script>
    <script type="text/javascript" src="/plugins/bootbox/bootbox.min.js">
    </script>
    <script type="text/javascript" src="/plugins/noty/themes/default.js">
    </script>
    <script type="text/javascript" src="/plugins/duallistbox/jquery.duallistbox.min.js">
    </script>
    <script type="text/javascript" src="/plugins/uniform/jquery.uniform.min.js">
    </script>
    <script type="text/javascript" src="/plugins/validation/jquery.validate.min.js">
    </script>
    <script type="text/javascript" src="/plugins/validation/additional-methods.min.js">
    </script>
    <script type="text/javascript" src="/plugins/select2/select2.min.js">
    </script>
    <script type="text/javascript" src="/plugins/datatables/jquery.dataTables.min.js">
    </script>
    <script type="text/javascript" src="/plugins/datatables/DT_bootstrap.js">
    </script>
    <script type="text/javascript" src="/assets/js/jquery.ztree.all-3.5.min.js">
    </script>
    <script type="text/javascript" src="/assets/js/app.js">
    </script>
    <script type="text/javascript" src="/assets/js/plugins.js">
    </script>
    <script type="text/javascript" src="/plugins/pickadate/picker.js">
    </script>
    <script type="text/javascript" src="/plugins/pickadate/picker.date.js">
    </script>
    <script type="text/javascript" src="/plugins/pickadate/picker.time.js">
    </script>
    <script type="text/javascript" src="/plugins/bootstrap-colorpicker/bootstrap-colorpicker.min.js">
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
    <script type="text/javascript" src="/assets/js/custom.js">
    </script>
    <script type="text/javascript" src="/assets/js/echarts.min.js">
    </script>
    <script type="text/javascript" src="/assets/js/data.js">
    </script>
    <script type="text/javascript" src="/assets/js/totalCaseResult.js">
    </script>
    <script type="text/javascript" src="/assets/js/demo/form_wizard.js">
    </script>
    <script type="text/javascript" src="/assets/js/main.js">
    </script>
    <script type="text/javascript" src="/assets/js/demo/ui_general.js">
    </script>
</head>

<body>
<header class="header navbar navbar-fixed-top" role="banner">
    <div class="container">
        <a class="navbar-brand" href="/main/index.do">
            <img src="/assets/img/get-logo.png" alt="logo" />
            <strong>
                测试综合管理平台
            </strong>
        </a>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown user">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <i class="icon-male">
                    </i>
                    <span class="username">
                ${username}
              </span>
                    <i class="icon-caret-down small">
                    </i>
                </a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="pages_user_profile.html">
                            <i class="icon-user">
                            </i>
                            我的资料
                        </a>
                    </li>
                    <li>
                        <a href="pages_calendar.html">
                            <i class="icon-calendar">
                            </i>
                            我的日历
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <i class="icon-tasks">
                            </i>
                            我的任务
                        </a>
                    </li>
                    <li class="divider">
                    </li>
                    <li>
                        <a href="#" onclick="logout()">
                            <i class="icon-key">
                            </i>
                            退出
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</header>
<div id="container">
    <div id="sidebar" class="sidebar-fixed">
        <div id="sidebar-content">
            <ul id="nav">
                <li class="current">
                    <a href="/main/index.do">
                        <i class="icon-bar-chart">
                        </i>
                        用例结果分析
                    </a>
                </li>
            </ul>
            <div class="sidebar-widget align-center">
                <div class="btn-group" data-toggle="buttons" id="theme-switcher">
                    <label class="btn active">
                        <input type="radio" name="theme-switcher" data-theme="bright">
                        <i class="icon-sun">
                        </i>
                        白天
                    </label>
                    <label class="btn">
                        <input type="radio" name="theme-switcher" data-theme="dark">
                        <i class="icon-moon">
                        </i>
                        夜晚
                    </label>
                </div>
            </div>
        </div>
        <div id="divider" class="resizeable">
        </div>
    </div>
    <div id="content">
        <div class="container">
            <div class="page-header">
                <h3>用例结果分析</h3>
                <button id="warning1" class="btn btn-warning btn-notification close" data-type="warning" data-text="<strong>警告</strong><br>请至少选择表格一列数据！">
                </button>
                <button id="warning2" class="btn btn-warning btn-notification close" data-type="warning" data-text="<strong>警告</strong><br>多选时只能选择相同系统不同版本号！">
                </button>
                <button id="warning3" class="btn btn-warning btn-notification close" data-type="warning" data-text="<strong>警告</strong><br>请选择一个子模块！">
                </button>
                <button id="warning4" class="btn btn-warning btn-notification close" data-type="warning" data-text="<strong>警告</strong><br>请至少选择一种分析种类！">
                </button>
            </div>
            <div id="row1" class="row">
                <div class="col-md-12">
                    <div class="widget box" id="form_wizard">
                        <div class="widget-content">
                            <form class="form-horizontal" id="sample_form" action="#">
                                <div class="form-wizard">
                                    <div class="form-body">
                                        <ul class="nav nav-pills nav-justified steps">
                                            <li  id="firstLi">
                                                <a href="#tab1" data-toggle="tab" class="step">
                                                  <span class="number">
                                                    1
                                                  </span>
                                                    <span class="desc">
                                                        <i class="icon-ok">
                                                        </i>
                                                        选择系统与版本
                                                  </span>
                                                </a>
                                            </li>
                                            <li  id="secondLi">
                                                <a href="#tab2" data-toggle="tab" class="step">
                              <span class="number">
                                2
                              </span>
                                                    <span class="desc">
                                <i class="icon-ok">
                                </i>
                                确认详细模块与分析种类
                              </span>
                                                </a>
                                            </li>
                                            <li>
                                                <a href="#tab3" data-toggle="tab" class="step active">
                                                  <span class="number">
                                                    3
                                                  </span>
                                                    <span class="desc">
                                                        <i class="icon-ok">
                                                        </i>
                                                        最终确认
                                                  </span>
                                                </a>
                                            </li>
                                        </ul>
                                        <div id="bar" class="progress progress-striped" role="progressbar">
                                            <div class="progress-bar progress-bar-success">
                                            </div>
                                        </div>
                                        <div class="tab-content">
                                            <div class="alert alert-danger hide-default">
                                                <button class="close" data-dismiss="alert">
                                                </button>
                                                您填写的不完善，请再次确认
                                            </div>
                                            <div class="alert alert-success hide-default">
                                                <button class="close" data-dismiss="alert">
                                                </button>
                                                填写正确
                                            </div>
                                            <div class="tab-pane active" id="tab1">
                                                <h3 class="block padding-bottom-10px">
                                                    请选择您要分析的系统，多选时只能选择相同系统不同版本号
                                                </h3>
                                                <div class="row">
                                                    <div class="col-md-12">
                                                        <div class="widget box">
                                                            <div class="widget-content">
                                                                <table class="table table-striped table-bordered table-hover table-checkable datatable">
                                                                    <thead>
                                                                    <tr>
                                                                        <th class="checkbox-column">
                                                                        </th>
                                                                        <th>系统名称</th>
                                                                        <th>版本号</th>
                                                                        <th class="hide"></th>
                                                                        <th class="hidden-xs">环境</th>
                                                                        <th>负责人</th>
                                                                    </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                    <c:forEach items="${planHelperList}" var="planHelper">
                                                                        <tr>
                                                                            <td class="checkbox-column"><input type="checkbox" class="uniform" onchange="selectPlanNameAndVersion(this)"></td>
                                                                            <td>${planHelper.planName}</td>
                                                                            <td>${planHelper.uut}</td>
                                                                            <td class="hide">${planHelper.version}</td>
                                                                            <td>${planHelper.environment}</td>
                                                                            <td>${planHelper.leader}</td>
                                                                        </tr>
                                                                    </c:forEach>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="tab-pane" id="tab2">
                                                <h3 class="block padding-bottom-10px">
                                                    请先选择子功能模块，再选择分析种类
                                                </h3>
                                                <div class="row">
                                                    <div class="col-md-3">
                                                        <div class="widget box">
                                                            <div class="widget-header">
                                                                <h4>
                                                                    <i class="icon-reorder">
                                                                    </i>
                                                                     子功能模块结构
                                                                </h4>
                                                            </div>
                                                            <div class="widget-content">
                                                                <ul id="moduleNodeTree" class="ztree"></ul>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-9">
                                                        <div class="widget box">
                                                            <div class="widget-header">
                                                                <h4>
                                                                    <i class="icon-reorder">
                                                                    </i>
                                                                    可供分析的种类
                                                                </h4>
                                                            </div>
                                                            <div class="widget-content clearfix">
                                                                <div class="left-box">
                                                                    <input type="text" id="box1Filter" class="form-control box-filter" placeholder="Filter entries...">
                                                                    <button type="button" id="box1Clear" class="filter">
                                                                        x
                                                                    </button>
                                                                    <select id="box1View" multiple="multiple" class="multiple">
                                                                    </select>
                                                                    <span id="box1Counter" class="count-label">
                                                                    </span>
                                                                    <select id="box1Storage">
                                                                    </select>
                                                                </div>
                                                                <div class="dual-control">
                                                                    <button id="to2" type="button" class="btn">
                                                                        &nbsp;&gt;&nbsp;
                                                                    </button>
                                                                    <button id="allTo2" type="button" class="btn">
                                                                        &nbsp;&gt;&gt;&nbsp;
                                                                    </button>
                                                                    <br>
                                                                    <button id="to1" type="button" class="btn">
                                                                        &nbsp;&lt;&nbsp;
                                                                    </button>
                                                                    <button id="allTo1" type="button" class="btn">
                                                                        &nbsp;&lt;&lt;&nbsp;
                                                                    </button>
                                                                </div>
                                                                <div class="right-box">
                                                                    <input type="text" id="box2Filter" class="form-control box-filter" placeholder="Filter entries...">
                                                                    <button type="button" id="box2Clear" class="filter">
                                                                        x
                                                                    </button>
                                                                    <select id="box2View" multiple="multiple" class="multiple">
                                                                    </select>
                                                                    <span id="box2Counter" class="count-label">
                                                                    </span>
                                                                    <select id="box2Storage">
                                                                    </select>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                            </div>
                                            <div class="tab-pane" id="tab3">
                                                <h3 class="block padding-bottom-10px">
                                                    请确认分析信息
                                                </h3>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">
                                                        系统名称
                                                    </label>
                                                    <div class="col-md-4">
                                                        <label id="planName" class="control-label">
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">
                                                        版本号
                                                    </label>
                                                    <div class="col-md-6">
                                                        <label id="version" class="text-left">
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">
                                                        子模块名称
                                                    </label>
                                                    <div class="col-md-6">
                                                        <label id="node" class="control-label">
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="control-label col-md-3">
                                                        分析种类
                                                    </label>
                                                    <div class="col-md-6">
                                                        <label id="type" class="text-left">
                                                        </label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-actions fluid">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <div class="col-md-offset-3 col-md-9">
                                                    <a href="javascript:void(0);" class="btn button-previous">
                                                        <i class="icon-angle-left">
                                                        </i>
                                                        上一步
                                                    </a>
                                                    <a href="javascript:void(0);" class="btn btn-primary button-next">
                                                        下一步
                                                        <i class="icon-angle-right">
                                                        </i>
                                                    </a>
                                                    <a href="javascript:void(0);" class="btn btn-success button-submit">
                                                        提交
                                                        <i class="icon-angle-right">
                                                        </i>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div id="row2" class="row hide">
                <div class="col-md-12 hide">
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
                <div class="col-md-12 hide">
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
                <div class="col-md-12 hide">
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
                <div class="col-md-12 hide">
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
</div>
</body>

</html>

