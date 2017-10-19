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
    <img src="<c:url value="/images/logo.png"/>" alt="Логотип" height="50" width="70">
    <h1>Breakfast tracker</h1>
</header>
<nav>
    <ul class="button-group">
        <li><a href="/team/main" class="button">Моя текущая задача</a></li>
        <li><a href="/team/completed" class="button">Выполненные задачи</a></li>
        <li><a href="/team/assigned" class="button">Порученные задачи</a></li>
        <li><a href="/team/create" class="button">Поручить задачу</a></li>
        <li><a href="/team/" class="button">Взять задачу</a></li>
    </ul>
</nav>
<main>
    <article>
        <h2><c:out value="${currentTask.taskName}"/></h2>
        <h3> # <c:out value="${currentTask.taskID}"/></h3>
        <section>
            <p>
                <c:out value="${currentTask.description}"/>
            </p>
        </section>
        <table>
            <tr>
                <td><strong>Постановщик задачи:</strong></td>
                <td><c:out value="${author.employeePosition}"/> /</td>
                <td><c:out value="${author.employeeName}"/></td>
                <td><a href=mailto:<c:out value="${author.eMail}"/>><img src="<c:url value="/images/mail.png"/>"
                                                                                height="30" width="30"/></a></td>
            </tr>
        </table>
        <form id="myForm" method="post" action="<c:url value="/main"/>">
            <input type="hidden" name="taskID" value='<c:out value="${currentTask.taskID}"/>'/>
            <p><input type="submit" class="buttonclose" value="Завершить задачу"></p>
        <%--<p><a href="<c:url value="/main"/>" class="buttonclose">Завершить задачу</a></p>--%>
        </form>
    </article>


    <aside>
        <table>
            <tr>
                <td><strong>Статус:</strong></td>
                <td><c:out value="${currentTask.status}"/></td>
            </tr>
            <tr>
                <td><strong>Старт:</strong></td>
                <td><c:out value="${currentTask.workStartDate}"/></td>
            </tr>
            <tr>
                <td class="dedline"><strong>Дедлайн:</strong></td>
                <td class="dedline"><c:out value="${currentTask.deadline}"/></td>
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
