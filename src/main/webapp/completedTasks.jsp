<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file='style.css' %>
</style>
<html>
    <head>
        <title>bagtracker login</title>
    </head>

    <body>
        <header>
            <img src="<c:url value="/images/logo.png"/>" alt="Логотип" height="50" width="70">
            <h1>Breakfast tracker</h1>
        </header>
    <nav>
        <ul class="button-group">
            <li><a href="main.jsp" class="button">Моя текущая задача</a></li>
            <li><a href="#" class="button">Выполненные задачи</a></li>
            <li><a href="#" class="button">Порученные задачи</a></li>
            <li><a href="#" class="button">Поручить задачу</a></li>
            <li><a href="#" class="button">Взять задачу</a></li>
        </ul>
    </nav>
    <main>
        <article>
            <h2>Мои выполненные задачи:</h2>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Задача</th>
                        <th>Постановщик</th>
                        <th>Завершена</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${myClosedTasks}" var="task">
                        <tr>
                            <td><c:out value="${task.taskID}"/></td>
                            <td><c:out value="${task.taskName}"/></td>
                            <td><c:out value="${task.author}"/></td>
                            <td><c:out value="${task.closingDate}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </article>
    </main>


<footer>
    <time>2017</time>
    © by zykov:
    <a href="mailto:a.zykov.stc@innopolis.ru">a.zykov.stc@innopolis.ru</a>
</footer>
</body>

</html>

