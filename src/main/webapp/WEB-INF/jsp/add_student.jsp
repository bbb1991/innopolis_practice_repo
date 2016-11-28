<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@include file="fragments/header.jsp" %>
</head>
<body>

<div class="container">
    <h1 class="text-center">Add new student</h1>
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/add_student">
        <fieldset>

            <!-- Form Name -->
            <legend>Add student form</legend>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="sn">Surname</label>
                <div class="col-md-4">
                    <input id="sn" name="sn" type="text" placeholder="surname" class="form-control input-md"
                           required>

                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="fn">First name</label>
                <div class="col-md-4">
                    <input id="fn" name="fn" type="text" placeholder="first name" class="form-control input-md"
                           required>

                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="ln">Last name</label>
                <div class="col-md-4">
                    <input id="ln" name="ln" type="text" placeholder="last name" class="form-control input-md">

                </div>
            </div>


            <!-- Multiple Radios (inline) -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="sex_id">Choose Sex:</label>
                <div class="col-md-4">
                    <label class="radio-inline" for="sex_id">
                        <input type="radio" name="sex_id" id="sex_id" value="1" checked>
                        Male
                    </label>
                    <label class="radio-inline" for="sex_id">
                        <input type="radio" name="sex_id" id="sex_id" value="2">
                        Female
                    </label>
                </div>
            </div>

            <!-- Select Basic -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="course_id">Select course</label>
                <div class="col-md-4">
                    <select id="course_id" name="course_id" class="form-control input-md">
                        <c:forEach var="course" items="${courses}">
                            <option value="${course.id}">${course.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>


            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="submit"></label>
                <div class="col-md-4">
                    <button id="submit" name="submit" class="btn btn-success">SUBMIT</button>
                </div>
            </div>

        </fieldset>
    </form>

</div>

</body>
</html>
