<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>

<fmt:message key="order.status.${order.getStatus()}" var="ORDER_PAYED"/>

<fmt:message key="user.pageheader" var="pageHeader"/>
<fmt:message key="user.form.firstname.placeholder" var="firstnamePlaceholder"/>
<fmt:message key="common.currency.short" var="currency_label"/>

<fmt:formatDate var="orderDate" type="date" pattern="dd.MM.yyyy" value="${order.getDateOfOrder()}"/>
<fmt:formatDate var="orderStartDate" type="date" pattern="dd.MM.yyyy" value="${order.getStartDate()}"/>
<fmt:formatDate var="orderEndDate" type="date" pattern="dd.MM.yyyy" value="${order.getEndDate()}"/>

<fmt:formatNumber var="price_day" type="currency" currencySymbol="${currency_label}"
                  value="${order.getVehicle().getPrice()}"/>
<fmt:formatNumber var="total_price" type="currency" currencySymbol="${currency_label}"
                  value="${order.getVehicle().getPrice() * order.countDays()}"/>

<a:admin_page title="Main">
    <a:breadcrumbs items="${breadcrumbItems}"/>
    <a:alert/>

    <div class="row">
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

        <form method="post" enctype="multipart/form-data">
            <div class="checkbox">
                <label>
                    <input type="checkbox" name="issued"> <fmt:message key="order.issue.vehicle.confirm"/>
                </label>
            </div>

            <button class="btn btn-default" name="btn-confirm" value="accept" type="submit"><fmt:message
                    key="order.btn.issue.vehicle"/></button>
        </form>
    </div>
</a:admin_page>