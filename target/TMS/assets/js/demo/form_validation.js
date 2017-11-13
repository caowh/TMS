"use strict";
function searchAllAutoCase() {
    var planName=$("#searchPlanName").find("option:selected").text().trim()
    var treeObj=$.fn.zTree.getZTreeObj("searchModuleNodeTree")
    var nodes="";
    try {
        nodes=treeObj.getSelectedNodes(true)
    }catch (e){
        $('#warning2').click();
        return false
    }
    if(nodes.length==0){
        $('#warning2').click();
        return false
    }
    var node=nodes[0].cid
    for(var i=1;i<nodes.length;i++){
        node=node+","+nodes[i].cid
    }
    $('#searchA').html('正在查询...')
    searchAutoCase({node:String(node),planName:planName},function (res) {
        $('#searchA').html('查询')
        if(res.code==1){
            var result=res.result
            var data=new Array()
            for(var i=0;i<result.length;i++){
                var autoCaseHelper=result[i]
                var autoCase={}
                autoCase['id']='<input type="checkbox" class="uniform">'
                autoCase['true_id']=autoCaseHelper.id
                autoCase['用例id']=autoCaseHelper.caseId
                autoCase['用例描述']=autoCaseHelper.describe
                autoCase['子模块']=autoCaseHelper.node
                autoCase['操作']='<a href="/autoCaseRepertory/lookAutoCase.do?id='+autoCaseHelper.id+'" class="btn btn-xs bs-tooltip" title="Search" target="_blank"> <i class="icon-search"> </i> </a>'
                data.push(autoCase)
            }
            $('#autoCaseTable').dataTable().fnClearTable();   //将数据清除
            if(result.length>0){
                $('#autoCaseTable').dataTable().fnAddData(data,true);
            }
        }else {
            $('#warning4').click();
            console.error(res.message)
        }
    })

}

function validatePlanNode() {
    if($('#moduleNodeTree').html()=="找不到数据"){
        $('#warning1').click()
        return false;
    }else {
        var treeObj=$.fn.zTree.getZTreeObj("moduleNodeTree")
        var nodes=treeObj.getSelectedNodes(true)
        if(nodes.length==0){
            $('#warning3').click()
            return false;
        }
        return true;
    }
}
$(document).ready(function(){
    $('#autoCaseTable').DataTable({
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
        order : [],
        columns: [
            {data:'id',className : "checkbox-column",orderable : false},
            {data: "true_id",visible:false},
            {data: "用例id"},
            {data: "用例描述"},
            {data: "子模块"},
            {data: "操作"}
        ]
    });
    $('#planName').change(function () {
        $('#moduleNodeTree').empty()
        $('#moduleNodeTree').append('正在加载...')
        var selectText=$("#planName").find("option:selected").text()
        if(selectText==""){
            $('#moduleNodeTree').append("请选择一个计划！")
        }else {
            var depart=selectText.indexOf("_")
            getModuleTree({planName:selectText.substring(0,depart),planVersion:selectText.substring(depart+1,selectText.length).trim()},function (res) {
                if(res.code==1){
                    var nodeTree=[];
                    nodeTree.push(res.result)
                    var setting = {
                        view:{
                            selectedMulti:false
                        },
                        callback: {
                            onClick: function (event, treeId, treeNode) {
                                $('#node').val(treeNode.cid);
                            }
                        }
                    };
                    $.fn.zTree.init($("#moduleNodeTree"), setting, nodeTree);
                }else {
                    $('#moduleNodeTree').empty()
                    $('#moduleNodeTree').append('找不到数据')
                }
            })
        }
    })
    $('#searchPlanName').change(function () {
        $('#searchModuleNodeTree').empty()
        $('#searchModuleNodeTree').append('正在加载...')
        var selectText=$("#searchPlanName").find("option:selected").text()
        if(selectText==""||selectText==null){
            $('#searchModuleNodeTree').append("请选择一个计划！")
        }else {
            var depart=selectText.indexOf("_")
            getModuleTree({planName:selectText.substring(0,depart),planVersion:selectText.substring(depart+1,selectText.length).trim()},function (res) {
                if(res.code==1){
                    var nodeTree=[];
                    nodeTree.push(res.result)
                    var setting = {};
                    $.fn.zTree.init($("#searchModuleNodeTree"), setting, nodeTree);
                }else {
                    $('#searchModuleNodeTree').empty()
                    $('#searchModuleNodeTree').append('找不到数据')
                }
            })
        }
    })
    $('#execA').click(function () {
        var table=$('#autoCaseTable').dataTable()
        var nTrs=table.fnGetNodes()
        var ids=[]
        for(var i = 0; i < nTrs.length; i++){
            if($(nTrs[i]).find('.checked').length==1){
                ids.push(table.fnGetData(nTrs[i]).true_id)
            }
        }
        if(ids.length>0){
            window.open("/autoCaseRepertory/prepareExecute.do?ids="+JSON.stringify(ids))
        }else {
            $('#warning5').click()
            return false
        }
    })
    $.extend($.validator.defaults,{invalidHandler:function(c,a){var d=a.numberOfInvalids();if(d){var b=d==1?"你有一个必填项未填写内容！":"你有"+d+" 个必填项未填写内容！";noty({text:b,type:"error",timeout:2000})}}});$("#validate-1").validate();$("#validate-2").validate();$("#validate-3").validate();$("#validate-4").validate()});