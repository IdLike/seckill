<%@include file="common/tag.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>秒杀列表页</title>
    <%@include file="common/head.jsp"%>
</head>
<body>

    <div class="container">

        <div class="panel panel-defalut">
            <div class="panel-heading text-center">
                <h2>秒杀列表</h2>
            </div>
            <div class="panel-body">
                <table class="table table-hover">
                   <thead>
                     <tr>
                         <td>名称</td>
                         <td>库存</td>
                         <td>开始时间</td>
                         <td>结束时间</td>
                         <td>创建时间</td>
                         <td>详情页</td>
                     </tr>
                   </thead>
                    <tbody>
                    <c:forEach var="seckill" items="${seckills}">
                        <tr>
                            <td>${seckill.name}</td>
                            <td>${seckill.number}</td>
                            <td>
                                <fmt:formatDate type="both" value="${seckill.startTime}" />
                            </td>
                            <td>
                                <fmt:formatDate type="both" value="${seckill.endTime}" />
                               <%-- <fmt:formatDate value="${seckill.endTime}" pattern="yyyy-MM-dd hh:mm:ss "/>--%>
                            </td>
                            <td>
                                <fmt:formatDate type="both" value="${seckill.createTime}" />
                                 <%-- <fmt:formatDate value="${seckill.createTime}" pattern="yyyy-MM-dd hh:mm:ss "/>&ndash;%&gt;--%>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${user!=null&&user.userId!=0}">
                                        <a class="btn btn-info" href="/seckill/${user.userId}/${seckill.seckillId}/detail" target="_blank">详情</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a class="btn btn-info" href="/seckill/0/${seckill.seckillId}/detail" target="_blank">详情</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                        </tr>
                        
                    </c:forEach>
                    </tbody>
                </table>

            </div>

        </div>

    </div>


</body>
</html>