<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-md-2">
    <ul class="nav nav-pills nav-stacked" id="nav">
        <li><a href="${basePath}/admin/showCourse">课程管理<span class="badge pull-right">${sessionScope.countMap.courseCount}</span></a></li>
        <li><a href="${basePath}/admin/showStudent">学生管理<span class="badge pull-right">${sessionScope.countMap.studentCount}</span></a></li>
        <li><a href="${basePath}/admin/showTeacher">教师管理<span class="badge pull-right">${sessionScope.countMap.teacherCount}</span></a></li>
        <li><a href="${basePath}/admin/userPasswordReset">账号密码重置<sapn class="glyphicon glyphicon-repeat pull-right" /></a></li>
        <li><a href="${basePath}/admin/passwordReset">修改密码<sapn class="glyphicon glyphicon-pencil pull-right" /></a></li>
        <li><a href="${basePath}/logout">退出系统<sapn class="glyphicon glyphicon-log-out pull-right" /></a></li>
        <li class="disabled"><a href="##">Responsive</a></li>
    </ul>
</div>