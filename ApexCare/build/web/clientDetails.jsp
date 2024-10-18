<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Client Profile</title>
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/agentStyle.css">
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
                <li><a class="nav_items" href="${pageContext.request.contextPath}/profileAgent.jsp">Profile details</a></li>
                <li><a class="nav_items" href="${pageContext.request.contextPath}/dasboard.jsp">Dashboard</a></li>
            </ul>

            <h3 class="navHead">Clients</h3>
            <hr class="navLine">
            <ul>
                <li><a class="nav_items" href="${pageContext.request.contextPath}/clientDetails.jsp">Client Details</a></li>
                <li><a class="nav_items" href="${pageContext.request.contextPath}/clientHistory.jsp">Client History</a></li>
            </ul>

            <h3 class="navHead">Issues</h3>
            <hr class="navLine">
            <ul>
                <li><a class="nav_items" href="${pageContext.request.contextPath}/reportIssue.jsp">Report an Issue</a></li>
                <li><a class="nav_items" href="${pageContext.request.contextPath}/assignIssue.jsp">Assign an Issue</a> </li>
            </ul>

            <form action="${pageContext.request.contextPath}/logout" method="get" style="display: inline;">
                <button type="submit" class="logout_btn">Logout</button>
            </form>
        </nav>
    </div>

    <!-- Main Content Area -->
    <main class="mainContent">
        
        <!-- Search Bar to Find Client by ID -->
        <form action="${pageContext.request.contextPath}/clientDetails" method="post" class="searchBarForm">
            <label for="searchClientID" class="searchLabel">Search by Client ID:</label>
            <input type="text" name="clientID" id="searchClientID" class="searchBox" placeholder="Enter Client ID" required />
            <button type="submit" class="searchButton">Search</button>
        </form>
        
        <form action="${pageContext.request.contextPath}/clientDetails" method="post" >
            <div class="fields">
                <label class="lbl">Username:</label>
                <input type="text" name="username" class="inputBox" value="${usernameC}" />
            </div>
            <div class="fields">
                <label class="lbl">Client ID:</label>
                <input type="text" name="clientID" class="inputBox" value="${clientIDC}" />
            </div>
            <div class="fields">
                <label class="lbl">First Name:</label>
                <input type="text" name="firstName" class="inputBox" value="${firstnameC}" />
            </div>
            <div class="fields">
                <label class="lbl">Last Name:</label>
                <input type="text" name="lastName" class="inputBox" value="${lastnameC}" />
            </div>
            <div class="fields">
                <label class="lbl">Phone:</label>
                <input type="text" name="phone" class="inputBox" value="${phoneC}" />
            </div>
            <div class="fields">
                <label class="lbl">Email:</label>
                <input type="text" name="email" class="inputBox" value="${emailC}" />
            </div>
            <div class="fields">
                <label class="lbl">Address:</label>
                <input type="text" name="address" class="inputBox" value="${addressC}" />
            </div>

            </form>
        </main>
        </main>
</body>
</html>