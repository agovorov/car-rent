<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag %>
<%@attribute name="title" type="java.lang.String" required="true" %>
<c:if test="${lang == 'ru'}"><fmt:setLocale value='ru' scope="session"/></c:if>
<c:if test="${lang == 'en'}"><fmt:setLocale value='en' scope="session"/></c:if>
<fmt:setBundle basename="messages/messages"/>
<html>
<head>
    <title>${title}</title>
    <%--<link rel='stylesheet' href='webjars/bootstrap/3.3.6/css/bootstrap.min.css'>--%>
    <%--<link rel='stylesheet' href='webjars/bootstrap/3.3.6/css/bootstrap.min.css'>--%>
    <%--<script type='text/javascript' src='webjars/jquery/1.11.1/jquery.min.js'></script>--%>
    <%--<script type='text/javascript' src='webjars/bootstrap/3.3.6/js/bootstrap.min.js'></script>--%>

    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
</head>
<body>
    <jsp:doBody/>
</body>
</html>
