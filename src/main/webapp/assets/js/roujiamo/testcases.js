/**
 * Created by 曹文豪 on 2017/7/7.
 */
var Roujiamo={start:function (key) {
    $.ajax({
        type: "get",
        url:'/autoCaseRepertory/executeTestPlan.do?key='+key,
        dataType:"json",
        success: function(res){
            if(res.code==1){
                var result=res.result
                $('body').append('<script type="text/javascript">'+result+'</script>')
                $('body').append('<script type="text/javascript" src="/assets/js/roujiamo/ResultTotestlink.js"></script>')
            }else {
                alert(res.message);
            }
        },
        error:function () {
            $('#currentStautus').html('用例加载失败!')
        }
    });
    $('#currentStautus').html('用例加载中...')
}}

