<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Blog</title>
  <style>

    body {
      font-family: Arial, sans-serif;
      background-color: #F5F5F5;
    }

    h1 {
      text-align: center;
      margin-bottom: 20px;
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
  </style>
</head>
<body>
<h1>Мой блог</h1>
<br/>
<a class="center" href="${pageContext.request.contextPath}/all_posts">
  <button class="button">Открыть блог</button>
</a>
</body>
</html>