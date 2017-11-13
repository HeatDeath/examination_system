<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>学生成绩录入</title>
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
                        <h1 style="text-align: center;">学生打分</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form name="reset" class="form-horizontal" role="form" action="${basePath}/teacher/mark"
                          id="editForm" method="post">
                        <div class="form-group">
                            <div class="col-sm-10">
                                <input readonly="readonly" type="hidden" class="form-control" name="courseid"
                                       value="${selectedCourse.courseid}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">学号</label>
                            <div class="col-sm-10">
                                <input readonly="readonly" type="text" class="form-control" name="studentid"
                                       value="${selectedCourse.studentid}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">姓名</label>
                            <div class="col-sm-10">
                                <input readonly="readonly" type="text" name="name" class="form-control"
                                       value="${selectedCourse.studentCustom.username}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">成绩</label>
                            <div class="col-sm-10">
                                <input type="number" name="mark" class="form-control" placeholder="请输入成绩"
                                       onchange="check()">
                            </div>
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="button" id="submitForm">提交</button>
                            <button class="btn btn-default">重置</button>
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
<script>
    $("#nav li:nth-child(1)").addClass("active");

    function check() {
        var markVal = $("input[name=mark]").val();
        if (markVal == "" || markVal == null) {
            alert("请输入学生成绩！");
        }
    }


    $("#submitForm").click(function () {
        $('#editForm').ajaxSubmit({
            dateType: 'json',
            success: function (respText) {
                respText = $.parseJSON(respText);
                if (respText.msg == "fail"){
                    alert("成绩录入失败！请再次尝试录入成绩！");
                }else {
                    alert("成功录入学生成绩！");
                }
                window.location.href = "${basePath}" + respText.page_url;
            }
        })
    })

</script>
</html>