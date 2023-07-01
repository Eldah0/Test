<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>title </title>
</head>
<body>
<h1 style="text-align: center; color: blue;">Course Platform - Instructors</h1>

<div style="display: flex; justify-content: space-around;">
    <div style="display: flex; flex-direction: column;">
        <h2>New Instructor</h2>
        <div style="display: flex; flex-direction: column; align-items: center; gap: 2px;">
            <label>Name</label>
            <input type="text" name="userName">
            <span class="text-danger"></span>
        </div>
        <div style="display: flex; flex-direction: column; align-items: center; gap: 2px;">
            <label>Email</label>
            <input type="email" name="email">
            <span class="text-danger"></span>
        </div>
        <div style="display: flex; flex-direction: column; align-items: center; gap: 2px;">
            <label>Password</label>
            <input type="password" name="password">
            <span class="text-danger"></span>
        </div>
        <div style="display: flex; flex-direction: column; align-items: center; gap: 2px;">
            <label>Confirm Password</label>
            <input type="password" name="confirmPassword">
            <span class="text-danger"></span>
        </div>
        <input type="submit" value="Register">
    </div>

    <div style="display: flex; flex-direction: column;">
        <h2>Log in</h2>
        <div style="display: flex; flex-direction: column; align-items: center; gap: 2px;">
            <label>Email</label>
            <input type="email" name="loginEmail">
            <span class="text-danger"></span>
        </div>
        <div style="display: flex; flex-direction: column; align-items: center; gap: 2px;">
            <label>Password</label>
            <input type="password" name="loginPassword">
            <span class="text-danger"></span>
        </div>
        <input type="submit" value="Log in">
    </div>
</div>

<!-- Other page content -->

</body>
</html>
