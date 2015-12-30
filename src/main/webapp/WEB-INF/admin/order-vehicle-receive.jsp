<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>

<fmt:message key="order.status.${order.getStatus()}" var="ORDER_PAYED"/>

<fmt:message key="user.pageheader" var="pageHeader"/>
<fmt:message key="user.form.firstname.placeholder" var="firstnamePlaceholder"/>
<fmt:message key="common.currency.short" var="currency_label"/>

<fmt:message key="damage.note.placeholder" var="damage.placeholder"/>

<fmt:formatDate var="orderDate" type="date" pattern="dd.MM.yyyy" value="${order.getDateOfOrder()}"/>
<fmt:formatDate var="orderStartDate" type="date" pattern="dd.MM.yyyy" value="${order.getStartDate()}"/>
<fmt:formatDate var="orderEndDate" type="date" pattern="dd.MM.yyyy" value="${order.getEndDate()}"/>

<fmt:formatNumber var="price_day" type="currency" currencySymbol="${currency_label}"
                  value="${order.getVehicle().getPrice()}"/>
<fmt:formatNumber var="total_price" type="currency" currencySymbol="${currency_label}"
                  value="${order.getVehicle().getPrice() * order.countDays()}"/>

<c:set var="damage_note" value="${not empty param.damage_note ? param.damage_note : '' }"/>
<c:set var="damage_cost" value="${not empty param.damage_cost ? param.damage_cost : '' }"/>
<c:set var="damage_found" value="${not empty param.damage_found ? 'checked' : '' }"/>

<a:admin_page title="Main" jsitems="/js/order.js">
    <a:orderDisplay entity="${order}"/>

    <div class="row">
        <div class="col-md-12">
            <h3><fmt:message key="order.result.info"/></h3>

            <form method="post" enctype="multipart/form-data">
                <div class="checkbox">
                    <label class="has-damage">
                        <input type="checkbox"> <fmt:message key="order.vehicle.result.has.damage"/>
                    </label>
                </div>

                <div class="damage_info ${damage_found == "checked" ? "" : "hide"}">
                    <textarea class="form-control" name="damage_note" rows="5" placeholder="${damage.placeholder}">${damage_note}</textarea>
                    <div class="form-group">
                        <label class="sr-only-" for="damageCost"><fmt:message key="order.result.damage.cost"/></label>
                        <div class="input-group col-xs-3">
                            <input type="text" class="form-control currency" id="damageCost" name="damage_cost"
                                   value="${damage_cost}">
                            <div class="input-group-addon"><fmt:message key="common.currency.shortname"/></div>
                        </div>
                    </div>
                </div>

                <button class="btn btn-default" name="btn-confirm" value="accept" type="submit"><fmt:message
                        key="common.button.save"/></button>
            </form>
        </div>
    </div>
</a:admin_page>