<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="fragments/header.jsp" %>
</head>
<body>
<div class="container">
    <h1 class="text-center">Checking '${lecture.name.toUpperCase()}' visits</h1>

    <c:if test="${not empty students}">
        <form method="post" action="${pageContext.request.contextPath}/check_visit">
            <input type="submit" value="Save" class="btn btn-success">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Student</th>
                    <th>Visited</th>
                    <th>Comment</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${student.surname} ${student.firsname} ${student.secondname}<input type="hidden"
                                                                                               name="student_id"
                                                                                               value="${student.id}">
                        </td>
                        <td>
                            <div class="form-group">

                                <fieldset id="is_visited_${student.id}">
                                    <label class="radio-inline" for="is_visited_${student.id}">
                                        <input type="radio" name="is_visited_${student.id}" id="is_visited_${student.id}" value="1" checked>
                                        Visited
                                    </label>
                                    <label class="radio-inline" for="is_visited_${student.id}">
                                        <input type="radio" name="is_visited_${student.id}" id="is_visited_${student.id}" value="0">
                                        Not visited
                                    </label>
                                </fieldset>
                            </div>
                        </td>
                        <td><input type="text" name="comment_${student.id}"></td>
                    </tr>

                </c:forEach>
                </tbody>

        </form>
    </c:if>

</div>
</body>
</html>
