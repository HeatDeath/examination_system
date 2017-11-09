<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>添加教师信息</title>

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
                        <h1 style="text-align: center;">添加教师信息</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" role="form"
                          action="${basePath}/admin/addTeacher" id="editForm" method="post">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">工号</label>
                            <div class="col-sm-10">
                                <input type="number" class="form-control" name="userid" placeholder="请输入学号">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">姓名</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="username" placeholder="请输入姓名">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">性别</label>
                            <div class="col-sm-10">
                                <label class="checkbox-inline">
                                    <input type="radio" name="sex" value="男" checked>男
                                </label>
                                <label class="checkbox-inline">
                                    <input type="radio" name="sex" value="女">女
                                </label>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">出生年份</label>
                            <div class="col-sm-10">
                                <input type="date" value="1996-09-02" name="birthyear"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" name="degree">学历：</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="degree">
                                    <option value="本科">本科</option>
                                    <option value="硕士">硕士</option>
                                    <option value="博士">博士</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" name="title">职称：</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="title">
                                    <option value="普通教师">普通教师</option>
                                    <option value="助教">助教</option>
                                    <option value="讲师">讲师</option>
                                    <option value="副教授">副教授</option>
                                    <option value="教授">教授</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" name="grade">入职时间</label>
                            <div class="col-sm-10">
                                <input type="date" value="2015-09-02" name="grade"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label" name="grade">所属院系</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="collegeid">
                                    <c:forEach items="${collegeList}" var="item">
                                        <option value="${item.collegeid}">${item.collegename}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="button" id="submitAddTeacherForm">提交</button>
                            <button class="btn btn-default" type="reset">重置</button>
                        </div>
                    </form>
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
    $("#nav li:nth-child(3)").addClass("active");

    $("#submitAddTeacherForm").click(function () {
        $('#editForm').ajaxSubmit({
            dateType: 'json',
            success: function (respText) {
                respText = $.parseJSON(respText);
                if (respText.msg == "fail"){
                    alert("教师工号重复！添加失败！");
                }else {
                    alert("成功添加教师信息！");
                }
                window.location.href = "${basePath}" + respText.page_url;

            }
        })
    })
</script>
</html>