<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<title>添加学生信息</title>

	<jsp:include page="../common.jsp" />

</head>
<body>
	<!-- 顶栏 -->
	<jsp:include page="top.jsp" />
	<!-- 中间主体 -->
		<div class="container" id="content">
		<div class="row">
			<jsp:include page="menu.jsp" />
			<div class="col-md-10">
				<div class="panel panel-default">
				    <div class="panel-heading">
						<div class="row">
					    	<h1 style="text-align: center;">添加学生信息</h1>
						</div>
				    </div>
				    <div class="panel-body">
						<form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/admin/addStudent" id="editForm" method="post">
							  <div class="form-group">
							    <label class="col-sm-2 control-label">学号</label>
							    <div class="col-sm-10">
							      <input type="number" class="form-control" name="userid" placeholder="请输入学号"
								  <c:if test='${student!=null}'>
										 value="${student.userid}"
								  </c:if>>
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">姓名</label>
							    <div class="col-sm-10">
							      <input type="text" class="form-control" name="username" placeholder="请输入姓名"
								  <c:if test='${student!=null}'>
										 value="${student.username}"
								  </c:if>>
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
								    <input type="date" value="1979-01-01" name="birthyear"/>
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label" name="grade">入学时间</label>
							    <div class="col-sm-10">
								    <input type="date" value="2017-09-02" name="grade"/>
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
								<button class="btn btn-default" type="button" id="submitAddStudentForm">提交</button>
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
		$("#nav li:nth-child(2)").addClass("active")

        $("#submitAddStudentForm").click(function () {
            $('#editForm').ajaxSubmit({
                dateType: 'json',
                success: function (respText) {
                    respText = $.parseJSON(respText);
                    if (respText.msg == "fail"){
                        alert("学生信息添加失败！请再次尝试添加信息！");
                    }else {
                        alert("成功添加学生信息！");
                    }
                    window.location.href = "${pageContext.request.contextPath}" + respText.page_url;
                }
            })
        })
	</script>
</html>