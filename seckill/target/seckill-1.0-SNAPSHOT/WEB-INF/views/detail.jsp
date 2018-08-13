<%--
  Created by IntelliJ IDEA.
  User: fuck_sky
  Date: 2018/8/9
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>秒杀详情页</title>
    <%@include file="common/head.jsp"%>
</head>
<body>
    <div class="container">
        <div class="panel panel-default text-center">
            <div class="panel-heading">${seckill.name}</div>
            <div class="panel-body">
                <h2 class="text-danger">
                    <!--显示时间图标-->
                    <span class="glyphicon glyphicon-time"></span>
                    <!--展示倒计时-->
                    <span class="glyphicon" id="seckill-box"></span>
                </h2>
            </div>
        </div>
    </div>
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="userModel" >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title">
                       <%-- <span class="glyphicon glyphicon-user"></span>--%>
                        请输入验正信息
                    </h3>
                </div>
                <div class="modal-body ">
                    <div class="row " style="margin:0px auto;">
                        <div class="col-xs-4 col-xs-offset-2">
                            <input type="text" name="answer" id="answer" class="form-control">
                        </div>
                        <span style="visibility:hidden;">&nbsp;获取中</span>
                        <span style="background: burlywood;font-size:25px;font-style:oblique;"  id="question">5+2=?</span>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" id="submitButton" class="btn btn-primary">
                        提交
                    </button>
                    <span id="message" class="glyphicon"></span>
                </div>
            </div>
        </div>
    </div>

</body>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.js"></script>
<script src="https://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.js"></script>
<script src="/resources/js/seckill.js" type="text/javascript"></script>
<script type="text/javascript">
    function getQuestion(){
        var operators=new Array("+","-","*");
        var str="";
        for(var i=1;i<6;i++){

            if(i%2==0) {
                //运算符
                var index=parseInt(Math.random()*3); //0-2之间的数
                str+=operators[index];

            }else{
                 str+=parseInt(Math.random()*10+1);

            }

        }
        str+="=?";
        return str;


    }

   $(function(){
       //给span赋值

       $("#question").text(getQuestion());
       seckill.detail.init({
           userId:${userId},
           seckillId:${seckill.seckillId},
           startTime:${seckill.startTime.time},
           endTime:${seckill.endTime.time}
       });

   });
</script>
</html>
