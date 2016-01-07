<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="profile.pageheader" var="pageHeader"/>

<fmt:message key="profile.personal.email.placeholder" var="email_placeholder"/>
<fmt:message key="profile.personal.firstname.placeholder" var="first_name_placeholder"/>
<fmt:message key="profile.personal.lastname.placeholder" var="last_name_placeholder"/>
<fmt:message key="profile.personal.phone.placeholder" var="phone_placeholder"/>

<c:set var="email" value="${not empty param.email ? param.email : user.getEmail() }"/>
<c:set var="firstname" value="${not empty param.firstname ? param.firstname : user.getFirstName() }"/>
<c:set var="lastname" value="${not empty param.lastname ? param.lastname : user.getLastName() }"/>
<c:set var="phone" value="${not empty param.phone ? param.phone : user.getPhone() }"/>

<a:client_page title="${pageHeader}" breadcrumbItems="${breadcrumbItems}">
    <form method="post">
        <h3><fmt:message key="profile.personal.header"/>:</h3>
        <div class="form-group">
            <label for="email"><fmt:message key="order.personal.email"/></label>
            <input type="text" name="email" id="email" value="${email}" class="form-control"
                   placeholder="${email_placeholder}">
        </div>
        <div class="form-group">
            <label for="firstname"><fmt:message key="order.personal.firstname"/></label>
            <input type="text" name="firstname" id="firstname" value="${firstname}" class="form-control"
                   placeholder="${first_name_placeholder}">
        </div>

        <div class="form-group">
            <label for="lastname"><fmt:message key="order.personal.lastname"/></label>
            <input type="text" name="lastname" id="lastname" value="${lastname}" class="form-control"
                   placeholder="${last_name_placeholder}">
        </div>

        <div class="form-group">
            <label for="phone"><fmt:message key="order.personal.phone"/></label>
            <input type="text" name="phone" id="phone" value="${phone}" class="form-control phone"
                   placeholder="${phone_placeholder}">
        </div>


        <button class="btn btn-primary" type="submit"><fmt:message key="common.button.save"/></button>
    </form>
</a:client_page>