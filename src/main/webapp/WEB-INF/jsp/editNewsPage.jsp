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
    <title><fmt:message key="editpage.title"/></title>
    <link rel="stylesheet" href="style/editNews.css">
</head>
<body>
<header class="header">
    <div class="logo"><fmt:message key="news.newspage"/></div>
    <nav class="nav">
        <a href="MyController?command=go_to_index_page"><fmt:message key="header.news"/></a>
    </nav>
</header>
<div class="container">
    <form action="MyController" method="post">
        <input type="hidden" name="command" value="update_news">
        <input type="hidden" name="id" value="${news.newsId}">

        <div class="form-group">
            <label for="newsTitle"><fmt:message key="editpage.title.label"/></label>
            <input type="text" id="newsTitle" name="newsTitle" value="${news.newsTitle}" required>
        </div>

        <div class="form-group">
            <label for="newsContent"><fmt:message key="editpage.content.label"/></label>
            <textarea id="newsContent" name="newsContent" rows="10" required>${news.newsContent}</textarea>
        </div>

        <div class="form-group">
            <label for="newsImg"><fmt:message key="editpage.image.label"/></label>
            <input type="text" id="newsImg" name="imgPath">
            <c:if test="${not empty news.newsImg}">
                <img class="preview-img" src="${news.newsImg}" alt="Превью изображения">
            </c:if>
        </div>

        <button type="submit" class="btn"><fmt:message key="editpage.update.button"/></button>
    </form>
</div>

<footer class="footer">
    <p>&copy; 2024 Новостная страница. Все права защищены.</p>
</footer>
</body>
</html>
