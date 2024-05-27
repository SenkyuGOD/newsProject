<%--
  Created by IntelliJ IDEA.
  User: lolhk
  Date: 04.05.2024
  Time: 01:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Профиль пользователя</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 50px auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            background-color: #f4f4f4;
        }

        .profile-info {
            margin-bottom: 20px;
        }

        .profile-info h2 {
            margin-top: 0;
        }

        .profile-info p {
            margin-bottom: 5px;
        }

        .profile-info span {
            font-weight: bold;
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
<div class="container">
    <div class="profile-info">
        <h2>Профиль пользователя</h2>
        <p><span>Имя пользователя:</span> ${user.username}</p>
        <p><span>Email:</span> ${user.email}</p>
        <p><span>Роль:</span> ${user.role}</p>
        <!-- Дополнительная информация о пользователе, например, дата регистрации и т.д. -->
    </div>
    <button class="btn">Редактировать профиль</button>
</div>
</body>
</html>
