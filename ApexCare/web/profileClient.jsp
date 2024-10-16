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
                <li class="nav_items"><a href="${pageContext.request.contextPath}/profileClient.jsp">Profile details</a></li>
                <li class="nav_items"><a href="${pageContext.request.contextPath}/profileAgent.jsp">Agent Profile details</a></li>
                <li class="nav_items"><a href="${pageContext.request.contextPath}/profileTechnician.jsp">Tech Profile details</a></li>
            </ul>

            <h3 class="navHead">Contracts</h3>
            <hr class="navLine">
            <ul>
                <li class="nav_items"><a href="${pageContext.request.contextPath}/contractDetails.jsp">Contract Details</a></li>
                <li class="nav_items"><a href="${pageContext.request.contextPath}/contractHistory.jsp">Contract History</a></li>
                <li class="nav_items"><a href="${pageContext.request.contextPath}/manageContract.jsp">Manage Contract</a></li>
            </ul>

            <h3 class="navHead">Issues</h3>
            <hr class="navLine">
            <ul>
                <li class="nav_items"><a href="${pageContext.request.contextPath}/feedback.jsp">Feedback Survey</a></li>
                <li class="nav_items"><a href="${pageContext.request.contextPath}/complaint.jsp">Send a Complaint</a></li>
            </ul>

            <a href="${pageContext.request.contextPath}/login.jsp" class="logout_btn">Logout</a>
        </nav>
    </div>

    <!-- Main Content Area -->
    <main>
            <form method="post" action="profileClient">
                <label>Username:</label>
                <input type="text" name="username" value="${client.username}" /><br /><br />

                <label>First Name:</label>
                <input type="text" name="firstName" value="${client.firstName}" /><br /><br />

                <label>Last Name:</label>
                <input type="text" name="lastName" value="${client.lastName}" /><br /><br />

                <label>Phone:</label>
                <input type="text" name="phone" value="${client.phone}" /><br /><br />

                <label>Email:</label>
                <input type="text" name="email" value="${client.email}" /><br /><br />

                <label>Address:</label>
                <input type="text" name="address" value="${client.address}" /><br /><br />

                <button type="submit" class="editProfile">Edit Profile</button>
            </form>
        </main>
</body>
</html>