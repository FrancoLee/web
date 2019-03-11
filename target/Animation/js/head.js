var reg=new Array(5);
$(function(){
    reg[0]=false;
    reg[1]=false;
    reg[2]=false;
    reg[3]=false;
    reg[4]=false;
    // $('#left >li').click(function(e){
    //     e.preventDefault();
    //     $('#left >li').removeClass('active');
    //     $(this).addClass('active');
    // });
    $('#right >li').click(function(e){
        e.preventDefault();
        $('#right >li').removeClass('active');
        $(this).addClass('active');
    });
    //注册页面
    $('#username').bind('input propertychange',function(){
        var str=$(this).val();
        if(str.length<4||str.length>20){
            $('#user').removeClass();
            $('#user').addClass("glyphicon glyphicon-remove form-control-feedback");
            $('#user_manage').html("用户名必须在4-20个字符之间");
            reg[0]=false;
        }
        else{
            $.ajax({
                type:"get",
                dataType:"text",
                url:"/index/reg_check",
                data:{'username':$('#username').val()},
                beforeSend:function(XMLHttpRequest){
                    $('#user_manage').val("....");
                },
                success:function(msg){
                    //			alert(msg);
                    reg[0]=true;
                    if(msg=="1"){
                        $('#user').removeClass();
                        $('#user').addClass("glyphicon glyphicon-ok form-control-feedback");
                        $('#user_manage').html("用户名可用！");
                        reg[3]=true;
                    }
                    if(msg=="0"){
                        $('#user').removeClass();
                        $('#user').addClass("glyphicon glyphicon-remove form-control-feedback");
                        $('#user_manage').html("用户名已被占用！");
                        reg[3]=false;
                    }
                }
            })

        }

    });
    $('#password1').bind('input propertychange',function(){
        var str=$(this).val();
        if(str.length<6||str.length>24){
            $('#pass1').removeClass();
            $('#pass1').addClass("glyphicon glyphicon-remove form-control-feedback");
            $('#pass1_manage').html("密码必须在6-24字符之间");
            reg[1]=false;
        }
        else{
            $('#pass1').removeClass();
            $('#pass1').addClass("glyphicon glyphicon-ok form-control-feedback");
            $('#pass1_manage').html("");
            reg[1]=true;
        }
    });
    $('#password2').bind('input propertychange',function(){
        var str1=$(this).val();
        var str2=$('#password1').val();
        if(!(str1==str2)){
            $('#pass2').removeClass();
            $('#pass2').addClass("glyphicon glyphicon-remove form-control-feedback");
            $('#pass2_manage').html("密码不匹配");
            reg[2]=false;
        }
        else{
            $('#pass2').removeClass();
            $('#pass2').addClass("glyphicon glyphicon-ok form-control-feedback");
            $('#pass2_manage').html("");
            reg[2]=true;
        }
    });
    $('#register').click(function(){
        if(!reg[0]){
            //alert("用户名必须在8-24个字符之间");
            $('#username').val("");
            $('#user_manage').html("用户名必须在4-20个字符之间");
            $('#check_img').attr('src','/index/Check_img?'+Math.random());
            $('#check_manage').html("");

        }
        else if(!reg[3]){
            $('#username').val("");
            $('#user_manage').html("用户名已被占用！");
            $('#check_img').attr('src','/index/Check_img?'+Math.random());
            $('#check_manage').html("");
        }
        else if(!reg[1]){
            //	alert("密码必须在8-24字符之间");
            $('#password1').val("");
            $('#pass1_manage').html("密码必须在6-24字符之间");
            $('#check_img').attr('src','/index/Check_img?'+Math.random());
            $('#check_manage').html("");
        }
        else if(!reg[2]){
            //	alert("密码不匹配");
            $('#password2').val("");
            $('#pass2_manage').html("密码不匹配");
            $('#check_img').attr('src','/index/Check_img?'+Math.random());
            $('#check_manage').html("");
        }
        else if(!reg[4]){
            $('#check_manage').html("验证码不正确");
        }
        else
            $('#register_form').submit();


    });
    //登录页面
    $('#login').click(function(){
        if($('#login_username').val()==null){
            $('#login_user').html("请输入用户名！");
            return ;
        }
        if($('#login_password').val()==null){
            $('#login_pass').html("请输入密码！");
            return ;
        }
        $.ajax({
            type:"get",
            dataType:"text",
            url:"/index/login",
            data:{'username':$('#login_username').val(),'password':$('#login_password').val()},
            beforeSend:function(XMLHttpRequest){
                $('#login_manage').html("登录中....");
            },
            success:function(msg){
                if(msg=="0"){
                    //	alert("sss");
                    $('#login_manage').html("用户名或密码错误！");
                    $('#login_username').val("");
                    $('#login_password').val("");
                }
                else
                    window.location.reload();
            },
        });
    });
    //register_modal
    $('#register_modal').on('hide.bs.modal',function(){
        //alert("ss12");
        $(':input','#register_form')
            .not(':button')
            .val("");
        $('span','#register_form').html("").removeClass();
    });
    //log_model关闭
    $('#login_modal').on('hide.bs.modal',function(){
        $(':input','#login_form')
            .not(':button')
            .val("");
        $('span','login_form').html("");
    });
    //下拉
    $('#per_info').on('mouseover',function(){
        //	alert("ss1");
        $('#per_info').addClass("open");
    });
    $('#per_info').on('mouseleave',function(){
        $('#per_info').removeClass("open");

    });
    $('#search').on('click',function(){
        aler("ss");
    });
    $('#check').bind('input propertychange',function () {
        var s=$('#check').val();
        if(s.length==4){
            $.ajax({
                type:"get",
                dataType:"text",
                url:"/index/check_num",
                data:{'num':s},
                beforeSend:function(XMLHttpRequest){
                    $('#check_manage').val("检测中....");
                },
                success:function(msg){
                    if(msg=="1"){
                        //	alert("sss");
                        reg[4]=true;
                        $('#check_manage').html("验证码正确!");
                    }
                    else{
                        reg[4]=false;
                        $('#check_manage').html("验证码错误!");

                    }
                }
            });
        }
    })
    $('#check_a,#check_img').on('click',function ()  {
        $('#check_img').attr('src','/index/Check_img?'+Math.random());
        $('#check_manage').html("");
    });

});