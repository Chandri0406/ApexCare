<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="controller.feedbackServlet" %>

<!DOCTYPE html>
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
        <div>
        <!-- The form action points to the Servlet handling the submission -->
        <form name="FeedbackForm" action="${pageContext.request.contextPath}/feedbackServlet" method="post" onsubmit="return validateForm()">
            
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
        </form>

        <!-- Display success or error message only after form submission (POST request) -->
        <c:if test="${pageContext.request.method == 'POST'}">
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
</html>
