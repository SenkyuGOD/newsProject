<%--
  Created by IntelliJ IDEA.
  User: lolhk
  Date: 03.05.2024
  Time: 01:09
  To change this template use File | Settings | File Templates.
--%>
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

        header {
            background-color: #333;
            color: #fff;
            padding: 10px 0;
            text-align: center;
        }

        header h1 {
            margin: 0;
        }

        nav ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
            text-align: center;
        }

        nav ul li {
            display: inline;
            margin-right: 20px;
        }

        nav ul li a {
            color: #fff;
            text-decoration: none;
        }

        section {
            padding: 20px;
            display: flex;
            flex-wrap: wrap;
            justify-content: space-between;
        }

        article {
            background-color: #f4f4f4;
            padding: 20px;
            width: 30%; /* Ширина новостного блока */
            margin-bottom: 20px;
            box-sizing: border-box;
            border-radius: 5px;
        }

        article img {
            max-width: 100%;
            height: auto;
            border-radius: 5px;
        }

        footer {
            background-color: #333;
            color: #fff;
            text-align: center;
            padding: 10px 0;
            position: fixed;
            bottom: 0;
            width: 100%;
        }

        .btn {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<header>
    <h1>Новости</h1>
    <nav>
        <ul>
            <li><a href="#">Главная</a></li>
            <li><a href="#">Новости</a></li>
            <li><a href="#">Профиль</a></li>
            <li><a href="#">О нас</a></li>
            <!-- Здесь может быть другая навигация по сайту -->
        </ul>
    </nav>
</header>

<section>
    <article>
        <img src="<c:out value="${requestScope.AllNews[1].newsImg}"/>" alt="Описание изображения">
        <h2><c:out value="${requestScope.AllNews[1].newsTitle}" /></h2>
        <a href="MyController?command=go_to_news_page&id=2">Читать далее...</a>
    </article>

    <article>
        <img src="<c:out value="${requestScope.AllNews[0].newsImg}"/>" alt="Описание изображения">
        <h2><c:out value="${requestScope.AllNews[0].newsTitle}" /></h2>
        <a href="MyController?command=go_to_news_page&id=1">Читать далее...</a>
    </article>


</section>

<footer>
    <p>&copy; 2024 Новостная страница. Все права защищены.</p>
</footer>


</body>
</html>