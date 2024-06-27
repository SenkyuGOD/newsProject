<%--
  Created by IntelliJ IDEA.
  User: lolhk
  Date: 03.05.2024
  Time: 00:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="locale" value="${sessionScope.locale}"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="login.login_page"/></title>
    <link rel="stylesheet" href="style/auth.css">
</head>
<body>
<header>
    <h1><fmt:message key="login.auth"/> </h1>
</header>
<main>
    <form action="MyController" method="post">
        <input type="hidden" name="command" value="do_auth">
        <label for="username"><fmt:message key="login.username"/> </label>
        <input type="text" id="username" name="username" required>

        <label for="password"><fmt:message key="login.password"/> </label>
        <input type="password" id="password" name="password" required>

        <label for="remember-me">
            <input type="checkbox" id="remember-me" name="remember-me">
            <fmt:message key="login.remember_me"/>
        </label>

        <button type="submit">Login</button>
    </form>
    <p><fmt:message key="login.dont_have_acc"/> <a href="MyController?command=go_to_registration_page"><fmt:message
            key="login.register_here"/></a>.</p>
</main>
</body>
</html>

