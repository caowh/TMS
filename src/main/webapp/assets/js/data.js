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