<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>

<fmt:setBundle basename="messages/messages"/>
<fmt:message key="profile.pageheader" var="pageHeader"/>

<fmt:message key="profile.personal.email.placeholder" var="email_placeholder"/>
<fmt:message key="profile.personal.firstname.placeholder" var="first_name_placeholder"/>
<fmt:message key="profile.personal.lastname.placeholder" var="last_name_placeholder"/>
<fmt:message key="profile.personal.phone.placeholder" var="phone_placeholder"/>

<fmt:message key="order.address.country.placeholder" var="adr_country_placeholder"/>
<fmt:message key="order.address.city.placeholder" var="adr_city_placeholder"/>
<fmt:message key="order.address.street.placeholder" var="adr_street_placeholder"/>
<fmt:message key="order.address.streetnumber.placeholder" var="adr_street_number_placeholder"/>
<fmt:message key="order.address.flat.placeholder" var="adr_flat_placeholder"/>

<fmt:message key="order.passport.series.placeholder" var="p_series_placeholder"/>
<fmt:message key="order.passport.number.placeholder" var="p_number_placeholder"/>
<fmt:message key="order.passport.issuedate.placeholder" var="p_issue_placeholder"/>
<fmt:message key="order.passport.expiredate.placeholder" var="p_expire_placeholder"/>
<fmt:message key="order.passport.issueplace.placeholder" var="p_place_placeholder"/>

<c:set var="adr_country"
       value="${not empty param.adr_country ? param.adr_country : user.getPassport().getLivingAddress().getCountry() }"/>
<c:set var="adr_city"
       value="${not empty param.adr_city ? param.adr_city : user.getPassport().getLivingAddress().getCity() }"/>
<c:set var="adr_street"
       value="${not empty param.adr_street ? param.adr_street : user.getPassport().getLivingAddress().getStreet() }"/>
<c:set var="adr_street_number"
       value="${not empty param.adr_street_number ? param.adr_street_number : user.getPassport().getLivingAddress().getStreetNumber() }"/>
<c:set var="adr_flat"
       value="${not empty param.adr_flat ? param.adr_flat : user.getPassport().getLivingAddress().getAppartmentNumber() }"/>

<c:set var="pass_series" value="${not empty param.pass_series ? param.pass_series : user.getPassport().getSeries() }"/>
<c:set var="pass_number"
       value="${not empty param.pass_number ? param.pass_number : user.getPassport().getDocumentNumber() }"/>
<c:set var="pass_issue" value="${not empty param.pass_issue ? param.pass_issue : user.getPassport().getIssueDate() }"/>
<c:set var="pass_expire"
       value="${not empty param.pass_expire ? param.pass_expire : user.getPassport().getExpirationDate() }"/>
<c:set var="pass_place" value="${not empty param.pass_place ? param.pass_place : user.getPassport().getIssuePlace() }"/>


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


        <h3><fmt:message key="profile.address.header"/>:</h3>
        <div class="form-group">
            <label for="adrCountry"><fmt:message key="order.address.country"/></label>
            <input type="text" name="adr_country" id="adrCountry" value="${adr_country}" class="form-control"
                   placeholder="${adr_country_placeholder}">
        </div>
        <div class="form-group">
            <label for="adrCity"><fmt:message key="order.address.city"/></label>
            <input type="text" name="adr_city" id="adrCity" value="${adr_city}" class="form-control"
                   placeholder="${adr_city_placeholder}">
        </div>

        <div class="form-group">
            <label for="adrStreet"><fmt:message key="order.address.street"/></label>
            <input type="text" name="adr_street" id="adrStreet" value="${adr_street}" class="form-control"
                   placeholder="${adr_street_placeholder}">
        </div>

        <div class="form-group">
            <label for="adrStreetNumber"><fmt:message key="order.address.street.number"/></label>
            <input type="text" name="adr_street_number" id="adrStreetNumber" value="${adr_street_number}"
                   class="form-control" placeholder="${adr_street_number_placeholder}">
        </div>

        <div class="form-group">
            <label for="adrFlat"><fmt:message key="order.address.flat"/></label>
            <input type="text" name="adr_flat" id="adrFlat" value="${adr_flat}" class="form-control"
                   placeholder="${adr_flat_placeholder}">
        </div>


        <h3><fmt:message key="profile.passport.header"/>:</h3>
        <div class="form-group">
            <label for="pSeries"><fmt:message key="order.passport.series"/></label>
            <input type="text" name="pass_series" id="pSeries" value="${pass_series}" class="form-control"
                   placeholder="${p_series_placeholder}">
        </div>

        <div class="form-group">
            <label for="pDocNumber"><fmt:message key="order.passport.number"/></label>
            <input type="text" name="pass_number" id="pDocNumber" value="${pass_number}" class="form-control"
                   placeholder="${p_number_placeholder}">
        </div>

        <div class="form-group">
            <label for="pDIssue"><fmt:message key="order.passport.issuedate"/></label>
            <input type="text" name="pass_issue" id="pDIssue" value="${pass_issue}" class="form-control"
                   placeholder="${p_issue_placeholder}">
        </div>

        <div class="form-group">
            <label for="pExpire"><fmt:message key="order.passport.expiredate"/></label>
            <input type="text" name="pass_expire" id="pExpire" value="${pass_expire}" class="form-control"
                   placeholder="${p_expire_placeholder}">
        </div>

        <div class="form-group">
            <label for="pIssuePlace"><fmt:message key="order.passport.issue.place"/></label>
            <input type="text" name="pass_place" id="pIssuePlace" value="${pass_place}" class="form-control"
                   placeholder="${p_place_placeholder}">
        </div>
        <button class="btn btn-primary" type="submit"><fmt:message key="common.button.save"/></button>
    </form>
</a:client_page>