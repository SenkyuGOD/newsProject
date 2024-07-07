<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="locale" value="${sessionScope.locale}"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html lang="${sessionScope.locale.language}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><fmt:message key="news.newspage"/></title>
    <link rel="stylesheet" href="style/newsPage.css">
</head>
<body>
<header class="header">
    <div class="logo"><fmt:message key="news.newspage"/></div>
    <nav class="nav">
        <a href="MyController?command=go_to_index_page"><fmt:message key="header.news"/> </a>
    </nav>
</header>

<div class="container">
    <article class="news-article">
        <c:choose>
            <c:when test="${not empty news.newsImg}">
                <img class="news-img" src="${news.newsImg}" alt="Описание изображения">
            </c:when>
            <c:otherwise>
                <img class="news-img" src="default-image.jpg" alt="Нет изображения">
            </c:otherwise>
        </c:choose>
        <h2 class="news-title"><c:out value="${news.newsTitle}"/></h2>
        <p class="news-content"><c:out value="${news.newsContent}"/></p>

        <c:choose>
            <c:when test="${sessionScope.user.role eq 'AUTHOR' || sessionScope.user.role eq 'ADMIN'}">
                <a href="MyController?command=go_to_edit_news_page&id=${news.newsId}" class="edit-button"><fmt:message key="news.edit"/></a>
            </c:when>
        </c:choose>

        <c:choose>
            <c:when test="${sessionScope.user.role eq 'ADMIN'}">
                <form action="MyController" method="post" onsubmit="return confirm('<fmt:message key="news.delete.confirm"/>');">
                    <input type="hidden" name="command" value="delete_news">
                    <input type="hidden" name="id" value="${news.newsId}">
                    <button type="submit" class="delete-button"><fmt:message key="news.delete"/></button>
                </form>
            </c:when>
        </c:choose>
    </article>
</div>

<footer class="footer">
    <p>&copy; 2024 Новостная страница. Все права защищены.</p>
</footer>
</body>
</html>

