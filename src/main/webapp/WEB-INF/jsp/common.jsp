<%--
  Created by IntelliJ IDEA.
  User: rHotD
  Date: 2017/11/9
  Time: 9:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- 设置 basePath 代表 项目的根路径 --%>
<c:set var="basePath" scope="application" value="${pageContext.request.contextPath}"/>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 引入bootstrap -->
<link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap.min.css">
<!-- 引入JQuery  bootstrap.js-->
<script src="${basePath}/js/jquery-3.2.1.min.js"></script>
<script src="${basePath}/js/bootstrap.min.js"></script>
<script src="${basePath}/js/jquery.form.js"></script>
