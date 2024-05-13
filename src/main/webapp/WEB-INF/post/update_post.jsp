<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Post</title>
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
            margin: 0 auto;
            width: 400px;
            background-color: #fff;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
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

        input[type="text"]{
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            margin-bottom: 20px;
        }


        input[type="submit"] {
            background-color: #007bff;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            display: inline-block;
            margin: 0 auto;
        }

        input[type="submit"]:hover {
            background-color: #0069d9;
        }

        a {
            color: black;
            text-decoration: none;
            cursor: pointer;
            font-weight: bold;
        }
    </style>
</head>
<body>
<c:if test="${requestScope.getOrDefault('postUpdated',false)==false}">
    <div>
        <h2>Старые данные</h2>
        <div>
            <label for="header">header:</label>
            <p>${requestScope.getOrDefault("header", "")}</p>
            <br>
            <label for="content">content:</label>
            <p>${requestScope.getOrDefault("content", "")}</p>
            <br>
            <%String id = request.getParameter("id");%>
        </div>

        <h2>Изменить пост</h2>
        <form method="post" action="${pageContext.request.contextPath}/update_post">
            <input type="hidden" name="id" value="<%=id%>">
            <label for="header">header:</label>
            <input type="text" id="header" name="header">
            <br>
            <label for="content">content:</label>
            <textarea id="content" name="content" rows="10" cols="50"></textarea><br>
            <br>
            <div style="justify-content: center; display: flex">
                <input type="submit" value="Изменить пост">
            </div>
        </form>
    </div>
</c:if>
<c:if test="${requestScope.getOrDefault('postUpdated',false)==true}">
    <div>
        <form method="get" action="${pageContext.request.contextPath}/welcome">
            <h4 style="justify-content: center; display:flex;">Изменения внесены!</h4>
            <div style=" justify-content: center; display:flex;">
                <input type="submit" value="Вернуться на главную">
            </div>
        </form>
    </div>
</c:if>
</body>
</html>
