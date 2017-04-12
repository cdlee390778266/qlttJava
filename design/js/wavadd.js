var currentRoute = false;
$(document).on('ready', function () {

    // Init Waves
    Waves.init();
    Waves.attach('.drag-ripple', 'waves-block', true);
    Waves.attach('#bg-pattern', null, true);
    init();
    $(window).on('hashchange', routing);
    /**
     * Example source code click
     */
    $('#example .top-button').on('click', function () {
        var type = $(this).data('code');
        $('#source-code .box .code').addClass('hide');
        $('#source-code .box #code-' + type).removeClass('hide');
        $('#source-code').removeClass('hide');
        setTimeout(function () {
            $('#source-code').addClass('show');
        }, 50);
    });
    $('#source-code .top-button').on('click', function () {

        $('#source-code').removeClass('show');

        setTimeout(function () {
            $('#source-code .box .code').addClass('hide');
            $('#source-code').addClass('hide');
        }, 500);
    });
});