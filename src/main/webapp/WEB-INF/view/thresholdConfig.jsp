<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"
    />
    <title>GET主页</title>
    <link rel="icon" href="/assets/img/get-icon.png" type="image/x-icon"/>
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"
    />
    <link href="/assets/css/menu.css" rel="stylesheet" type="text/css"/>
    <!--[if lt IE 9]>
    <link rel="stylesheet" type="text/css" href="/plugins/jquery-ui/jquery.ui.1.10.2.ie.css"
    />
    <![endif]-->
    <link href="/assets/css/main.css?_MM" rel="stylesheet" type="text/css"/>
    <link href="/assets/css/plugins.css" rel="stylesheet" type="text/css"/>
    <link href="/assets/css/responsive.css" rel="stylesheet" type="text/css"/>
    <link href="/assets/css/icons.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="/assets/css/fontawesome/font-awesome.min.css">
    <link rel="stylesheet" href="/assets/css/zTreeStyle/zTreeStyle.css">
    <!--[if IE 7]>
    <link rel="stylesheet" href="/assets/css/fontawesome/font-awesome-ie7.min.css">
    <![endif]-->
    <!--[if IE 8]>
    <link href="/assets/css/ie8.css" rel="stylesheet" type="text/css"/>
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
    <script type="text/javascript" src="/plugins/bootbox/bootbox.min.js">
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
        $(document).ready(function () {
            App.init();
            Plugins.init();
            FormComponents.init()
        });
    </script>
    <script type="text/javascript" src="/assets/js/custom.js">
    </script>
    <script type="text/javascript" src="/assets/js/data.js">
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
            <img src="/assets/img/get-logo.png" alt="logo"/>
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
                        <a href="#" id="updatePassword">
                            <i class="icon-anchor">
                            </i>
                            修改密码
                        </a>
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
                        <i class="icon-desktop">
                        </i>
                        GET首页
                    </a>
                </li>
                <li class="active open">
                    <a href="javascript:void(0);">
                        <i class="icon-dashboard">
                        </i>
                        自动化测试管理
                    </a>
                    <ul class="sub-menu" style="display: block">
                        <li>
                            <a href="/main/index.do">
                                <i class="icon-bar-chart">
                                </i>
                                分析与统计
                            </a>
                        </li>
                        <li  class="active">
                            <a href="/main/loadThresholdPage.do">
                                <i class="icon-edit">
                                </i>
                                阈值参数配置
                            </a>
                        </li>
                        <li>
                            <a href="/main/autoCaseRepertory.do">
                                <i class="icon-suitcase">
                                </i>
                                用例仓库
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="current">
                    <a href="/linuxMonitor/index.do?ip=192.168.4.173">
                        <i class="icon-eye-open">
                        </i>
                        性能监控
                    </a>
                </li>
            </ul>
            <div class="sidebar-title">
            <span>
              系统消息
            </span>
            </div>
            <ul class="notifications demo-slide-in">
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
                <h3>阈值参数配置</h3>
                <button id="warning1" class="btn btn-warning btn-notification close" data-type="warning"
                        data-text="<strong>警告</strong><br>请至少选择表格一列数据！">
                </button>
                <button id="warning2" class="btn btn-warning btn-notification close" data-type="warning"
                        data-text="<strong>警告</strong><br>多选时只能选择相同系统不同版本号！">
                </button>
                <button id="warning3" class="btn btn-warning btn-notification close" data-type="warning"
                        data-text="<strong>警告</strong><br>请至少选择一种分析种类！">
                </button>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="widget box" id="form_wizard">
                        <div class="widget-content">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="widget">
                                        <div class="widget-header">
                                            <h4>
                                                <i class="icon-reorder">
                                                </i>
                                                阈值参数列表
                                            </h4>
                                            <div class="toolbar no-padding">
                                                <div class="btn-group">

                                                </div>
                                            </div>
                                        </div>
                                        <div class="widget-content">
                                            <table class="table table-striped table-bordered table-checkable table-highlight-head table-no-inner-border table-hover">
                                                <thead>
                                                <tr>

                                                    <th>
                                                        参数名称
                                                    </th>
                                                    <th class="align-center">
                                                        参数描述
                                                    </th>
                                                    <th class="align-center">
                                                        参数值
                                                    </th>
                                                    <th class="align-center">
                                                        操作
                                                    </th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>

                                                    <td>
                                                        评估参数
                                                    </td>
                                                    <td class="align-center">
                                                        对开发进行评估的评分阈值
                                                    </td>
                                                    <td class="align-center">
                                                        0.7
                                                    </td>
                                                    <td class="align-center">
                          <span class="btn-group">
                            <a href="javascript:void(alert('this is search button'));" class="btn btn-xs bs-tooltip"
                               title="Search">
                              <i class="icon-search">
                              </i>
                            </a>
                            <a href="javascript:void(0);" class="btn btn-xs bs-tooltip" title="Edit">
                              <i class="icon-pencil">
                              </i>
                            </a>
                            <a href="javascript:void(0);" class="btn btn-xs bs-tooltip" title="Delete">
                              <i class="icon-trash">
                              </i>
                            </a>
                          </span>
                                                    </td>

                                                </tr>
                                                <tr>

                                                    <td>
                                                        参数2
                                                    </td>
                                                    <td class="align-center">
                                                        这是参数2
                                                    </td>
                                                    <td class="align-center">
                                                        0.8
                                                    </td>
                                                    <td class="align-center">
                          <span class="btn-group">
                            <a href="javascript:void(0);" class="btn btn-xs bs-tooltip" title="Search">
                              <i class="icon-search">
                              </i>
                            </a>
                            <a href="javascript:void(0);" class="btn btn-xs bs-tooltip" title="Edit">
                              <i class="icon-pencil">
                              </i>
                            </a>
                            <a href="javascript:void(0);" class="btn btn-xs bs-tooltip" title="Delete">
                              <i class="icon-trash">
                              </i>
                            </a>
                          </span>
                                                    </td>

                                                </tr>
                                                <tr>

                                                    <td>
                                                        参数3
                                                    </td>
                                                    <td class="align-center">
                                                        这是参数3
                                                    </td>
                                                    <td class="align-center">
                                                        0.9
                                                    </td>
                                                    <td class="align-center">
                          <span class="btn-group">
                            <a href="javascript:void(0);" class="btn btn-xs bs-tooltip" title="Search">
                              <i class="icon-search">
                              </i>
                            </a>
                            <a href="javascript:void(0);" class="btn btn-xs bs-tooltip" title="Edit">
                              <i class="icon-pencil">
                              </i>
                            </a>
                            <a href="javascript:void(0);" class="btn btn-xs bs-tooltip" title="Delete">
                              <i class="icon-trash">
                              </i>
                            </a>
                          </span>
                                                    </td>

                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
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

