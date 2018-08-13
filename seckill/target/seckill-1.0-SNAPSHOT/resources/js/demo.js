var seckill= {
    //封装秒杀相关的ajax的url
    url: {},
    validateUser: function (answer) {
        //验证结果是否正确
        //问题表达式
        var str=$("#question").text();
        var question= str.substring(0,str.indexOf("="));
        console.log("用户输入:"+answer);
        console.log("表达式结果："+eval(question));
        if(eval(question)==answer){
            //结果正确
            return true;
        }else {
            return false;
        }

    },
    //详情页秒杀逻辑
    detail: {
        init: function (params) {
            //判断是否登陆，计时

            //取出输入框的值
            /*var answer=$("#answer").val();*/
            var startTime = params["startTime"];
            var endTime = params["endTime"];
            var seckillId = params["seckillId"];

            //验证
            if (!seckill.validateUser(answer)) {
                var userModel = $("#userModel");
                userModel.modal({
                    show: true,
                    backdrop: "static",//禁止位置关闭
                    keyboard: false
                });

                $("#submitButton").click(function () {
                    /*    var answer=$("#answer").val();*/
                    if (seckill.validateUser(answer)) {
                        // $.cookie("usrId", inputText, {expirse: 7, path: "/seckill"})
                        //刷新页面
                        window.location.reload();

                    } else {
                        $("#message").hide().html("<label class='label label-danger'>验证失败</label>").show(300);
                    }

                });

            }
        }
    }
}

