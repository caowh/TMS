// 验证
(function (){
    // 验证码获取
    function getverificat(){
        var url="before/getLoginValidateJpg.do?math="
        $("#verificatImg").attr({src:url+Math.random()});
    }
    getverificat();
    $("#verificatImg").click(getverificat);
    // 输入验证
    var mytest={
        phone:/^0?(12[0-9]|13[0-9]|14[57]|15[012356789]|16[0-9]|17[0-9]|18[0-9]|19[0-9])[0-9]{8}$/,//手机号验证
        username:/^[a-zA-Z0-9_-]{4,16}$/ ,//非法用户字符检查
        email:/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/,//邮箱验证,
        phoneTest:function(str){
            if (!this.phone.test(str)) {
                return false
            }else{
                return true
            }
        },
        usernameTest:function(str){
            if (!(!str||this.username.test(str))) {
                return false
            }else{
                return true
            }
        },
        emailTest:function(str){
            if (!(!str||this.email.test(str))) {
                return false
            }else{
                return true
            }
        }
    };
    function blurTest(){
        var username=$("#username");
        var usernamesignup=$("#usernamesignup");
        var emailsignup=$("#emailsignup");
        usernamesignup.blur(function(){
            var myName=$(this).val();
            if(!mytest.usernameTest(myName)){
                $(this).focus().siblings().find("span").html("用户名4到16位（字母，数字，下划线，减号)").css({opacity:1});
            }else{
                $(this).siblings().find("span").css({opacity:0});
                // 用户名重复检测
                if(myName){
                    //console.log(JSON.stringify({username:myName}))
                    $.ajax({
                        type: "post",
                        contentType: "application/json; charset=utf-8",
                        url: "before/checkUserName.do",
                        data: JSON.stringify({username:myName}),
                        dataType: "json",
                        success: function(res){
                            if(res.code==0){
                                $("#usernamesignup").focus().siblings().find("span").html(res.message).css({opacity:1});
                            }
                        }
                    })
                }
            }
        })
        username.blur(function(){
            if(!mytest.usernameTest($(this).val())){
                $(this).focus().siblings().find("span").html("用户名不符合规范").css({opacity:1});
            }else{
                $(this).siblings().find("span").css({opacity:0});
            }
        })
        // 获取验证码及验证
        emailsignup.blur(function(){
            var value=$(this).val()
            if(!mytest.emailTest(value)){
                $(this).focus().siblings().find("span").html("邮箱不符合规范").css({opacity:1});
            }else{
                $(this).siblings().find("span").css({opacity:0});
                if(value){
                    $.ajax({
                        type: "post",
                        contentType: "application/json; charset=utf-8",
                        url: "before/sendEmailToGetValidateCode.do",
                        data: JSON.stringify({email:value}),
                        dataType: "json",
                        success: function(res){
                            if(res.code==0){
                                emailsignup.focus().siblings().find("span").html(res.message).css({opacity:1});
                            }else{
                                emailsignup.siblings().find("span").html("已发送，请注意查看邮箱！").css({opacity:1});
                            }
                        }
                    })
                }
            }
        })
    }
    blurTest();
    function recode(){
        var passwordsignup=$("#passwordsignup");
        var passwordsignup_confirm=$("#passwordsignup_confirm");
        passwordsignup.blur(function(){
            passwordsignup_confirm.focus();
        });
        passwordsignup_confirm.blur(function(){
            if(passwordsignup.val()!=passwordsignup_confirm.val()){
                $(this).siblings().find("span").css({opacity:1});
                passwordsignup.focus();
            }else{
                $(this).siblings().find("span").css({opacity:0});
            }
        })
    }
    recode();
    // 注册
    $("#regTj").click(function(){
        //console.log($("#register").find("form").serializeArray());
        var formData=$("#register").find("form").serializeArray(),sendData={};
        for(var i=0;i<formData.length;i++){
            //console.log(formData[i].name+"-------"+formData[i].value);
            sendData[formData[i].name]=formData[i].value;
        };
        //console.log(JSON.stringify(sendData));
        $.ajax({
            type: "post",
            contentType: "application/json; charset=utf-8",
            url: "before/register.do",
            data: JSON.stringify(sendData),
            dataType: "json",
            success: function(res){

            }
        })
    })
    // 登录
    $("#logTj").click(function(){
        //console.log($("#register").find("form").serializeArray());
        var formData=$("#login").find("form").serializeArray(),sendData={};
        for(var i=0;i<formData.length;i++){
            //console.log(formData[i].name+"-------"+formData[i].value);
            sendData[formData[i].name]=formData[i].value;
        };
        //console.log(JSON.stringify(sendData));
        $.ajax({
            type: "post",
            contentType: "application/json; charset=utf-8",
            url: "before/login.do",
            data: JSON.stringify(sendData),
            dataType: "json",
            success: function(res){
                alert(JSON.stringify(res))
            }
        })
    })
})();

