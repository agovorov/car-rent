<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setBundle basename="messages/messages"/>
<fmt:message key="user.pageheader" var="pageHeader"/>
<fmt:message key="user.form.lastname.placeholder" var="lastnamePlaceholder"/>
<fmt:message key="user.form.firstname.placeholder" var="firstnamePlaceholder"/>
<fmt:message key="user.form.email.placeholder" var="emailPlaceholder"/>
<fmt:message key="user.form.phone.placeholder" var="phonePlaceholder"/>

<c:set var="lastname" value="${not empty param.lastname ? param.lastname : user.getLastName() }"/>
<c:set var="firstname" value="${not empty param.firstname ? param.firstname : user.getFirstName() }"/>
<c:set var="email" value="${not empty param.email ? param.email : user.getEmail() }"/>
<c:set var="phone" value="${not empty param.phone ? param.phone : user.getPhone() }"/>
<c:set var="role_id" value="${not empty param.role ? param.role : user.getRoleId() }"/>

<a:page title="Main">
    <a:breadcrumbs items="${breadcrumbItems}"/>
    <a:alert/>

    <div class="row">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="fLastname"><fmt:message key="user.form.lastname"/></label>
                <input type="text" class="form-control" id="fLastname" name="lastname" maxlength="50"
                       placeholder="${lastnamePlaceholder}" value="${lastname}">
            </div>

            <div class="form-group">
                <label for="fFirstname"><fmt:message key="user.form.firstname"/></label>
                <input type="text" class="form-control" id="fFirstname" name="firstname"
                       placeholder="${firstnamePlaceholder}" value="${firstname}">
            </div>

            <div class="form-group">
                <label for="fEmail"><fmt:message key="user.form.email"/></label>
                <input type="text" class="form-control" id="fEmail" name="email" maxlength="50"
                       placeholder="${emailPlaceholder}" value="${email}">
            </div>

            <div class="form-group">
                <label for="fPhone"><fmt:message key="user.form.phone"/></label>
                <input type="text" class="form-control phone" id="fPhone" name="phone"
                       placeholder="${phonePlaceholder}" value="${phone}">
            </div>

            <div class="form-group">
                <label for="fRole"><fmt:message key="user.form.role"/></label>
                <select class="form-control" id="fRole" name="role">
                    <c:forEach items="${roles}" var="role">
                        <option ${role.getId() == role_id ? 'selected' : ''} value="${role.getId()}">${role.getValue()}</option>
                    </c:forEach>
                </select>
            </div>

            <button class="btn btn-default" type="submit"><fmt:message key="common.button.save"/></button>
        </form>
    </div>
</a:page>