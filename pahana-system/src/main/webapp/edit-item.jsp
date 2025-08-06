<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page session="true" %>


<html>
<head>
    <title>Edit Item</title>
</head>
<body>

    <a href="items" class="back-link">Back to Items</a>

    <div class="container">
        <h2>Edit Item - ${item.id}</h2>

        <form action="edit-item" method="post">
            <input type="hidden" name="itemId" value="${item.id}" />

            <label>Item Name:</label>
            <input type="text" name="title" value="${item.title}" required />

            <label>Unit Price:</label>
            <input type="number" name="unitPrice" value="${item.unitPrice}" step="0.01" min="0" required />

            <label>Stock Quantity:</label>
            <input type="number" name="stockQty" value="${item.stockQty}" step="0.01" min="0" required />

            <input type="submit" value="Update Item" />
        </form>
    </div>

</body>
</html>

