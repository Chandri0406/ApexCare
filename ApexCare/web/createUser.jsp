<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.User, models.Clients" %> <!-- Adjust package names as necessary -->
<%@ page import="controller.CreateUserServlet" %> <!-- Adjust package names as necessary -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create User</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/createStyle.css"> <!-- Link to your CSS file -->
</head>
<body>

<div class="bg"> 
    <h2 id="lgLabel">CREATE A USER</h2>

    <!-- Display error message if it exists -->
    <c:if test="${not empty errorMessage}">
        <div class="error-message">
            <p>${errorMessage}</p>
        </div>
    </c:if>

    <form action="createUser" method="post">
        <div>
            <label for="firstName" class="lbl">First Name: </label> 
            <input type="text" name="firstName" class="inputBox" required />
        </div>

        <div>
            <label for="lastName" class="lbl">Last Name: </label> 
            <input type="text" name="lastName" class="inputBox" required />
        </div>

        <div>
            <label for="phone" class="lbl">Phone: </label> 
            <input type="text" name="phone" class="inputBox" required />
        </div>

        <div>
            <label for="email" class="lbl">Email: </label> 
            <input type="email" name="email" class="inputBox" required id="email" />
        </div>

        <div>
            <label for="confirmEmail" class="lbl">Confirm Email: </label> 
            <input type="email" name="confirmEmail" required id="confirmEmail" class="inputBox" />
            <span class="EmailValidation error-message" id="emailError"></span> 
        </div>

        <div>
            <label for="address" class="lbl">Address: </label> 
            <input type="text" name="address" required class="inputBox" />
        </div>

        <div>
            <label for="username" class="lbl">Username: </label> 
            <input type="text" name="username" required class="inputBox" />
        </div>

        <div>
            <label for="password" class="lbl">Password: </label> 
            <input type="password" name="password" required id="password" class="inputBox" />
        </div>

        <div>
            <label for="confirmPassword" class="lbl">Confirm Password: </label> 
            <input type="password" name="confirmPassword" required id="confirmPassword" class="inputBox" />
            <span class="PasswordValidation error-message" id="passwordError"></span> 
        </div>

       <button type="submit" class="btn">Sign Up</button>
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
</script>

</body>
</html>
