<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/loginStyle.css"> <!-- Link to your CSS file -->
</head>
<body>
<div id="loginBody">
    <h2 id="lgLabel">LOGIN</h2>

    <form method="post" action="${pageContext.request.contextPath}/login">
        <div>
            <label class="lbl">Username</label>
            <input type="text" name="Username" class="inputBox" required>
        </div>

        <div>
            <label class="lbl">Password</label>
            <input type="password" name="Password"  id="myInput" class="inputBox" required>
            <input type="checkbox" onclick="showPassword()">Show Password
        </div>
                    
        <div>
            <label class="lbl">Role</label>
            <select name="role" class="inputBox">
                <option class="lbl" name="defualt" value="">Choose a Role</option>
                <option class="lbl" name="Agent" value="Agent">Agent</option>
                <option class="lbl" name="Client" value="Client">Client</option>
                <option class="lbl" name="Technician" value="Technician">Technician</option>
            </select>
        </div>
                    
        <div class="Validation"> <!-- This could show validation messages -->
            <c:if test="${not empty error}">
                <span class="errorMessage">${error}</span>
            </c:if>
        </div>

        <button id="btn" type="submit">Login</button>
    </form>

    <div>
        Not a user? <a href="${pageContext.request.contextPath}/createUser.jsp">Create an account</a>
        <a href="${pageContext.request.contextPath}/profileClient.jsp">Profile</a>
    </div>
</div>
    
    <script>
        function showPassword() {
            var x = document.getElementById("myInput");
                if (x.type === "password") {
                  x.type = "text";
                } else {
                  x.type = "password";
                }
              } 
    </script>
</body>
</html>