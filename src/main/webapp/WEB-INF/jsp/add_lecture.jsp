<%--
  Created by IntelliJ IDEA.
  User: bbb1991
  Date: 11/29/16
  Time: 1:14 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="fragments/header.jsp"%>


    <script type="text/javascript" src="http://momentjs.com/downloads/moment.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.43/js/bootstrap-datetimepicker.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.43/css/bootstrap-datetimepicker.min.css" />

</head>
<body>


<div class="container">
    <h1 class="text-center">Add new lecture</h1>
    <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/add_lecture">

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <fieldset>

            <!-- Form Name -->
            <legend>Add lecture form</legend>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="title">Title</label>
                <div class="col-md-4">
                    <input id="title" name="title" type="text" placeholder="Lecture title" class="form-control input-md"
                           required>

                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="location">Location</label>
                <div class="col-md-4">
                    <input id="location" name="location" type="text" placeholder="Location" class="form-control input-md"
                           required>
                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="datetimepicker1">Begin date</label>
                <div class="col-md-4">
                    <div class='input-group date' id='datetimepicker1'>
                        <input type='text' class="form-control" name="begin_date"/>
                        <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                    </div>
                </div>
            </div>

            <script type="text/javascript">
                $(function () {
                    $('#datetimepicker1').datetimepicker();
                });
            </script>


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
