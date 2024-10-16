<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="controller.feedbackServlet" %>
<!DOCTYPE html>t"
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Send Feedback</title>
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
    <main>
        
    </main>
            <script>
                 // Basic client-side validation for the form
                function validateForm() {
                    var clientId = document.forms["complaintForm"]["clientID"].value;
                    var issueId = document.forms["complaintForm"]["issueID"].value;
                    var dateReported = document.forms["complaintForm"]["dateReported"].value;
                    var dateResolved = document.forms["complaintForm"]["dateResolved"].value;
                    var description = document.forms["complaintForm"]["description"].value;

                    if (clientId === "" || issueId === "" || dateReported === "" || description === "") {
                        alert("All fields except Date Resolved are required.");
                        return false;
                    }
                    return true;
                }
            </script>
</body>
</html>
