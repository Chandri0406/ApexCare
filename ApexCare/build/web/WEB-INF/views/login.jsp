<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/loginStyle.css"> <!-- Link to your CSS file -->
</head>
<body>
<div id="loginBody">
    <h2 id="lgLabel">Login</h2>

    <form method="post" action="loginUser">
        <div class="fields">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required />
        </div>

        <div class="fields">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required />
        </div>

        <div class="fields">
            <label for="role">Role:</label>
            <select id="role" name="role" required>
                <option value="">Select Role</option>
                <option value="Client">Client</option>
                <option value="Agent">Service Agent</option>
                <option value="Technician">Technician</option>
            </select>
        </div>

        <div>
            <c:if test="${not empty error}">
                <p class="errorMSG">${error}</p> <!-- Added class for error messages -->
            </c:if>
        </div>

        <button id="loginBtn" type="submit">Login</button>
    </form>

    <div>
        Not a user? <a href="${pageContext.request.contextPath}/createUser.jsp"><u>Create an account</u></a>
    </div>
</div>
</body>
</html>
