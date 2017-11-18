<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <title>GET--登录</title>
    <link rel="icon" href="/assets/img/get-icon.png" type="image/x-icon"/>
    <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="/assets/css/main.css" rel="stylesheet" type="text/css" />
    <link href="/assets/css/plugins.css" rel="stylesheet" type="text/css" />
    <link href="/assets/css/responsive.css" rel="stylesheet" type="text/css"/>
    <link href="/assets/css/icons.css" rel="stylesheet" type="text/css" />
    <link href="/assets/css/login.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="/assets/css/fontawesome/font-awesome.min.css">
    <!--[if IE 7]>
    <link rel="stylesheet" href="/assets/css/fontawesome/font-awesome-ie7.min.css">
    <![endif]-->
    <!--[if IE 8]>
    <link href="/assets/css/ie8.css" rel="stylesheet" type="text/css" />
    <![endif]-->
    <script type="text/javascript" src="/assets/js/libs/jquery-1.10.2.min.js">
    </script>
    <script type="text/javascript" src="/bootstrap/js/bootstrap.min.js">
    </script>
    <script type="text/javascript" src="/assets/js/libs/lodash.compat.min.js">
    </script>
    <!--[if lt IE 9]>
    <script src="/assets/js/libs/html5shiv.js">
    </script>
    <![endif]-->
    <script type="text/javascript" src="/plugins/uniform/jquery.uniform.min.js">
    </script>
    <script type="text/javascript" src="/plugins/validation/jquery.validate.min.js">
    </script>
    <script type="text/javascript" src="/plugins/nprogress/nprogress.js">
    </script>
    <script type="text/javascript" src="/assets/js/data.js">
    </script>
    <script type="text/javascript" src="/assets/js/login.js">
    </script>
    <script type="text/javascript" src="/assets/js/all.js"></script>
    <script>
        $(document).ready(function() {
            Login.init()
        });
    </script>
</head>

<body class="login">
<div class="snow-container" style="position:fixed;top:0;left:0;width:100%;height:100%;z-index:-1;"></div>

<div class="logo">
    <img src="/assets/img/logo.png" alt="logo" /></div>
<div class="box">
    <div class="content">
        <form id="login" class="form-vertical login-form" action="404.jsp" method="post">
            <h3 class="form-title">
                <img src="/assets/img/get-logo.png"  style="height: 40px;width: 118px">
            </h3>
            <div class="alert fade in alert-danger" style="display: none;">
                <i class="icon-remove close" data-dismiss="alert">
                </i>
                <span></span>
            </div>
            <div class="form-group">
                <div class="input-icon">
                    <i class="icon-user">
                    </i>
                    <input type="text" name="username" class="form-control" placeholder="用户名"
                           autofocus="autofocus" data-rule-required="true" data-msg-required="请输入用户名"
                    />
                </div>
            </div>
            <div class="form-group">
                <div class="input-icon">
                    <i class="icon-lock">
                    </i>
                    <input type="password" name="password" class="form-control" placeholder="密码"
                           data-rule-required="true" data-msg-required="请输入密码"
                    />
                </div>
            </div>
            <div class="form-group">
                <div class="input-icon">
                    <i class="icon-adn">
                    </i>
                    <input type="text" name="valiteCode" class="form-control" placeholder="验证码"
                           data-rule-required="true" data-msg-required="请输入验证码"
                    />
                </div>
            </div>
            <div class="form-group">
                <img id="verificatImg">
            </div>
            <div class="form-actions">
                <label class="checkbox pull-left">
                    <input type="checkbox" class="uniform" name="remember">
                    记住密码
                </label>
                <button type="submit" class="submit btn btn-primary pull-right">
                    登录
                    <i class="icon-angle-right">
                    </i>
                </button>
            </div>
        </form>
        <form id="registerForm" class="form-vertical register-form" action="500.jsp" method="post"
              style="display: none;">
            <h3 class="form-title">
                <img src="/assets/img/get-logo.png"  style="height: 40px;width: 118px">
            </h3>
            <div class="alert fade in" id="alert" style="display: none;">
                <i class="icon-remove close" data-dismiss="alert">
                </i>
                <span></span>
            </div>
            <div class="form-group">
                <div class="input-icon">
                    <i class="icon-user">
                    </i>
                    <input type="text" id="username" name="username" class="form-control" placeholder="用户名"
                           autofocus="autofocus" data-rule-required="true" data-rule-rangelength="[2,10]" data-msg-rangelength="字符长度必须在{0}-{1}之间" data-msg-required="请输入用户名"/>
                </div>
            </div>
            <div class="form-group">
                <div class="input-icon">
                    <i class="icon-lock">
                    </i>
                    <input type="password" name="password" class="form-control" placeholder="密码"
                           id="register_password" data-rule-required="true" data-rule-rangelength="[6,16]" data-msg-rangelength="字符长度必须在{0}-{1}之间" data-msg-required="请输入密码"/>
                </div>
            </div>
            <div class="form-group">
                <div class="input-icon">
                    <i class="icon-ok">
                    </i>
                    <input type="password" name="password_confirm" class="form-control" placeholder="确认密码"
                           data-rule-required="true" data-rule-equalTo="#register_password" data-rule-rangelength="[6,16]" data-msg-rangelength="字符长度必须在{0}-{1}之间" data-msg-required="请输入确认密码"/>
                </div>
            </div>
            <div class="form-group">
                <div class="input-icon">
                    <i class="icon-envelope">
                    </i>
                    <a id="registerEmailA" class="btn btn-default">发送验证码</a>
                    <input type="text" id="registerEmail" name="email" class="form-control" placeholder="邮箱"
                           data-rule-required="true" data-rule-email="true" data-msg-required="请输入邮箱"/>

                </div>
            </div>
            <div class="form-group">
                <div class="input-icon">
                    <i class="icon-adn">
                    </i>
                    <input type="text" name="valiteCode" class="form-control" placeholder="验证码"
                           data-rule-required="true" data-msg-required="请输入验证码"
                    />
                </div>
            </div>
            <div class="form-actions">
                <button type="button" class="back btn btn-default pull-left">
                    <i class="icon-angle-left">
                    </i>
                    登录
                    </i>
                </button>
                <button id="register" type="submit" class="submit btn btn-primary pull-right">
                    注册
                    <i class="icon-angle-right">
                    </i>
                </button>
            </div>
        </form>
    </div>
    <div class="inner-box">
        <div class="content">
            <i class="icon-remove close hide-default">
            </i>
            <a href="#" class="forgot-password-link">
                忘记密码?
            </a>
            <form id="updatePwd" class="form-vertical forgot-password-form hide-default"
                  method="post">
                    <div class="form-group">
                        <div class="input-icon">
                            <i class="icon-envelope">
                            </i>
                            <a id="updatePwdEmailA" class="btn btn-default">发送验证码</a>
                            <input type="text" id="updatePwdEmail" name="email" class="form-control" placeholder="邮箱"
                                   data-rule-required="true" data-rule-="true" data-rule-email="true" data-msg-required="请输入您的邮箱"
                            />
                        </div>
                    </div>
                <div class="form-group">
                    <div class="input-icon">
                        <i class="icon-adn">
                        </i>
                        <input type="text" name="valiteCode" class="form-control" placeholder="邮件中的验证码"
                               data-rule-required="true" data-msg-required="请输入验证码"
                        />
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-icon">
                        <i class="icon-lock">
                        </i>
                        <input type="password" name="password" class="form-control" placeholder="密码"
                               id="update_password" data-rule-required="true" data-rule-rangelength="[6,16]" data-msg-rangelength="字符长度必须在{0}-{1}之间" data-msg-required="请输入密码"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-icon">
                        <i class="icon-ok">
                        </i>
                        <input type="password" name="password_confirm" class="form-control" placeholder="确认密码"
                               data-rule-required="true" data-rule-equalTo="#update_password" data-rule-rangelength="[6,16]" data-msg-rangelength="字符长度必须在{0}-{1}之间" data-msg-required="请输入确认密码"/>
                    </div>
                </div>
                <button id="resetPwd" type="submit" class="submit btn btn-default btn-block">
                    重置密码
                </button>
            </form>
            <div class="forgot-password-done hide-default">
                <i></i>
                <span></span>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <a href="#" class="sign-up">
        没有账号？
        <strong>
            注册
        </strong>
    </a>
</div>
</body>

</html>


