<%--
  Created by IntelliJ IDEA.
  User: lolhk
  Date: 03.05.2024
  Time: 01:01
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
    <title><fmt:message key="register.reg_page"/></title>
    <link rel="stylesheet" href="style/reg.css">
</head>
<body>
<header>
    <h1><fmt:message key="register.reg_page"/></h1>
</header>
<main>
    <form action="MyController" method="post">
        <input type="hidden" name="command" value="do_registration">

        <label for="username"><fmt:message key="register.username"/> </label>
        <input type="text" id="username" name="username" required>

        <label for="email"><fmt:message key="register.email"/> </label>
        <input type="email" id="email" name="email" required>

        <label for="password"><fmt:message key="register.password"/> </label>
        <input type="password" id="password" name="password" required>

        <label for="confirm-password"><fmt:message key="register.conf_pass"/> </label>
        <input type="password" id="confirm-password" name="confirm-password" required>

        <label for="role"><fmt:message key="register.reg_as"/> </label>
        <select id="role" name="role" required>
            <option value="READER"><fmt:message key="register.reader"/></option>
            <option value="AUTHOR"><fmt:message key="register.author"/></option>
        </select>


        <button type="submit"><fmt:message key="register.reg"/></button>
    </form>
    <p><fmt:message key="register.alr_have_acc"/> <a href="MyController?command=go_to_auth_page"><fmt:message key="register.login"/></a>.</p>
</main>
</body>
</html>

