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
					    	<h1 class="col-md-5">已选该课程学生名单</h1>
						</div>
				    </div>
				    <table class="table table-bordered">
					        <thead>
					            <tr>
									<th>学号</th>
									<th>姓名</th>
									<th>分数</th>
									<th>操作</th>
					            </tr>
					        </thead>
					        <tbody>
								<c:forEach items="${pageInfo.list}" var="item">
									<tr>
										<td>${item.studentCustom.userid}</td>
										<td>${item.studentCustom.username}</td>
										<c:if test="${!item.over}">
											<td>未打分</td>
											<td>
												<button class="btn btn-default btn-xs btn-info" onClick="window.location.href='/teacher/mark?studentid=${item.studentid}&courseid=${item.courseid}'">打分</button>
											</td>
										</c:if>
										<c:if test="${item.over}">
											<td>${item.mark}</td>
											<td>已打分</td>
										</c:if>
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
											<a href="${basePath}/teacher/showCourseGrade?page=${pageInfo.prePage}&courseid=${courseID}">&laquo;上一页</a>
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
												<a href="${basePath}/teacher/showCourseGrade?page=${nav}&courseid=${courseID}">${nav}</a>
											</li>
										</c:if>
									</c:forEach>

									<%-- 下一页 --%>
									<c:if test="${pageInfo.hasNextPage}">
										<li><a href="${basePath}/teacher/showCourseGrade?page=${pageInfo.nextPage}&courseid=${courseID}">下一页&raquo;</a></li>
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

//        function confirmd() {
//            var msg = "您真的确定要删除吗？！";
//            if (confirm(msg)==true){
//                return true;
//            }else{
//                return false;
//            }
//        }
//
//        $("#sub").click(function () {
//            $("#form1").submit();
//        });
	</script>
</html>