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
    <link rel="stylesheet" href="/assets/css/SyntaxHighlighter.css">
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
    <script type="text/javascript" src="/assets/js/shCore.js">
    </script>
    <script type="text/javascript" src="/assets/js/shBrushJScript.js">
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
            var sex="${user.sex}"
            if(sex==1){
                $('#sex option').eq(0).attr('selected','selected')
                $('#ddSex').html("女")
            }else if(sex==2){
                $('#sex option').eq(1).attr('selected','selected')
                $('#ddSex').html("男")
            }
        });
    </script>
    <script type="text/javascript" src="/assets/js/custom.js">
    </script>
    <script type="text/javascript" src="/assets/js/data.js">
    </script>
    <script type="text/javascript" src="/assets/js/main.js">
    </script>
    <script type="text/javascript" src="/assets/js/demo/ui_general.js">
    </script>
    <script type="text/javascript" src="/assets/js/demo/form_validation_lookAutoCase.js">
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
                <div class="page-title">
                    <h3>
                        个人资料
                    </h3>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="tabbable tabbable-custom tabbable-full-width">
                        <ul class="nav nav-tabs">
                            <li class="active">
                                <a href="#tab_overview" data-toggle="tab">
                                    展示
                                </a>
                            </li>
                            <li>
                                <a href="#tab_edit_account" data-toggle="tab">
                                    更新
                                </a>
                            </li>
                        </ul>
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
                        <div class="tab-content row">
                            <div class="tab-pane active" id="tab_overview">
                                <div class="col-md-3">
                                    <div class="list-group">
                                        <li class="list-group-item no-padding">
                                            <img src="/before/getUserPicture.do?name=${username}" alt="">
                                        </li>
                                    </div>
                                </div>
                                <div class="col-md-9">
                                    <div class="row profile-info">
                                        <div class="col-md-7">
                                            <div class="alert alert-info">
                                                ${user.watchword}
                                            </div>
                                            <h1>
                                                ${user.nickname}
                                            </h1>
                                            <dl class="dl-horizontal">
                                                <dt>
                                                    性别
                                                </dt>
                                                <dd id="ddSex">

                                                </dd>
                                                <dt>
                                                    年龄
                                                </dt>
                                                <dd>
                                                    ${user.age}
                                                </dd>
                                                <dt>
                                                    标签
                                                </dt>
                                                <dd>
                                                    ${user.mark}
                                                </dd>
                                            </dl>
                                            <p>
                                                ${user.description}
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane" id="tab_edit_account">
                                <form class="form-horizontal" action="/main/updateUserProfile.do" method="POST" enctype="multipart/form-data">
                                    <div class="col-md-12">
                                        <div class="widget">
                                            <div class="widget-content">
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="col-md-4 control-label">
                                                                姓名:
                                                            </label>
                                                            <div class="col-md-8">
                                                                <input type="text" name="nickname" class="form-control" value="${user.nickname}">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-md-4 control-label">
                                                                口号:
                                                            </label>
                                                            <div class="col-md-8">
                                                                <input type="text" name="watchword" class="form-control" value="${user.watchword}">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-md-4 control-label">
                                                                标签:
                                                            </label>
                                                            <div class="col-md-8">
                                                                <input type="text" name="mark" class="form-control" value="${user.mark}">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-md-4 control-label">
                                                                简介:
                                                            </label>
                                                            <div class="col-md-8" >
                                                                <textarea  class="form-control" name="description">${user.description}</textarea>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <div class="form-group">
                                                            <label class="col-md-4 control-label">
                                                                性别:
                                                            </label>
                                                            <div class="col-md-8">
                                                                <select class="form-control" name="sex" id="sex">
                                                                    <option value="1">
                                                                        女
                                                                    </option>
                                                                    <option value="2">
                                                                        男
                                                                    </option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-md-4 control-label">
                                                                年龄:
                                                            </label>
                                                            <div class="col-md-8">
                                                                <input type="text" name="age" class="form-control input-width-small"
                                                                       value="${user.age}">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-md-4 control-label">
                                                                照片:
                                                            </label>
                                                            <div class="col-md-8">
                                                                <img src="/before/getUserPicture.do?name=${username}">
                                                                <input  type="file" class="form-control" name="picture">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-actions align-center">
                                            <input type="submit" value="确认修改" class="btn btn-primary">
                                        </div>
                                    </div>
                                </form>
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

