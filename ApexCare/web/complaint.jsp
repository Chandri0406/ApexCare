<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="controller.complaintServlet" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Send a Complaint</title>
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
    </head>
    <body>
        <div>
        <!-- The form action points to the Servlet handling the submission -->
        <form name="complaintForm" action="${pageContext.request.contextPath}/complaint" method="post" onsubmit="return validateForm()">
            
            <div>
                <label for="clientID">Client ID:</label>
                <input type="number" name="clientID" id="clientID" />
            </div>

            <div>
                <label for="issueID">Issue ID:</label>
                <input type="text" name="issueID" id="issueID" />
            </div>

            <div>
                <label for="dateReported">Date:</label>
                <input type="date" name="dateReported" id="dateReported" />
            </div>

            <div>
                <label for="dateResolved">Date resolved:</label>
                <input type="date" name="dateResolved" id="dateResolved" />
            </div>

            <div>
                <label for="description">Description:</label>
                <input type="text" name="description" id="description" />
            </div>

            <button type="submit">Send</button>
        </form>

        <!-- Conditionally display the success message -->
        <c:if test="${not empty isComplaintSent && isComplaintSent}">
            <p style="color:red;">Complaint sent successfully</p>
        </c:if>
    </div>
    </body>
</html>
