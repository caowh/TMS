<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <script type="text/javascript" src="/plugins/select2/select2.min.js">
    </script>
    <script type="text/javascript" src="/plugins/fileinput/fileinput.js">
    </script>
    <script type="text/javascript" src="/plugins/validation/jquery.validate.min.js">
    </script>
    <script type="text/javascript" src="/plugins/validation/additional-methods.min.js">
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
            var result="${result}"
            if(result.indexOf("成功")>=0){
                $('#alert1 .alert-success').removeClass("hide-default")
                $('#alert1 .alert-success').append(result)
            }else if(result.indexOf("失败")>=0){
                $('#alert1 .alert-danger').removeClass("hide-default")
                $('#alert1 .alert-danger').append(result)
            }
            var result1="${result1}"
            if(result1.indexOf("成功")>=0){
                $('#alert3 .alert-success').removeClass("hide-default")
                $('#alert3 .alert-success').append(result1)
            }else if(result1.indexOf("失败")>=0){
                $('#alert3 .alert-danger').removeClass("hide-default")
                $('#alert3 .alert-danger').append(result1)
            }
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
    <script type="text/javascript" src="/assets/js/main.js">
    </script>
    <script type="text/javascript" src="/assets/js/demo/ui_general.js">
    </script>
    <script type="text/javascript" src="/assets/js/demo/form_validation.js">
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
                        <li>
                            <a href="/main/loadThresholdPage.do">
                                <i class="icon-edit">
                                </i>
                                阈值参数配置
                            </a>
                        </li>
                        <li  class="active">
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
                系统消息（在线人数：<a href="#" id="onlineUserCount"></a><span id="onlineUser" class="hide"></span>）
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
                <h3>自动化用例仓库</h3>
                <button id="warning1" class="btn btn-warning btn-notification close" data-type="warning" data-text="<strong>警告</strong><br>当前不存在任何节点！">
                </button>
                <button id="warning2" class="btn btn-warning btn-notification close" data-type="warning" data-text="<strong>警告</strong><br>查询时请先选择节点！">
                </button>
                <button id="warning3" class="btn btn-warning btn-notification close" data-type="warning" data-text="<strong>警告</strong><br>请先选择一个节点，不能选择根节点！">
                </button>
                <button id="warning4" class="btn btn-warning btn-notification close" data-type="warning" data-text="<strong>警告</strong><br>查询过程出现错误！">
                </button>
                <button id="warning5" class="btn btn-warning btn-notification close" data-type="warning" data-text="<strong>提示</strong><br>请先勾选用例！">
                </button>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="widget box">
                        <div class="widget-header">
                            <h4>
                                <i class="icon-reorder">
                                </i>
                                自动化用例上传
                            </h4>
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
                            <div class="tab-content" id="alert1">
                                <div class="alert alert-danger hide-default">
                                    <i class="icon-remove close" data-dismiss="alert">
                                    </i>
                                </div>
                                <div class="alert alert-success hide-default">
                                    <i class="icon-remove close" data-dismiss="alert">
                                    </i>
                                </div>
                            </div>
                            <form class="form-horizontal row-border" id="validate-3" action="/autoCaseRepertory/upload.do" method="POST" enctype="multipart/form-data" onsubmit="return validatePlanNode()">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">
                                        编写人员
                                        <span class="required">
                                          *
                                        </span>
                                    </label>
                                    <div class="col-md-6">
                                        ${writer}
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">
                                        用例文件
                                        <span class="required">
                                          *
                                        </span>
                                    </label>
                                    <div class="col-md-9">
                                        <input type="file" name="multipartFiles" id="multipartFiles" class="required" multiple="multiple">
                                    </div>
                                </div>
                                <div class="form-group hide">
                                    <label class="col-md-3 control-label">
                                        环境变量
                                    </label>
                                    <div class="col-md-9">
                                        <input type="file" name="envFile" id="envFile">
                                    </div>
                                </div>
                                <div class="form-group hide">
                                    <label class="col-md-3 control-label">
                                        执行说明
                                    </label>
                                    <div class="col-md-9">
                                        <input type="file" name="readMeFile" id="readMeFile" accept="text/plain">
                                    </div>
                                </div>
                                <div class="form-group">
                                <label class="col-md-3 control-label">
                                    类型
                                    <span class="required">
                                          *
                                        </span>
                                </label>
                                <div class="col-md-6">
                                    <select name="type" class="form-control required">
                                        <option value="1">
                                            postman接口测试
                                        </option>
                                        <option value="2" selected>
                                            GVML规范测试
                                        </option>
                                    </select>
                                </div>
                            </div>
                                <div class="form-group">
                                    <label class="col-md-3 control-label">
                                        节点名称
                                        <span class="required">
                                          *
                                        </span>
                                    </label>
                                    <div class="col-md-6">
                                        <div class="widget-content">
                                            <ul id="moduleNodeTree" class="ztree">
                                            </ul>
                                            <input type="text" id="node" name="node" class="hide"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-md-3">
                                        变更原因
                                        <span class="required">
                                  *
                                </span>
                                    </label>
                                    <div class="col-md-6">
                                        <input type="text" class="form-control required" name="updateReason" />
                                        <span class="help-block">
                                  初次提交或者修改用例的原因
                                </span>
                                    </div>
                                </div>
                                <div class="form-actions align-center">
                                    <input type="submit" value="上传" class="btn btn-primary">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="widget box">
                        <div class="widget-header">
                            <h4>
                                <i class="icon-reorder">
                                </i>
                                用例检索与执行
                            </h4>
                            <div class="form-actions fluid">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="col-md-offset-3 col-md-9">
                                            <a href="#" id="searchA" onclick="searchAllAutoCase()" class="btn btn-primary">
                                                查询
                                            </a>
                                            <a href="#" id="execA" class="btn btn-primary">
                                                执行
                                            </a>
                                            <a href="#" id="moveA" class="btn btn-primary">
                                                移动
                                            </a>
                                            <a href="#" id="deleteA" class="btn btn-danger">
                                                删除
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="widget-content">
                            <div class="tab-content" id="alert2">
                                <div class="alert alert-danger hide-default">
                                    <i class="icon-remove close" data-dismiss="alert">
                                    </i>
                                    <span></span>
                                </div>
                                <div class="alert alert-success hide-default">
                                    <i class="icon-remove close" data-dismiss="alert">
                                    </i>
                                    <span></span>
                                </div>
                            </div>
                            <form class="form-horizontal" id="sample_form" action="#">
                                <div class="form-wizard">
                                    <div class="form-body">
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
                                            <div class="tab-pane active">
                                                <div class="row">
                                                    <div class="col-md-3">
                                                        <div class="widget box">
                                                            <div class="widget-content">
                                                                <div class="form-group">
                                                                    <div class="col-md-12">
                                                                        <label class="control-label">
                                                                            请先选择产品节点，再点击下方查询（按“ctrl”可多选）
                                                                        </label>
                                                                    </div>
                                                                    <div class="col-md-12">
                                                                        <ul id="searchModuleNodeTree" class="ztree">
                                                                        </ul>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-9">
                                                        <div class="widget box">
                                                            <div class="widget-content">
                                                                <table id="autoCaseTable" class="table table-striped table-bordered table-hover table-checkable">
                                                                    <thead>
                                                                    <tr>
                                                                        <th class="checkbox-column">
                                                                            <input type="checkbox" class="uniform">
                                                                        </th>
                                                                        <th>true_id</th>
                                                                        <th>用例id</th>
                                                                        <th>用例描述</th>
                                                                        <th>子模块</th>
                                                                        <th>操作</th>
                                                                    </tr>
                                                                    </thead>
                                                                    <tbody>
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
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="widget box">
                        <div class="widget-header">
                            <h4>
                                <i class="icon-reorder">
                                </i>
                                testLink用例文件上传
                            </h4>
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
                            <div class="tab-content" id="alert3">
                                <div class="alert alert-danger hide-default">
                                    <i class="icon-remove close" data-dismiss="alert">
                                    </i>
                                </div>
                                <div class="alert alert-success hide-default">
                                    <i class="icon-remove close" data-dismiss="alert">
                                    </i>
                                </div>
                            </div>
                            <form class="form-horizontal row-border" id="validate-4" action="/autoCaseRepertory/uploadCaseFiles.do" method="POST" enctype="multipart/form-data">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">
                                        用例文件（可批量）
                                        <span class="required">
                                          *
                                        </span>
                                    </label>
                                    <div class="col-md-9">
                                        <input type="file" name="multipartFiles" class="required" multiple="multiple">
                                        <span class="help-block">
                                            以caseID或“caseID+"_"+"文件描述（编号等）"”命名
                                        </span>
                                    </div>
                                </div>
                                <div class="form-actions align-center">
                                    <input type="submit" value="上传" class="btn btn-primary">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>

