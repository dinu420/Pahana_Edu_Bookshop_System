<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Pahana Edu - Login</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="assets/css/login.css">
</head>
<body>
    <div class="login-container">
        <h2>Login to Pahana Bookshop</h2>

        <form action="login" method="post">
            <label>Username:</label>
            <input type="text" name="username" required />

            <label>Password:</label>
            <div class="password-wrapper">
                <input type="password" name="password" id="password" required />
                <i class="fa-regular fa-eye toggle-password" id="togglePassword"></i>
            </div>

            <input type="submit" value="Login" />
        </form>

        <p>${requestScope.error}</p>
    </div>

    <script>
        const toggle = document.getElementById("togglePassword");
        const password = document.getElementById("password");

        toggle.addEventListener("click", () => {
            const type = password.getAttribute("type") === "password" ? "text" : "password";
            password.setAttribute("type", type);
            toggle.classList.toggle("fa-eye");
            toggle.classList.toggle("fa-eye-slash");
        });
    </script>
</body>
</html>
