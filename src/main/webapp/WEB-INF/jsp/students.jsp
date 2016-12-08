<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<%@include file="fragments/header.jsp" %>
<body>
<div class="container">
    <h1 class="text-center">Students list</h1>

    <c:if test="${not empty students}">
        <table class="table table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>Surname</th>
                <th>First name</th>
                <th>Second name</th>
                <th>Sex</th>
                <th>Course</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="student" items="${students}">
                <tr>
                    <td>${student.id}</td>
                    <td>${student.surname}</td>
                    <td>${student.firsname}</td>
                    <td>${student.secondname}</td>
                    <td>${student.sexId == 1 ? "Male" : "Female"}</td>
                    <td>${student.course.name}</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>

        <br>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <a href="${pageContext.request.contextPath}/add_student">Add new student</a>
        </sec:authorize>
    </c:if>
</div>
</body>
</html>
