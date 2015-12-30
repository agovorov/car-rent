<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="auth.pageheader" var="pageHeader"/>
<fmt:message key="auth.login.placeholder" var="loginPlaceholder"/>
<fmt:message key="auth.passwd.placeholder" var="passwdPlaceholder"/>
<fmt:message key="login.title" var="title"/>

<c:set var="prefix" value="${pageContext.request.contextPath}"/>
<c:set var="login" value="${not empty param.login ? param.login : '' }"/>

<a:genericpage title="${title}" jsitems="${jsitems}" cssitems="${prefix}/css/auth.css">
    <jsp:attribute name="header"><a:nav/></jsp:attribute>
    <jsp:attribute name="menu">
        <h2 class="form-register-heading"><fmt:message key="auth.header"/></h2>
    </jsp:attribute>
    <jsp:body>
        <a:alert/>
        <form class="form-register" method="post">
            <label for="inputEmail" class="sr-only"><fmt:message key="auth.login"/></label>
            <input type="email" name="login" id="inputEmail" class="form-control" placeholder="${loginPlaceholder}" required autofocus value="${login}">
            <label for="inputPassword" class="sr-only"><fmt:message key="auth.password"/></label>
            <input type="password" name="passwd" id="inputPassword" class="form-control" placeholder="${passwdPlaceholder}" required>
            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="auth.button.login"/></button>
        </form>
    </jsp:body>
</a:genericpage>