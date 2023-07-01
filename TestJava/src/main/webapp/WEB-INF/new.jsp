<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 6/30/2023
  Time: 9:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>New Classes</title>
</head>
<body>
<h1>Create a course</h1>
<form:form action="/course" modelAttribute="course"  method="post">
    <div>
        <form:label path="name">Name</form:label>
        <form:input path="name"></form:input>
        <form:errors path="name" class="text-danger"></form:errors>
    </div>


    <div>
        <form:label path="dayOfWeek">Day of Week</form:label>
        <form:input path="dayOfWeek" type="time"></form:input>
        <form:errors path="dayOfWeek" class="text-danger"></form:errors>
    </div>

    <div>
        <form:label path="price">Price</form:label>
        <form:input path="price"></form:input>
        <form:errors path="price" class="text-danger"></form:errors>
    </div>

    <div>
        <form:label path="description">Description</form:label>
        <form:input path="description"></form:input>
        <form:errors path="description" class="text-danger"></form:errors>
    </div>

    <a class="linkBtn" href="/dashboard">Cancel</a>
    <input class="input" type="submit" value="Submit"/>

</form:form>
</body>
</html>
