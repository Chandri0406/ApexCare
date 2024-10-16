<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="models.Clients" %>
<%
    request.setAttribute("contentPage", "profileClientContent.jsp"); // Content page to include
    request.setAttribute("username", ""); // Pass the username to the layout
    // Set pageTitle and other attributes as needed
    String pageTitle = "Client Profile"; // Example title
    request.setAttribute("pageTitle", pageTitle);
%>
<jsp:include page="/WEB-INF/jsp/client_layout.jsp"/>
