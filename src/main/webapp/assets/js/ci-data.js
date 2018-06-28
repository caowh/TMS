/**
 * Created by 曹文豪 on 2017/8/29.
 */
/**
 * 获取提交信息接口
 * */
var address = 'http://192.168.31.14:8086';
var gerrit = 'http://192.168.4.172:8081';
function getStatusChanges(data, fun) {
    $.ajax({
        type: "post",
        contentType: "application/json; charset=utf-8",
        url: address+"/status/changes",
        data: JSON.stringify(data),
        dataType: "json",
        success: fun
    })
}

function changeAbandon(data, fun) {
    $.ajax({
        type: 'POST',
        url: address+"/project/change/abandon",
        data: JSON.stringify(data),
        contentType: "application/json; charset=utf-8",
        success: fun,
    });
}

function getChange(change_id,fun) {
    $.get(address+"/project/change?change_id="+change_id,fun)
}

function getOwner(account_id,fun) {
    $.get(address+"/project/owner?account_id="+account_id, fun);
}


function statusQuery(change_id,fun){
    $.get(address+"/status/query?change_id="+change_id,fun,'text');
}

function getProjects(fun) {
    $.get(address+"/project/list",fun,'json');
}