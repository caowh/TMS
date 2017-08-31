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
 * 用例总数统计接口
 * 参数，data：post请求的数据（json）；fun：回调函数，返回用例总数
 *     {
            planName:planName,
            type:"severity",
            node:"0"，
            version:"v1.0"
        }
 * */
function getCaseTotalCount(data,fun) {
    $.ajax({
        type: "post",
        contentType: "application/json; charset=utf-8",
        url: "/CaseResult/caseTotalCount.do",
        data: JSON.stringify(data),
        dataType: "json",
        success: fun
    })
}