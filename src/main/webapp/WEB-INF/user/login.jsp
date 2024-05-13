<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Login</title>
    <style>
        html, body {
            height: 100%;
            margin: 0;
        }

        body {
            display: flex;
            align-items: center;
            justify-content: center;
            background-color: #f2f2f2;
            font-family: Verdana, sans-serif;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.3);
            max-width: 400px;
            margin: auto;
        }

        input[type="text"],
        input[type="password"],
        input[type="submit"] {
            display: block;
            width: 100%;
            padding: 10px;
            font-size: 16px;
            margin-bottom: 10px;
            border-radius: 3px;
            border: none;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0069d9;
        }
    </style>
</head>
<body>
<div>
    <h2>Вход</h2>
    <form method="post" action="${pageContext.request.contextPath}/login">
        <label for="name">name:</label>
        <input type="text" id="name" name="name">
        <br>
        <label for="password">password:</label>
        <input type="password" id="password" name="password">
        <br>
        <c:if test="${requestScope.getOrDefault('loginAttempt',true)==false}">
            <p>Введены неверный логин или пароль</p>
        </c:if>
        <input type="submit" value="Войти">
    </form>
</div>
</body>
</html>