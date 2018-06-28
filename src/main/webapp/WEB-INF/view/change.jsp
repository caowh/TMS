<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>GET</title>
    <link rel="icon" href="/assets/img/get-icon.png" type="image/x-icon"/>
    <link rel="stylesheet" href="/assets/css/style.css">
    <style>
        p{
            color:#fff;
            font-size:15px;
            text-shadow: 0 0 10px #529DFF;
            float: left;
        }
        .d{
            width:15px;
            float: left;
            height:15px;
            margin-top: 4px;
            border-radius:7px;
        }
        a{
            color: #e1e1e1;
        }
        .d1{
            background: #40e112;
        }
        .d2{
            background: #96e9c6;
        }
        .d3{
            background: #e90c33;
        }
        .d4{
            background: #e9b82c;
        }
        .d5{
            background: #ffffff;
        }
        .title{
            display: block;font-size: 24px;color:#fff;text-align: center
        }
        h5.tt_bg{height:119px;text-align:center;font-size:20px;color: #9affde;line-height:70px;margin:0 auto;text-shadow:0 0 10px #529DFF;}
        h4.tt_bg{height:119px;text-align:center;font-size:24px;color:#FFF;line-height:70px;margin:0 auto;text-shadow:0 0 10px #529DFF;}
        .tt_bg span{
            color: #9affde;
        }
    </style>
</head>
<body>
<div class="wrap about_page" id="wrap">
    <div class="wrapper">
        <div class="events_sec" id="events_sec">
            <div class="content">
                <div class="events_list">
                    <span class="title"></span>
                    <span class="ic_time png"></span>
                    <ul>
                    </ul>
                </div>
            </div>
        </div>

        <div class="wage_sec" id="wage_sec">
            <div class="content">
                <h3 class="tt_bg" id="project"></h3>
                <h5 class="tt_bg" id="subject"></h5>
                <h4 class="tt_bg">
                    分支：<span id="branch"></span>，
                    提交人：<span id="name"></span>，
                    gerrit ID：<span id="change_id"></span>
                </h4>
                <div style="width:265px;height:119px;margin:0 auto;">
                    <li class="d d1"></li>
                    <p>开始&nbsp;&nbsp;</p>
                    <li class="d d2"></li>
                    <p>成功&nbsp;&nbsp;</p>
                    <li class="d d3"></li>
                    <p>失败&nbsp;&nbsp;</p>
                    <li class="d d4"></li>
                    <p>警告&nbsp;&nbsp;</p>
                    <li class="d d5"></li>
                    <p>关闭</p>
                </div>
            </div>
        </div>
    </div>
</div>

<audio id="audios" controls="true" autoplay="autoplay" class="audio" style="display:none;"></audio>

<script src="/assets/js/ci-data.js"></script>
<script src="/assets/js/canvas-nest.min.js"></script>
<script src="/assets/js/libs/jquery-1.10.2.min.js"></script>
<script>
    var change_id = '${change_id}';
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
    function abandon(change_id) {
        if(confirm('确定要丢弃本次提交么?')){
            abandonPost(change_id,'手动丢弃');
        }
    }
    function abandonPost(change_id,message) {
        changeAbandon({change_id:change_id,message:message},function(result){
            if(result!=''){
                alert("丢弃失败，"+result)
            }else {
                $('#end').remove()
                $('ul').append('<li class="right_li"><span class="ic_events png"></span><p class="txt_events" style="color: #ffffff"><strong>'+new Date().Format('yyyy-MM-dd hh:mm:ss')+' '+message+'</strong></p></li>')
                $('.events_list').append('<span class="ic_arr png" id="end"></span>')
            }
        })
    }
    $(document).ready(function(){
        $('#change_id').html(change_id)
        getChange(change_id,function(result){
            $('#project').html(result.project)
            $('#subject').html(result.subject)
            $('#branch').html(result.branch)
            $('.title').html(new Date(result.created).Format('yyyy-MM-dd hh:mm:ss'));
            var account_id = result.owner._account_id
            getOwner(account_id,function(result){
                $('#name').html(result)
            })
            function showMessage(data) {
                var right_li =  '<li class="right_li"><span class="ic_events png"></span><p class="txt_events" style="&{color}"><strong>&{time} &{message}</strong>&{button}</p></li>'
                var left_li =  '<li class="left_li"><span class="ic_events"><i class="png"></i></span><p class="txt_events" style="&{color}"><strong>&{message} &{time}</strong>&{button}</p></li>'
                var index = right_li;
                var number = 0;
                var log_button = '<a target="_blank" href="&{log_url}">查看日志</a>';
                var abandon_button ='<a href="#" onclick="abandon('+change_id+')">丢弃本次提交</a>';
                var type = data.type
                if(type === 0){
                    var message = JSON.parse(data.message)
                    if(message[0].change_id == change_id){
                        if(number === 0){
                            number++
                            var time = new Date(message[0].begin_time).Format('yyyy-MM-dd hh:mm:ss');
                            $('#end').remove()
                            $('ul').append(index.replace('&{time}',time).replace('&{message}','静态检查')
                                .replace('&{button}',log_button).replace('&{log_url}',message[0].log_url)
                                .replace('&{color}','color:#40e112')
                            )
                        }
                        if(number === 1 && message[0].flow_status != 'BEGIN'){
                            number++
                            index = left_li
                            var time = new Date(message[0].end_time).Format('yyyy-MM-dd hh:mm:ss');
                            var my_index = index.replace('&{time}',time).replace('&{message}','测试结束')
                            var my_button = log_button.replace('&{log_url}',message[0].log_url)
                            if(message[0].flow_status == 'SUCCESS'){
                                my_index = my_index.replace('&{color}','color:#96e9c6')

                            }else if(message[0].flow_status == 'FAILURE'){
                                my_index = my_index.replace('&{color}','color:#e90c33')
                                my_button += '&nbsp;&nbsp;'+abandon_button
                            }else {
                                my_index = my_index.replace('&{color}','color:#e9b82c')
                            }
                            $('#end').remove()
                            $('ul').append(my_index.replace('&{button}',my_button))
                        }
                        if(number === 2 && message.length >= 2){
                            number++
                            index = right_li
                            var time = new Date(message[1].begin_time).Format('yyyy-MM-dd hh:mm:ss');
                            $('#end').remove()
                            $('ul').append(index.replace('&{time}',time).replace('&{message}','人工审查')
                                .replace('&{button}',log_button).replace('&{log_url}',message[1].log_url)
                                .replace('&{color}','color:#40e112')
                            )
                        }
                        if(number === 3 && message[1].flow_status != 'BEGIN'){
                            number++
                            index = left_li
                            var time = new Date(message[1].end_time).Format('yyyy-MM-dd hh:mm:ss');
                            var my_index = index.replace('&{time}',time).replace('&{message}','结果更新')
                            var my_button = log_button.replace('&{log_url}',message[1].log_url)
                            if(message[1].flow_status == 'SUCCESS'){
                                my_index = my_index.replace('&{color}','color:#96e9c6')
                            }else {
                                my_index = my_index.replace('&{color}','color:#e9b82c')
                                my_button += '&nbsp;&nbsp;'+abandon_button
                            }
                            $('#end').remove()
                            $('ul').append(my_index.replace('&{button}',my_button))
                        }
                        if(number === 4 && message.length >= 3){
                            number++
                            index = right_li
                            var time = new Date(message[2].begin_time).Format('yyyy-MM-dd hh:mm:ss');
                            $('#end').remove()
                            $('ul').append(index.replace('&{time}',time).replace('&{message}','单元测试')
                                .replace('&{button}',log_button).replace('&{log_url}',message[2].log_url)
                                .replace('&{color}','color:#40e112')
                            )
                        }
                        if(number === 5 && message[2].flow_status != 'BEGIN'){
                            index = left_li
                            var time = new Date(message[2].end_time).Format('yyyy-MM-dd hh:mm:ss');
                            var my_index = index.replace('&{time}',time).replace('&{message}','测试结束')
                            var my_button = log_button.replace('&{log_url}',message[2].log_url)
                            if(message[2].flow_status == 'SUCCESS'){
                                my_index = my_index.replace('&{color}','color:#96e9c6')

                            }else if(message[2].flow_status == 'FAILURE'){
                                my_index = my_index.replace('&{color}','color:#e90c33')
                                my_button += '&nbsp;&nbsp;'+abandon_button
                            }else {
                                my_index = my_index.replace('&{color}','color:#e9b82c')
                            }
                            $('ul').append(my_index.replace('&{button}',my_button))
                        }

                        $('#end').remove()
                        if(result.status=='ABANDONED'){
                            if(index.indexOf('right_li')>0){
                                $('ul').append('<li class="left_li"><span class="ic_events"><i class="png"></i></span><p class="txt_events" style="color: #ffffff"><strong>提交已被丢弃 '+new Date(result.updated).Format('yyyy-MM-dd hh:mm:ss')+'</strong></p></li>')
                            }else {
                                $('ul').append('<li class="right_li"><span class="ic_events png"></span><p class="txt_events" style="color: #ffffff"><strong>'+new Date(result.updated).Format('yyyy-MM-dd hh:mm:ss')+' 提交已被丢弃</strong></p></li>')
                            }
                            $('.events_list').append('<span class="ic_arr png" id="end"></span>')
                        }else if(result.mergeable==false&&result.status=='NEW'){
                            abandonPost(change_id,'检测到冲突，系统自动丢弃');
                        }else {
                            $('.events_list').append('<span class="ic_arr png" id="end"></span>')
                        }
                    }
                }
            }
            var wsServer = 'ws://192.168.31.14:8086/webSocket'; //服务器地址
            var websocket = new WebSocket(wsServer); //创建WebSocket对象
            websocket.onmessage = function (evt) {
                //解析socket返回，触发页面变化
                showMessage(JSON.parse(evt.data))
            };
            websocket.onopen = function (evt) {
                console.log("连接打开！")
            }
            window.onbeforeunload = function () {
                websocket.close()
            }
            statusQuery(change_id,function (data) {
                console.log(JSON.parse(data))
                showMessage({message:data,type:0})
            })
        })
    });
</script>
</body>
</html>
