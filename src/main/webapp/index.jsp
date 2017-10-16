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
<main>
    <article>
        <h2>Войдите в систему</h2>

        <form id="myForm" method="post" action="<c:url value="/auth"/>">
            <p>e-mail<br>
                <input type="text" class="login" name="login"></p>
            <p>Пароль<br>
                <input class="login" type="password" name="password"></p>
            <p><input type="submit" class="buttonclose" value="Войти"></p>
        </form>
    </article>
</main>
<footer>
    <time>2017</time>
    © by zykov:
    <a href="mailto:a.zykov.stc@innopolis.ru">a.zykov.stc@innopolis.ru</a>
</footer>
</body>
</html>
