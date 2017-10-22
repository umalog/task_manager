<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" errorPage = "error.jsp" language="java" %>

<style>
    <%@include file='style.css' %>
</style>
<html>
<head>
    <title>bagtracker</title>
</head>

<body>
<header>
    <img src="<c:url value="/images/logo.png"/>" alt="Логотип" height="50" width="70">
    <h1>Breakfast tracker</h1>
</header>
<nav>
    <ul class="button-group">
        <li><a href="/team/main" class="button">Моя текущая задача</a></li>
        <li><a href="/team/completed" class="button">Выполненные задачи</a></li>
        <li><a href="/team/assigned" class="button">Порученные задачи</a></li>
        <li><a href="/team/create" class="button">Поручить задачу</a></li>
        <li><a href="/team/take" class="button">Взять задачу</a></li>
    </ul>
</nav>
<main>
    <form method="post" action="<c:url value="/create"/>">
        <article>
            <input class="taskname" type="text" name="taskname" placeholder="Введите название задачи"/>
            <textarea class="task" name="task" required rows=4>Описание задачи</textarea>
            <h3>Выбрать исполнителя:
                <h3>
                    <p>
                        <select name="contractor" class="selcls">
                            <option value="0">Не назначать исполнителя</option>
                            <c:forEach items="${freeEmployers}" var="worker">
                                <option value="<c:out value="${worker.employeeID}"/>"><c:out
                                        value="${worker.employeeName}"/>, <c:out
                                        value="${worker.employeePosition}"/>
                                </option>
                            </c:forEach>
                        </select>
                        <%--<p><a href="#" class="buttonclose">Назначить задачу</a></p>--%>
                    <p><input type="submit" class="buttonclose" value="Назначить задачу"></p>

                </h3>
            </h3>

        </article>
        <aside>
            <table>
                <tr>
                    <td colspan=2><strong>Сроки исполнения:</strong>
                </tr>
                </td>
                <tr>
                    <td class="dedline"><strong>Дедлайн:</strong></td>
                    <td class="dedline"><input class="date" type="date" name="dedline"/></td>
                </tr>
            </table>
        </aside>
    </form>
</main>

<footer>
    <time>2017</time>
    © by zykov:
    <a href="mailto:a.zykov.stc@innopolis.ru">a.zykov.stc@innopolis.ru</a>
</footer>
</body>
</html>
