<%@tag %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ attribute name="vehicle" type="com.epam.ag.model.Vehicle" required="true" %>
<%@ attribute name="order" type="com.epam.ag.model.Order" %>
<fmt:setBundle basename="messages/messages"/>

<c:set var="consumption" value="${vehicle.getConsumption()}"/>
<%--<c:set var="price" value="${vehicle.getPrice()}"/>--%>
<%--<c:if test="${not empty price}">--%>
    <%--<fmt:formatNumber var="price" value="${price}" groupingUsed=" " maxFractionDigits="0"/>--%>
<%--</c:if>--%>

<fmt:message key="common.currency.short" var="currency_label"/>
<fmt:formatNumber var="price" type="currency" currencySymbol="" value="${vehicle.getPrice()}" maxFractionDigits="0"/>
<fmt:formatNumber var="total_price" type="currency" currencySymbol="" maxFractionDigits="0"
                  value="${vehicle.getPrice() * order.countDays()}"/>

<%--<c:set var="img" value="/img/img.png"/>--%>
<%--<c:set var="img_id" value="${vehicle.getGalleryId()>0?vehicle.getGalleryId():}"--%>
<%--<c:set var="img" value="${vehicle.getGalleryId()>0?'/i?id='+vehicle.getGalleryId() : '/img/img.png'}" />--%>
<%--<c:set var="img" value="${vehicle.getGalleryId() > 0 ? '/i?id='+vehicle.getGalleryId() : '/img/img.png'}" />--%>

<c:set var="img" value="/img/img.png"/>
<c:if test="${vehicle.getGalleryId() > 0}">
    <c:set var="img" value="/i?id=${vehicle.getGalleryId()}&i=1"/>
</c:if>

<div class="vehicle-block shadow_block ">
    <h2>${vehicle.getFullName()}</h2>
    <img src="/img/px.png" style="background-image: url(${img});" width="204" height="101" />
    <div class="price-a-day">
        <span class="money">${price}</span>
        <span class="currency-class"><fmt:message key="order.tenge.a.day"/></span>
    </div>

    <div class="vehicle-options row">
        <div class="col-xs-4 option-block gearshift">
            <div class="option-label">${vehicle.getVehicleGearShift().getValue()}</div>
        </div>
        <div class="col-xs-4 option-block">
            ${vehicle.getVolume()}
            <div class="option-label"><fmt:message key="order.vehicle.volume"/></div>
        </div>
        <div class="col-xs-4 option-block">
            ${consumption}
            <div class="option-label"><fmt:message key="order.vehicle.consump"/></div>
        </div>
    </div>

    <div class="bottom-block row">
        <div class="col-xs-6 total-price-class">${total_price}</div>
        <div class="col-xs-6 order-block"><a href="/controller?action=order-verify&id=${vehicle.getId()}"><fmt:message
                key="order.vehicle.choose"/></a></div>
    </div>
</div>