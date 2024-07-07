<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:set var="locale" value="${sessionScope.locale}"/>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="messages"/>

<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="header.add_news"/></title>
    <link rel="stylesheet" type="text/css" href="style/createNews.css">
</head>
<body>
<header>
    <div class="container">
        <nav class="nav-left">
            <a href="MyController?command=go_to_index_page" class="nav-link"><fmt:message key="header.news"/></a>
        </nav>
        <h1><fmt:message key="header.add_news"/></h1>
    </div>
</header>
<main>
    <h2><fmt:message key="header.add_news"/></h2>
    <form action="MyController?command=create_news" method="post">
        <label for="title"><fmt:message key="news.title"/></label>
        <input type="text" id="title" name="title" required>

        <label for="content"><fmt:message key="news.content"/></label>
        <textarea id="content" name="content" required></textarea>

        <label for="imageUrl"><fmt:message key="news.imageUrl"/></label>
        <input type="text" id="imageUrl" name="imageUrl" required>

        <button type="submit"><fmt:message key="button.submit"/></button>
    </form>
</main>
</body>
</html>

