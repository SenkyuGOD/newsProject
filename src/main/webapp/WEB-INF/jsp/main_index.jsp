<%--
  Created by IntelliJ IDEA.
  User: lolhk
  Date: 02.05.2024
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>News Page</title>
    <link rel="stylesheet" href="style/main.css">
</head>
<body>
<header>
    <div class="container">
        <h1>Latest News</h1>
        <div class="auth-buttons">
            <button id="login-btn">Login</button>
            <button id="register-btn">Register</button>
        </div>
        <div class="language-selector">
            <label for="language">Language:</label>
            <select id="language">
                <option value="en">English</option>
                <option value="ru">Русский</option>
            </select>
        </div>
    </div>
</header>
<nav>
    <ul>
        <li><a href="#news">News</a></li>
    </ul>
</nav>
<main>
    <section id="news">
        <h2>News</h2>
        <article>
            <h3>Event 1</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
        </article>
        <article>
            <h3>Event 2</h3>
            <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
        </article>
        <article>
            <h3>Event 3</h3>
            <p>Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
        </article>
        <article>
            <h3>Event 4</h3>
            <p>Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>
        </article>
    </section>
</main>
<footer>
    <p>&copy; 2024 News Page</p>
</footer>
</body>
</html>


