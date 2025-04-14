<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.smartfarm.db.model.*" %>

<html>
<head>
    <title>My Farm Dashboard</title>
</head>
<body>
<h1>Добро пожаловать на вашу ферму!</h1>

<!-- ==== ЖИВОТНЫЕ ==== -->
<h2>Ваши животные</h2>
<ul>
    <%
        List<Animal> animals = (List<Animal>) request.getAttribute("animals");
        for (Animal animal : animals) {
    %>
    <li>
        <%= animal.getType() %> - <%= animal.getName() %>
        <form method="post" action="my_farm" style="display:inline;">
            <input type="hidden" name="_method" value="DELETE" />
            <input type="hidden" name="resource" value="animal" />
            <input type="hidden" name="id" value="<%= animal.getId() %>" />
            <button type="submit">Удалить</button>
        </form>
    </li>
    <%
        }
    %>
</ul>

<h3>Добавить животное</h3>
<form method="post" action="my_farm">
    <input type="hidden" name="resource" value="animal" />
    Тип: <input type="text" name="type" required />
    Имя: <input type="text" name="name" required />
    <button type="submit">Добавить</button>
</form>


<!-- ==== РАСТЕНИЯ ==== -->
<h2>Ваши растения</h2>
<ul>
    <%
        List<Plant> plants = (List<Plant>) request.getAttribute("plants");
        for (Plant plant : plants) {
    %>
    <li>
        <%= plant.getType() %> - Посажено: <%= plant.getPlantedDate() %>
        <form method="post" action="my_farm" style="display:inline;">
            <input type="hidden" name="_method" value="DELETE" />
            <input type="hidden" name="resource" value="plant" />
            <input type="hidden" name="id" value="<%= plant.getId() %>" />
            <button type="submit">Удалить</button>
        </form>
    </li>
    <%
        }
    %>
</ul>

<h3>Добавить растение</h3>
<form method="post" action="my_farm">
    <input type="hidden" name="resource" value="plant" />
    Тип: <input type="text" name="type" required />
    Дата посадки: <input type="date" name="plantedDate" required />
    <button type="submit">Добавить</button>
</form>


<!-- ==== СЕНСОРЫ ==== -->
<h2>Ваши сенсоры</h2>
<ul>
    <%
        List<Sensor> sensors = (List<Sensor>) request.getAttribute("sensors");
        for (Sensor sensor : sensors) {
    %>
    <li>
        <%= sensor.getType() %> - Расположение: <%= sensor.getLocation() %>
        <form method="post" action="my_farm" style="display:inline;">
            <input type="hidden" name="_method" value="DELETE" />
            <input type="hidden" name="resource" value="sensor" />
            <input type="hidden" name="id" value="<%= sensor.getId() %>" />
            <button type="submit">Удалить</button>
        </form>
    </li>
    <%
        }
    %>
</ul>

<h3>Добавить сенсор</h3>
<form method="post" action="my_farm">
    <input type="hidden" name="resource" value="sensor" />
    Тип: <input type="text" name="type" required />
    Расположение: <input type="text" name="location" required />
    <button type="submit">Добавить</button>
</form>

</body>
</html>
