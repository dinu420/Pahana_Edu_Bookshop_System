<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, com.pahanasystem.model.Item" %>
<%@ page session="true" %>
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
    <title>Item Management</title>
    <link rel="stylesheet" href="assets/css/item.css">
<body>
    <a class="back-link-top" href="home.jsp">Back to Dashboard</a>

    <div class="container">
        <div class="header-row">
            <h2>Item Management</h2>
            <a class="add-button" href="add-item.jsp">+ Add New Item</a>
        </div>

        <table>
            <tr>
                <th>Item Id</th>
                <th>Item Name</th>
                <th>Unit Price</th>
                <th>Stock Quantity</th>
                <th>Actions</th>
            </tr>
          <c:forEach var="i" items="${items}">
            <tr>
                <td>${i.id}</td>
                <td>${i.title}</td>
                <td>${i.unitPrice}</td>
                <td>${i.stockQty}</td>
                <td>
                    <a class="button" href="edit-item?itemId=${i.id}">Edit</a>
                    <a class="button danger" href="delete-item?itemId=${i.id}"
                       onclick="return confirm('Are you sure you want to delete this item?');">Delete</a>
                </td>
            </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
