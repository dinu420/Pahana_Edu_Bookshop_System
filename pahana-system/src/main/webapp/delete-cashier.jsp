<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cashier Accounts</title>
<link rel="stylesheet" href="assets/css/delete-cashier.css">
</head>
<body>

<a class="back-link-top" href="admin-dashboard.jsp">Back to Dashboard</a>

<h3>Cashier List</h3>
<table>
    <tr>
        <th>Username</th>
        <th>Action</th>
    </tr>
    <c:forEach var="cashier" items="${cashierList}">
        <tr>
            <td>${cashier.username}</td>
            <td>
                <form action="deleteCashier" method="post" style="display:inline;">
   				 <input type="hidden" name="username" value="${cashier.username}" />
    				<button type="submit" class="button danger" 
           			 onclick="return confirm('Are you sure you want to delete this cashier?');">
       				 Delete
    				</button>
					</form>

            </td>
        </tr>
    </c:forEach>
</table>



</body>
</html>
