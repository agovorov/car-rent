<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<c:set var="prefix" value="${pageContext.request.contextPath}"/>

<fmt:message key="confirm.date.pageheader" var="pageHeader"/>
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

<c:set var="adr_country" value="${not empty param.adr_country ? param.adr_country : 'Казахстан' }"/>
<c:set var="adr_city" value="${not empty param.adr_city ? param.adr_city : 'Караганда' }"/>
<c:set var="adr_street" value="${not empty param.adr_street ? param.adr_street : 'Бухар Жырау' }"/>
<c:set var="adr_street_number" value="${not empty param.adr_street_number ? param.adr_street_number : '50' }"/>
<c:set var="adr_flat" value="${not empty param.adr_flat ? param.adr_flat : '12' }"/>

<c:set var="pass_series" value="${not empty param.pass_series ? param.pass_series : 'MA' }"/>
<c:set var="pass_number" value="${not empty param.pass_number ? param.pass_number : '01234567890123' }"/>
<c:set var="pass_issue" value="${not empty param.pass_issue ? param.pass_issue : '20.12.2010' }"/>
<c:set var="pass_expire" value="${not empty param.pass_expire ? param.pass_expire : '20.12.2035' }"/>
<c:set var="pass_place" value="${not empty param.pass_place ? param.pass_place : 'МЮ РК' }"/>

<c:set var="consumption" value="${order.getVehicle().getConsumption()}"/>
<c:set var="price_day" value="${order.getVehicle().getPrice()}"/>
<c:set var="price_total" value="${price_day * order.countDays() }"/>
<%--<c:if test="${not empty price}">--%>
<%--<fmt:formatNumber var="price" value="${price}" groupingUsed=" " maxFractionDigits="0"/>--%>
<%--</c:if>--%>
<%--<fmt:formatNumber var="total_price" value="${price * order.countDays()}" groupingUsed=" " maxFractionDigits="2"/>--%>
<c:set var="date_start" value="${DateConverter.formatDate(order.getStartDate()) }"/>
<c:set var="date_end" value="${DateConverter.formatDate(order.getEndDate()) }"/>

<a:order title="${pageHeader}" jsitems="/js/order.js">
    <a:alert/>
    <div class="vehicle-big-block">
        <h1>${order.getVehicle().getFullName()}</h1>

        <div class="row">
            <div class="col-xs-8">
                <img src="#" width="550" height="300">
            </div>
            <div class="col-xs-4">
                <div class="vehicle-options row-">
                    <div class="col-xs-4- option-block">
                        <div class="option-label">${order.getVehicle().getVehicleGearShift().getValue()}</div>
                    </div>
                    <div class="col-xs-4- option-block">
                            ${order.getVehicle().getVolume()}
                        <div class="option-label"><fmt:message key="order.vehicle.volume"/></div>
                    </div>
                    <div class="col-xs-4- option-block">
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
        <div class="price-info-row row">
            <div class="col-xs-8 labels"><fmt:message key="order.price.days"/></div>
            <div class="col-xs-4 values2">${order.countDays()} (${date_start} - ${date_end})
            </div>
        </div>
    </div>

    <div class="passport_user_info">
        <div class="header"><fmt:message key="order.personal.info"/></div>

        <label><fmt:message key="order.personal.fio"/>: </label>
        <label>${order.getCustomer().getFullName()}</label>

        <form method="post">
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


            <h3>Passport data:</h3>
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
            <button class="btn btn-primary" type="submit"><fmt:message key="order.button.confirm"/></button>
        </form>
    </div>
</a:order>
<script type='text/javascript' src='${prefix}/js/order.js'></script>
