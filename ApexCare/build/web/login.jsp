<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/loginStyle.css"> <!-- Link to your CSS file -->
</head>
<body>
    <header>
        <div>
            <img src="${pageContext.request.contextPath}/images/logoBig.png" id="logo">
        </div>
    </header>
    
<div id="loginBody">
    <h2 id="lgLabel">LOGIN</h2>

    <form method="post" action="${pageContext.request.contextPath}/login">
        <div class="fields">
            <label class="lbl">Username</label>
            <input type="text" name="Username" class="inputBox" required>
        </div>

        <div class="fields">
            <label class="lbl">Password</label>
            <input type="password" name="Password"  id="myInput" class="inputBox" required>
            <input type="checkbox" onclick="showPassword()">Show Password
        </div>
                    
        <div class="fields">
            <label class="lbl">Role</label>
            <select name="role" class="inputBox">
                <option class="inputBox" name="defualt" value="">Choose a Role</option>
                <option class="inputBox" name="Agent" value="Agent">Agent</option>
                <option class="inputBox" name="Client" value="Client">Client</option>
                <option class="inputBox" name="Technician" value="Technician">Technician</option>
            </select>
        </div>
                    
        <!-- Display error message if available -->
            <div class="Validation">
                <c:if test="${not empty errorMessage}">
                    <span class="errorMessage">${errorMessage}</span>
                </c:if>
            </div>

            <div class="fields">
                <button id="btn" type="submit" action="/">Login</button>
            </div>
        
    </form>

    <div id="link">
        Not a user? <a href="${pageContext.request.contextPath}/createUser.jsp">Create an account</a>
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
