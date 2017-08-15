<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Login and Registration</title>
   
    <link rel="stylesheet" type="text/css" href="css/demo.css" />

    <!--必要样式-->
    <link rel="stylesheet" type="text/css" href="css/style3.css" />
    <link rel="stylesheet" type="text/css" href="css/animate-custom.css" />
</head>
<body>
<div id="container_demo" >
    <a class="hiddenanchor" id="toregister"></a>
    <a class="hiddenanchor" id="tologin"></a>
    <div id="wrapper">
        <div id="login" class="animate form">
            <form  action="" autocomplete="on">
                <h1>登录</h1>
                <p>
                    <label for="username" class="uname" data-icon="u" >您的用户名<span></span></label>
                    <input id="username" name="username" required="required" type="text" placeholder="myusername"/>
                </p>
                <p>
                    <label for="password" class="youpasswd" data-icon="p">你的密码</label>
                    <input id="password" name="password" required="required" type="password" placeholder="**********" />
                </p>
                <p>
                    <label for="verification" class="verification" data-icon="e">请输入验证码</label>
                    <input id="verification" name="valiteCode" required="required" type="text" placeholder="5s1W" />
                </p>
                <p>
                    <img id="verificatImg" src="" alt="验证码" title="点击切换验证码">
                </p>
                <p class="keeplogin">
                    <input type="checkbox" name="remember" id="loginkeeping" value="loginkeeping" />
                    <label for="loginkeeping">记住密码</label>
                </p>
                <p class="login button">
                    <input id="logTj" type="button" value="登录" />
                </p>
                <p class="change_link">
                    不是成员?<a href="#toregister" class="to_register">加入我们</a>
                </p>
            </form>
        </div>

        <div id="register" class="animate form">
            <form  action="" autocomplete="on">
                <h1>注册</h1>
                <p>
                    <label for="usernamesignup" class="uname" data-icon="u">用户名<span ></span></label>
                    <input id="usernamesignup" name="username" required="required" type="text" placeholder="mysuperusername690" />
                </p>
                <p>
                    <label for="emailsignup" class="youmail" data-icon="e" >邮箱<span ></span></label>
                    <input id="emailsignup" name="email" required="required" type="email" placeholder="mysupermail@mail.com"/>
                </p>
                <p>
                    <label for="passwordsignup" class="youpasswd" data-icon="p">密码</label>
                    <input id="passwordsignup" name="password" required="required" type="password" placeholder="*******"/>
                </p>
                <p>
                    <label for="passwordsignup_confirm" class="youpasswd" data-icon="p">确认密码<span >两次密码不一致</span></label>
                    <input id="passwordsignup_confirm"  required="required" type="password" placeholder="*******"/>
                </p>
                <p>
                    <label for="verificat" class="verificat" data-icon="e">请输入验证码</label>
                    <input id="verificat" name="valiteCode" required="required" type="text" placeholder="4w5Z"/>
                </p>
                <p class="signin button">
                    <input id="regTj" type="button" value="注册"/>
                </p>
                <p class="change_link">
                    已经是成员?<a href="#tologin" class="to_register"> 去登录 </a>
                </p>
            </form>
        </div>
    </div>
</div>
<script src="js/jquery-1.11.3.js"></script>
<script src="js/loginReg.js"></script>
</body>
</html>
