<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>修改普通账户密码</title>

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
                        <h1 style="text-align: center;">重置非管理员用户密码</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" name="reset" role="form"
                          action="${pageContext.request.contextPath}/admin/userPasswordReset" id="editForm"
                          method="post">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">账号(非管理员账号)</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" name="username" placeholder="请输入用户名">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" placeholder="请输入密码" name="password">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">确认密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" name="password2" placeholder="请再次输入密码"
                                onchange="check_password()">
                            </div>
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="button" id="submitResetPasswordForm">提交</button>
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
<script>
    $("#nav li:nth-child(4)").addClass("active");

    function check_password() {
        console.log("check_password!");
        var password_1 = $("input[name=password]");
        var password_2 = $("input[name=password2]");
        if (password_1.val() != password_2.val()){
            alert("请保证两次输入密码的一致性！");
            // 清空 password2 input 框的数据
            password_2.val("");
        }
    }

    $("#submitResetPasswordForm").click(function () {
        $('#editForm').ajaxSubmit({
            dateType: 'json',
            success: function (respText) {
                respText = $.parseJSON(respText);
                alert(respText.msg);
                window.location.href = "${pageContext.request.contextPath}" + respText.page_url;
            }
        })
    })

</script>
</html>