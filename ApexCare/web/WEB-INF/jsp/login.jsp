<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/loginStyle.css"> <!-- Link your CSS file here -->
    <title>Login</title>
</head>
<body>

<div class="bg">
    <form action="/login" method="post">
        <div class="username">
            <div class="Rectangle3"></div>
            <label class="Username">Username</label>
            <input type="text" name="username" required>
            <div class="UsernameValidation"> <!-- This could show validation messages -->
                <c:if test="${not empty error}">${error}</c:if>
            </div>
        </div>

        <div class="password">
            <div class="Rectangle4"></div>
            <label class="Password">Password</label>
            <input type="password" name="password" required>
            <div class="PasswordValidation"> <!-- This could show validation messages -->
                <c:if test="${not empty error}">${error}</c:if>
            </div>
        </div>

        <div class="role">
            <div class="Rectangle4"></div>
            <label class="Role">Role</label>
            <select name="role">
                <option value="Agent">Agent</option>
                <option value="Client">Client</option>
                <option value="Technician">Technician</option>
            </select>
        </div>

        <button class="btn" type="submit">
            <div class="Rectangle5">Login</div>
        </button>
    </form>
</div>

</body>
</html>
