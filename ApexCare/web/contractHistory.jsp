<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<h2>Select Contract ID</h2>
<form method="get" action="${pageContext.request.contextPath}/contractHistory">
    <select name="selectedID" onchange="this.form.submit()">
        <option value="">-- Select Contract ID --</option>
        <c:forEach var="contractID" items="${contractIDs}">
            <option value="${contractID}" <c:if test="${contractID == param.selectedID}">selected</c:if>>${contractID}</option>
        </c:forEach>
    </select>
</form>

<c:if test="${not empty contractId}">
    <h3>Contract Details</h3>
    <form>
        <label for="clientId">Client ID:</label>
        <input type="text" id="clientId" name="clientId" value="${clientId}" readonly /><br>

        <label for="startDate">Start Date:</label>
        <input type="text" id="startDate" name="startDate" value="${fn:substring(startDate, 0, 10)}" readonly placeholder="yyyy/mm/dd" /><br>

        <label for="endDate">End Date:</label>
        <input type="text" id="endDate" name="endDate" value="${fn:substring(endDate, 0, 10)}" readonly placeholder="yyyy/mm/dd" /><br>

        <label for="status">Status:</label>
        <input type="text" id="status" name="status" value="${status}" readonly /><br>

        <label for="type">Type:</label>
        <input type="text" id="type" name="type" value="${type}" readonly /><br>

        <label for="residency">Residency:</label>
        <input type="text" id="residency" name="residency" value="${residency}" readonly /><br>
    </form>
</c:if>

<c:if test="${not empty errorMessage}">
    <p style="color:red;">${errorMessage}</p>
</c:if>