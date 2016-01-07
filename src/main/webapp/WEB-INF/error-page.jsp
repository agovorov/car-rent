<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="error.title" var="title"/>

<c:set var="prefix" value="${pageContext.request.contextPath}"/>

<a:genericpage title="${title}">
    <jsp:attribute name="header"><a:nav/></jsp:attribute>
    <jsp:attribute name="menu">
        <h2 class="form-register-heading"><fmt:message key="error.header"/></h2>
    </jsp:attribute>
    <jsp:body>
        Error code: ${pageContext.errorData.statusCode}<br>
        Message: ${pageContext.exception.message}<br>
        Exception: ${pageContext.exception}<br>
        <c:forEach var="trace" items="${pageContext.exception.stackTrace}">
            <p>${trace}</p>
        </c:forEach>
    </jsp:body>
</a:genericpage>