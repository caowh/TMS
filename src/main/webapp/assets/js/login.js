var Login=function(){var b=function(){if($.fn.uniform){$(":radio.uniform, :checkbox.uniform").uniform()}};
var c=function(){
    $(".sign-up").click(function(h){
        h.preventDefault();
        $(".login-form").slideUp(350,function(){
            $(".register-form").slideDown(350);
            $(".sign-up").hide();
            $(".inner-box").hide();
        })
    });
    $(".back").click(function(h){
        h.preventDefault();
        $(".register-form").slideUp(350,function(){
            $(".login-form").slideDown(350);
            $(".sign-up").show();
            $(".inner-box").show();
            $(".forgot-password-form").slideUp(350)
            $(".inner-box .close").hide();
        })
    })
};
var g=function(){
    $(".forgot-password-link").click(function(h){
        h.preventDefault();
        $(".login-form").slideToggle(350);
        // $(".register-form").slideToggle(350);
        $(".forgot-password-form").slideToggle(350);
        $(".inner-box .close").fadeToggle(350)
    });
    $(".inner-box .close").click(function(){
        $(".forgot-password-link").click()
        $(".login-form").slideDown(350);
    })
};
var e=function(){if($.validator){$.extend($.validator.defaults,{errorClass:"has-error",validClass:"has-success",highlight:function(k,i,j){if(k.type==="radio"){this.findByName(k.name).addClass(i).removeClass(j)}else{$(k).addClass(i).removeClass(j)}$(k).closest(".form-group").addClass(i).removeClass(j)},unhighlight:function(k,i,j){if(k.type==="radio"){this.findByName(k.name).removeClass(i).addClass(j)}else{$(k).removeClass(i).addClass(j)}$(k).closest(".form-group").removeClass(i).addClass(j);$(k).closest(".form-group").find('label[generated="true"]').html("")}});
var h=$.validator.prototype.resetForm;$.extend($.validator.prototype,{resetForm:function(){h.call(this);this.elements().closest(".form-group").removeClass(this.settings.errorClass+" "+this.settings.validClass)},showLabel:function(j,k){var i=this.errorsFor(j);if(i.length){i.removeClass(this.settings.validClass).addClass(this.settings.errorClass);if(i.attr("generated")){i.html(k)}}else{i=$("<"+this.settings.errorElement+"/>").attr({"for":this.idOrName(j),generated:true}).addClass(this.settings.errorClass).addClass("help-block").html(k||"");if(this.settings.wrapper){i=i.hide().show().wrap("<"+this.settings.wrapper+"/>").parent()}if(!this.labelContainer.append(i).length){if(this.settings.errorPlacement){this.settings.errorPlacement(i,$(j))}else{i.insertAfter(j)}}}if(!k&&this.settings.success){i.text("");if(typeof this.settings.success==="string"){i.addClass(this.settings.success)}else{this.settings.success(i,j)}}this.toShow=this.toShow.add(i)}})}};
var d=function(){if($.validator){$(".login-form").validate({invalidHandler:function(i,h){
    NProgress.start();
    $(".login-form .alert-danger").show();
    $(".login-form .alert-danger span").html('请先输入要填的信息');
    setTimeout(function () {
        $(".login-form .alert-danger").hide();
    },2000)
    NProgress.done()
},submitHandler:function(h){
    var formData=$("#login").serializeArray(),sendData={};
    for(var i=0;i<formData.length;i++){
        sendData[formData[i].name]=formData[i].value;
    };
    $.ajax({
        type: "post",
        contentType: "application/json; charset=utf-8",
        url: "before/login.do",
        data: JSON.stringify(sendData),
        dataType: "json",
        success: function(res){
            if(res.code==1){
                window.location.href="/main/index.do"
            }else {
                $(".login-form .alert-danger").show();
                $(".login-form .alert-danger span").html(res.message);
                setTimeout(function () {
                    $(".login-form .alert-danger").hide();
                },2000)
            }
        }
    })
}})}};
var f=function(){if($.validator){$(".forgot-password-form").validate(
    {
        success:function(e,h) {
                    if ($(h).attr("name")=="email"&&sessionStorage.getItem($(h).val()+"update")!="true"){
                        $('#resetPwd').attr("disabled","disabled")
                        $.ajax({
                            type: "post",
                            contentType: "application/json; charset=utf-8",
                            url: "before/updatePwdToGetValidateCode.do",
                            data: JSON.stringify({email:$(h).val()}),
                            dataType: "json",
                            success: function(res){
                                $('#resetPwd').removeAttr("disabled")
                                if(res.code==1){
                                    e.html("发送成功,60s内不能重复发送");
                                    sessionStorage.setItem($(h).val()+"update","true");
                                    setTimeout(function () {
                                        sessionStorage.removeItem($(h).val()+"update")
                                    },60000)
                                    $('#updatePwdEmail').attr("placeholder","邮箱验证通过后自动发送")
                                }else {
                                    e.parent().parent().removeClass("has-success").addClass("has-error");
                                    e.parent().children("input").attr("placeholder","请重新输入");
                                    e.html(res.message);
                                }
                            }
                        })
                }
                },
        submitHandler:function(h){
            if($('#updatePwdEmail').attr("placeholder")=="请重新输入"){
                $('#updatePwdEmail').attr("placeholder","邮箱验证通过后自动发送")
            }else {
                $(".inner-box").slideUp(350,function(){
                    $(".forgot-password-form").hide();
                    $(".forgot-password-link").hide();
                    $(".inner-box .close").hide();
                });
                var formData=$("#updatePwdForm").serializeArray(),sendData={};
                for(var i=0;i<formData.length;i++){
                    sendData[formData[i].name]=formData[i].value;
                };
                $.ajax({
                    type: "post",
                    contentType: "application/json; charset=utf-8",
                    url: "before/updatePasswordByEmail.do",
                    data: JSON.stringify(sendData),
                    dataType: "json",
                    success: function(res){
                        $(".forgot-password-form input").val("");
                        $(".forgot-password-form input").removeClass("has-success");
                        $(".forgot-password-form div").removeClass("has-success");
                        $(".forgot-password-form label").remove();
                        if(res.code==1){
                            $(".forgot-password-done i").attr({class:"icon-ok success-icon"});
                            $(".forgot-password-done span").html("修改密码成功!");
                            $(".forgot-password-done").show();
                            $(".inner-box").slideDown(350)
                            setTimeout(function () {
                                $(".forgot-password-link").show();
                                $(".forgot-password-done").hide();
                                $(".login-form").slideDown(350);
                            },2000)
                        }else {
                            $(".forgot-password-done i").attr({class:"icon-remove danger-icon"});
                            $(".forgot-password-done span").html(res.message);
                            $(".forgot-password-done").show();
                            $(".inner-box").slideDown(350)
                            setTimeout(function () {
                                $(".forgot-password-form").slideDown(350)
                                $(".inner-box .close").show();
                                $(".forgot-password-link").show();
                                $(".forgot-password-done").hide();
                            },2000)
                        }
                    }
                })
            }
            return false
        }
})}};

var a=function(){if($.validator){$(".register-form").validate({
    success:function(e,h) {
        console.log(sessionStorage.getItem($(h).val()))
        if ($(h).attr("name")=="email"&&sessionStorage.getItem($(h).val()+"register")!="true"){
            $('#register').attr("disabled","disabled")
            $.ajax({
                type: "post",
                contentType: "application/json; charset=utf-8",
                url: "before/registerToGetValidateCode.do",
                data: JSON.stringify({email:$(h).val()}),
                dataType: "json",
                success: function(res){
                    $('#register').removeAttr("disabled")
                    if(res.code==1){
                        e.html("发送成功,60s内不能重复发送");
                        sessionStorage.setItem($(h).val()+"register","true");
                        setTimeout(function () {
                            sessionStorage.removeItem($(h).val()+"register")
                        },60000)
                        $('#registerEmail').attr("placeholder","邮箱验证通过后自动发送")
                    }else {
                        e.parent().parent().removeClass("has-success").addClass("has-error");
                        e.parent().children("input").attr("placeholder","请重新输入");
                        e.html(res.message);
                    }
                }
            })
        }else if($(h).attr("name")=="username"){
            $('#register').attr("disabled","disabled")
            $.ajax({
                type: "post",
                contentType: "application/json; charset=utf-8",
                url: "before/checkUserName.do",
                data: JSON.stringify({username:$(h).val()}),
                dataType: "json",
                success: function(res){
                    $('#register').removeAttr("disabled")
                    if(res.code==1){
                        e.html("该名称尚未注册");
                        $('#username').attr("placeholder","用户名")
                    }else {
                        e.parent().parent().removeClass("has-success").addClass("has-error");
                        e.parent().children("input").attr("placeholder","请重新输入");
                        e.html(res.message);
                    }
                }
            })
        }
    },
    submitHandler:function(h){
        if($('#registerEmail').attr("placeholder")=="请重新输入"){
            $('#registerEmail').attr("placeholder","邮箱验证通过后自动发送")
        }else if($('#username').attr("placeholder")=="请重新输入"){
            $('#username').attr("placeholder","用户名")
        }else {
            var formData=$("#registerForm").serializeArray(),sendData={};
            for(var i=0;i<formData.length;i++){
                sendData[formData[i].name]=formData[i].value;
            };
            $.ajax({
                type: "post",
                contentType: "application/json; charset=utf-8",
                url: "before/register.do",
                data: JSON.stringify(sendData),
                dataType: "json",
                success: function(res){
                    if(res.code==1){
                        $("#alert").addClass('alert-success').show();
                        $("#alert span").html("注册成功!");
                        setTimeout(function () {
                            $("#alert").removeClass('alert-success').hide();
                            $(".register-form").slideUp(350)
                            $(".login-form").slideDown(350)
                        },2000)
                    }else {
                        $("#alert").addClass('alert-danger').show();
                        $("#alert span").html(res.message);
                        setTimeout(function () {
                            $("#alert").removeClass('alert-danger').hide();
                        },2000)
                    }
                }
            })
        }
        return false
    }
})}};

// var r=function(){if($.validator){
//     $.validator.addMethod(
//         "updatePwdByEmail",
//         function(value, element) {
//             alert(value)
//             $.ajax({
//                 type: "post",
//                 contentType: "application/json; charset=utf-8",
//                 url: "before/updatePwdToGetValidateCode.do",
//                 data: JSON.stringify({email:value}),
//                 dataType: "json",
//                 success: function(res){
//                     if(res.code==1){
//                         return  true;
//                     }else {
//                         return  false;
//                     }
//                 }
//             })
//         },
//         $.validator.format("邮件发送失败")
//     );
// }};
return{init:function(){b();c();g();e();d();f();a()},}}();

function getverificat(){
    var url="before/getLoginValidateJpg.do?math="
    $("#verificatImg").attr({src:url+Math.random()});
};
$(document).ready(function(){
    getverificat();
    $("#verificatImg").click(getverificat);
    sendEmail("updatePwdEmail");
    sendEmail("registerEmail")
});

function sendEmail(elID){
    var cansend=true;
    setTimeout(cansend=true,60000);
    $("#"+elID+"A").click(function(){
        if(!cansend){
            return "";
        }
        var elInput=$(this).next();
        var sendAddress=elInput.val();
        var url1="before/registerToGetValidateCode.do";
        var url2="before/updatePwdToGetValidateCode.do";
        var url="";
        if(elID=="registerEmail"){
            url=url1;
        }else{
            url=url2;
        }
        alert("正在验证邮箱,请稍后！");
        $.ajax({
            type: "post",
            contentType: "application/json; charset=utf-8",
            url: url,
            data: JSON.stringify({email:sendAddress}),
            dataType: "json",
            success: function(res){
                if(res.code==1){
                    alert("发送成功,60s内不能重复发送");
                    cansend=false
                }else {
                    alert(res.message);
                }
            }
        })
    })
}
