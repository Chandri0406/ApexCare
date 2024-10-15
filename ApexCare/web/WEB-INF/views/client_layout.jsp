<!-- WEB-INF/jsp/layout.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${pageTitle}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/site.css" />
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
                <li class="nav_items"><a href="${pageContext.request.contextPath}/client/profileClient.jsp">Profile details</a></li>
                <li class="nav_items"><a href="${pageContext.request.contextPath}/agent/profileAgent.jsp">Agent Profile details</a></li>
                <li class="nav_items"><a href="${pageContext.request.contextPath}/technician/profileTechnician.jsp">Tech Profile details</a></li>
            </ul>

            <h3 class="navHead">Contracts</h3>
            <hr class="navLine">
            <ul>
                <li class="nav_items"><a href="${pageContext.request.contextPath}/client/contractDetails.jsp">Contract Details</a></li>
                <li class="nav_items"><a href="${pageContext.request.contextPath}/client/contractHistory.jsp">Contract History</a></li>
                <li class="nav_items"><a href="${pageContext.request.contextPath}/client/manageContract.jsp">Manage Contract</a></li>
            </ul>

            <h3 class="navHead">Issues</h3>
            <hr class="navLine">
            <ul>
                <li class="nav_items"><a href="${pageContext.request.contextPath}/client/feedback.jsp">Feedback Survey</a></li>
                <li class="nav_items"><a href="${pageContext.request.contextPath}/client/complaint.jsp">Send a Complaint</a></li>
            </ul>

            <a href="${pageContext.request.contextPath}/login.jsp" class="logout_btn">Logout</a>
        </nav>
    </div>

    <!-- Main Content Area -->
    <main>
        <jsp:include page="${contentPage}" />
    </main>
</body>
</html>
