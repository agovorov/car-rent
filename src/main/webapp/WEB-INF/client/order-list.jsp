<%@ page import="com.epam.ag.model.Order" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="dict.vehicle.pageheader" var="pageHeader"/>
<c:set var="ORDER_CONFIRMED" value="<%=Order.OrderStatus.CONFIRMED%>"/>
<c:set var="ORDER_DAMAGED" value="<%=Order.OrderStatus.DAMAGED%>"/>

<a:client_page title="${pageHeader}">
    <c:if test="${not empty orders}">
        <table class="table table-striped">
            <tr>
                <th><fmt:message key="order.confirm.table.name"/></th>
                <th><fmt:message key="order.confirm.table.order.date"/></th>
                <th><fmt:message key="order.confirm.table.rent.dates"/></th>
                <th><fmt:message key="order.confirm.table.days"/></th>
                <th><fmt:message key="order.confirm.table.status"/></th>
                <th><fmt:message key="order.confirm.table.action"/></th>
            </tr>
            <c:forEach items="${orders}" var="order" varStatus="s">
                <fmt:formatDate var="orderDate" type="date" pattern="dd.MM.yyyy" value="${order.getDateOfOrder()}"/>
                <fmt:formatDate var="orderStartDate" type="date" pattern="dd.MM.yyyy" value="${order.getStartDate()}"/>
                <fmt:formatDate var="orderEndDate" type="date" pattern="dd.MM.yyyy" value="${order.getEndDate()}"/>

                <fmt:message key="order.status.${order.getStatus()}" var="status"/>

                <tr class="row-${s.count % 2}">
                    <td>Заказ №${order.getId()}</td>
                    <td>${orderDate}</td>
                    <td>${orderStartDate} - ${orderEndDate}</td>
                    <td>${order.countDays()}</td>
                    <td class="order-status order-status-${order.getStatus().ordinal()}">${status}</td>
                    <td><a href="/controller?action=order-detail&id=${order.getId()}"><fmt:message
                            key="order.confirm.detail"/></a>

                        <c:if test="${order.getStatus() == ORDER_CONFIRMED}">
                            <a href="/controller?action=order-pay&id=${order.getId()}"><fmt:message
                                key="order.pay.for.it"/></a>
                        </c:if>

                        <c:if test="${order.getStatus() == ORDER_DAMAGED}">
                            <a href="/controller?action=order-refund&id=${order.getId()}"><fmt:message
                                    key="order.refund.for.it"/></a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty orders}">
        <div class="alert alert-info" role="alert"><fmt:message key="order.confirm.table.nodata"/></div>
    </c:if>
</a:client_page>