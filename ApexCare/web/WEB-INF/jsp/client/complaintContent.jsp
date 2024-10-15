<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
    <h1>Complaint Form</h1>
    
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>
    
    <c:if test="${not empty success}">
        <p style="color:green;">${success}</p>
    </c:if>

    <form action="submitComplaint" method="post">
        <div>
            <label for="clientID">Client ID:</label>
            <input type="number" name="clientID" required />
        </div>
        
        <div>
            <label for="issueID">Issue ID:</label>
            <input type="text" name="issueID" required />
        </div>
        
        <div>
            <label for="dateReported">Date Reported:</label>
            <input type="date" name="dateReported" required />
        </div>
        
        <div>
            <label for="dateResolved">Date Resolved:</label>
            <input type="date" name="dateResolved" />
        </div>
        
        <div>
            <label for="description">Description:</label>
            <input type="text" name="description" required />
        </div>
        
        <button type="submit">Send</button>
    </form>
</main>
