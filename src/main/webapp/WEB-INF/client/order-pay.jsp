<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="client.order.page.pay" var="pageHeader"/>

<c:set var="card_number" value="${not empty param.card_number ? param.card_number : '' }"/>
<c:set var="card_month" value="${not empty param.card_month ? param.card_month : '' }"/>
<c:set var="card_year" value="${not empty param.card_year ? param.card_year : '' }"/>
<c:set var="card_holder" value="${not empty param.card_holder ? param.card_holder : '' }"/>

<fmt:message key="common.currency.short" var="currency_label"/>
<fmt:formatNumber var="price" type="currency" currencySymbol="${currency_label}"
                  value="${price}"/>

<a:client_page title="${pageHeader}">
    <h2><fmt:message key="order.pay.price"/>: ${price}</h2>
    <form method="post">
        <div class="form-group">
            <label for="cardNumber"><fmt:message key="order.pay.card.number"/></label>
            <input type="text" name="card_number" id="cardNumber" value="${card_number}"
                   class="form-control card-number"
                   placeholder="<fmt:message key="order.pay.card.number.placeholder"/>">
        </div>
        <div class="form-group">
            <label for="cardValidMonth"><fmt:message key="order.pay.card.month"/></label>
            <input type="text" name="card_month" id="cardValidMonth" value="${card_month}" class="form-control month"
                   placeholder="<fmt:message key="order.pay.card.month.placeholder"/>">
        </div>

        <div class="form-group">
            <label for="cardValidYear"><fmt:message key="order.pay.card.year"/></label>
            <input type="text" name="card_year" id="cardValidYear" value="${card_year}" class="form-control year"
                   placeholder="<fmt:message key="order.pay.card.year.placeholder"/>">
        </div>

        <div class="form-group">
            <label for="cardHolder"><fmt:message key="order.card.holder"/></label>
            <input type="text" name="card_holder" id="cardHolder" value="${card_holder}"
                   class="form-control card-holder"
                   placeholder="<fmt:message key="order.pay.card.holder.placeholder"/>">
        </div>

        <div class="form-group">
            <label for="cardCV"><fmt:message key="order.card.cv"/></label>
            <input type="text" name="card_cv" id="cardCV" value="${card_cv}" class="form-control int-4"
                   placeholder="<fmt:message key="order.pay.card.cv.placeholder"/>">
        </div>

        <button class="btn btn-primary" type="submit"><fmt:message key="order.button.pay"/></button>
    </form>
</a:client_page>