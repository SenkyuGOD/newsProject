<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Новостная страница</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }

        .news-article {
            background-color: #f4f4f4;
            padding: 20px;
            border-radius: 5px;
        }

        .news-title {
            font-size: 24px;
            margin-top: 0;
        }

        .news-content {
            font-size: 18px;
        }

        .news-img {
            max-width: 100%;
            height: auto;
            border-radius: 5px;
        }

        .footer {
            background-color: #333;
            color: #fff;
            text-align: center;
            padding: 10px 0;
            position: fixed;
            bottom: 0;
            width: 100%;
        }
    </style>
</head>
<body>
<div class="container">
    <article class="news-article">
        <img class="news-img" src="<c:out value="${requestScope.news[0].newsImg}"/>" alt="Описание изображения">
        <h2 class="news-title"><c:out value="${requestScope.news[0].newsTitle}"/></h2>
        <p class="news-content"><c:out value="${requestScope.news[0].newsContent}"/></p>
    </article>
</div>

<footer class="footer">
    <p>&copy; 2024 Новостная страница. Все права защищены.</p>
</footer>
</body>
</html>
