<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Регистрация</title></head>
<body>
<h2>Регистрация</h2>
<form action="register" method="post">
    Имя: <input type="text" name="name" required><br>
    Email: <input type="email" name="email" required><br>
    <input type="submit" value="Зарегистрироваться">
</form>
<a href="login.jsp">Уже есть аккаунт? Войти</a>
</body>
</html>
