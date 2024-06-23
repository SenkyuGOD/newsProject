<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Главная - Новостной портал</title>
    <link rel="stylesheet" href="style/main.css">
</head>
<body>
<header>
    <div class="container">
        <nav class="nav-left">
            <a href="#news" class="nav-link">News</a>
        </nav>
        <h1>Latest News</h1>
        <div class="auth-buttons">
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <a href="MyController?command=go_to_profile_page" class="profile-btn">Profile</a>
                    <a href="MyController?command=do_logout" class="logout-btn">Logout</a>
                </c:when>
                <c:otherwise>
                    <a href="MyController?command=go_to_auth_page" class="auth-buttons">Login</a>
                    <a href="MyController?command=go_to_registration_page" class="auth-buttons">Register</a>
                </c:otherwise>
            </c:choose>
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
<main>
    <h2>Последние новости</h2>
    <article>
        <h3><a href="news1.html">Заголовок новости 1</a></h3>
        <p>Краткое описание новости 1...</p>
    </article>
    <article>
        <h3><a href="news2.html">Заголовок новости 2</a></h3>
        <p>Краткое описание новости 2...</p>
    </article>
</main>
<footer>
    <p>&copy; 2024 Новостной портал</p>
</footer>
</body>
</html>

