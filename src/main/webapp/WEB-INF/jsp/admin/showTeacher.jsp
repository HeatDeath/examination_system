<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>教师信息显示</title>

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
					    	<h1 class="col-md-5">教师名单管理</h1>
							<form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;" action="${pageContext.request.contextPath}/admin/searchTeacher" id="form1" method="post">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="请输入姓名" name="findByName">
									<span class="input-group-addon btn" onclick="$('#form1').submit()" id="sub">搜索</span>
								</div>
							</form>
							<button class="btn btn-default col-md-2" style="margin-top: 20px" onClick="window.location.href='/admin/addTeacher'">
								添加教师信息
								<sapn class="glyphicon glyphicon-plus"/>
							</button>

						</div>
				    </div>
				    <table class="table table-bordered">
					        <thead>
					            <tr>
									<th>教师编号</th>
									<th>姓名</th>
									<th>性别</th>
									<th>出生年份</th>
									<th>学历</th>
									<th>职称</th>
									<th>入职年份</th>
									<th>学院</th>
									<th>操作</th>
					            </tr>
					        </thead>
					        <tbody>
							<c:forEach  items="${pageInfo.list}" var="item">
								<tr>
									<td>${item.userid}</td>
									<td>${item.username}</td>
									<td>${item.sex}</td>
									<td><fmt:formatDate value="${item.birthyear}" dateStyle="medium" /></td>
									<td>${item.degree}</td>
									<td>${item.title}</td>
									<td><fmt:formatDate value="${item.grade}" dateStyle="medium" /></td>
									<td>${item.collegeName}</td>
									<td>
										<button class="btn btn-default btn-xs btn-info" onClick="location.href='/admin/editTeacher?id=${item.userid}'">修改</button>
										<button class="btn btn-default btn-xs btn-danger btn-primary" onclick="confirmPrompt('${item.username}', '${item.userid}')">删除</button>
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
											<a href="${pageContext.request.contextPath}/admin/showTeacher?page=${pageInfo.prePage}&username=${queryParam.username}">&laquo;上一页</a>
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
												<a href="${pageContext.request.contextPath}/admin/showTeacher?page=${nav}&username=${queryParam.username}">${nav}</a>
											</li>
										</c:if>
									</c:forEach>

									<%-- 下一页 --%>
									<c:if test="${pageInfo.hasNextPage}">
										<li><a href="${pageContext.request.contextPath}/admin/showTeacher?page=${pageInfo.nextPage}&username=${queryParam.username}">下一页&raquo;</a></li>
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
		$("#nav li:nth-child(3)").addClass("active");

        function confirmPrompt(userName, userId) {
            var confirm_msg = "确定要删除 "+userName + "(工号:" + userId +")" + "吗？！";
            var userChoose = confirm(confirm_msg);
            console.log("程序执行到 confirmPrompt");
            if (userChoose == true){
                var get_url = "${pageContext.request.contextPath}/admin/removeTeacher?id=" + userId;
                $.get(get_url, function (respText) {
                    respText = $.parseJSON(respText);
                    if (respText.msg == "fail"){
                        alert("教师信息删除失败！请再次尝试删除教师！");
                    }else {
                        alert("成功删除教师信息！");
                        window.location.href = "${pageContext.request.contextPath}/admin/showTeacher";
                    }
                })
            }
        }

	</script>
</html>