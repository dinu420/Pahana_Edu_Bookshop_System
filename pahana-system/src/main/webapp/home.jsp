<%@ page session="true" %>
<%@ page import="com.pahanasystem.model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
<link rel="stylesheet" href="assets/css/home.css">
</head>
<body>
    <div class="nav">
        <strong>Welcome, <%= user.getUsername() %></strong> |
        <a href="customers">Customer Management</a>
        <a href="items">Item Management</a>
        <a href="billing.jsp">Billing</a>
        <a href="help.jsp">Help</a>
        <a href="logout.jsp">Logout</a>
    </div>

    <div class="section">
        <h2>Dashboard</h2>
        <p>Select an operation from the navigation menu.</p>
    </div>
</body>
</html>
