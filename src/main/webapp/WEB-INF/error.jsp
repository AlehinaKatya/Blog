<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page</title>
    <style>

        body {
            font-family: Arial, sans-serif;
            background-color: #F5F5F5;
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
        }

        h2 {
            font-size: 20px;
            text-align: center;
            margin-top: 0;
            margin-bottom: 10px;
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

        a {
            color: black;
            text-decoration: none;
            cursor: pointer;
            font-weight: bold;
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
<div>
    <div>
        <h1>
            Произошла ошибка
        </h1>
        <h2><%=  request.getAttribute("error") %></h2>
        <a class="center" href="${pageContext.request.contextPath}/all_posts">
            <button class="button" >Вернуться на главную</button>
        </a>
    </div>

</div>
</body>
</html>