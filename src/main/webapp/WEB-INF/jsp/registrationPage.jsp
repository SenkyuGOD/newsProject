<%--
  Created by IntelliJ IDEA.
  User: lolhk
  Date: 03.05.2024
  Time: 01:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registration Page</title>
    <link rel="stylesheet" href="style/reg.css">
</head>
<body>
<header>
    <h1>Register</h1>
</header>
<main>
    <form action="MyController" method="post">
        <input type="hidden" name="command" value="do_registration">

        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <label for="confirm-password">Confirm Password:</label>
        <input type="password" id="confirm-password" name="confirm-password" required>

        <label for="role">Register as:</label>
        <select id="role" name="role" required>
            <option value="READER">Reader</option>
            <option value="AUTHOR">Author</option>
        </select>


        <button type="submit">Register</button>
    </form>
    <p>Already have an account? <a href="MyController?command=go_to_auth_page">Login here</a>.</p>
</main>
</body>
</html>

