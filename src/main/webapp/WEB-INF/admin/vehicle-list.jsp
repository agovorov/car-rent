<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="dict.vehicle.pageheader" var="pageHeader"/>
<a:page title="${pageHeader}">
    <a:breadcrumbs items="${breadcrumbItems}"/>
    <a:alert/>

    <table class="table table-striped">
        <tr>
            <th><fmt:message key="vehicle.table.title"/></th>
            <th><fmt:message key="vehicle.table.price"/></th>
            <th colspan="2"><fmt:message key="vehicle.table.action"/></th>
        </tr>
        <c:forEach items="${vehicles}" var="vehicle" varStatus="s">
            <fmt:formatNumber var="price" type="currency" value="${vehicle.getPrice()}" />
            <tr class="row-${s.count % 2}">
                <td>${vehicle.getManufacturer().getValue()} ${vehicle.getModel()}, ${vehicle.getYear()}</td>
                <td>${price}</td>
                <td><a href="/controller?action=vehicle-update&id=${vehicle.getId()}"><fmt:message key="dict.action.update"/></a></td>
                <td><a href="/controller?action=vehicle-delete&id=${vehicle.getId()}"><fmt:message key="dict.action.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</a:page>