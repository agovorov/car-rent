<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="auth.pageheader" var="pageHeader"/>
<fmt:message key="auth.login.placeholder" var="loginPlaceholder"/>
<fmt:message key="auth.passwd.placeholder" var="passwdPlaceholder"/>

<c:set var="url" value="${pageContext.request.contextPath}"/>
<c:set var="login" value="${not empty param.login ? param.login : '' }"/>

<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="${url}/img/favicon.ico">

    <title>${pageHeader}</title>

    <!-- Bootstrap core CSS -->
    <link href="${url}/webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link href="${url}/css/auth.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <a:alert/>
        <form class="form-signin" method="post">
            <h2 class="form-signin-heading"><fmt:message key="auth.header"/></h2>
            <label for="inputEmail" class="sr-only"><fmt:message key="auth.login"/></label>
            <input type="email" name="login" id="inputEmail" class="form-control" placeholder="${loginPlaceholder}" required autofocus value="${login}">
            <label for="inputPassword" class="sr-only"><fmt:message key="auth.password"/></label>
            <input type="password" name="passwd" id="inputPassword" class="form-control" placeholder="${passwdPlaceholder}" required>
            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="auth.button.login"/></button>
        </form>

    </div> <!-- /container -->
</body>
