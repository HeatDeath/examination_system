<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>我的课程</title>
    <jsp:include page="../common.jsp"/>
</head>
<body>
<!-- 顶栏 -->
<jsp:include page="top.jsp"/>
<!-- 中间主体 -->
<div class="container" id="content">
    <div class="row">
        <jsp:include page="menu.jsp"/>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 class="col-md-5">课程列表</h1>
                    </div>
                </div>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>课程号</th>
                        <th>课程名称</th>
                        <th>授课老师编号</th>
                        <th>上课时间</th>
                        <th>上课地点</th>
                        <th>周数</th>
                        <th>课程类型</th>
                        <th>学分</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageInfo.list}" var="item">
                        <tr>
                            <td>${item.courseid}</td>
                            <td>${item.coursename}</td>
                            <td>${item.teacherid}</td>
                            <td>${item.coursetime}</td>
                            <td>${item.classroom}</td>
                            <td>${item.courseweek}</td>
                            <td>${item.coursetype}</td>
                            <td>${item.score}</td>
                            <td>
                                <button class="btn btn-default btn-xs btn-info"
                                        onclick="selectedCourse('${item.coursename}','${item.courseid}')">
                                    选课
                                </button>
                                <!--弹出框-->
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="panel-footer">
                    <c:if test="${page!=null}">
                        <nav style="text-align: center">
                            <ul class="pagination">
                                    <%-- 上一页 --%>
                                <c:if test="${pageInfo.hasPreviousPage}">
                                    <li>
                                        <a href="${basePath}/student/showCourse?page=${pageInfo.prePage}">&laquo;上一页</a>
                                    </li>
                                </c:if>

                                    <%-- 中间的索引 --%>
                                <c:forEach items="${pageInfo.navigatepageNums}" var="nav">
                                    <%-- 当前页 --%>
                                    <c:if test="${nav == pageInfo.pageNum}">
                                        <li class="active"><a href="">${nav}</a></li>
                                    </c:if>
                                    <c:if test="${nav != pageInfo.pageNum}">
                                        <li>
                                            <a href="${basePath}/student/showCourse?page=${nav}">${nav}</a>
                                        </li>
                                    </c:if>
                                </c:forEach>

                                    <%-- 下一页 --%>
                                <c:if test="${pageInfo.hasNextPage}">
                                    <li>
                                        <a href="${basePath}/student/showCourse?page=${pageInfo.nextPage}">下一页&raquo;</a>
                                    </li>
                                </c:if>

                            </ul>
                        </nav>
                    </c:if>

                </div>
            </div>

        </div>
    </div>
</div>
<div class="container" id="footer">
    <div class="row">
        <div class="col-md-12"></div>
    </div>
</div>
</body>
<script type="text/javascript">
    <%--设置菜单中--%>
    $("#nav li:nth-child(1)").addClass("active");

    function selectedCourse(courseName, courseId) {
        var confirm_msg = "确定要选择 " + courseName + "(课程号:" + courseId + ")" + "吗？！";
        var userChoose = confirm(confirm_msg);

        if (userChoose == true) {
            var get_url = "${basePath}/student/doSelectedCourse?id=" + courseId;
            $.get(get_url, function (respText) {
                respText = $.parseJSON(respText);
                if (respText.msg == "fail") {
                    alert("选课失败！不要重复选择课程！");
                } else {
                    alert("成功选择课程！");
                }
                window.location.href = "${basePath}" + respText.page_url;
            })
        }

    }
</script>
</html>