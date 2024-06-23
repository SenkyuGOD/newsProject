<%--
  Created by IntelliJ IDEA.
  User: lolhk
  Date: 04.05.2024
  Time: 01:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>
    <link rel="stylesheet" href="style/style.css">
</head>
<body>
<header>
    <h1>User Profile</h1>
</header>
<main>
    <p>Welcome, <c:out value="${sessionScope.user.username}" />!</p>
    <p>Email: <c:out value="${sessionScope.user.email}" /></p>
    <a href="MyController?command=do_logout">Logout</a>
</main>
</body>
</html>

