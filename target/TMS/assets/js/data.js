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