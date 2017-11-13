var ownerWaitTime_end=waitTimeGlobal
setTimeout(function(){
    showCaseTestResult()
    $('#currentStautus').html('用例执行完毕')
    if(window.isSendToTestlink){
        var testlinkJson=getCaseDetailResult(arrglobal)
        $('#currentStautus').html('开始发送至testlink......')
        $.ajax({
            type: "post",
            url:'http://'+window.address+'/api/v1/testlinkservice/fillAllResult',
            data: testlinkJson,
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function(data){
                showTestlinkDetailResult(data)
                $('#currentStautus').html('发送成功，测试完毕！')
            },
            error:function () {
                $('#currentStautus').html('发送失败，请确认服务器连接正常！')
            }
        });
    }
},ownerWaitTime_end);





