<%@ page import="com.epam.ag.model.Order" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="dict.vehicle.pageheader" var="pageHeader"/>

<c:set var="ORDER_WAITING" value="<%=Order.OrderStatus.WAITING%>"/>
<c:set var="ORDER_CONFIRMED" value="<%=Order.OrderStatus.CONFIRMED%>"/>
<c:set var="ORDER_PAYED" value="<%=Order.OrderStatus.PAYED%>"/>
<c:set var="ORDER_VEHICLE_RETURNED" value="<%=Order.OrderStatus.VEHICLE_TAKEN%>"/>

<a:admin_page title="${pageHeader}" cssitems="/css/order.css">
    <c:if test="${not empty orders}">
        <table class="table table-striped">
            <tr>
                <th><fmt:message key="order.confirm.table.nn"/></th>
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
                    <td>${s.count}</td>
                    <td>Заказ №${order.getId()}</td>
                    <td>${orderDate}</td>
                    <td>${orderStartDate} - ${orderEndDate}</td>
                    <td>${order.countDays()}</td>
                    <td class="order-status order-status-${order.getStatus().ordinal()}">${status}</td>
                    <td class="actions-href"><a
                            href="/controller?action=order-detail-adm&id=${order.getId()}"><fmt:message
                            key="order.confirm.detail"/></a>

                        <c:if test="${order.getStatus() == ORDER_WAITING}">
                            <a href="/controller?action=order-confirm-detail&id=${order.getId()}"><fmt:message
                                    key="order.confirm"/></a>
                        </c:if>

                        <%--<c:if test="${order.getStatus() == ORDER_CONFIRMED}">--%>
                            <%--<a href="/controller?action=order-pay&id=${order.getId()}"><fmt:message--%>
                                    <%--key="order.pay.for.it"/></a>--%>
                        <%--</c:if>--%>

                        <c:if test="${order.getStatus() == ORDER_PAYED}">
                            <a href="/controller?action=order-issue&id=${order.getId()}"><fmt:message
                                    key="order.give.a.car"/></a>
                        </c:if>

                        <c:if test="${order.getStatus() == ORDER_VEHICLE_RETURNED}">
                            <a href="/controller?action=order-received&id=${order.getId()}"><fmt:message
                                    key="order.vehicle.received"/></a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty orders}">
        <div class="alert alert-info" role="alert"><fmt:message key="order.confirm.table.nodata"/></div>
    </c:if>
</a:admin_page>