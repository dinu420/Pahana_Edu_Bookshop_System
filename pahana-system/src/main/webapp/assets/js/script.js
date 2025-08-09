document.addEventListener("DOMContentLoaded", () => {
    console.log("Loading page script initialized.");
});

const toggle = document.getElementById("togglePassword");
const password = document.getElementById("password");

toggle.addEventListener("click", () => {
    const type = password.getAttribute("type") === "password" ? "text" : "password";
    password.setAttribute("type", type);
    toggle.classList.toggle("fa-eye");
    toggle.classList.toggle("fa-eye-slash");
});
