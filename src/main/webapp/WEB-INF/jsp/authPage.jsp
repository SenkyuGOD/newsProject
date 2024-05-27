<%--
  Created by IntelliJ IDEA.
  User: lolhk
  Date: 03.05.2024
  Time: 00:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Страница авторизации</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 400px;
            margin: 100px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f4f4f4;
        }

        .input-group {
            margin-bottom: 20px;
        }

        .input-group label {
            display: block;
            margin-bottom: 5px;
        }

        .input-group input {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
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

        .register-link {
            text-decoration: none;
            color: #007bff;
        }

        .register-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Авторизация</h2>
    <form action="MyController" method="post">
        <input type="hidden" name="command" value="do_auth"/>
        <div class="error-message" id="error-message">
            <c:if test="${not (param.authError eq null) }">
                <c:out value="${param.authError}"/>
            </c:if>
        </div>
        <div class="input-group">
            <label for="login">Имя пользователя:</label>
            <input type="text" id="login" name="login" required>
        </div>
        <div class="input-group">
            <label for="password">Пароль:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <button type="submit" class="btn">Войти</button>
    </form>
    <p>Нет аккаунта? <a href="MyController?command=go_to_registration_page" class="register-link">Зарегистрируйтесь</a></p>
</div>
</body>
</html>
