function showAtCase(casedesc,time) {
    $("#currentCase").html(casedesc)
    setTime(time/1000)
    $("#atResult").css("display","block");
}
function showMtCase(casedesc, pass,time) {
    $("#currentCase").html(casedesc)
    $("#ddPC").css("display","block")
    $("#passCriteria").html(pass)
    setTime(time/1000)
    $("#mtResult").css("display","block");
}
function hideCase() {
    if ($("#mtResult").css("display")=="block"){
        $("#mtResult").css("display","none")
        $("#ddPC").css("display","none")
        $("#mtResult input:radio[name='result']").attr("checked",false);
    }
    if ($("#atResult").css("display")=="block"){
        $("#atResult").css("display","none")
        $("#atResult label").html("");
    }
}
function showCaseResult(result) {
    $("#atResult").css("display","block")
    $("#atResult label").html($("#atResult label").text()+"   "+result)

}
function getCaseResult() {
    if($("#resultP:checked").val()!=null){
        return "p";
    }
    else if ($("#resultF:checked").val()!=null){
        return "f";
    }
    else {
        return "none";
    }
}
function setTime(time) {
    if (time > 0) {
        $("#currentTime").html(time)
        time--;
        setTimeout(function() {
            setTime(time);
        },1000);
    }
}
function getRemainingTime(time) {
    if (time > 0) {
        $("#currentStautus").html("开始执行用例，剩余用时："+time/1000+"(s)，剩余用例数："+(window.arrCasePrepareRun.length-1))
        if(waitTimeGlobal==0&&$("#currentTime").html()==1){
            $("#caseStatusChangeButton").html("启动")
            $("#casePlayBack").css("display","inline-block")
            return false;
        }
        time=time-1000;
        setTimeout(function() {
            getRemainingTime(time);
        },1000);
    }
}
function addCaseToArrGlobal(caseid,result,describe,message,functionName) {
    var myCase={}
    myCase["caseid"]=caseid
    myCase["result"]=result
    myCase["describe"]=describe
    myCase["message"]=message
    myCase["functionName"]=functionName
    var size=window.arrglobal.length
    window.arrglobal[size]=myCase

}
function showCaseTestResult() {
    var success=0,failed=0,ignore=0;
    for(var i=0;i<window.arrglobal.length;i++){
        var result=window.arrglobal[i]["result"];
        if(result=="p"){
            success++
        }else if(result=="f"){
            failed++
        } else {
            ignore++
        }
    }
    $("#eachTest").css("display","none")
    $("#caseResult").css("display","block")
    $("#caseTotalResult").html("成功："+success+"；失败："+failed+"；忽略："+ignore)
    var json=getCaseDetailResult(window.arrglobal)
    $("#caseDetailResult input:text").val(json)
}
function showTestlinkDetailResult(data) {
    var success=0,failed=0;
    var arrResult=data[0]["data"]
    for(var i=0;i<arrResult.length;i++){
        if(arrResult[i]["result"]=="success"){
            success++
        }else {
            failed++
        }
    }
    $("#testlinkResult").css("display","block")
    $("#testlinkTotalResult").html("成功："+success+"；失败："+failed)
    $("#testlinkDetailResult input:text").val(JSON.stringify(data))
}
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
function saveCaseTestResult(caseRetryJson) {
    var caseDetailResult=$("#caseDetailResult input:text").val()
    $.ajax({
        type: "post",
        url:"http://localhost:8085/api/v1/testservice/saveCaseDetailResult",
        data: caseDetailResult
    });
    $.ajax({
        type: "post",
        url:"http://localhost:8085/api/v1/testservice/saveCaseRetryJson",
        data: caseRetryJson
    });
}
function saveTestlinkDetailResult() {
    var testlinkDetailResult=$("#testlinkDetailResult input:text").val()
    $.ajax({
        type: "post",
        url:"http://localhost:8085/api/v1/testservice/saveTestlinkDetailResult",
        data: testlinkDetailResult
    })
}
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
function changeStyleCaseResult() {
    if($("#detailCaseCheckbox:checked").val()!=null){
        $("#caseDetailResult").css("display","block")
        $("#caseTotalResult").css("display","none")
    }else {
        $("#caseDetailResult").css("display","none")
        $("#caseTotalResult").css("display","block")
    }
}
function changeStyleTestlinkResult() {
    if($("#detailtestLinkCheckbox:checked").val()!=null){
        $("#testlinkDetailResult").css("display","block")
        $("#testlinkTotalResult").css("display","none")

    }else {
        $("#testlinkDetailResult").css("display","none")
        $("#testlinkTotalResult").css("display","block")
    }
}
function getTestScript(json) {

    var data=JSON.parse(json)[0]["data"]
    console.log(data)
    var script="<script type='text/javascript'>";
    for(var i=0;i<data.length;i++){
        var result=data[i]["result"];
        if(result!="p"){
            var functionName=data[i]["functionName"];
            if(script.indexOf(functionName)<0){
                script+=functionName+"();"
            }
        }
    }
    return script+"</script>"
}
function removeFirst(functionName) {
    window.currentCase=functionName
    for(var i=0;i<window.arrCasePrepareRun.length;i++){
        if(window.arrCasePrepareRun[i].function==functionName){
            window.arrCasePrepareRun.splice(i,1)
            break
        }
    }
}
function pushBefore(functionName,id) {
    window.arrCasePrepareRun.push({function:functionName,id:id})
}
function casePause() {
    for(var i=0;i<window.arrCasePrepareRun.length;i++){
        clearTimeout(window.arrCasePrepareRun[i].id)
    }
    window.waitTimeGlobal=0
}

function casePlayBack() {
    $("body").append("<script type='text/javascript'>"+window.currentCase+"();"+"</script>")
    setTimeout(function () {
        $("#casePlayBack").css("display","inline-block")
        $("#caseStatusChangeButton").css("display","inline-block")
        $("#currentStautus").html("用例回放结束！")
        window.waitTimeGlobal=0
    },window.waitTimeGlobal)
    $("#casePlayBack").css("display","none")
    $("#caseStatusChangeButton").css("display","none")
    $("#currentStautus").html("回放用例中...")
    window.arrglobal.splice(window.arrglobal.length-1,1)
}
function caseContinue() {
    var str="";
    for(var i=0;i<window.arrCasePrepareRun.length;i++){
        str+=window.arrCasePrepareRun[i].function+"();"
    }
    window.arrCasePrepareRun.length=0
    $("#casePlayBack").css("display","none")
    $("body").append("<script type='text/javascript'>"+str+"</script>")
}
function ResultTotestlink() {
    getRemainingTime(waitTimeGlobal)
    var endId=setTimeout(function(){
        showCaseTestResult()
        $("#currentStautus").html("用例执行完毕")
        if(window.isSendToTestlink){
            var testlinkJson=getCaseDetailResult(window.arrglobal)
            $("#currentStautus").html("开始发送至testlink......")
            $.ajax({
                type: "post",
                url:"http://"+window.address+"/api/v1/testlinkservice/fillAllResult",
                data: testlinkJson,
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function(data){
                    showTestlinkDetailResult(data)
                    $("#currentStautus").html("发送成功，测试完毕！")
                },
                error:function () {
                    $("#currentStautus").html("发送失败，请确认服务器连接正常！")
                }
            });
        }
    },window.waitTimeGlobal);
    window.arrCasePrepareRun.push({function:"ResultTotestlink",id:endId})
}
function caseStatusChange() {
    if($("#caseStatusChangeButton").html()=="暂停"){
        casePause()
        $("#caseStatusChangeButton").html("正在暂停...")
    }else if($("#caseStatusChangeButton").html()=="启动"){
        $("#caseStatusChangeButton").html("暂停")
        caseContinue()
    }
}
var Roujiamo={
    start:function (key) {
            $.ajax({
                type: "get",
                url:"/autoCaseRepertory/executeTestPlan.do?key="+key,
                dataType:"json",
                success: function(res){
                    if(res.code==1){
                        var result=res.result
                        $("body").append("<script type='text/javascript'>"+result+"</script>")
                    }else {
                        alert(res.message);
                    }
                },
                error:function () {
                    $("#currentStautus").html("用例加载失败!")
                }
            });
            $("#currentStautus").html("用例加载中...")
        },
    redirectConsolePrint:function () {
        window.lastLog="";
        console.oldLog = console.log;
        console.log = function(str) {
            console.oldLog(str);
            lastLog = str;
        }
        window.lasterr="";
        console.error = function(str) {
            lasterr = str;
        }
        window.lastwarn="";
        console.warn = function(str) {
            lastwarn = str;
        }
    },
    assertEqual:function(str1, str2){
        var info={}
        info["message"]=""
        if(str1!=null&&str2!=null){
            if(str1==str2){
                info["result"]="p"
            }else{
                info["result"]="f"
                info["message"]="expect:"+str1+","+"actual:"+str2
            }
        }else {
            info["result"]="f"
            info["message"]="expect:"+str1+","+"actual:"+str2
        }
        return info
    },
    assertNotNull:function (obj){
        var info={}
        info["message"]=""
        if(obj!=null){
            info["result"]="p"
        }else{
            info["result"]="f"
            info["message"]="result is null"
        }
        return info
    },
    assertVp:function (v1,v2) {
        var info={}
        info["message"]=""
        if (v1!=null&&v2!=null){
            var b1,b2,b3,b4,b5,b6,between=0.1;
            b1=this.assertEqual(Math.round(v1.range),Math.round(v2.range)).result;  //单位;米
            Math.abs(Number(v1.lon).toFixed(1)-Number(v2.lon).toFixed(1))<=between?b2="p":b2="f";   //单位;度
            Math.abs(Number(v1.lat).toFixed(1)-Number(v2.lat).toFixed(1))<=between?b3="p":b3="f";   //单位;度
            b4=this.assertEqual(Math.round(v1.height),Math.round(v2.height)).result  //单位;米
            Math.abs(Number(v1.pitch).toFixed(1)-Number(v2.pitch).toFixed(1))<=between?b5="p":b5="f";   //单位;度
            Math.abs(Number(v1.heading).toFixed(1)-Number(v2.heading).toFixed(1))<=between?b6="p":b6="f";   //单位;度
            if(b1=="p"&&b2=="p"&&b3=="p"&&b4=="p"&&b5=="p"&&b6=="p"){
                info["result"]="p"
            }else{
                info["result"]="f"
                info["message"]="expect:"+ JSON.stringify(v1)+","+"actual:"+JSON.stringify(v2)
            }
        } else {
            info["message"]="expect:"+ JSON.stringify(v1)+","+"actual:"+JSON.stringify(v2)
        }
        return info
    },
    assertFr:function (f1,f2) {
        var info={}
        info["message"]=""
        if (f1!=null&&f2!=null){
            var b1=this.assertEqual(f1.maxLon,f2.maxLon).result
            var b2=this.assertEqual(f1.minLon,f2.minLon).result
            var b3=this.assertEqual(f1.minLat,f2.minLat).result
            var b4=this.assertEqual(f1.maxLat,f2.maxLat).result
            if(b1=="p"&&b2=="p"&&b3=="p"&&b4=="p"){
                info["result"]="p"
            }else{
                info["result"]="f"
                info["message"]="expect:"+ JSON.stringify(f1)+","+"actual:"+JSON.stringify(f2)
            }
        } else {
            info["result"]="f"
            info["message"]="expect:"+ JSON.stringify(f1)+","+"actual:"+JSON.stringify(f2)
        }
        return info
    },
    assertFrNotNull:function (fr) {
        var info={}
        info["message"]=""
        if (fr!=null){
            var b1=this.assertNotNull(fr.maxLon).result
            var b2=this.assertNotNull(fr.minLon).result
            var b3=this.assertNotNull(fr.minLat).result
            var b4=this.assertNotNull(fr.minLon).result
            if(b1=="p"&&b2=="p"&&b3=="p"&&b4=="p"){
                info["result"]="p"
            }else{
                info["result"]="f"
                info["message"]="result:"+ JSON.stringify(fr)
            }
        } else {
            info["result"]="f"
            info["message"]="result is null"
        }
        return info
    },
    assertISNull:function (obj) {
        var info={};
        info["message"]="";
        if(!obj){
            info["result"]="p";
            info["message"]="is  "+obj;
        }else{
            info["result"]="f";
            info["message"]="is  "+obj;
        }
        return info
    },
    assertNotEqual:function (result,expect) {
        var info={};
        info["message"]="";
        if(result!==null&&expect!==null){
            if (expect === result) {
                info["result"]="f";
                info["message"]="expect: "+expect+" , "+"actual:"+result;
            } else {
                info["result"]="p";
                info["message"]="expect: "+expect+" , "+"actual: "+result;
            }
        }
        else {
            info["result"]="f";
            info["message"]="expect: "+expect+" , "+"actual: "+result;
        }
        return info
    },
    assertObjEqual:function (obj1,obj2) {
        var isEqual= cmp(obj1, obj2)
        var info={};
        if(obj1!==null&&obj2!==null){
            info["message"]="";
            if(isEqual){
                info["result"]="p";
                info["message"]="obj1 equal obj2 ";
            }else {
                info["result"]="f";
                info["message"]="obj1 not equal obj2 ";
            }
        }
        else {
            info["result"]="f";
            info["message"]="有对象为空:"+"obj1:"+obj1+","+"obj2:"+obj2;
        }
        return info
    },
    showAtCase:function (casedesc,time) {
        $("#currentCase").html(casedesc)
        setTime(time/1000)
        $("#atResult").css("display","block")
    },
    showMtCase:function (casedesc, pass,time) {
        $("#currentCase").html(casedesc)
        $("#ddPC").css("display","block")
        $("#passCriteria").html(pass)
        setTime(time/1000)
        $("#mtResult").css("display","block")
    },
    hideCase:function () {
        if ($("#mtResult").css("display")=="block"){
            $("#mtResult").css("display","none")
            $("#ddPC").css("display","none")
            $("#mtResult input:radio[name='result']").attr("checked",false);
        }
        if ($("#atResult").css("display")=="block"){
            $("#atResult").css("display","none")
            $("#atResult label").html("");
        }
    },
    showCaseResult:function (result) {
        $("#atResult").css("display","block")
        $("#atResult label").html($("#atResult label").text()+"   "+result)
    },
    getCaseResult:function () {
        if($("#resultP:checked").val()!=null){
            return "p"
        }
        else if ($("#resultF:checked").val()!=null){
            return "f"
        }
        else {
            return "none"
        }
    },
    addCaseToArrGlobal:function (caseid,result,describe,message,functionName) {
        var myCase={}
        myCase["caseid"]=caseid
        myCase["result"]=result
        myCase["describe"]=describe
        myCase["message"]=message
        myCase["functionName"]=functionName
        var size=window.arrglobal.length
        window.arrglobal[size]=myCase
    },
}


