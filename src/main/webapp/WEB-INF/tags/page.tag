<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@tag %>
<%@attribute name="title" type="java.lang.String" required="true" %>
<%--<c:if test="${lang == 'ru'}"><fmt:setLocale value='ru' scope="session"/></c:if>--%>
<%--<c:if test="${lang == 'en'}"><fmt:setLocale value='en' scope="session"/></c:if>--%>
<fmt:setBundle basename="messages/messages"/>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="/img/favicon.ico">

    <title>${title}</title>

    <!-- Bootstrap core CSS -->
    <link href="/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>

<a:nav/>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <a:left-menu/>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <jsp:doBody/>
        </div>
    </div>
</div>

<script type='text/javascript' src='/webjars/jquery/1.11.1/jquery.min.js'></script>
<script type='text/javascript' src='/webjars/bootstrap/3.3.6/js/bootstrap.min.js'></script>
</body>
</html>