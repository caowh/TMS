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
        submitHandler:function(h){
                $(".inner-box").slideUp(350,function(){
                    $(".forgot-password-form").hide();
                    $(".forgot-password-link").hide();
                    $(".inner-box .close").hide();
                });
                var formData=$("#updatePwd").serializeArray(),sendData={};
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
            return false
        }
})}};

var a=function(){if($.validator){$(".register-form").validate({
    submitHandler:function(h){
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
        return false
    }
})}};

return{init:function(){b();c();g();e();d();f();a()},}}();
var emailA;
function getverificat(){
    var url="before/getLoginValidateJpg.do?math="
    $("#verificatImg").attr({src:url+Math.random()});
};
$(document).ready(function(){
    getverificat();
    $("#verificatImg").click(getverificat);
    clickA($('#updatePwdEmailA'));
    clickA($('#registerEmailA'));
});


function getValidateCode(a) {
    setTimeout(function () {
        var elInput=a.next();
        console.log(a.attr('id'))
        if(elInput.attr('class').indexOf('has-error')>0||elInput.val().trim()==''){
            return '';
        }else {
            var sendAddress=elInput.val();
            var url1="before/registerToGetValidateCode.do";
            var url2="before/updatePwdToGetValidateCode.do";
            var url="";
            if(a.attr('id')=="registerEmailA"){
                url=url1;
            }else{
                url=url2;
            }
            a.html('发送中...')
            a.attr({'style':'opacity: 0.5'}).unbind('click')
            $.ajax({
                type: "post",
                contentType: "application/json; charset=utf-8",
                url: url,
                data: JSON.stringify({email:sendAddress}),
                dataType: "json",
                success: function(res){
                    if(res.code==1){
                        settime(a,60)
                    }else {
                        clean(a)
                        a.parent().append('<label id="errorEmail" style="color: red">'+res.message+'</label>')
                        setTimeout(function () {
                            $('#errorEmail').remove();
                        },2000)
                    }
                }
            })
        }
    },500)

}

function settime(a,timer) {
   if(timer>0){
       a.html(timer+'s')
       timer--;
       setTimeout(function () {
           settime(a,timer)
       },1000)
   }else {
       clean(a)
   }
}

function clean(a) {
    a.removeAttr('style')
    a.html('获取验证码')
    clickA(a)
}

function clickA(a) {
    a.click(function () {
        getValidateCode(a)
    })
}
