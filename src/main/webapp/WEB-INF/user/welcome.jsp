<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Страница пользователя</title>
    <style>

        body {
            font-family: Arial, sans-serif;
            background-color: #F5F5F5;
        }

        /* Стили для никнейма */
        .username {
            font-size: 24px;
            font-weight: bold;
            margin-left: 20px;
        }

        /* Стили для кнопок */
        .button {
            background-color: #008CBA;
            color: white;
            border: none;
            padding: 8px 16px;
            border-radius: 4px;
            cursor: pointer;
            margin-right: 8px;
        }

        ul {
            list-style: none;
            padding: 0;
            margin: 0;
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
            max-width: 1200px;
            margin: 50px auto;
        }

        li {
            background-color: #FFFFFF;
            box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            padding: 20px;
            width: calc(33.33% - 20px);
            margin-bottom: 30px;
            box-sizing: border-box;
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        h2 {
            font-size: 20px;
            margin-top: 0;
            margin-bottom: 10px;
        }

        .info {
            font-size: 14px;
            color: #999999;
            margin-bottom: 10px;
        }

        p {
            font-size: 16px;
            line-height: 1.5;
            margin-bottom: 20px;
        }

        .center {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 50px;
        }

    </style>
</head>
<body>

<div class="center">
    <div class>
        <jsp:useBean id="user" scope="request" type="org.example.blog.dto.UserDto"/>
        <c:set var="user" value="${user}"/>
    </div>
    <div class="username">
        Имя пользователя: ${user.name}
    </div>
</div>

<a class="center" href="${pageContext.request.contextPath}/all_posts">
    <button class="button">Все</button>
</a>

<a class="center" href="${pageContext.request.contextPath}/create_post">
    <button class="button">Добавить пост</button>
</a>

<jsp:useBean id="postService" class="org.example.blog.service.impl.PostServiceImpl"/>
<jsp:useBean id="postList" scope="request" type="java.util.List"/>
<h1>Мои посты</h1>

<div>
    <ul>
        <c:forEach var="post" items="${postList}">
            <li>
                <h2>${post.getHeader()}</h2>
                    ${post.getContent()}<br><br>
                <div class="info">
                    <span>Дата публикации: </span> ${post.getDateOfPublication()}<br>
                    <span>Автор: </span> ${post.getUserName()}
                </div>
                <div style="display: flex;">
                    <a href="${pageContext.request.contextPath}/delete_post?id=${post.getId()}">
                        <button class="button">Удалить</button>
                    </a>
                    <a href="${pageContext.request.contextPath}/update_post?id=${post.getId()}">
                        <button class="button">Изменить</button>
                    </a>
                </div>
            </li>
        </c:forEach>
    </ul>
</div>
</body>
</html>