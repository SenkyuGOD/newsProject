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
    <title><fmt:message key="main.latest_news"/></title>
    <link rel="stylesheet" href="style/main.css">
</head>
<body>
<header>
    <div class="container">
        <nav class="nav-left">
            <a href="#news" class="nav-link"><fmt:message key="header.news"/></a>
        </nav>
        <h1><fmt:message key="header.latest_news"/></h1>
        <div class="auth-buttons">
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <a href="MyController?command=go_to_profile_page" class="auth-link"><fmt:message
                            key="header.profile"/></a>
                    <a href="MyController?command=do_logout" class="auth-link"><fmt:message key="header.logout"/></a>
                </c:when>
                <c:otherwise>
                    <a href="MyController?command=go_to_auth_page" class="auth-link"><fmt:message
                            key="header.login"/></a>
                    <a href="MyController?command=go_to_registration_page" class="auth-link"><fmt:message
                            key="header.register"/></a>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="language-selector">
            <span><fmt:message key="header.language"/></span>
            <a href="MyController?command=set_Locale&lang=en" class="language-link"><fmt:message
                    key="header.language_en"/></a> |
            <a href="MyController?command=set_Locale&lang=ru" class="language-link"><fmt:message
                    key="header.language_ru"/></a>
        </div>
    </div>
</header>


<main>
    <h2><fmt:message key="header.news"/></h2>
    <article>
        <h3><a href="news1.html"><fmt:message key="news.title1"/> </a></h3>
        <p><fmt:message key="news.description1"/></p>
    </article>
    <article>
        <h3><a href="news2.html"><fmt:message key="news.title2"/> </a></h3>
        <p><fmt:message key="news.description2"/></p>
    </article>
</main>
<footer>
    <p><fmt:message key="footer.copyright"/></p>
</footer>
</body>
</html>