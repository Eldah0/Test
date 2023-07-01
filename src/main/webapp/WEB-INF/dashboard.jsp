<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 6/30/2023
  Time: 9:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isErrorPage="true" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<h1>${user.userName}!</h1>
<p>Course schedule</p>
<a href="/logout">Logout</a>

<table>
  <thead>
  <tr>
    <th>Class name</th>
    <th>Instructor</th>
    <th>Weekday</th>
    <th>Price</th>
    <th>Time</th>
    <th>Description</th>

  </tr>
  </thead>
  <tbody>
  <c:forEach var="course" items="${allCourse}">
    <tr>
      <td><c:out value="${course.name}"/>
        <c:if test = "${course.user.id!=user.id}">
      <td><a href="/classes/${course.id}/edit">Edit</a></td>
      </c:if>

      </td>

      <td><c:out value="${course.user.userName}"/></td>

      <td><c:out value="${course.dayOfWeek}"/></td>
      <td><c:out value="${course.price}"/></td>
      <td><c:out value="${course.description}"/></td>

    </tr>
  </c:forEach>
  </tbody>
</table>

<a href="/courses/new">+ new course</a>

</body>
</html>
