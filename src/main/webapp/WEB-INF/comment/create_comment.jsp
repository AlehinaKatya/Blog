<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Comment</title>
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

<c:if test="${requestScope.getOrDefault('commentCreated',false)==false}">
    <div>
        <%String post_id = request.getParameter("post_id");%>
        <h2>Оставить комментарий</h2>
        <form method="post" action="${pageContext.request.contextPath}/create_comment">
            <input type="hidden" name="post_id" value="<%=post_id%>">
            <label for="content">content:</label>
            <textarea id="content" name="content" rows="10" cols="50"></textarea><br>
            <br>
            <div style="justify-content: center; display: flex">
                <input type="submit" value="Опубликовать комментарий">
            </div>
        </form>
    </div>
</c:if>
<c:if test="${requestScope.getOrDefault('commentCreated',false)==true}">
    <div>
        <%String post_id = request.getParameter("post_id");%>
        <form method="get" action="${pageContext.request.contextPath}/post_comments?post_id=<%=post_id%>">
            <input type="hidden" name="post_id" value="<%=post_id%>">
            <h4 style="justify-content: center; display:flex;">Комментарий опубликован!</h4>
            <div style=" justify-content: center; display:flex;">
                <input type="submit" value="Просмотр">
            </div>
        </form>
    </div>
</c:if>
</body>
</html>

