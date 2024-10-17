<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Client Profile</title>
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/clientStyle.css">
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
                <li><a class="nav_items" href="${pageContext.request.contextPath}/profileClient.jsp">Profile details</a></li>
                <li><a class="nav_items" href="${pageContext.request.contextPath}/profileAgent.jsp">Agent Profile details</a></li>
                <li><a class="nav_items" href="${pageContext.request.contextPath}/profileTechnician.jsp">Tech Profile details</a></li>
            </ul>

            <h3 class="navHead">Contracts</h3>
            <hr class="navLine">
            <ul>
                <li><a class="nav_items" href="${pageContext.request.contextPath}/contractDetails.jsp">Contract Details</a></li>
                <li><a class="nav_items" href="${pageContext.request.contextPath}/contractHistory.jsp">Contract History</a></li>
                <li><a class="nav_items" href="${pageContext.request.contextPath}/manageContract.jsp">Manage Contract</a></li>
            </ul>

            <h3 class="navHead">Issues</h3>
            <hr class="navLine">
            <ul>
                <li><a class="nav_items" href="${pageContext.request.contextPath}/feedback.jsp">Feedback Survey</a></li>
                <li><a class="nav_items" href="${pageContext.request.contextPath}/complaint.jsp">Send a Complaint</a> </li>
            </ul>

            <a href="${pageContext.request.contextPath}/login.jsp" class="logout_btn">Logout</a>
        </nav>
    </div>

    <!-- Main Content Area -->
    <main class="mainContent">
        <!-- Client Data Table -->
        <div class="tableContainer">
            <h3>Client Information</h3>
            <table class="clientTable">
                <tr>
                    <th>Username</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Address</th>
                </tr>
                <tr>
                    <td>${client.username}</td>
                    <td>${client.firstName}</td>
                    <td>${client.lastName}</td>
                    <td>${client.phone}</td>
                    <td>${client.email}</td>
                    <td>${client.address}</td>
                </tr>
            </table>
        </div>
        
        
        <form method="post" action="${pageContext.request.contextPath}/profileClient">
            <div class="fields">
                <label class="lbl">Username:</label>
                <input type="text" name="username" class="inputBox" value="${client.username}" />
            </div>
            <div class="fields">
                <label class="lbl">First Name:</label>
                <input type="text" name="firstName" class="inputBox" value="${client.firstName}" />
            </div>
            <div class="fields">
                <label class="lbl">Last Name:</label>
                <input type="text" name="lastName" class="inputBox" value="${client.lastName}" />
            </div>
            <div class="fields">
                <label class="lbl">Phone:</label>
                <input type="text" name="phone" class="inputBox" value="${client.phone}" />
            </div>
            <div class="fields">
                <label class="lbl">Email:</label>
                <input type="text" name="email" class="inputBox" value="${client.email}" />
            </div>
            <div class="fields">
                <label class="lbl">Address:</label>
                <input type="text" name="address" class="inputBox" value="${client.address}" />
            </div>

                <button type="submit" id="editProfile">Edit Profile</button>
            </form>
        </main>
</body>
</html>