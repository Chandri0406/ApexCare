<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
    <h2>Viewing Contract</h2>
    
    <form method="get" action="contractHistory">
        <select name="selectedID" onchange="this.form.submit()">
            <option value="">Select a contract</option>
            <c:forEach var="contract" items="${contracts}">
                <option value="${contract.contractID}">${contract.contractID}</option>
            </c:forEach>
        </select>
    </form>
    
    <c:if test="${not empty selectedContract}">
        <section class="contract-card">
            <div class="form-group">
                <label>Contract ID:</label>
                <input type="text" value="${selectedContract.contractID}" readonly /><br /><br />
            </div>
            <div class="form-group">
                <label>Client ID:</label>
                <input type="text" value="${selectedContract.clientID}" readonly /><br /><br />
            </div>
            <div class="form-group">
                <label>Start Date:</label>
                <input type="date" value="${fn:substring(selectedContract.startDate, 0, 10)}" readonly /><br /><br />
            </div>
            <div class="form-group">
                <label>End Date:</label>
                <input type="date" value="${fn:substring(selectedContract.endDate, 0, 10)}" readonly /><br /><br />
            </div>
            <div class="form-group">
                <label>Status:</label>
                <input type="text" value="${selectedContract.status}" readonly /><br /><br />
            </div>
            <div class="form-group">
                <label>Type:</label>
                <input type="text" value="${selectedContract.type}" readonly /><br /><br />
            </div>
            <div class="form-group">
                <label>Residency:</label>
                <input type="text" value="${selectedContract.residency}" readonly />
            </div>
        </section>
    </c:if>
</main>
