<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file='style.css' %>
</style>
<html>
<head>
    <title>bagtracker</title>

</head>

<body>
<header>
    <img src="<c:url value="/images/logo.png"/>" alt="Логотип" height="50" width="50">

    <h1>Breakfast tracker</h1>
</header>
<nav>
    <ul class="button-group">
        <li><a href="#" class="button">Моя текущая задача</a></li>
        <li><a href="#" class="button">Выполненные задачи</a></li>
        <li><a href="#" class="button">Порученные задачи</a></li>
        <li><a href="#" class="button">Поручить задачу</a></li>
        <li><a href="#" class="button">Взять задачу</a></li>
    </ul>
</nav>

<main>
    <article>
        <h2>Здесь должна выходить тема или название задачи</h2>
        <h3>ID задачи</h3>

        <section>
            <p>здесь должно быть описание задачи: сделать что важное, интересное, полездное, крутое и очень-очень
                значимое для любимой компании<br>
                и желательно как можно срокорее!!!
            </p>
        </section>
        <table>
            <tr>
                <td><strong>Постановщик задачи:</strong></td>
                <td>Здесь ФИО постановщика</td>
                <td><a href=mailto:"адрес"><img src="<c:url value="/images/mail.png"/>"
                                                height="50" width="50"/></a></td>
            </tr>
        </table>
        <p><a href="#" class="buttonclose">Завершить задачу</a></p>
    </article>

    <aside>
        <table>
            <tr>
                <td><strong>Статус:</strong></td>
                <td>сюда вывести статус</td>
            </tr>
            <tr>
                <td><strong>Старт:</strong></td>
                <td>сюда дату старта</td>
            </tr>
            <tr>
                <td class="dedline"><strong>Дедлайн:</strong></td>
                <td class="dedline">сюда дату дедлайна</td>
            </tr>
        </table>
    </aside>
</main>

<footer>
    <time>2017</time>
    © by zykov:
    <a href="mailto:a.zykov.stc@innopolis.ru">a.zykov.stc@innopolis.ru</a>
</footer>

</body>
</html>
