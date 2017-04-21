/* 
* @Author: lee
* @Date:   2017-04-07 14:10:42
* @Last Modified time: 2017-04-19 14:14:00
*/

$(function(){

    var showDialog = function($dialogEle,callBack){
        $dialogEle.removeClass('fadeOut').css('display','block').addClass('fadeIn');
        if(typeof callBack == 'function')
            callBack();
    }

    var hideDialog = function($dialogEle,callBack){
        $dialogEle.removeClass('fadeIn').addClass('fadeOut').css('display','none');
        if(typeof callBack == 'function')
            callBack();
    }

    //弹窗
    $('body').delegate('.addPoolDialog', 'tap', function(){
        showDialog($('#addPoolDialog'));
    });
         
    $('#addPoolDialog .dialog-btn-close,#addPoolDialog .dialog-mask').tap(function(){
        hideDialog($('#addPoolDialog'));
    })

    $('#addPoolDialog .dialog-btn-confirm').tap(function(){
        hideDialog($('#addPoolDialog'),function(){
            console.log('提交');
        });
    })

    $('body').delegate('#addPool', 'change', function(event) {
        $('.select-mask').text($(this).val());
    });

    //查询数据
    $('.retrieval-form').submit(function(){
        var html = '';
        loadingShow($('.qltt-toast'));
        $.ajax({
            url: '../data/result.json',
            type: 'POST',
            success :  function(resData){
                for(var i in resData){
                    html += '<div class="retrieval-item">'
                         +      '<div class="retrievalItemL"><span>' + resData[i].name + '</span>' + resData[i].nums + '</div>'
                         +      '<div class="retrievalItemR"><span class="addPoolDialog">加入选股池</span></div>'
                         +  '</div>'
                }

                $('.retrieval').html(html);
                loadingHide($('.qltt-toast'));
            },
            error: function(xhr, type){
                alert('获取数据失败!');
                loadingHide($('.qltt-toast'));
            }
           
        })
        
        return false;
    })

})