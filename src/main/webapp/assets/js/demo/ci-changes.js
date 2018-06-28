var mytable;
$(document).ready(function(){
    $('#changesDatable').DataTable({
        order : [ 0, 'desc' ],
        aLengthMenu: [ 10, 25, 50],
        language:{
            sProcessing : "处理中...",
            sLengthMenu : "显示 _MENU_ 项结果",
            sZeroRecords : "没有匹配结果",
            sInfo : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
            sInfoEmpty : "显示第 0 至 0 项结果，共 0 项",
            sInfoFiltered : "(由 _MAX_ 项结果过滤)",
            sInfoPostFix : "",
            sSearch : "",
            searchPlaceholder : "关键字搜索",
            sUrl : "",
            sEmptyTable : "表中数据为空",
            sLoadingRecords : "载入中...",
            sInfoThousands : ",",
            oPaginate : {
                sFirst : "首页",
                sPrevious : "上页",
                sNext : "下页",
                sLast : "末页"
            },
            oAria : {
                sSortAscending : ": 以升序排列此列",
                sSortDescending : ": 以降序排列此列"
            }
        },
        columns: [
            {data:'提交ID'},
            {data:'change_id',visible:false},
            {data: "提交时间"},
            {data: "最后更新时间"},
            {data: "提交人"},
            {data: "提交信息"},
            {data: "分支"},
            {data: "静态检查"},
            {data: "人工审查"},
            {data: "单元测试"},
            {data: "入库"},
        ]
    });
    mytable=$('#changesDatable').dataTable()
    var begin='<a href="${url}" target="_blank"><img src="/assets/img/loading.gif" width="40%" height="40%"></a>';
    var success='<a class="icon-ok" style="color: #40e112;font-size: 28px" href="${url}" target="_blank"></a>';
    var failure='<a class="icon-remove" style="color: #e90c33;font-size: 28px" href="${url}" target="_blank"></a>';
    var warning='<a class="icon-warning-sign" style="color: #e9b82c;font-size: 28px" href="${url}" target="_blank"></a>';
    var projects = new Array();
    getProjects(function (data) {
        for(var i=0;i<data.length;i++){
            projects.push(data[i])
            $('#projects').append('<option value="'+data[i]+'">'+data[i]+'</option>')
        }
    })
    function updateStatus(flowStatus,change,type) {
        var flow_status=flowStatus.flow_status
        var icon;
        if(flow_status=='BEGIN'){
            icon=begin;
        }else if(flow_status=='SUCCESS'){
            icon=success;
        }else if(flow_status=='FAILURE'){
            icon=failure;
        }else {
            icon=warning;
        }
        change[type]=icon.replace('${url}',flowStatus.log_url)
    }
    function updateOneChange(changeMsg) {
        var change ={}
        change['提交ID']='<a href="/ci/change.do?change_id='+changeMsg._number+'" target="_blank">'+changeMsg._number+'</a>'
        change['change_id']=changeMsg._number
        change['提交时间']=new Date(changeMsg.created).Format('yyyy-MM-dd hh:mm:ss');
        change['最后更新时间']=new Date(changeMsg.updated).Format('yyyy-MM-dd hh:mm:ss');
        change['提交人']=changeMsg.name
        change['提交信息']=changeMsg.subject
        change['分支']=changeMsg.branch
        change['静态检查']=null
        change['人工审查']=null
        change['单元测试']=null
        change['入库']=null
        var status = changeMsg.status
        var icon;
        if(status=='MERGED'){
            icon=success;
            change['入库']=success.replace('${url}',gerrit+'/#/c/'+changeMsg._number+'/')
        }else if(status=='ABANDONED'){
            icon=failure;
        }else {
            icon=begin
        }
        change['入库']=icon.replace('${url}',gerrit+'/#/c/'+changeMsg._number+'/')
        var flowStatusList=changeMsg.flowStatusList
        if(flowStatusList!=null&&flowStatusList.length>0){
            for(var j=0;j<flowStatusList.length;j++){
                var flowStatus = flowStatusList[j]
                if(flowStatus.flow_type=='10'){
                    updateStatus(flowStatus,change,'静态检查')
                }else if(flowStatus.flow_type=='20'){
                    updateStatus(flowStatus,change,'人工审查')
                }else if(flowStatus.flow_type=='30'){
                    updateStatus(flowStatus,change,'单元测试')
                }
            }
        }
        return change
    }
    function refreshTableData() {
        $('.widget-header img').removeClass('hide');
        mytable.fnClearTable()
        getStatusChanges({project:$("#projects").find("option:selected").val(),after:$('#after').val()},function (data) {
            var array=new Array()
            for(var i=0;i<data.length;i++){
                var change = updateOneChange(data[i])
                array.push(change)
            }
            if(array.length>0)mytable.fnAddData(array,true);
            $('.widget-header img').addClass('hide');
        })
    }
    refreshTableData()
    var wsServer = 'ws://192.168.31.14:8086/webSocket';
    var websocket = new WebSocket(wsServer);
    websocket.onmessage = function (evt) {
        function refreshChangeStatus(parse) {
            var type = parse.type
            //流程更新消息
            if(type===0){
                var message = JSON.parse(parse.message)
                var nTrs=mytable.fnGetNodes()
                for(var i = 0; i < nTrs.length; i++){
                    var change_id = mytable.fnGetData(nTrs[i]).change_id
                    if(change_id==message[0].change_id){
                        var change =new Array();
                        var position = mytable.fnGetPosition(nTrs[i])
                        for(var j = 0; j < message.length; j++){
                            var flowStatus=message[j]
                            if(flowStatus.flow_type=='10'){
                                updateStatus(message[j],change,'静态检查')
                                mytable.fnUpdate(change['静态检查'],position,7,false)
                            }else if(flowStatus.flow_type=='20'){
                                updateStatus(flowStatus,change,'人工审查')
                                mytable.fnUpdate(change['人工审查'],position,8,false)
                            }else if(flowStatus.flow_type=='30'){
                                updateStatus(flowStatus,change,'单元测试')
                                mytable.fnUpdate(change['单元测试'],position,9,false)
                                if(flowStatus.flow_status=='SUCCESS'){
                                    mytable.fnUpdate(success.replace('${url}',gerrit+'/#/c/'+flowStatus.change_id+'/'),position,10,false)
                                }
                            }
                        }
                        break
                    }else if(i===nTrs.length-1){
                        //最后一个没匹配上，则检测到有新的提交信息，也有可能是其他项目的提交
                        break
                    }
                }
            }else if(type===1){
                var message = parse.message
                var nTrs=mytable.fnGetNodes()
                for(var i = 0; i < nTrs.length; i++){
                    var change_id = mytable.fnGetData(nTrs[i]).change_id
                    if(change_id==message){
                        var position = mytable.fnGetPosition(nTrs[i])
                        mytable.fnUpdate(failure.replace('${url}',gerrit+'/#/c/'+message+'/'),position,10,false)
                        break;
                    }
                }
            }else if(type===2){
                var message = parse.message
                var nTrs=mytable.fnGetNodes()
                for(var i = 0; i < nTrs.length; i++){
                    var change_id = mytable.fnGetData(nTrs[i]).change_id
                    if(change_id==message){
                        var position = mytable.fnGetPosition(nTrs[i])
                        mytable.fnUpdate(begin.replace('${url}',gerrit+'/#/c/'+message+'/'),position,10,false)
                        break;
                    }
                }
            }else if(type===3){
                var message = JSON.parse(parse.message)
                if(message.project==$("#projects").find("option:selected").val()){
                    var change = updateOneChange(message)
                    mytable.fnAddData(change,true)
                }
            }else if(type===4){
                var message = JSON.parse(parse.message)
                var nTrs=mytable.fnGetNodes()
                for(var i = 0; i < nTrs.length; i++){
                    var change_id = mytable.fnGetData(nTrs[i]).change_id
                    if(change_id==message.change_id){
                        var position = mytable.fnGetPosition(nTrs[i])
                        mytable.fnDeleteRow(position,function () {
                            var change = updateOneChange(message)
                            mytable.fnAddData(change,true)
                        },false)
                        break;
                    }
                }
            }
        }

        refreshChangeStatus(JSON.parse(evt.data))
    };
    websocket.onopen = function (evt) {
        // console.log("连接打开！")
    }
    window.onbeforeunload = function () {
        websocket.close()
    }
    $('#projects').change(function () {
        refreshTableData()
    })
    $('#after').change(function () {
        refreshTableData()
    })
    $('.widget-header input:text').on('input propertychange', function(e){
        var value=$(this).val()
        if(value!=""&&projects.indexOf(value)>=0){
            $("#projects").val(value);
            refreshTableData()
        }
    });
});
