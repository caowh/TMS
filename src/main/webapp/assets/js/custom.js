"use strict";
var sendMessage;
Date.prototype.Format = function(format){

    var o = {

        "M+" : this.getMonth()+1, //month

        "d+" : this.getDate(), //day

        "h+" : this.getHours(), //hour

        "m+" : this.getMinutes(), //minute

        "s+" : this.getSeconds(), //second

        "q+" : Math.floor((this.getMonth()+3)/3), //quarter

        "S" : this.getMilliseconds() //millisecond

    }

    if(/(y+)/.test(format)) {

        format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));

    }

    for(var k in o) {

        if(new RegExp("("+ k +")").test(format)) {

            format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));

        }

    }

    return format;

}
function showMessage(msg) {
    if(msg.type==0){
        $('#sidebar .notifications.demo-slide-in').prepend('<li style="display: none;"><div class="col-left"><span class="label label-info"><i class="icon-envelope"></i></span></div><div class="col-right with-margin"><span class="message">'+msg.message+'</span><span class="time">'+msg.username+'&nbsp;&nbsp;'+msg.time+'</span></div></li>')
        $("#sidebar .notifications.demo-slide-in > li:eq(0)").slideDown(500)
    }else if(msg.type==1){
        var arr=JSON.parse(msg.message)
        $('#onlineUserCount').html(arr.length)
        var users="";
        $.each(arr,function (index,item) {
            users=users+item+"&nbsp;&nbsp;";
        })
        $('#onlineUser').html(users)
    }
}
$(document).ready(function(){
    $(".sidebar-search").submit(function(a){
        $(".sidebar-search-results").slideDown(200);
        return false
    });
    $(".sidebar-search-results .close").click(function(){
        $(".sidebar-search-results").slideUp(200)
    });
    $(".row-bg-toggle").click(function(a){
        a.preventDefault();
        $(".row.row-bg").each(function(){
            $(this).slideToggle(200)})
    });
    $("#sparkline-bar").sparkline("html",{type:"bar",height:"35px",zeroAxis:false,barColor:App.getLayoutColorCode("red")});
    $("#sparkline-bar2").sparkline("html",{type:"bar",height:"35px",zeroAxis:false,barColor:App.getLayoutColorCode("green")});
    $(".widget .toolbar .widget-refresh").click(function(){
        var a=$(this).parents(".widget");App.blockUI(a);
        window.setTimeout(function(){
            App.unblockUI(a);noty({text:"<strong>Widget updated.</strong>",type:"success",timeout:1000})}, 1000)});
    setTimeout(function(){
        $("#sidebar .notifications.demo-slide-in > li:eq(1)").slideDown(500)},3500);
    setTimeout(function(){
        $("#sidebar .notifications.demo-slide-in > li:eq(0)").slideDown(500)},7000)

    var wsServer = 'ws://192.168.4.173:8888/webSocket'; //服务器地址
    var websocket = new WebSocket(wsServer); //创建WebSocket对象
    websocket.onmessage = function (evt) {
        showMessage(JSON.parse(evt.data))
    };
    window.onbeforeunload = function () {
        websocket.close()
    }
    sendMessage=function (message){
        websocket.send(message)
    }
    $('#onlineUserCount').click(function () {
        bootbox.alert($('#onlineUser').html())
    })
});