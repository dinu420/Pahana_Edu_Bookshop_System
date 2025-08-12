<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Dashboard</title>
</head>
<body>

<a class="back-link-top" href="logout.jsp">Back to Login</a>
<h2>Admin Dashboard</h2>

<h3>Create Cashier Account</h3>
<form action="createCashier" method="post">
    <label>Username:</label>
    <input type="text" name="username" required><br><br>

    <label>Password:</label>
    <input type="password" name="password" required><br><br>

    <input type="submit" value="Create Cashier">
</form>

<!-- Show messages if any -->
<%
    String message = (String) request.getAttribute("message");
    String error = (String) request.getAttribute("error");
    if (message != null) {
%>
    <p style="color:green;"><%= message %></p>
<%
    }
    if (error != null) {
%>
    <p style="color:red;"><%= error %></p>
<%
    }
%>

</body>
</html>
