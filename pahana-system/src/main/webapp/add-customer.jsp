<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add New Customer</title>
    <link rel="stylesheet" href="assets/css/add-customer.css">
</head>
<body>
    <a class="back-link-top" href="customers">Back to Customers</a>

    <div class="form-container">
        <h2>Add New Customer</h2>

        <form action="add-customer" method="post">
            <label>Name:</label>
            <input type="text" name="name"/><br/>

            <label>Address:</label>
            <input type="text" name="address"  /><br/>

            <label>Telephone:</label>
            <input type="text" name="telephone"  /><br/>

            <label>Units Consumed:</label>
            <input type="number" name="units" value="0" /><br/>

            <input type="submit" value="Add Customer" />
        </form>
                 <!-- Error message block -->
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <div style="color:red; margin-bottom:10px;">
            <%= error %>
        </div>
    <%
        }
    %>
    </div>
</body>
</html>
