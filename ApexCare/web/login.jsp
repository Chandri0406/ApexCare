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
            <input type="text" name="username" class="inputBox" required>
            <div class="Validation"> <!-- This could show validation messages -->
                <c:if test="${not empty error}">${error}</c:if>
            </div>
        </div>

        <div>
            <label class="lbl">Password</label>
            <input type="password" name="password" class="inputBox" required>
            <div class="Validation"> <!-- This could show validation messages -->
                <c:if test="${not empty error}">${error}</c:if>
            </div>
        </div>

        <div>
            <label class="lbl">Role</label>
            <select name="role" class="inputBox">
                 <option class="lbl" value="">Choose a Role</option>
                <option class="lbl" value="Agent">Agent</option>
                <option class="lbl" value="Client">Client</option>
                <option class="lbl" value="Technician">Technician</option>
            </select>
        </div>

        <button id="btn" type="submit">Login</button>
    </form>

    <div>
        Not a user? <a href="${pageContext.request.contextPath}/createUser.jsp"><u>Create an account</u></a>
    </div>
    <div>
        Complaints <a href="${pageContext.request.contextPath}/complaint.jsp"><u>Send a Complaint</u></a>
    </div>
    <div>
        Feedback <a href="${pageContext.request.contextPath}/feedback.jsp"><u>Submit Feedback</u></a>
    </div>
</div>
</body>
</html>
