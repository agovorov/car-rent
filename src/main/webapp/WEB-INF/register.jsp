<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="register.pageheader" var="pageHeader"/>
<fmt:message key="register.email.placeholder" var="emailPlaceholder"/>
<fmt:message key="register.passwd.placeholder" var="passwdPlaceholder"/>
<fmt:message key="register.firstname.placeholder" var="firstnamePlaceholder"/>
<fmt:message key="register.lastname.placeholder" var="lastnamePlaceholder"/>
<fmt:message key="register.phone.placeholder" var="phonePlaceholder"/>

<fmt:message key="register.title" var="title"/>

<c:set var="prefix" value="${pageContext.request.contextPath}"/>
<c:set var="email" value="${not empty param.email ? param.email : '' }"/>
<c:set var="passwd" value="${not empty param.passwd ? param.passwd : '' }"/>
<c:set var="firstname" value="${not empty param.firstname ? param.firstname : '' }"/>
<c:set var="lastname" value="${not empty param.lastname ? param.lastname : '' }"/>
<c:set var="phone" value="${not empty param.phone ? param.phone : '' }"/>

<a:genericpage title="${title}" jsitems="${jsitems}" cssitems="${prefix}/css/auth.css">
    <jsp:attribute name="header"><a:nav/></jsp:attribute>
    <jsp:attribute name="menu">
        <h2 class="form-register-heading"><fmt:message key="register.pageheader"/></h2>
    </jsp:attribute>
    <jsp:body>
        <a:alert/>
        <form class="form-register" method="post">
            <input type="email" name="email" id="inputEmail" class="form-control" placeholder="${emailPlaceholder}"
                   required1 autofocus value="${email}">
            <input type="password" name="passwd" id="inputPassword" class="form-control"
                   placeholder="${passwdPlaceholder}"
                   required1 value="${passwd}">
            <input type="text" name="firstname" id="inputFirstname" class="form-control"
                   placeholder="${firstnamePlaceholder}" required1 value="${firstname}">
            <input type="text" name="lastname" id="inputLastname" class="form-control"
                   placeholder="${lastnamePlaceholder}"
                   required1 value="${lastname}">
            <input type="text" name="phone" id="inputPhone" class="form-control phone" placeholder="${phonePlaceholder}"
                   required1
                   value="${phone}">

            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message
                    key="register.button.register"/></button>
        </form>
    </jsp:body>
</a:genericpage>