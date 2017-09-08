"use strict";
var arr={planName:'',version:'',planVersion:'',type:'',node:'0',typeName:'',nodeName:'',mark:false};

$(document).ready(function(){
    var c=$("#sample_form");
    var d=$("#form_wizard");
    var a=$(".alert-danger",c);
    var f=$(".alert-success",c);
    c.validate({
        doNotHideMessage:true,
        focusInvalid:false,
        invalidHandler:function(h,g){f.hide();a.show()},
        submitHandler:function(g){f.show();a.hide()}});
    var e=function(){
        $("#tab3 .form-control-static",c).each(function(){
            var g=$('[name="'+$(this).attr("data-display")+'"]',c);
            if(g.is(":text")||g.is("textarea")){
                $(this).html(g.val())
            }else{
                if(g.is("select")){
                    $(this).html(g.find("option:selected").text())
                }else{
                    if(g.is(":radio")&&g.is(":checked")){
                        $(this).html(g.attr("data-title"))
                    }
                }
            }
        })};
    var b=function(k,g,h){
        var l=g.find("li").length;
        var m=h+1;
        $(".step-title",d).text("Step "+(h+1)+" of "+l);
        $("li",d).removeClass("done");
        var n=g.find("li");
        for(var j=0;j<h;j++){
            $(n[j]).addClass("done")
        }
        if(m==1){
            d.find(".button-previous").hide()
        }else{
            d.find(".button-previous").show()
        }
        if(m>=l){
            d.find(".button-next").hide();
            d.find(".button-submit").show();
            e()
        }else{
            d.find(".button-next").show();
            d.find(".button-submit").hide()
        }
    };
    d.bootstrapWizard({
        nextSelector:".button-next",
        previousSelector:".button-previous",
        onTabClick:function(i,g,h,j){
            f.hide();a.hide();
            if(c.valid()==false){
                return false
            }
            if(arr.planName==''){
                $('#warning1').click()
                return false
            }
            if($('#box2View option').size()==0&&$('#secondLi').attr('class')=='active'){
                $('#warning3').click()
                return false
            }else {
                arr.type=''
                arr.typeName=''
                $('#box2View option').each(function () {
                    if(arr.type==''){
                        arr.type=$(this).val()
                        arr.typeName=$(this).html()
                    }else {
                        arr.type=arr.type+','+$(this).val()
                        arr.typeName=arr.typeName+'<br>'+$(this).html()
                    }
                })
                $('#planName').html(arr.planName)
                $('#version').html(arr.version)
                $('#node').html(arr.nodeName)
                $('#type').html(arr.typeName)
            }
            b(i,g,j)
        },
        onNext:function(i,g,h){
            f.hide();
            a.hide();
            if(c.valid()==false){
                return false
            }
            if(arr.planName==''){
                $('#warning1').click()
                return false
            }
            if($('#firstLi').attr('class')=='active'){
                getModuleTree({planName:arr.planName,planVersion:arr.planVersion},function (res) {
                    if(res.code==1){
                        var nodeTree=[];
                        nodeTree.push(res.result)
                        var setting = {};
                        $.fn.zTree.init($("#moduleNodeTree"), setting, nodeTree);
                    }else {
                        $('#moduleNodeTree').append('找不到数据')
                    }
                })
                getSupportType({version:arr.version},function (res) {
                    if(res.code==1){
                        console.log(res)
                        var supportTypes=res.result
                        $.each(supportTypes,function (index, type) {
                            var message=''
                            if(type=='severity'){
                                message='问题严重级别分布情况'
                            }
                            else if(type=='severityCompare'){
                                message='问题严重级别情况版本对比'
                            }
                            else if(type=='caseBugCount'){
                                message='子模块用例问题分布情况'
                            }
                            else if(type=='caseBugRatioCompare'){
                                message='子模块用例问题分布版本对比'
                            }
                            $('#box1View').append('<option value="'+type+'">'+message+'</option>')
                        })
                    }else {
                        $('#box1View').append('<option value="'+type+'" disabled="disabled">找不到数据</option>')
                    }
                })
            }
            if($('#box2View option').size()==0&&$('#secondLi').attr('class')=='active'){
                $('#warning3').click()
                return false
            }else {
                arr.type=''
                arr.typeName=''
                $('#box2View option').each(function () {
                    if(arr.type==''){
                        arr.type=$(this).val()
                        arr.typeName=$(this).html()
                    }else {
                        arr.type=arr.type+','+$(this).val()
                        arr.typeName=arr.typeName+'<br>'+$(this).html()
                    }
                })
                $('#planName').html(arr.planName)
                $('#version').html(arr.version)
                $('#node').html(arr.nodeName)
                $('#type').html(arr.typeName)
            }
            b(i,g,h)
        },
        onPrevious:function(i,g,h){f.hide();a.hide();b(i,g,h)},
        onTabShow:function(j,g,i){
            var k=g.find("li").length;
            var l=i+1;
            var h=(l/k)*100;
            d.find(".progress-bar").css({width:h+"%"})
        }
    });
    d.find(".button-previous").hide();
    $("#form_wizard .button-submit").click(function(){
        alert("You just finished the wizard.")
    }).hide()
});

function selectPlanNameAndVersion(obj) {
    var span=$(obj).parent()
    var plan=span.parent().parent().parent().children('td')
    var planName=plan.eq(1).html()
    var version=plan.eq(2).html()
    var planVersion=plan.eq(3).html()
    if(span.attr('class')=='checked'){
        if(arr.planName==''){
            arr.planName=planName
            arr.version=version
            arr.planVersion=planVersion
        }else if(arr.planName==planName){
            if(arr.version==''){
                arr.version=version
                arr.planVersion=planVersion
            }else {
                arr.version=arr.version+'<br>'+version
                arr.planVersion=arr.planVersion+','+planVersion
            }
        }else {
            $('#warning2').click()
            arr.mark=true
            $(obj).click()
        }
    }else {
        if(arr.mark){
            arr.mark=false
        }
        else {
            arr.version=arr.version.replace(version,'')
            arr.planVersion=arr.planVersion.replace(planVersion,'')
            if(arr.planVersion.indexOf(',')==0){
                arr.planVersion=arr.planVersion.substring(1,arr.planVersion.length)
            }else if(arr.planVersion.endsWith(',')){
                arr.planVersion=arr.planVersion.substring(0,arr.planVersion.length-1)
            }
            if(arr.version.indexOf('<br>')==0){
                arr.version=arr.version.substring(4,arr.version.length)
            }else if(arr.version.endsWith('<br>')){
                arr.version=arr.version.substring(0,arr.version.length-4)
            }
            if(arr.version==''){
                arr.planName=''
            }
        }
    }
}