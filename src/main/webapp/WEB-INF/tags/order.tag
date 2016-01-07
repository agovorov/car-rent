<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@tag %>
<%@attribute name="title" type="java.lang.String" required="true" %>
<%@ attribute name="cssitems" %>
<%@ attribute name="jsitems" %>
<%--<c:if test="${lang == 'ru'}"><fmt:setLocale value='ru' scope="session"/></c:if>--%>
<%--<c:if test="${lang == 'en'}"><fmt:setLocale value='en' scope="session"/></c:if>--%>
<fmt:setBundle basename="messages/messages"/>
<c:set var="prefix" value="${pageContext.request.contextPath}"/>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="${prefix}/img/favicon.ico">

    <title>${title}</title>
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,300,500&subset=latin,cyrillic' rel='stylesheet' type='text/css'>

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
<body> KILLED!!
<a:nav/>
<div class="container">
    <div class="nav-steps row">
        <div class="col-xs-4 step"><a href="#"><fmt:message key="order.step1"/></a></div>
        <div class="col-xs-4 step"><a href="#"><fmt:message key="order.step2"/></a></div>
        <div class="col-xs-4 step"><a href="#"><fmt:message key="order.step3"/></a></div>
    </div>

    <div class="col-sm-3 col-md-2 col-sm-offset-1- blog-sidebar">
        LEFT BLOCK
    </div>

    <div class="col-sm-8 col-sm-offset-3 col-md-10 col-md-offset-2 blog-main">
        <jsp:doBody/>
    </div>
</div>
<%--
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            MENU
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <jsp:doBody/>
        </div>
    </div>
</div>--%>

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