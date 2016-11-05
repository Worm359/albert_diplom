<%@ page import="player.utilities.CookieUtilities" %>
<%@ page import="player.servlets.utilities.ServletUtilities" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ваша страница</title>
    <link href="http://localhost:8080/albert_diplom_war/app/styles/main.css" rel="stylesheet" type="text/css"/>
    <%--<link rel="stylesheet" href="http://localhost:8080/albert_diplom_war/app/styles/sc-player-standard.css" type="text/css">--%>
    <%--<script type="text/javascript" src="user_page.js"></script>--%>
    <script type="text/javascript" src="http://localhost:8080/albert_diplom_war/app/users/dist/jquery.min.js"></script>
    <script src="http://localhost:8080/albert_diplom_war/app/users/audiojs/audio.min.js"></script>
    <!--<script src="dist/libs/jquery.js"></script>-->
    <!-- 5 include the minified jstree source -->
    <style>
        ol { padding: 10px; margin: 0px; list-style: decimal-leading-zero inside; color: #ccc; width: 460px; border-top: 1px solid #ccc; font-size: 0.9em; }
        ol li { position: relative; margin: 0px; padding: 9px 2px 10px; border-bottom: 1px solid #ccc; cursor: pointer; }
        ol li a { display: block; text-indent: -3.3ex; padding: 0px 0px 0px 20px; }
        li.playing { color: #aaa; text-shadow: 1px 1px 0px rgba(255, 255, 255, 0.3); }
        li.playing a { color: #000; }
        li.playing:before { content: '♬'; width: 14px; height: 14px; padding: 3px; line-height: 14px; margin: 0px; position: absolute; left: -24px; top: 9px; color: #000; font-size: 13px; text-shadow: 1px 1px 0px rgba(0, 0, 0, 0.2); }
        li a {color: white;}
    </style>

</head>
<body>
<header>
    <p>
        <a href="http://localhost:8080/albert_diplom_war/app/controller?action=displayAllFriends">Мои подписки</a>&nbsp;|&nbsp;
        <a href="javascript:void(0);">На главную</a>&nbsp;|&nbsp;
        <a href="http://localhost:8080/albert_diplom_war/app/controller?action=logOut">Выйти</a>&nbsp;|&nbsp;
    <%--<a href="http://localhost:8080/albert_diplom_war/app/settings/userSettings.jsp">Settings</a>&nbsp;|&nbsp;<br>--%>
    </p>
</header>
<div id="content" style="height: 1500px;">
<p>Страница пользователя <span style="color: black;">${userData.userID}</span>
    <c:if test="${user==userData.userID}">
        (это вы)
    </c:if>
</p>
    <div class="folders" style="height: 1000px;">
        <fieldset style="height: 1000px;">
            <legend>
                Плэйлист:
            </legend>
            <div id="jstree">
                <!-- in this example the tree is populated from inline HTML -->
                <ul>
                    <li>Корневая папка
                            <li id="child_node_1">Folk
                                <ul>
                                    <li id="child_node_2">Korpiklaani
                                        <ul><li id="dasda">dsdasd</li></ul></li>
                                    <li id="child_node_3">Fintroll <ul><li></li></ul></li>
                                    <li id="child_node_4">Arstidir<ul><li></li></ul></li>
                                </ul>
                            </li>
                            <li id="child_node_5">Рок
                                <ul>
                                <li id="ds">Tool
                                    <ul>
                                        <li>Aenima</li>
                                        <li>10000 days</li>
                                        <li>Lateralus</li>
                                        <li>Undertow</li>
                                    </ul>
                                </li>
                                <li id="asda">Interpol<ul><li></li></ul></li>
                                </ul>
                            </li>
                            <li id="llg">Свалка<ul><li></li></ul></li>
                    </li>
                </ul>
            </div>
        </fieldset>
        <div class="songs" style="position: absolute; top: 250px; right: 230px;">
            <audio preload></audio>
            <ol>
                <li><a href="#" data-src="http://localhost:8080/albert_diplom_war/app/users/mp3/1.mp3">Vicarious</a></li>
                <li><a href="#" data-src="http://localhost:8080/albert_diplom_war/app/users/mp3/2.mp3">Jambi</a></li>
                <li><a href="#" data-src="http://localhost:8080/albert_diplom_war/app/users/mp3/3.mp3">Wings For Marie (Pt 1)</a></li>
                <li><a href="#" data-src="http://localhost:8080/albert_diplom_war/app/users/mp3/4.mp3">10,000 Days (Wings Pt 2)</a></li>
                <li><a href="#" data-src="http://localhost:8080/albert_diplom_war/app/users/mp3/5.mp3">The Pot</a></li>
                <li><a href="#" data-src="http://localhost:8080/albert_diplom_war/app/users/mp3/6.mp3">Lipan Conjuring</a></li>
                <li><a href="#" data-src="http://localhost:8080/albert_diplom_war/app/users/mp3/7.mp3">Lost Keys (Blame Hofmann)</a></li>
                <li><a href="#" data-src="http://localhost:8080/albert_diplom_war/app/users/mp3/8.mp3">Rosetta Stoned</a></li>
                <li><a href="#" data-src="http://localhost:8080/albert_diplom_war/app/users/mp3/9.mp3">Intension</a></li>
                <li><a href="#" data-src="http://localhost:8080/albert_diplom_war/app/users/mp3/10.mp3">Right In Two</a></li>
                <li><a href="#" data-src="http://localhost:8080/albert_diplom_war/app/users/mp3/11.mp3">Viginti Tres</a></li>
            </ol>
        </div>
    </div>
</div>



<script src="http://localhost:8080/albert_diplom_war/app/users/dist/jstree.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
<script>
    $(function() {
        // Setup the player to autoplay the next track
        var a = audiojs.createAll({
            trackEnded: function() {
                var next = $('ol li.playing').next();
                if (!next.length) next = $('ol li').first();
                next.addClass('playing').siblings().removeClass('playing');
                audio.load($('a', next).attr('data-src'));
                audio.play();
            }
        });

        // Load in the first track
        var audio = a[0];
        first = $('ol a').attr('data-src');
        $('ol li').first().addClass('playing');
        audio.load(first);

        // Load in a track on click
        $('ol li').click(function(e) {
            e.preventDefault();
            $(this).addClass('playing').siblings().removeClass('playing');
            audio.load($('a', this).attr('data-src'));
            audio.play();
        });
    })
</script>
<script>
    // 6 create an instance when the DOM is ready
    $('#jstree').jstree();
    $('#jstree').on("changed.jstree", function (e, data) {
        console.log(data.selected);
    });
    // 8 interact with the tree - either way is OK
    $('button').on('click', function () {
        $('#jstree').jstree(true).select_node('child_node_1');
        $('#jstree').jstree('select_node', 'child_node_1');
        $.jstree.reference('#jstree').select_node('child_node_1');
    });

</script>
<%--<script>
    audiojs.events.ready(function()
    {
        var as = audiojs.createAll();
    });
</script>--%>
<%--<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="D:\диплом\soundcloud-custom-player-master\js\soundcloud.player.api.js"></script>
<script type="text/javascript" src="../js/sc-player.js"></script>
<script type="text/javascript" src=""></script>--%>
</body>
</html>
