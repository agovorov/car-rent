<%@ tag import="com.epam.ag.model.Order" %>
<%@tag %>
<%@attribute name="entity" type="com.epam.ag.model.Order" required="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="ORDER_VEHICLE_RETURNED" value="<%=Order.OrderStatus.VEHICLE_TAKEN%>"/>
<c:set var="ORDER_DAMAGED" value="<%=Order.OrderStatus.DAMAGED%>"/>

<fmt:setBundle basename="messages/messages"/>
<fmt:message key="common.currency.short" var="currency_label"/>

<fmt:formatDate var="orderDate" type="date" pattern="dd.MM.yyyy" value="${order.getDateOfOrder()}"/>
<fmt:formatDate var="orderStartDate" type="date" pattern="dd.MM.yyyy" value="${order.getStartDate()}"/>
<fmt:formatDate var="orderEndDate" type="date" pattern="dd.MM.yyyy" value="${order.getEndDate()}"/>

<fmt:formatNumber var="price_day" type="currency" currencySymbol="${currency_label}"
                  value="${order.getVehicle().getPrice()}"/>
<fmt:formatNumber var="total_price" type="currency" currencySymbol="${currency_label}"
                  value="${order.getVehicle().getPrice() * order.countDays()}"/>

<fmt:formatNumber var="damage_price" type="currency" currencySymbol="${currency_label}"
                  value="${order.getDamagePrice()}"/>

<fmt:message key="order.status.${order.getStatus()}" var="ORDER_PAYED"/>

<div class="row">
    <div class="col-md-6">
        <h3><fmt:message key="order.confirm.order.info"/></h3>
        <div class="form-group">
            <label><fmt:message key="order.confirm.table.order.date"/>:</label>
            <spam class="sd">${orderDate}</spam>
        </div>

        <div class="form-group">
            <label><fmt:message key="order.confirm.table.rent.dates"/>:</label>
            <spam class="sd">${orderStartDate} - ${orderEndDate}</spam>
        </div>

        <div class="form-group">
            <label><fmt:message key="order.confirm.table.days"/>:</label>
            <spam class="sd">${order.countDays()}</spam>
        </div>

        <div class="form-group">
            <label><fmt:message key="order.confirm.price.a.day"/>:</label>
            <spam class="sd">${price_day}</spam>
        </div>

        <div class="form-group">
            <label><fmt:message key="order.confirm.price.total"/>:</label>
            <spam class="sd">${total_price}</spam>
        </div>

        <div class="form-group">
            <label><fmt:message key="order.confirm.status"/>:</label>
            <spam class="sd">${ORDER_PAYED}</spam>
        </div>

        <c:if test="${order.getStatus() == ORDER_DAMAGED}">
            <hr>
            <div class="form-group">
                <label><fmt:message key="order.damage.note"/>:</label>
                <spam class="sd">${order.getDamageNote()}</spam>
            </div>

            <div class="form-group">
                <label><fmt:message key="order.damage.price"/>:</label>
                <spam class="sd">${damage_price}</spam>
            </div>
        </c:if>
    </div>

    <div class="col-md-6">
        <h3><fmt:message key="order.confirm.customer.info"/></h3>
        <div class="form-group">
            <label><fmt:message key="order.confirm.client.name"/>:</label>
            <spam class="sd">${order.getCustomer().getFullName()}</spam>
        </div>

        <div class="form-group">
            <label><fmt:message key="order.confirm.client.phone"/>:</label>
            <spam class="sd">${order.getCustomer().getPhone()}</spam>
        </div>

        <h3><fmt:message key="order.confirm.vehicle.info"/></h3>
        <div class="form-group">
            <label><fmt:message key="order.confirm.vehicle.name"/>:</label>
            <spam class="sd">${order.getVehicle().getFullName()}, ${order.getVehicle().getYear()} </spam>
        </div>
    </div>
    <span class="text-muted">A:orderDisplay</span>
</div>
