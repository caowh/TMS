"use strict";
$(document).ready(function(){
    $('#checkbox').change(function () {
        if ($('#checkbox').prop('checked')) {
            $('#formGroup_plan').removeClass('hide')
        }else {
            $('#formGroup_plan').addClass('hide')
        }
    })

    $.extend($.validator.defaults,{invalidHandler:function(c,a){var d=a.numberOfInvalids();if(d){var b=d==1?"你有一个必填项未填写内容！":"你有"+d+" 个必填项未填写内容！";noty({text:b,type:"error",timeout:2000})}}});$("#validate-1").validate();$("#validate-2").validate();$("#validate-3").validate();$("#validate-4").validate()});