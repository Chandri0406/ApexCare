<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<main>
    <form method="post" action="profileClient">
        <label>Username:</label>
        <input type="text" name="username" value="${client.username}" /><br /><br />

        <label>First Name:</label>
        <input type="text" name="firstName" value="${client.firstName}" /><br /><br />

        <label>Last Name:</label>
        <input type="text" name="lastName" value="${client.lastName}" /><br /><br />

        <label>Phone:</label>
        <input type="text" name="phone" value="${client.phone}" /><br /><br />

        <label>Email:</label>
        <input type="text" name="email" value="${client.email}" /><br /><br />

        <label>Address:</label>
        <input type="text" name="address" value="${client.address}" /><br /><br />

        <button type="submit">Edit Profile</button>
    </form>
</main>
