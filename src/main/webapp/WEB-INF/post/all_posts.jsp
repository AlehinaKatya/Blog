<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All posts</title>
    <style>

        body {
            font-family: Arial, sans-serif;
            background-color: #F5F5F5;
        }

        .button {
            background-color: #008CBA;
            color: white;
            border: none;
            padding: 8px 16px;
            border-radius: 4px;
            cursor: pointer;
            margin-right: 8px;
        }

        .center {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 50px;
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

        input[type="text"] {
            background-color: #cccccc;
        }

        input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #0069d9;
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
    </style>
</head>
<body>
    <c:if test="${requestScope.getOrDefault('user', null)==null}">
        <a class="center" href="${pageContext.request.contextPath}/signup">
            <button class="button">Зарегистрироваться/Войти</button>
        </a>
    </c:if>
    <c:if test="${requestScope.getOrDefault('user', null)!=null}">
        <a class="center" href="${pageContext.request.contextPath}/welcome">
            <button class="button">Профиль</button>
        </a>
    </c:if>
    <br>
    <jsp:useBean id="postService" class="org.example.blog.service.impl.PostServiceImpl"/>
    <jsp:useBean id="allPostsList" scope="request" type="java.util.List"/>
    <h1>Все посты блога</h1>

    <form method="get" action="${pageContext.request.contextPath}/find_post">
        <label for="header">search:</label>
        <input type="text" id="header" name="header">
        <input type="submit" value="Найти">
    </form>

    <div>
        <ul>
            <c:forEach var="allpost" items="${allPostsList}">
                <li>
                    <h2>${allpost.getHeader()}</h2>
                        ${allpost.getContent()}<br><br>
                    <div class="info">
                        <span>Дата публикации: </span> ${allpost.getDateOfPublication()}<br>
                        <span>Автор: </span> ${allpost.getUserName()}
                    </div>
                    <c:if test="${requestScope.getOrDefault('user', null)!=null}">
                        <div style="display: flex;">
                            <a href="${pageContext.request.contextPath}/post_comments?post_id=${allpost.getId()}">
                                <button class="button">Посмотреть комментарии</button>
                            </a>
                        </div>
                    </c:if>
                </li>
            </c:forEach>
        </ul>
    </div>

</body>
</html>
