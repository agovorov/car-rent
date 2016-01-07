<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<c:set var="prefix" value="${pageContext.request.contextPath}"/>
<fmt:message key="confirm.date.pageheader" var="pageHeader"/>

<c:set var="consumption" value="${order.getVehicle().getConsumption()}"/>
<%--<c:set var="price_day" value="${order.getVehicle().getPrice()}"/>--%>
<%--<c:set var="price_total" value="${price_day * order.countDays() }"/>--%>

<%--<c:set var="price_day" value="${order.getVehicle().getPrice()}"/>--%>

<fmt:formatDate var="orderDateStart" type="date" pattern="dd.MM.yyyy" value="${order.getStartDate()}"/>
<fmt:formatDate var="orderDateEnd" type="date" pattern="dd.MM.yyyy" value="${order.getEndDate()}"/>

<fmt:formatNumber var="price_day" type="currency" currencySymbol="${currency_label}"
                  value="${order.getVehicle().getPrice()}"/>
<fmt:formatNumber var="price_total" type="currency" currencySymbol="${currency_label}"
                  value="${order.getVehicle().getPrice() * order.countDays()}"/>


<%--Passport data--%>
<c:set var="adr_country" value="${order.getCustomer().getPassport().getLivingAddress().getCountry()}"/>
<c:set var="adr_city" value="${order.getCustomer().getPassport().getLivingAddress().getCity()}"/>
<c:set var="adr_street" value="${order.getCustomer().getPassport().getLivingAddress().getStreet()}"/>
<c:set var="adr_street_number" value="${order.getCustomer().getPassport().getLivingAddress().getStreetNumber()}"/>
<c:set var="adr_flat" value="${order.getCustomer().getPassport().getLivingAddress().getAppartmentNumber()}"/>

<c:set var="pass_series" value="${order.getCustomer().getPassport().getSeries()}"/>
<c:set var="pass_number" value="${order.getCustomer().getPassport().getDocumentNumber()}"/>
<c:set var="pass_issue_date" value="${order.getCustomer().getPassport().getIssueDate()}"/>
<c:set var="pass_expire_date" value="${order.getCustomer().getPassport().getExpirationDate()}"/>
<c:set var="pass_place" value="${order.getCustomer().getPassport().getIssuePlace()}"/>

<fmt:formatDate var="pass_issue" type="date" pattern="dd.MM.yyyy" value="${pass_issue_date}"/>
<fmt:formatDate var="pass_expire" type="date" pattern="dd.MM.yyyy" value="${pass_expire_date}"/>

<a:two_column title="${pageHeader}"
              cssitems="/css/order.css"
              breadcrumbItems="${breadcrumbItems}">
    <jsp:attribute name="left">
        <form>
            <div class="form-group">
                <label for="dStart"><fmt:message key="order.vehicle.datestart"/></label>
                <input type="text" class="form-control" id="dStart" value="${orderDateStart}" disabled>
            </div>
            <div class="form-group">
                <label for="dEnd"><fmt:message key="order.vehicle.dateend"/></label>
                <input type="text" class="form-control" id="dEnd" value="${orderDateEnd}" disabled>
            </div>

            <div class="form-group">
                <label for="dDays"><fmt:message key="order.vehicle.days"/></label>
                <input type="text" class="form-control" id="dDays" value="${order.countDays()}" disabled>
            </div>
        </form>
    </jsp:attribute>

    <jsp:body>
        <div class="vehicle-big-block">
            <h1>${order.getVehicle().getFullName()}</h1>

            <div class="row">
                <div class="col-xs-8">
                    <img src="#" width="550" height="300">
                </div>
                <div class="col-xs-4">
                    <div class="vehicle-options row-">
                        <div class="col-xs-4- option-block-">
                            <div class="option-label">${order.getVehicle().getVehicleGearShift().getValue()}</div>
                        </div>
                        <div class="col-xs-4- option-block-">
                                ${order.getVehicle().getVolume()}
                            <div class="option-label"><fmt:message key="order.vehicle.volume"/></div>
                        </div>
                        <div class="col-xs-4- option-block-">
                                ${consumption}
                            <div class="option-label"><fmt:message key="order.vehicle.consump"/></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="order-price-info">
            <div class="price-info-row row">
                <div class="col-xs-8 labels"><fmt:message key="order.price.a.day"/></div>
                <div class="col-xs-4 values">${price_day} <fmt:message key="common.currency.shortname"/></div>
            </div>

            <div class="price-info-row row">
                <div class="col-xs-8 labels"><fmt:message key="order.price.total"/></div>
                <div class="col-xs-4 values2">${price_total} <fmt:message key="common.currency.shortname"/></div>
            </div>
        </div>

        <div class="passport_user_info">
            <h3><fmt:message key="order.personal.info"/></h3>

            <label><fmt:message key="order.personal.fio"/>: </label>
            <label>${order.getCustomer().getFullName()}</label>

            <form method="post">
                <div class="form-group">
                    <label for="adrCountry"><fmt:message key="order.address.country"/></label>
                    <input type="text" disabled id="adrCountry" value="${adr_country}"
                           class="form-control">
                </div>
                <div class="form-group">
                    <label for="adrCity"><fmt:message key="order.address.city"/></label>
                    <input type="text" disabled id="adrCity" value="${adr_city}" class="form-control">
                </div>

                <div class="form-group">
                    <label for="adrStreet"><fmt:message key="order.address.street"/></label>
                    <input type="text" disabled id="adrStreet" value="${adr_street}"
                           class="form-control">
                </div>

                <div class="form-group">
                    <label for="adrStreetNumber"><fmt:message key="order.address.street.number"/></label>
                    <input type="text" disabled id="adrStreetNumber" value="${adr_street_number}"
                           class="form-control">
                </div>

                <div class="form-group">
                    <label for="adrFlat"><fmt:message key="order.address.flat"/></label>
                    <input type="text" disabled id="adrFlat" value="${adr_flat}" class="form-control">
                </div>


                <h3><fmt:message key="order.passport.header"/>:</h3>
                <div class="form-group">
                    <label for="pSeries"><fmt:message key="order.passport.series"/></label>
                    <input type="text" disabled id="pSeries" value="${pass_series}"
                           class="form-control">
                </div>

                <div class="form-group">
                    <label for="pDocNumber"><fmt:message key="order.passport.number"/></label>
                    <input type="text" disabled id="pDocNumber" value="${pass_number}"
                           class="form-control">
                </div>

                <div class="form-group">
                    <label for="pDIssue"><fmt:message key="order.passport.issuedate"/></label>
                    <input type="text" disabled id="pDIssue" value="${pass_issue}" class="form-control">
                </div>

                <div class="form-group">
                    <label for="pExpire"><fmt:message key="order.passport.expiredate"/></label>
                    <input type="text" disabled id="pExpire" value="${pass_expire}"
                           class="form-control">
                </div>

                <div class="form-group">
                    <label for="pIssuePlace"><fmt:message key="order.passport.issue.place"/></label>
                    <input type="text" disabled id="pIssuePlace" value="${pass_place}"
                           class="form-control">
                </div>
                <button class="btn btn-primary" type="submit"><fmt:message key="order.button.confirm"/></button>
            </form>
        </div>
    </jsp:body>
</a:two_column>
