$(function() {
    var canvas=$("#canvas");
    var a=canvas.css("width");
    var b=canvas.css("height");
    $("#html5_3d_animation").html5_3d_animation({
        window_width: a,
        window_height: b,
        window_background: '#134695',
        star_count: '1000',
        star_color: '#FBFFAF',
        star_depth: '100'
    });
});
$("#content").find("li").click(function(){
    if($(this).attr("class")!="active"){
        $(this).toggleClass("active").siblings("li").toggleClass("active").parents("ul").siblings("div").toggleClass("active");
    }
});
$("#inputPwd2").blur(function(){
    var inputPwd1=$("#inputPwd1")
    var a=$(this).val();
    var b=inputPwd1.val();
    a==b?"":(alert("密码不一致"),$(this).val(""),inputPwd1.val(""),inputPwd1.focus());
});
$("#inputEmail12").blur(function(){
    var sendMail=confirm("是否确认邮箱，将为您发送验证码");
    if(sendMail){
        var mailUrl="";
        var msg=$(this).val();
        $.post(mailUrl,msg,function(res){
            if(res.code=="0"){
                alert("系统繁忙，稍后再试！")
            }else{
                alert("发送成功，请等待验证码输入！")
            }
        })
    }
});
$("#inputName").blur(function(){
    var nameurl="";
    var msg=$(this).val();
    $.post(nameurl,msg,function(res){
        if(res.code=="0"){
            alert("用户名已存在！");
            $("#inputName").val("").focus();
        }
    })
});
$("#loginbtn").click(function(){
    var loginurl='';
    var logininfo=$("#loginForm").serializeArray();
    $.post(loginurl,logininfo,function(res){
        if(res.code=="0"){
            alert(res.msg)
        }
    })
});

$("#regbtn").click(function(){
    var regurl='';
    var reginfo=$("#regForm").serializeArray();
    console.log(reginfo)
    $.post(regurl,reginfo,function(res){
        if(res.code=="0"){
            alert(res.msg)
        }
    })
});
