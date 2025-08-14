<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Cashier Accounts</title>
<link rel="stylesheet" href="assets/css/admin-dashboard.css">
</head>
<body>

<a class="back-link-top" href="admin-dashboard.jsp">Back to Dashboard</a>

<div class="dashboard-container">
    <h2>Cashier Accounts</h2>

    <h3>Create Cashier Account</h3>
    <form action="createCashier" method="post">
        <label>Username:</label>
        <input type="text" name="username" required>

        <label>Password:</label>
        <input type="password" name="password" required>

        <input type="submit" value="Create Cashier">
    </form>

    <!-- Show messages -->
    <p class="success-message">${message}</p>
    <p class="error-message">${error}</p>
</div>
</body>
</html>