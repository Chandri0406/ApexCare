<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="models.User, models.Technician" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Client Profile</title>
     <link rel="stylesheet" type="text/css" href="">
</head>
<body>
    <!-- Top Bar -->
    <header class="headerBar">
        <img src="${pageContext.request.contextPath}/images/logoMini.png" alt="Logo" id="logo">
        <span class="headTitle">Hello, ${username}</span>
    </header>

    <!-- Main Layout Container -->
    <div class="navBox">
        <!-- Sidebar -->
        <nav>
            <h3 class="navHead">Profile</h3>
            <hr class="navLine">
            <ul>
                <li><a class="nav_items" href="${pageContext.request.contextPath}/profileTechnician.jsp">Profile details</a></li>
                <li><a class="nav_items" href="${pageContext.request.contextPath}/profileTechnician.jsp">Dashboard</a></li>
            </ul>

            <h3 class="navHead">Jobs</h3>
            <hr class="navLine">
            <ul>
                <li><a class="nav_items" href="${pageContext.request.contextPath}/contractDetails.jsp">Contract Details</a></li>
            </ul>

            <a href="${pageContext.request.contextPath}/login.jsp" class="logout_btn">Logout</a>
        </nav>
    </div>

    <!-- Main Content Area -->
    <main class="mainContent">
        <form method="post" action="${pageContext.request.contextPath}/profileTechnician">
            <div class="fields">
                <label class="lbl">TechnicianID:</label>
                <input type="text" name="techID" class="inputBox" value="${technician.technicianID}" />
            </div>
            <div class="fields">
                <label class="lbl">Username:</label>
                <input type="text" name="username" class="inputBox" value="${technician.username}" />
            </div>
            <div class="fields">
                <label class="lbl">First Name:</label>
                <input type="text" name="firstName" class="inputBox" value="${technician.firstName}" />
            </div>
            <div class="fields">
                <label class="lbl">Last Name:</label>
                <input type="text" name="lastName" class="inputBox" value="${technician.lastName}" />
            </div>
            <div class="fields">
                <label class="lbl">Phone:</label>
                <input type="text" name="phone" class="inputBox" value="${technician.phone}" />
            </div>
            <div class="fields">
                <label class="lbl">Email:</label>
                <input type="text" name="email" class="inputBox" value="${technician.email}" />
            </div>
            </form>
        </main>
</body>
</html>
