/**
 * Created by user on 2017/8/21.
 */
function logout() {
    $.ajax({
        type: "get",
        url: "/main/logout.do",
        dataType: "json",
        success: function(res){
            console.log(res)
            window.location.href="/login.jsp"
        }
    })
}


$(document).ready(function(){
    $('#updatePassword').click(function () {
        bootbox.dialog({
            title : "修改密码",
            message : "<div class='well ' style='margin-top:25px;'><form class='form-horizontal' role='form'><div class='form-group'><label class='col-sm-3 control-label no-padding-right' for='txtOldPwd'>旧密码</label><div class='col-sm-9'><input type='password' id='txtOldPwd' placeholder='请输入旧密码' class='col-xs-10 col-sm-5 form-control' /></div></div><div class='space-4'></div><div class='form-group'><label class='col-sm-3 control-label no-padding-right' for='txtNewPwd1'>新密码</label><div class='col-sm-9'><input type='password' id='txtNewPwd1' data-rule-required='true' data-rule-rangelength='[6,16]' data-msg-rangelength='字符长度必须在{0}-{1}之间' data-msg-required='请输入密码' placeholder='请输入新密码' class='col-xs-10 col-sm-5 form-control' /></div></div><div class='space-4'></div><div class='form-group'><label class='col-sm-3 control-label no-padding-right' for='txtNewPwd2'>确认新密码</label><div class='col-sm-9'><input type='password' id='txtNewPwd2'  data-rule-required='true' data-rule-rangelength='[6,16]' data-msg-rangelength='字符长度必须在{0}-{1}之间' data-msg-required='请输入密码' placeholder='再次输入新密码' class='col-xs-10 col-sm-5 form-control' /></div></div></form></div>",
            buttons : {
                "success" : {
                    "label" : "<i class='icon-ok'></i> 保存",
                    "className" : "btn-sm btn-success",
                    "callback" : function() {
                        var txt1 = $("#txtOldPwd").val();
                        var txt2 = $("#txtNewPwd1").val();
                        var txt3 = $("#txtNewPwd2").val();

                        if(txt1 == "" || txt2 == "" || txt3 == ""){
                            bootbox.alert("密码不能为空");
                            return false;
                        }
                        if(txt2 != txt3 ){
                            bootbox.alert("两次输入新密码不一致，请重新输入!");
                            return false;
                        }
                        updatePwd({'old':txt1,"new":txt2},function (res) {
                            if(res.code==1){
                                bootbox.alert("密码更新成功");
                            }else {
                                bootbox.alert(res.message);
                            }
                        });
                    }
                },
                "cancel" : {
                    "label" : "<i class='icon-info'></i> 取消",
                    "className" : "btn-sm btn-danger",
                    "callback" : function() { }
                }
            }
        });
    })
    $('#broadcast').click(function () {
        bootbox.dialog({
            title:"群发消息--所有在线用户",
            message:"<div class='well ' style='margin-top:25px;'><form class='form-horizontal' role='form'><div class='form-group'><div class='col-sm-9'><input type='text' id='txtOldPwd' placeholder='请输入...' class='col-xs-10 col-sm-5 form-control' /></div></div></form></div>",
            buttons:{
                "success" : {
                    "label" : "<i class='icon-ok'></i> 发送",
                    "className" : "btn-sm btn-success",
                    "callback" : function() {
                        var txt1 = $("#txtOldPwd").val();
                        sendMessage(txt1)
                    }
                }
            }
        })
    })
})

