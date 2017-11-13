<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>修改用户密码</title>

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
                        <h1 style="text-align: center;">重置密码</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <form name="reset" class="form-horizontal" role="form"
                          action="${basePath}/passwordReset" id="editForm" method="post">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">旧密码</label>
                            <div class="col-sm-10">
                                <input type="password" class="form-control" name="oldPassword" placeholder="请输入旧密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">新密码</label>
                            <div class="col-sm-10">
                                <input type="password" name="password1" class="form-control" placeholder="请输入密码">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">确认密码</label>
                            <div class="col-sm-10">
                                <input type="password" name="password2" class="form-control" placeholder="请再次输入密码"
                                       onchange="check_password()">
                            </div>
                        </div>
                        <div class="form-group" style="text-align: center">
                            <button class="btn btn-default" type="button" id="submitResetPassword">提交</button>
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
    $("#nav li:nth-child(2)").addClass("active");

    function check_password() {
        console.log("check_password!");
        var password_1 = $("input[name=password1]");
        var password_2 = $("input[name=password2]");
        if (password_1.val() != password_2.val()) {
            alert("请保证两次输入密码的一致性！");
            // 清空 password2 input 框的数据
            password_2.val("");
        }
    }

    $("#submitResetPassword").click(function () {
        $('#editForm').ajaxSubmit({
            dateType: 'json',
            success: function (respText) {
                respText = $.parseJSON(respText);
                alert(respText.msg);
                window.location.href = "${basePath}" + respText.page_url;
            }
        })
    })
</script>
</html>