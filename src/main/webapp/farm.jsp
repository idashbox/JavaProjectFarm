<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.smartfarm.model.User" %>
<%@ page import="com.smartfarm.model.Animal" %>
<html>
<head>
    <title>Ферма - Пользователи и Животные</title>
    <style>
        body { font-family: Arial, sans-serif; }
        table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }
        th, td { border: 1px solid black; padding: 8px; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
</head>
<body>
<h2>Список пользователей</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Имя</th>
        <th>Email</th>
    </tr>
    <%
        List<User> users = (List<User>) request.getAttribute("users");
        for (User user : users) {
    %>
    <tr>
        <td><%= user.getId() %></td>
        <td><%= user.getName() %></td>
        <td><%= user.getEmail() %></td>
    </tr>
    <% } %>
</table>

<h2>Список животных</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Вид</th>
        <th>Имя</th>
        <th>ID владельца</th>
    </tr>
    <%
        List<Animal> animals = (List<Animal>) request.getAttribute("animals");
        for (Animal animal : animals) {
    %>
    <tr>
        <td><%= animal.getId() %></td>
        <td><%= animal.getType() %></td>
        <td><%= animal.getName() %></td>
        <td><%= animal.getOwnerId() %></td>
    </tr>
    <% } %>
</table>
</body>
</html>
