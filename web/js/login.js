$(function(){

    $('#switch_qlogin').click(function(){
        $('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
        $('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
        $('#switch_bottom').animate({left:'0px',width:'70px'});
        $('#teach_login').css('display','none');
        $('#stu_login').css('display','block');

    });
    $('#switch_login').click(function(){

        $('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
        $('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
        $('#switch_bottom').animate({left:'154px',width:'70px'});

        $('#teach_login').css('display','block');
        $('#stu_login').css('display','none');
    });
});

