"use strict";
jQuery.fn.extend({
    autoHeight: function(){
        return this.each(function(){
            var $this = jQuery(this);
            if( !$this.attr('_initAdjustHeight') ){
                $this.attr('_initAdjustHeight', $this.outerHeight());
            }
            _adjustH(this).on('input', function(){
                _adjustH(this);
            });
        });
        /**
         * 重置高度
         * @param {Object} elem
         */
        function _adjustH(elem){
            var $obj = jQuery(elem);
            return $obj.css({height: $obj.attr('_initAdjustHeight'), 'overflow-y': 'hidden'})
                .height( elem.scrollHeight );
        }
    }
});
$(document).ready(function(){
    $('#caseContent').autoHeight()
    dp.SyntaxHighlighter.BloggerMode();
    dp.SyntaxHighlighter.ClipboardSwf = '/assets/js/clipboard.swf';
    dp.SyntaxHighlighter.HighlightAll('code');
    $('#caseContent').bind('input propertychange',function () {
        $('.dp-highlighter').remove()
        $('#code').html($('#caseContent').val())
        dp.SyntaxHighlighter.HighlightAll('code');
    })
    $.extend($.validator.defaults,{invalidHandler:function(c,a){var d=a.numberOfInvalids();if(d){var b=d==1?"你有一个必填项未填写内容！":"你有"+d+" 个必填项未填写内容！";noty({text:b,type:"error",timeout:2000})}}});$("#validate-1").validate();$("#validate-2").validate();$("#validate-3").validate();$("#validate-4").validate()});