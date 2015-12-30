<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="dict.vehicle.pageheader" var="pageHeader"/>
<a:admin_page title="${pageHeader}">
    <a:breadcrumbs items="${breadcrumbItems}"/>
    <a:alert/>

    <c:if test="${not empty orders}">
        <table class="table table-striped">
            <tr>
                <th><fmt:message key="order.confirm.table.name"/></th>
                <th><fmt:message key="order.confirm.table.order.date"/></th>
                <th><fmt:message key="order.confirm.table.rent.dates"/></th>
                <th><fmt:message key="order.confirm.table.days"/></th>
                <th><fmt:message key="order.confirm.table.action"/></th>
            </tr>
            <c:forEach items="${orders}" var="order" varStatus="s">
                <fmt:formatDate var="orderDate" type="date" pattern="dd.MM.yyyy" value="${order.getDateOfOrder()}"/>
                <fmt:formatDate var="orderStartDate" type="date" pattern="dd.MM.yyyy" value="${order.getStartDate()}"/>
                <fmt:formatDate var="orderEndDate" type="date" pattern="dd.MM.yyyy" value="${order.getEndDate()}"/>
                <tr class="row-${s.count % 2}">
                    <td>Заказ №${order.getId()}</td>
                    <td>${orderDate}</td>
                    <td>${orderStartDate} - ${orderEndDate}</td>
                    <td>${order.countDays()}</td>
                    <td><a href="/controller?action=order-confirm-detail&id=${order.getId()}"><fmt:message
                            key="order.confirm.detail"/></a></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <c:if test="${empty orders}">
        <div class="alert alert-info" role="alert"><fmt:message key="order.confirm.table.nodata"/></div>
    </c:if>
</a:admin_page>