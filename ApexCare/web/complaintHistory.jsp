<%@page import="java.util.List"%>
<%@page import="models.Complaint"%>
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
                <li><a class="nav_items" href="${pageContext.request.contextPath}/complaintHistory.jsp">Complaint History</a></li>
            </ul>

            <h3 class="navHead">Issues</h3>
            <hr class="navLine">
            <ul>
                <li><a class="nav_items" href="${pageContext.request.contextPath}/reportIssue.jsp">Report an Issue</a></li>
                <li><a class="nav_items" href="${pageContext.request.contextPath}/assignIssue.jsp">Assign an Issue</a></li>
            </ul>

            <form action="${pageContext.request.contextPath}/logout" method="get" style="display: inline;">
                <button type="submit" class="logout_btn">Logout</button>
            </form>
        </nav>
    </div>

    <!-- Main Content Area -->
    <main class="mainContent">
        <form action="${pageContext.request.contextPath}/complaintHistory" method="post" class="searchBarForm">
            <label for="searchClientID" class="lbl">Search by Client ID:</label>
            <input type="integer" name="clientIDS" class="searchBox" placeholder="Enter Client ID" required />
            <button type="submit" id="searchbtn">Search</button>
        </form>
    

    <c:if test="${not empty errorMessage}">
        <div style="color: red;">${errorMessage}</div>
    </c:if>

    <table class="tbl">
        <tr id="header">
            <th>Complaint ID</th>
            <th>Client ID</th>
            <th>Issue ID</th>
            <th>Date Reported</th>
            <th>Date Resolved</th>
            <th>Description</th>
        </tr>
            <%
        List<Complaint> complaintsList = (List<Complaint>) request.getAttribute("complaints");
        if (complaintsList != null && !complaintsList.isEmpty()) {
            for (Complaint com : complaintsList) {
            %>
                <tr>
                    <td><%= com.getComplaintID() %></td>
                    <td><%= com.getClientID() %></td>
                    <td><%= com.getIssueID() %></td>
                    <td><%= com.getDateReported() %></td>
                    <td><%= com.getDateResolved() != null ? com.getDateResolved() : "Not Resolved" %></td>
                    <td><%= com.getDescription() %></td>
                </tr>
    <%
            }
        } else {
    %>
                <tr>
                    <td colspan="6">No complaints found.</td>
                </tr>
    <%
        }
    %>
        </c:forEach>
    </table>
    </main>
</body>
</html>