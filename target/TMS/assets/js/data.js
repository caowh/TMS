/**
 * Created by user on 2017/8/29.
 */
/**
 * 用例及问题数综合分析接口
 * 参数，data：post请求的数据（json）；fun：回调函数，返回图表信息
 *      {
            planName:planName,
            type:"severity",
            node:"0"，
            version:"v1.0"
        }
 * */
function getCaseAnalyseResult(data, fun) {
    $.ajax({
        type: "post",
        contentType: "application/json; charset=utf-8",
        url: "/CaseResult/analyse.do",
        data: JSON.stringify(data),
        dataType: "json",
        success: fun
    })
}


/**
 * 多版本系统获取子模块树
 * 参数，data：post请求的数据（json）；fun：回调函数,子模块树
 *     {
            planName:planName,
            version:"v1.0,v2.0"
        }
 * */
function getModuleTree(data,fun) {
    $.ajax({
        type: "post",
        contentType: "application/json; charset=utf-8",
        url: "/main/getModuleTree.do",
        data: JSON.stringify(data),
        dataType: "json",
        success: fun
    })
}


/**
 * 获取GV5产品根目录
 * */
function getProjectTree(fun) {
    $.ajax({
        type: "get",
        url: "/main/getProjectTree.do",
        dataType: "json",
        success: fun
    })
}

/**
 * 查看支持的分析类型
 * 参数，data：post请求的数据（json）；fun：回调函数，支持的分析类型
 *     {
            version:"v1.0,v2.0"
        }
 * */
function getSupportType(data,fun) {
    $.ajax({
        type: "post",
        contentType: "application/json; charset=utf-8",
        url: "/main/getSupportType.do",
        data: JSON.stringify(data),
        dataType: "json",
        success: fun
    })
}


/**
 * 判断用户是否已登录
 *@fun：回调函数，支持的分析类型
 *     {
            version:"v1.0,v2.0"
        }
 * */
function isLogin(fun) {
    $.ajax({
        type: "get",
        url: "/before/isLogin.do",
        dataType: "json",
        async:false,
        success: fun
    })
}

/**
 * 修改阈值的方法
 * 参数，fun：回调函数，修改阈值的方法
 *     {
            version:"v1.0,v2.0"
        }
 * */
function modifyThreshold(idname) {

    $.ajax({
        type: "post",
        url: "/main/modifyThreshold.do?value=" + $("#" + idname).val(),
        error: function () {
            alert("修改失败");
        },
        success: function (res) {
            alert("修改成功");
        }
    })
}



/**
 * 查询自动化测试用例
 * 参数，fun：回调函数，返回符合条件所有的自动化用例
 *   data  {
            node:"30,40"
        }
 * */
function searchAutoCase(node,fun) {
    $.ajax({
        type: "get",
        url: "/autoCaseRepertory/searchAutoCase.do?node="+node,
        dataType: "json",
        success: fun
    })
}


/**
 * 根据旧密码修改密码
 * 参数，fun：回调函数，返回符合条件所有的自动化用例
 *   data  {
            old:""
            new:""
        }
 * */
function updatePwd(data,fun) {
    $.ajax({
        type: "post",
        contentType: "application/json; charset=utf-8",
        url: "/main/updatePwd.do",
        data: JSON.stringify(data),
        dataType: "json",
        success: fun
    })
}


/**
 * 修改计划的更新状态
 * 参数，fun：回调函数，返回符合条件所有的自动化用例
 *   data  {
            planId:""
            update:""  true or false
        }
 * */
function updatePlanStatus(data,fun) {
    $.ajax({
        type: "post",
        contentType: "application/json; charset=utf-8",
        url: "/CaseResult/updatePlanStatus.do",
        data: JSON.stringify(data),
        dataType: "json",
        success: fun
    })
}



/**
 * 删除自动化用例
 * 参数，fun：回调函数，返回删除是否成功信息
 * 参数，ids:""  删除的用例id列表
 * */
function deleteAutoCase(ids,fun) {
    $.ajax({
        type: "get",
        url: "/autoCaseRepertory/deleteAutoCase.do?ids="+ids,
        dataType: "json",
        success: fun
    })
}

/**
 * 移动自动化用例
 * 参数，fun：回调函数，返回移动是否成功信息
 * 参数，ids:""  移动的用例id列表
 * */
function moveAutoCase(ids,nodeName,fun) {
    $.ajax({
        type: "get",
        url: "/autoCaseRepertory/moveAutoCase.do?ids="+ids+"&nodeName="+nodeName,
        dataType: "json",
        success: fun
    })
}

