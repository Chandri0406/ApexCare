<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.User, models.Clients" %> <!-- Replace with actual package -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create User</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/createStyle.css">
</head>
<body>

<div>
    <form method="post" action="createUser" id="signupForm">
        <div>
            <label for="firstName">First Name: </label>
            <input type="text" name="firstName" required />
        </div>

        <div>
            <label for="lastName">Last Name: </label>
            <input type="text" name="lastName" required />
        </div>

        <div>
            <label for="phone">Phone: </label>
            <input type="text" name="phone" required />
        </div>

        <div>
            <label for="email">Email: </label>
            <input type="email" name="email" required id="email" class="input-field" />
        </div>

        <div>
            <label for="confirmEmail">Confirm Email: </label>
            <input type="email" name="confirmEmail" required id="confirmEmail" class="input-field" />
            <span class="error-message" id="emailError"></span>
        </div>

        <div>
            <label for="address">Address: </label>
            <input type="text" name="address" required />
        </div>

        <div>
            <label for="username">Username: </label>
            <input type="text" name="username" required />
        </div>

        <div>
            <label for="password">Password: </label>
            <input type="password" name="password" required id="password" class="input-field" />
        </div>

        <div>
            <label for="confirmPassword">Confirm Password: </label>
            <input type="password" name="confirmPassword" required id="confirmPassword" class="input-field" />
            <span class="error-message" id="passwordError"></span>
        </div>

        <button type="submit">Sign Up</button>
    </form>
</div>

<script>
    // Real-time validation for email and password match
    document.getElementById('confirmEmail').addEventListener('input', validateEmail);
    document.getElementById('confirmPassword').addEventListener('input', validatePassword);

    function validateEmail() {
        const email = document.getElementById('email').value;
        const confirmEmail = document.getElementById('confirmEmail').value;
        const emailError = document.getElementById('emailError');

        if (email !== confirmEmail) {
            document.getElementById('confirmEmail').classList.add('input-error');
            emailError.textContent = "Emails do not match!";
        } else {
            document.getElementById('confirmEmail').classList.remove('input-error');
            emailError.textContent = "";
        }
    }

    function validatePassword() {
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirmPassword').value;
        const passwordError = document.getElementById('passwordError');

        if (password !== confirmPassword) {
            document.getElementById('confirmPassword').classList.add('input-error');
            passwordError.textContent = "Passwords do not match!";
        } else {
            document.getElementById('confirmPassword').classList.remove('input-error');
            passwordError.textContent = "";
        }
    }

    // Prevent form submission if there are validation errors
    document.getElementById('signupForm').addEventListener('submit', function (event) {
        validateEmail();
        validatePassword();

        const emailError = document.getElementById('emailError').textContent;
        const passwordError = document.getElementById('passwordError').textContent;

        if (emailError || passwordError) {
            event.preventDefault(); // Stop form submission
        }
    });
</script>

</body>
</html>
