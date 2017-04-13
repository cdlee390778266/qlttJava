/* 
* @Author: lee
* @Date:   2017-04-07 14:10:42
* @Last Modified time: 2017-04-13 10:23:24
*/

;(function ($,window,document) {

      $.fn.dialog = function(opts){

       var $self = this;
       var options = {
            dialogId : 'dialog',
            headHtml : '<div class="dialog-head dialog-head-bg">推荐到我的荐股</div>',
            bodyHtml : '<div class="dialog-body"><input type="text" placeholder="推荐理由" /></div>',
            footHtml : '<div class="dialog-foot"><span class="dialog-btn-close">取消</span><span class="dialog-btn-confirm">确定</span></div>',
            animatedIn : 'fadeIn',
            animatedOut : 'fadeOut',
            maskClass : 'dialog-mask',
            closeClass : '.dialog-btn-close',
            confirmClass : '.dialog-btn-confirm',
            closeCallBack : '',
            confirmCallBack : ''
       }

       var options = $.extend({},options,opts);

       var createHtml =  function(){
           var html = '';
           html += '<div class="dialog animated" id="' + options.dialogId + '">'
                +       '<div class="' + options.maskClass + '"></div>'
                +       '<div class="dialog-box">'
                +           options.headHtml
                +           options.bodyHtml
                +           options.footHtml
                +       '</div>'
                +   '</div>'
           $('body').append(html);
       }

       var preventDefault = function(e){
            e.preventDefault();
            e.stopPropagation();
       }

       var showDialog = function(callBack){
           $('#'+options.dialogId).removeClass(options.animatedOut).css('display','block').addClass(options.animatedIn);
           if(typeof callBack == 'function')
                callBack();
       }

       var hideDialog = function(callBack){
           $('#'+options.dialogId).removeClass(options.animatedIn).addClass(options.animatedOut).css('display','none');
           if(typeof callBack == 'function')
                callBack();
       }

       var init = function(){
            createHtml();

            $('body').delegate($self, 'tap', function(){
                console.log($self)
                showDialog();
            });
         
            $('#'+options.dialogId).find(options.closeClass).tap(function(e){
                hideDialog(options.closeCallBack);
                preventDefault(e);
            });

            $('#'+options.dialogId).find(options.confirmClass).tap(function(e){
                hideDialog(options.confirmCallBack);
                preventDefault(e);
            });


            $('#'+options.dialogId).find('.'+options.maskClass).tap(function(e){
                hideDialog();
                preventDefault(e);
            });

       }

       init();

   }
       
})(Zepto,window,document,undefined)