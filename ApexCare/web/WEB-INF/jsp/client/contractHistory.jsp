<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    request.setAttribute("contentPage", "client/contractHistoryContent.jsp");
    request.setAttribute("username", "Your Username Here"); // Adjust as needed
    String pageTitle = "Contract History"; 
    request.setAttribute("pageTitle", pageTitle);
%>
<jsp:include page="/WEB-INF/jsp/client/client_layout.jsp"/>
