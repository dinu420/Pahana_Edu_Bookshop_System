<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>

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
    <title>Edit Customer</title>
    <link rel="stylesheet" href="assets/css/edit-customer.css">
</head>
<body>
    <a class="back-link-top" href="customers">Back to Customers</a>

    <div class="form-container">
        <h2>Edit Customer - ${customer.accountNo}</h2>

        
        <c:if test="${not empty error}">
            <div class="error-message">${error}</div>
        </c:if>

        <form action="edit-customer" method="post">
            <input type="hidden" name="accountNo" value="${customer.accountNo}" />
            
            <label>Name:</label>
            <input type="text" name="name" value="${customer.name}" required />
            
            <label>Address:</label>
            <input type="text" name="address" value="${customer.address}" required />
            
            <label>Telephone:</label>
            <input type="text" name="telephone" value="${customer.telephone}" required />
            
            <label>Units Consumed:</label>
            <input type="number" name="units" value="${customer.unitsConsumed}" min="0" required />
            
            <input type="submit" value="Update Customer" />
        </form>
    </div>
</body>
</html>
