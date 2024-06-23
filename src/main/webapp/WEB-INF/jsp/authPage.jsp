<%--
  Created by IntelliJ IDEA.
  User: lolhk
  Date: 03.05.2024
  Time: 00:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" href="style/auth.css">
</head>
<body>
<header>
    <h1>Login</h1>
</header>
<main>
    <form action="MyController" method="post">
        <input type="hidden" name="command" value="do_auth">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <label for="remember-me">
            <input type="checkbox" id="remember-me" name="remember-me">
            Remember me
        </label>

        <button type="submit">Login</button>
    </form>
    <p>Don't have an account? <a href="MyController?command=go_to_registration_page">Register here</a>.</p>
</main>
</body>
</html>

