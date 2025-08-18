<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add New Item</title>
    <link rel="stylesheet" href="assets/css/add-item.css">
</head>
<body>
    <a href="items" class="back-link">Back to Items</a>

    <div class="container">
        <h2>Add New Item</h2>
        <form action="add-item" method="post">
            <label>Item Name:</label>
            <input type="text" name="title" />

            <label>Unit Price:</label>
            <input type="number" name="unitPrice" step="0.01"  />
            
            <label>Stock Quantity:</label>
            <input type="number" name="stockQty"  />

            <input type="submit" value="Add Item" />
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
