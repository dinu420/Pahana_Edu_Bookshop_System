<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Generate Bill</title>
    <link rel="stylesheet" href="assets/css/billing.css">
</head>
<body>
    <a href="home.jsp" class="back-link-top">Back to Dashboard</a>

    <div class="container">
        <h2>Billing System</h2>

        <form method="get" action="billing" class="form-row">
            <label>Account Number:</label>
            <input type="text" name="accountNo" value="${param.accountNo}" required/>
            <button type="submit">Search</button>
        </form>

        <c:if test="${not empty customer}">
            <hr/>
            <div class="customer-card">
            <h3>Customer Details</h3>
            <p><strong>Name:</strong> ${customer.name}</p>
            <p><strong>Telephone:</strong> ${customer.telephone}</p>
            <p><strong>Units Consumed:</strong> ${customer.unitsConsumed}</p>

            <form method="post" action="billing">
                <input type="hidden" name="accountNo" value="${customer.accountNo}" />
                <button type="submit">Download Bill as PDF</button>
            </form>
            </div>
        </c:if>

        <c:if test="${not empty error}">
            <p class="error-message">${error}</p>
        </c:if>
    </div>
</body>

</html>
