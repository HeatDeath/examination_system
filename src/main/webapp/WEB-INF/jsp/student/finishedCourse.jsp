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
					    	<h1 class="col-md-5">已修课程</h1>
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
									<th>成绩</th>
					            </tr>
					        </thead>
					        <tbody>
							<c:forEach  items="${finishedCourseList}" var="item">
								<tr>
									<td>${item.courseCustom.courseid}</td>
									<td>${item.courseCustom.coursename}</td>
									<td>${item.courseCustom.teacherid}</td>
									<td>${item.courseCustom.coursetime}</td>
									<td>${item.courseCustom.classroom}</td>
									<td>${item.courseCustom.courseweek}</td>
									<td>${item.courseCustom.coursetype}</td>
									<td>${item.courseCustom.score}</td>
									<td style="color: red">${item.mark}</td>
								</tr>
							</c:forEach>
					        </tbody>
				    </table>
				    <div class="panel-footer">

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
		$("#nav li:nth-child(3)").addClass("active")


        function confirmd() {
            var msg = "您真的确定要删除吗？！";
            if (confirm(msg)==true){
                return true;
            }else{
                return false;
            }
        }

        $("#sub").click(function () {
            $("#form1").submit();
        });
	</script>
</html>