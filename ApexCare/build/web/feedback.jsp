<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="controller.feedbackServlet" %>

<!-- <!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Submit Feedback</title>
        <script>
            // Basic client-side validation for the form
            function validateForm() {
                var clientId = document.forms["feedbackForm"]["clientID"].value;
                var issueId = document.forms["feedbackForm"]["issueID"].value;
                var rating = document.forms["feedbackForm"]["rating"].value;
                var comments = document.forms["feedbackForm"]["comments"].value;
                var dateProvided = document.forms["feedbackForm"]["dateProvided"].value;

                if (clientId === "" || issueId === "" || dateProvided === "") {
                    alert("All fields except Comments are required.");
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <div> -->
        <!-- The form action points to the Servlet handling the submission -->
        <!--<form name="FeedbackForm" action="${pageContext.request.contextPath}/feedbackServlet" method="post" onsubmit="return validateForm()">
            
            <div>
                <label for="clientID">Client ID:</label>
                <input type="number" name="clientID" id="clientID" />
            </div>

            <div>
                <label for="issueID">Issue ID:</label>
                <input type="text" name="issueID" id="issueID" />
            </div>

            <div>
                <label for="rating">Rating:</label>
                <input type="number" name="rating" id="rating" />
            </div>

            <div>
                <label for="comments">Comments:</label>
                <input type="text" name="comments" id="comments" />
            </div>
            
            <div>
                <label for="dateProvided">Date:</label>
                <input type="date" name="dateProvided" id="dateProvided" />
            </div>

            <button type="submit">Submit</button>
        </form> -->

        <!-- Display success or error message only after form submission (POST request) -->
        <!-- <c:if test="${pageContext.request.method == 'POST'}">
            <c:choose>
                <c:when test="${not empty isComplaintSent && isComplaintSent}">
                    <p style="color:green;">Complaint sent successfully and added to the database.</p>
                </c:when>
                <c:when test="${not empty errorMessage}">
                    <p style="color:red;">${errorMessage}</p>
                </c:when>
            </c:choose>
        </c:if>
    </div>
    </body>
</html> -->
        
<!DOCTYPE html>
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

            <form action="${pageContext.request.contextPath}/logout" method="get" style="display: inline;">
                <button type="submit" class="logout_btn">Logout</button>
            </form>

        </nav>
    </div>

    <!-- Main Content Area -->
    <main class="mainContent">
        <form name="feedbackForm" action="${pageContext.request.contextPath}/feedbackServlet" method="post" onsubmit="return validateForm()">
            
            <div class="fields">
                <label for="clientID" class="lbl">Client ID:</label>
                <input type="number" name="clientID" id="clientID" class="inputBox" value="" />
            </div>

            <div class="fields">
                <label for="issueID" class="lbl">Issue ID:</label>
                <input type="text" name="issueID" id="issueID" class="inputBox" value="" />
            </div>

            <div class="fields">
                <label for="rating" class="lbl">Rating:</label>
                <input type="number" name="rating" id="rating" class="inputBox" value="" />
            </div>

            <div class="fields">
                <label for="comments" class="lbl">Comments:</label>
                <input type="text" name="comments" id="comments"class="inputBox" value=""  />
            </div>
            
            <div class="fields">
                <label for="dateProvided" class="lbl">Date:</label>
                <input type="date" name="dateProvided" id="dateProvided" class="inputBox" value="" />
            </div>

            <button id="feedbackbtn" type="submit" class="btn">Submit</button>
        </form>
    </main>
            <script>
                 // Basic client-side validation for the form
                function validateForm() {
                var clientId = document.forms["feedbackForm"]["clientID"].value;
                var issueId = document.forms["feedbackForm"]["issueID"].value;
                var rating = document.forms["feedbackForm"]["rating"].value;
                var comments = document.forms["feedbackForm"]["comments"].value;
                var dateProvided = document.forms["feedbackForm"]["dateProvided"].value;

                if (clientId === "" || issueId === "" || dateProvided === "" || rating === "") {
                    alert("All fields except Comments are required.");
                    return false;
                }
                return true;
            }
            </script>
</body>
</html>
