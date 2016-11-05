<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Илья
  Date: 24.05.2016
  Time: 23:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Your friends</title>
    <script type="text/javascript" src="http://localhost:8080/albert_diplom_war/app/communication/remove_friend.js"></script>
    <link href="http://localhost:8080/albert_diplom_war/app/styles/main.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<header>
    <p>
        <a href="javascript:void(0);">Мои подписки</a>&nbsp;|&nbsp;
        <a href="http://localhost:8080/albert_diplom_war/app/controller?action=displayUserPage">На главную</a>&nbsp;|&nbsp;
        <a href="http://localhost:8080/albert_diplom_war/app/controller?action=logOut">Выйти</a>&nbsp;|&nbsp;
        <%--<a href="http://localhost:8080/albert_diplom_war/app/settings/userSettings.jsp">Settings</a>&nbsp;|&nbsp;<br>--%>
    </p>
</header>
<div id="content">

<table border="0px" id="usersTable" width="500px">
    <tr>
        <td>
            <form action="http://localhost:8080/albert_diplom_war/app/controller?action=searchUsers" method="POST">
                <input type="text"
                       size="30"
                       maxlength="20"
                       id="search_field"
                      <%-- onkeyup="doSearch();"--%>
                       name="searchString"
                >
                &nbsp;&nbsp;
                <input type="submit" value="поиск">
            </form>

        </td>
    </tr>
    <c:forEach var="friend" items="${userData}">
        <tr>
            <td>
                <a href="http://localhost:8080/albert_diplom_war/app/controller?action=displayUserPage&other_user=${friend.user.userID}">
                    ${friend.user.userID}
                </a>
                <%--<a href="http://localhost:8080/albert_diplom_war/app/controller?action=removeUserFromFriends&subscription=${friend.user.userID}">--%>
                <button onclick="removeFriend('${friend.user.userID}', this)">
                    Удалить из друзей
                </button>
            </td>
        </tr>
    </c:forEach>
</table>
    <hr>

    <c:if test="${(searchData!=null)&&(searchData.size()==0)}">
        <h1>Не найдено ничегошеньки.</h1>
    </c:if>
    <c:if test="${(searchData!=null)&&(searchData.size()>0)}">
        <p class="headline">Search results:</p>
        <table border="5px red" id="searchTable" width="500px">
        <c:forEach var="user" items="${searchData}">
            <tr>
                <td>
                    <a href="http://localhost:8080/albert_diplom_war/app/controller?action=displayUserPage&other_user=${user.userID}">
                            ${user.userID}
                    </a>
                </td>
            </tr>
        </c:forEach>
        </table>
    </c:if>

</div>


</body>
</html>
