<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<title>课程信息显示</title>

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
					    	<h1 class="col-md-5">课程名单管理</h1>


							<form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;" action="${pageContext.request.contextPath}/admin/searchCourse" id="form1" method="post">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="请输入课程名称" name="coursename">
									<%--<input type="hidden" name="page" value="1">--%>
									<span class="input-group-addon btn" id="sub" onclick="$('#form1').submit()">搜索</span>
								</div>
							</form>


							<button class="btn btn-default col-md-2" style="margin-top: 20px" onClick="location.href='/admin/addCourse'">
								添加课程信息
								<sapn class="glyphicon glyphicon-plus"/>
							</button>

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
										<button class="btn btn-default btn-xs btn-info" onclick="window.location.href='/admin/editCourse?id=${item.courseid}'">修改</button>
										<button class="btn btn-default btn-xs btn-danger btn-primary" onclick="confirmPrompt('${item.coursename}', '${item.courseid}')">删除</button>
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
									<%--&lt;%&ndash; 首页 &ndash;%&gt;--%>
									<%--<c:if test="${pageInfo.firstPage!=null}">--%>
										<%--<li>--%>
											<%--<a href="${pageContext.request.contextPath}/admin/showCourse?page=${pageInfo.firstPage}&coursename=${queryParam.coursename}">首页</a>--%>
										<%--</li>--%>
									<%--</c:if>--%>

									<%-- 上一页 --%>
									<c:if test="${pageInfo.hasPreviousPage}">
										<li>
											<a href="${pageContext.request.contextPath}/admin/showCourse?page=${pageInfo.prePage}&coursename=${queryParam.coursename}">&laquo;上一页</a>
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
												<a href="${pageContext.request.contextPath}/admin/showCourse?page=${nav}&coursename=${queryParam.coursename}">${nav}</a>
											</li>
										</c:if>
									</c:forEach>

									<%-- 下一页 --%>
									<c:if test="${pageInfo.hasNextPage}">
										<li><a href="${pageContext.request.contextPath}/admin/showCourse?page=${pageInfo.nextPage}&coursename=${queryParam.coursename}">下一页&raquo;</a></li>
									</c:if>

									<%--&lt;%&ndash; 末页 &ndash;%&gt;--%>
									<%--<c:if test="${pageInfo.firstPage!=null}">--%>
										<%--<li>--%>
											<%--<a href="${pageContext.request.contextPath}/admin/showCourse?page=${pageInfo.lastPage}&coursename=${queryParam.coursename}">最后一页</a>--%>
										<%--</li>--%>
									<%--</c:if>--%>
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

        function confirmPrompt(courseName, courseId) {
            var confirm_msg = "确定要删除 "+courseName + "(课程号:" + courseId +")" + "吗？！";
            var userChoose = confirm(confirm_msg);
            console.log("程序执行到 confirmPrompt");
            if (userChoose == true){
                var get_url = "${pageContext.request.contextPath}/admin/removeCourse?id=" + courseId;
                $.get(get_url, function (respText) {
                    respText = $.parseJSON(respText);
                    if (respText.msg == "fail"){
                        alert("课程信息删除失败！请再次尝试删除课程！");
                    }else {
                        alert("成功删除课程信息！");
                        window.location.href = "${pageContext.request.contextPath}/admin/showCourse";
                    }
                })
			}
        }


//        $("#sub").click(function () {
//            $("#form1").ajaxSubmit({
//				datatype: 'json',
//				success: function (respText) {
//				    alert("json 返回成功！");
//					console.log(respText);
//					console.log($.type(respText));
//
//					var return_json = $.parseJSON(respText);
//					console.log(return_json);
//					console.log(return_json.courseCount);
//					console.log(return_json.pageInfo);
//					console.log(return_json.pageInfo.list);
//
//					respText = $.parseJSON(respText);
//					var res = [];
//					$.each(respText.pageInfo.list, function (i, item) {
//                        res.push('<tr>');
//                        res.push('<td>' + item.courseid + '</td>');
//                        res.push('<td>' + item.coursename + '</td>');
//                        res.push('<td>' + item.teacherid + '</td>');
//                        res.push('<td>' + item.coursetime + '</td>');
//                        res.push('<td>' + item.classroom + '</td>');
//                        res.push('<td>' + item.courseweek + '</td>');
//                        res.push('<td>' + item.coursetype + '</td>');
//                        res.push('<td>' + item.score + '</td>');
//                        res.push('<td>');
//                        res.push("<button class=\"btn btn-default btn-xs btn-info\" onClick=\"location.href=\'/admin/editCourse?id=" + item.courseid + "\'\">修改</button>");
//                        res.push("<button class=\"btn btn-default btn-xs btn-danger btn-primary\" onClick=\"location.href=\'/admin/removeCourse?id=" + item.courseid + "\'\">删除</button>");
//                        res.push('</td>');
//                        res.push('</tr>');
//                    });
//					$("tbody").empty().html(res.join(""));
//                }})
//			});

        
	</script>
</html>