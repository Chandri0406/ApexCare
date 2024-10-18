<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
        <form name="FeedbackForm" action="${pageContext.request.contextPath}/feedbackServlet" method="post" onsubmit="return validateForm()">
            
            <div class="fields">
                <label for="clientID" class="lbl">Client ID:</label>
                <input type="number" name="clientID" id="clientID" class="inputBox" value="${clientID}" readonly/>
            </div>

            <div class="fields">
                <label for="issueID" class="lbl">Issue ID:</label>
                <input type="text" name="issueID" id="issueID" class="inputBox" value="" />
            </div>

            <div class="stars">
                <label for="rating" class="lbl">Rating:</label>
                <!-- Create 5 stars for rating -->
                    <img src="${pageContext.request.contextPath}/images/starB.png" class="star" onclick="setRating(1)" data-value="1" alt="1 Star" id="star1">
                    <img src="${pageContext.request.contextPath}/images/starB.png" class="star" onclick="setRating(2)" data-value="2" alt="2 Stars" id="star2">
                    <img src="${pageContext.request.contextPath}/images/starB.png" class="star" onclick="setRating(3)" data-value="3" alt="3 Stars" id="star3">
                    <img src="${pageContext.request.contextPath}/images/starB.png" class="star" onclick="setRating(4)" data-value="4" alt="4 Stars" id="star4">
                    <img src="${pageContext.request.contextPath}/images/starB.png" class="star" onclick="setRating(5)" data-value="5" alt="5 Stars" id="star5">
                    <input type="hidden" name="rating" id="rating" value="" />
            </div>

            <div class="fields">
                <label for="comments" class="lbl">Comments:</label>
                <input type="text" name="comments" id="comments"class="inputBox" value=""  />
            </div>
            
            <div class="fields">
                <label for="dateProvided" class="lbl">Date:</label>
                <input type="date" name="dateProvided" id="dateProvided" class="inputBox" value="" />
            </div>

            <button type="submit" id="feedbackbtn">Submit</button>
        </form>
    </main>
            <script>
                function setRating(rating) {
                    // Update hidden input field with the selected rating
                    document.getElementById('rating').value = rating;

                    // Change the star images
                    for (let i = 1; i <= 5; i++) {
                        const star = document.getElementById('star' + i);
                        star.src = (i <= rating) ? "${pageContext.request.contextPath}/images/starY.png" : "${pageContext.request.contextPath}/images/starB.png";
                    }
                }
                
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
</body>
</html>
