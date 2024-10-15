<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.User, models.Clients" %> <!-- Replace with actual package -->
<!DOCTYPE html>
<html>
<head>
    <title>Create User</title>
    <link rel="stylesheet" type="text/css" href="/css/loginStyle.css"> <!-- Link to your CSS file -->
</head>
<body>
<div id="loginBody">
    <h2 class="feedback-text">Create User</h2>

    <form method="post" action="createUser">
        <div class="input-field">
            <div class="rectangle"></div>
            <label class="label" for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" required />
        </div>

        <div class="input-field">
            <div class="rectangle"></div>
            <label class="label" for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" required />
        </div>

        <div class="input-field">
            <div class="rectangle"></div>
            <label class="label" for="phone">Phone:</label>
            <input type="text" id="phone" name="phone" required />
        </div>

        <div class="input-field">
            <div class="rectangle"></div>
            <label class="label" for="email">Email:</label>
            <input type="email" id="email" name="email" required />
        </div>

        <div class="input-field">
            <div class="rectangle"></div>
            <label class="label" for="confirmEmail">Confirm Email:</label>
            <input type="email" id="confirmEmail" name="confirmEmail" required />
            <c:if test="${not empty errorMessage}">
                <p class="validation-error">${errorMessage}</p>
            </c:if>
        </div>

        <div class="input-field">
            <div class="rectangle"></div>
            <label class="label" for="address">Address:</label>
            <input type="text" id="address" name="address" required />
        </div>

        <div class="input-field">
            <div class="rectangle"></div>
            <label class="label" for="username">Username:</label>
            <input type="text" id="username" name="username" required />
            <c:if test="${not empty usernameError}">
                <p class="validation-error">${usernameError}</p>
            </c:if>
        </div>

        <div class="input-field">
            <div class="rectangle"></div>
            <label class="label" for="password">Password:</label>
            <input type="password" id="password" name="password" required />
        </div>

        <div class="input-field">
            <div class="rectangle"></div>
            <label class="label" for="confirmPassword">Confirm Password:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required />
            <c:if test="${not empty passwordError}">
                <p class="validation-error">${passwordError}</p>
            </c:if>
        </div>

        <button id="loginBtn" type="submit">Sign Up</button>
    </form>
</div>
</body>
</html>
