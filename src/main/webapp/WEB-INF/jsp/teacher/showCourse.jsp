<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>课程信息显示</title>

	<jsp:include page="../common.jsp"/>

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
					    	<h1 class="col-md-5">我教授的课程</h1>
							<%--<form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;" action="${basePath}/teacher/selectCourse" id="form1" method="post">--%>
								<%--<div class="input-group">--%>
									<%--<input type="text" class="form-control" placeholder="请输入课程名" name="findByName">--%>
									<%--<span class="input-group-addon btn" onclick="document.getElementById('form1').submit" id="sub">搜索</span>--%>
								<%--</div>--%>
							<%--</form>--%>
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
							<c:forEach  items="${pageInfo.list}" var="item">
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
										<button class="btn btn-default btn-xs btn-info" onClick="window.location.href='/teacher/showCourseGrade?courseid=${item.courseid}'">成绩</button>
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
											<a href="${basePath}/teacher/showCourse?page=${pageInfo.prePage}">&laquo;上一页</a>
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
												<a href="${basePath}/teacher/showCourse?page=${nav}">${nav}</a>
											</li>
										</c:if>
									</c:forEach>

									<%-- 下一页 --%>
									<c:if test="${pageInfo.hasNextPage}">
										<li><a href="${basePath}/teacher/showCourse?page=${pageInfo.nextPage}">下一页&raquo;</a></li>
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
		$("#nav li:nth-child(1)").addClass("active");
	</script>
</html>