<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
    <h1>Feedback Form</h1>
    
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>
    
    <c:if test="${not empty success}">
        <p style="color:green;">${success}</p>
    </c:if>

    <form action="submitFeedback" method="post">
        <div>
            <label for="clientID">Client ID:</label>
            <input type="number" name="clientID" required />
        </div>
        
        <div>
            <label for="issueID">Issue ID:</label>
            <input type="text" name="issueID" required />
        </div>
        
        <div>
            <label for="rating">Rating (1-5):</label>
            <input type="number" name="rating" min="1" max="5" required />
        </div>
        
        <div>
            <label for="dateProvided">Date:</label>
            <input type="date" name="dateProvided" required />
        </div>
        
        <div>
            <label for="comments">Comments:</label>
            <input type="text" name="comments" />
        </div>
        
        <button type="submit">Submit</button>
    </form>
</main>
