<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8" %>
<%--<c:if test="${lang == 'ru'}"><fmt:setLocale value='ru' scope="session"/></c:if>--%>
<%--<c:if test="${lang == 'en'}"><fmt:setLocale value='en' scope="session"/></c:if>--%>
<fmt:setBundle basename="messages/messages"/>
<c:set var="prefix" value="${pageContext.request.contextPath}"/>

<%@attribute name="title" type="java.lang.String" required="true" %>
<%@attribute name="cssitems" %>
<%@attribute name="jsitems" %>
<%@attribute name="header" fragment="true" %>
<%@attribute name="menu" fragment="true" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="${prefix}/img/favicon.ico">

    <title>${title}</title>
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,300,500&subset=latin,cyrillic' rel='stylesheet'
          type='text/css'>

    <!-- Bootstrap core CSS -->
    <link href="${prefix}/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${prefix}/css/main.css" rel="stylesheet">
    <c:if test="${not empty cssitems}">
        <c:forEach items="${cssitems}" var="css">
            <link href="${prefix}${css}" rel="stylesheet">
        </c:forEach>
    </c:if>
</head>
<body>

<jsp:invoke fragment="header"/>

<div class="container">
    <div class="row">
        <div class="col-sm-4 sidebar">
            <jsp:invoke fragment="menu"/>
        </div>

        <div class="col-sm-8 main">
            <jsp:doBody/>
        </div>
    </div>
</div>

<footer>
        <ul class="dropdown-menu">
            <li><a href="/controller?action=user-tmp">LOGIN AS USER!</a></li>
            <li><a href="/controller?action=admin-tmp">LOGIN AS ADMIN!</a></li>
        </ul>
</footer>


<script type='text/javascript' src='${prefix}/webjars/jquery/1.11.1/jquery.min.js'></script>
<script type='text/javascript' src='${prefix}/webjars/bootstrap/3.3.6/js/bootstrap.min.js'></script>
<script type='text/javascript' src='${prefix}/webjars/jquery-mask-plugin/1.13.4/dist/jquery.mask.min.js'></script>
<script type='text/javascript' src='${prefix}/js/mask.js'></script>
<c:if test="${not empty jsitems}">
    <c:forEach items="${jsitems}" var="js">
        <script type='text/javascript' src='${prefix}${js}'></script>
    </c:forEach>
</c:if>
</body>
</html>