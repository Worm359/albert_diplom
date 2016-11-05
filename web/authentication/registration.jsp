<%--
  Created by IntelliJ IDEA.
  User: Илья
  Date: 22.05.2016
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration page</title>
    <link rel="stylesheet" href="http://localhost:8080/albert_diplom_war/authentication/styles/form_style.css" />
    <script language='javascript' type='text/javascript'>
        function check(input) {
            if (input.value != document.getElementById('password').value) {
                input.setCustomValidity('Пароли не совпадают.');
            } else {
                // input is valid -- reset the error message
                input.setCustomValidity('');
            }
        }
    </script>
</head>
<body>
<div id="content">
<h1>Registration page</h1>
<p>Введите логин и пароль.<br> Логин и пароль могут содержать латинские строчные и прописные
буквы, а также символ "_" и цифры.</p>
<%
    String error_string = (String) session.getAttribute("registrationError");
    if (error_string!=null){
        out.print("<script>alert('"+error_string+"');</script>");
        session.removeAttribute("registrationError");
    }
%>

<form action="http://localhost:8080/albert_diplom_war/registration" method="POST">
    <fieldset>
        <legend>Введите свои регистрационные данные</legend>
        <div class="description">
            Логин:
        </div>
        <div class="fields">
            <input type="text" name="login_reg" required>
        </div>
        <br>
        <div class="description">
            Пароль:
        </div>
        <div class="fields">
            <input type="password" size="20" maxlength="20" name="password_reg" id="password" required>
        </div>
        <br>
        <div class="description">
            Повторите пароль:
        </div>
        <div class="fields">
            <input type="password" size="20" maxlength="20" oninput="check(this);" required>
        </div>
    </fieldset>
    <input type="submit" value="Зарегистрироваться" />
</form>
</div>
</body>
</html>
