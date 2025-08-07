<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.pahanasystem.model.Customer" %>
<%@ page session="true" %>
<%@ page import="com.pahanasystem.service.impl.CustomerServiceImpl" %>
<%@ page import="com.pahanasystem.dao.impl.CustomerDaoImpl" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%
    // Session check
    Object user = session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    
%>

<html>
<head>
    <title>Customer Management</title>
    <link rel="stylesheet" href="assets/css/customer.css">
</head>
<body>
<a href="home.jsp" class="back-link-top">Back to Dashboard</a>

<div class="container">
    <div class="header-row">
    
    <h2>Customer Management</h2>
    <a class="add-button" href="add-customer.jsp">+ Add New Customer</a>
    </div>
    <br/><br/>

    <table>
        <tr>
            <th>Account No</th>
            <th>Name</th>
            <th>Address</th>
            <th>Telephone</th>
            <th>Units Consumed</th>
            <th>Actions</th>
        </tr>
      <c:forEach var="c" items="${customers}">
    <tr>
       		<td>${c.accountNo}</td>
			<td>${c.name}</td>
			<td>${c.address}</td>
			<td>${c.telephone}</td>
			<td>${c.unitsConsumed}</td>

        <td>
            <a class="button" href="edit-customer?accountNo=${c.accountNo}">Edit</a>
            <a class="button danger"
               href="delete-customer?accountNo=${c.accountNo}"
               onclick="return confirm('Are you sure you want to delete this customer?');">Delete</a>
        </td>
    </tr>
</c:forEach>

    </table>
    </div>

    <br/>
</body>
</html>
