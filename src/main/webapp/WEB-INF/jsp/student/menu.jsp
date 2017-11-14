<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common.jsp" />
<div class="col-md-2">
    <ul class="nav nav-pills nav-stacked" id="nav">
        <li><a href="${basePath}/student/showCourse">所有课程<span class="badge pull-right">${sessionScope.countMap.courseCount}</span></a></li>
        <li><a href="${basePath}/student/showSelectedCourse">已选课程<span class="badge pull-right">${sessionScope.countMap.studentSelectedCourseCount}</span></a></li>
        <li><a href="${basePath}/student/finishedCourse">已修课程<span class="badge pull-right">${sessionScope.countMap.studentFinishCourseCount}</span></a></li>
        <li><a href="${basePath}/student/passwordReset">修改密码<sapn class="glyphicon glyphicon-pencil pull-right" /></a></li>
        <li><a href="${basePath}/logout">退出系统<sapn class="glyphicon glyphicon-log-out pull-right" /></a></li>
        <li class="disabled"><a href="#">Responsive</a></li>
    </ul>
</div>