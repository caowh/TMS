/**
 * Created by user on 2017/6/30.
 */
/**
 * 显示自动用例的具体测试信息
 * @param casedesc 当前测试用例描述
 * @param time 当前测试用例执行总时间
 */
function showAtCase(casedesc,time) {
    $('#currentCase').html(casedesc)
    settime(time/1000)
    $('#atResult').css('display','block')
}


/**
 * 显示手动用例的具体测试信息
 * @param casedesc 当前测试用例描述
 * @param pass 当前测试用例的通过准则
 * @param time 当前测试用例执行总时间
 */
function showMtCase(casedesc, pass,time) {
    $('#currentCase').html(casedesc)
    $('#ddPC').css('display','block')
    $('#passCriteria').html(pass)
    settime(time/1000)
    $('#mtResult').css('display','block')
}


/**
 * 还原当前用例的具体测试信息
 */
function hideCase() {
    if ($('#mtResult').css('display')=='block'){
        $('#mtResult').css('display','none')
        $('#ddPC').css('display','none')
        $('#mtResult input:radio[name="result"]').attr("checked",false);
    }
    if ($('#atResult').css('display')=='block'){
        $('#atResult').css('display','none')
        $('#atResult label').html('')
    }
}

/**
 * 在页面上显示每个case的执行结果
 * @param result 执行结果
 */
function showCaseResult(result) {

    $('#atResult').css('display','block')
    $('#atResult label').html($('#atResult label').text()+"   "+result)
    
}

/**
 * 得到手动case的测试结果
 * @return result 返回手动测试的结果
 */
function getCaseResult() {
    if($('#resultP:checked').val()!=null){
        return "p"
    }
    else if ($('#resultF:checked').val()!=null){
        return "f"
    }
    else {
        return "none"
    }
}

/**
 * 当前用例执行时间倒计时
 * @param time 当前测试用例剩余执行时间
 */
function settime(time) {
    if (time > 0) {
        $('#currentTime').html(time)
        time--;
        setTimeout(function() {
            settime(time)
        },1000)
    }
}

/**
 * 推送用例执行结果
 * @param caseid 当前测试用例id
 * @param result 当前测试执行结果
 * @param describe 当前测试用例的描述
 * @param message 当前测试信息
 * @param functionName 当前对应的函数名称
 */
function addCaseToArrGlobal(caseid,result,describe,message,functionName) {

    var myCase={}
    myCase["caseid"]=caseid
    myCase["result"]=result
    myCase["describe"]=describe
    myCase["message"]=message
    myCase["functionName"]=functionName
    var size=arrglobal.length
    arrglobal[size]=myCase

}


/**
 * 显示测试结果到页面
 */
function showCaseTestResult() {
    var success=0,failed=0,ignore=0;
    for(var i=0;i<arrglobal.length;i++){
        var result=arrglobal[i]["result"];
        if(result=="p"){
            success++
        }else if(result=="f"){
            failed++
        } else {
            ignore++
        }
    }
    $('#eachTest').css("display","none")
    $('#caseResult').css("display","block")
    $('#caseTotalResult').html('成功：'+success+'；失败：'+failed+'；忽略：'+ignore)
    var json=getCaseDetailResult(arrglobal)
    $('#caseDetailResult input:text').val(json)
}



/**
 * 显示testlink接口的返回信息到页面
 * @param data testlink接口返回的详细信息
 */
function showTestlinkDetailResult(data) {
    var success=0,failed=0;
    var arrResult=data[0]['data']
    for(var i=0;i<arrResult.length;i++){
        if(arrResult[i]['result']=="success"){
            success++
        }else {
            failed++
        }
    }
    $('#testlinkResult').css("display","block")
    $('#testlinkTotalResult').html('成功：'+success+'；失败：'+failed)
    $('#testlinkDetailResult input:text').val(JSON.stringify(data))
}





/**
 * 转化测试结果为json格式返回，适用于单测试计划
 * @param resultarray 所有用例执行结果数组
 * @return json 转化后的json串，用于发送至testlink
 */
function getCaseRetryJson(resultarray) {
    array=new Array()
    arr={}
    arr["planname"]=planname
    resultarray.forEach(function (eachCase) {
        delete eachCase.describe
        delete eachCase.message
    })
    arr["data"]=resultarray
    array[0]=arr
    var arrParse = JSON.stringify(array)
    return arrParse
}


/**
 * 保存测试结果信息
 * @param caseRetryJson 回归测试用到的的json字符串
 */
function saveCaseTestResult(caseRetryJson) {
    var caseDetailResult=$('#caseDetailResult input:text').val()
    $.ajax({
        type: "post",
        url:'http://localhost:8085/api/v1/testservice/saveCaseDetailResult',
        data: caseDetailResult
    });
    $.ajax({
        type: "post",
        url:'http://localhost:8085/api/v1/testservice/saveCaseRetryJson',
        data: caseRetryJson
    });
}



/**
 * 将发送到testlink的返回信息保存
 */
function saveTestlinkDetailResult() {
    var testlinkDetailResult=$('#testlinkDetailResult input:text').val()
    $.ajax({
        type: "post",
        url:'http://localhost:8085/api/v1/testservice/saveTestlinkDetailResult',
        data: testlinkDetailResult
    })
}



/**
 * 转化测试结果为json格式返回，适用于单测试计划
 * @param resultarray 所有用例执行结果数组
 * @return json 转化后的json串，用于显示测试结果的详细信息
 */
function getCaseDetailResult(resultarray) {
    array=new Array()
    try {
        arr={}
        arr["planname"]=planname
        arr["data"]=resultarray
        array[0]=arr
        var arrParse = JSON.stringify(array)
        return arrParse
    }catch (e){
        console.log(e)
        console.log("output json failed!")
    }
}



/**
 * 是否显示测试结果的详细信息
 */
function changeStyleCaseResult() {
    if($("#detailCaseCheckbox:checked").val()!=null){
        $('#caseDetailResult').css('display','block')
        $('#caseTotalResult').css('display','none')
    }else {
        $('#caseDetailResult').css('display','none')
        $('#caseTotalResult').css('display','block')
    }
}


/**
 * 是否显示发送到testlink结果的详细信息
 */
function changeStyleTestlinkResult() {
    if($("#detailtestLinkCheckbox:checked").val()!=null){
        $('#testlinkDetailResult').css('display','block')
        $('#testlinkTotalResult').css('display','none')

    }else {
        $('#testlinkDetailResult').css('display','none')
        $('#testlinkTotalResult').css('display','block')
    }
}


/**
 * 生成测试用例的控制文件，包含所有未通过和忽略掉的用例
 * @param json 某一次测试结果的json
 * @return script 生成的script文件字符串
 */
function getTestScript(json) {

    var data=JSON.parse(json)[0]["data"]
    console.log(data)
    var script='<script type="text/javascript">';
    for(var i=0;i<data.length;i++){
        var result=data[i]['result'];
        if(result!="p"){
            var functionName=data[i]['functionName'];
            if(script.indexOf(functionName)<0){
                script+=functionName+'();'
            }
        }
    }
    return script+'</script>'
}















