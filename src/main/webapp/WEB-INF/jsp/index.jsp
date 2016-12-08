<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<%@include file="fragments/header.jsp" %>
<body>
<br>
<a href="${pageContext.request.contextPath}/students">Students list</a><br>
<a href="${pageContext.request.contextPath}/lectures">Lectures list</a><br>

<sec:authorize access="hasRole('ROLE_ADMIN')">
    <a href="${pageContext.request.contextPath}/check_visit">Check visit</a>
</sec:authorize>

</body>
</html>
