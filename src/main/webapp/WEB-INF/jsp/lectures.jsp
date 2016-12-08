<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="fragments/header.jsp" %>
</head>
<body>

<div class="container">
    <h1 class="text-center">Lectures list</h1>

    <c:if test="${not empty lectures}">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Begin date</th>
                <th>Location</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="lecture" items="${lectures}">
                <tr class="clickable-row" data-href="check_visit?id=${lecture.id}">
                    <td>${lecture.id}</td>
                    <td>${lecture.name}</td>
                    <td><fmt:formatDate pattern="dd MMMM yyyy HH:mm"
                                        value="${lecture.beginDate}"/></td>
                    <td>${lecture.location}</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>

        <script>
            $(document).ready(function ($) {
                $(".clickable-row").click(function () {
                    window.document.location = $(this).data("href");
                });
            });
        </script>
        <br>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <a href="${pageContext.request.contextPath}/add_lecture">Add new lecture</a>
        </sec:authorize>
    </c:if>
</div>

</body>
</html>
