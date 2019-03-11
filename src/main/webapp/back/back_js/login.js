$(function () {
    $('#login').click(function () {
        var user = $('#user').val();
        var pass = $('#password').val();
        if (user.length < 4 || user.length > 10)
            $('#err').html("账号在4-10位之间,请重新输入！<br>");
        else if (pass.length < 4 || pass.length >= 15)
            $('#err').html("密码在4-15位之间,请重新输入！<br>");
        else {
            var req=$.ajax({
                type: "post",
                dataType: "json",
                url: "/admin/login",
                data: {'user': user, 'password': pass},
                beforeSend: function () {
                    $('#err').html("登录中<br>");
                },
                success: function (check) {
                    if (check) {
                      //  var token=req.getResponseHeader('token');
                        //alert(token);
                        window.location.href="/back/index";
                    }
                    else {
                        $('#err').html("用户名或密码错误<br>");

                    }
                }
            });
        }
    });
});