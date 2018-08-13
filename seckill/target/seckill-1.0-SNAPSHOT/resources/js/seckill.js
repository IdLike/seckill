var seckill= {
    //封装秒杀相关的ajax的url
    url: {
        now:function () {
            return "/seckill/time/now"; //系统时间
        },
        exposer:function (seckillId) {
            return "/seckill/"+seckillId+"/exposer"; //暴露秒杀地址
        },
        execution:function (userId,seckillId,md5) {
            return "/seckill/"+userId+"/"+seckillId+"/"+md5+"/execution"; //秒杀开始地址
        }
    },
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
    countdown:function(userId,seckillId,nowTime,startTime,endTime)
    {
        //时间判断
        var seckillBox=$("#seckill-box");

       /* console.log("s"+startTime);
          console.log("n"+nowTime);*/

        if(nowTime>endTime){
            //秒杀结束
            seckillBox.html("秒杀结束！")
        }else  if(startTime>nowTime){
            //计时
            var killTime=new Date(startTime);
            //jquey提供的函数
            seckillBox.countdown(killTime,function (event) {
                var format=event.strftime("秒杀倒计时：%D天 %H时 %M分 %S秒");
                seckillBox.html(format);
            }).on("finish.countdown",function () {
                //暴露秒杀地址，控制实现逻辑，执行秒杀操作
                seckill.handleSeckill(userId,seckillId,seckillBox);
            });
        }else {
            //秒杀开始
            seckill.handleSeckill(userId,seckillId,seckillBox);
        }

    },
    handleSeckill:function(userId,seckillId,seckillBox){
        seckillBox.html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.post(seckill.url.exposer(seckillId),{},function (result) {
            if(result && result["success"]){
                var exposer=result["data"];
                if(exposer["exposed"]){
                    //开启秒杀
                    //获取秒杀地址
                    var killUrl=seckill.url.execution(userId,seckillId,exposer["md5"]);
                    console.log("killUrl:"+killUrl);
                    $("#killBtn").one("click",function () {
                        //限制用户只能点击一次
                        $(this).addClass("disabled");
                       //判断用户是否登录
                        console.log("userID:"+userId);
                        if(userId==0){
                            window.location.href="/login/DoLogin";
                        }else{
                            //弹出验证框
                            var userModel = $("#userModel");
                            userModel.modal({
                                show: true,
                                backdrop: "static",//禁止位置关闭
                                keyboard: false
                            });
                            $("#submitButton").click(function () {
                                  var answer=$("#answer").val();
                                if (seckill.validateUser(answer)) {
                                    $("#userModel").modal('hide'); //关闭模态框
                                    //发送秒杀请求
                                    $.post(killUrl,{},function (result) {
                                        if(result){
                                            var killResult=result["data"];
                                            var state=result["state"];
                                            var stateInfo=killResult["stateInfo"];
                                            console.log("stateInfo:"+stateInfo);
                                            //显示秒杀结果
                                            seckillBox.html('<span class="lebel lebel-success">'+stateInfo+'</span>');

                                        }
                                    });

                                } else {
                                    $("#message").hide().html("<label class='label label-danger'>验证失败</label>").show(300);
                                }
                            });
                        }
                    });

                }else{
                    //未开启秒杀
                    var now=exposer["now"];
                    var start=exposer["start"];
                    var  end=exposer["end"];
                    seckill.countdown(seckillId,now,start,end);
                }
            }

        });

    },
    //详情页秒杀逻辑
    detail: {
        init: function (params) {
            //取值
            var userId=params["userId"];
            var startTime = params["startTime"];
            var endTime = params["endTime"];
            var seckillId = params["seckillId"];

            //计时交互
            $.get(seckill.url.now(),{},function (result) {
                if(result && result["success"]){
                    var nowTime=result["data"];
                    //时间判断
                    seckill.countdown(userId,seckillId,nowTime,startTime,endTime);
                }else{
                    console.log("result:"+result);
                }
            });
        }
    }
}

