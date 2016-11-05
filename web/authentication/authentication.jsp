<%--
  Created by IntelliJ IDEA.
  User: Илья
  Date: 22.05.2016
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" import="player.utilities.CookieUtilities" %>
<html>
<head>
    <title>Authentication page</title>
    <link rel="stylesheet" href="http://localhost:8080/albert_diplom_war/authentication/styles/form_style.css" />
</head>
<body>
<%
    String userNameFromCookie=CookieUtilities.getCookieValue(request, "user_login", "null");
%>
<div id="content">
<h1>Authentication page</h1>

<form action="http://localhost:8080/albert_diplom_war/authentication" method="POST">
    <fieldset>
        <legend>Введите логин и пароль</legend>
        <div class="description">
            Логин
        </div>
        <div class="fields">
            <input type="text" name="login_auth"
                <%
                    if(!userNameFromCookie.equals("null"))
                    {
                        out.print(" value="+'"'+userNameFromCookie+'"');
                    }
                %>
            >
        </div>
        <br>
        <div class="description">
            Пароль
        </div>
        <div class="fields">
            <input type="password" size="20" maxlength="20" name="password_auth">
        </div>
    </fieldset>
    <br><input type="submit" value="Войти" />
</form>

<%--
<form action="../authentication" method="POST">
    Логин:<br><input type="text" name="login_auth"
    <%
    if(!userNameFromCookie.equals("null"))
    {
        out.print(" value="+'"'+userNameFromCookie+'"');
    }
    %>
            >
    <br>Пароль:<br><input type="password" size="20" maxlength="20" name="password_auth">
    <br><input type="submit" value="Войти" />
</form>--%>
<p>Еще не завели аккаунт? Зарегистрируйтесь!<br>
<a href="http://localhost:8080/albert_diplom_war/authentication/registration.jsp">Ссылка на регистрацию</a> </p>
</div>
</body>
</html>
