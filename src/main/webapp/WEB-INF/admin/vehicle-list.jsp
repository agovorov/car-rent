<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="dict.vehicle.pageheader" var="pageHeader"/>
<c:set var="prefix" value="${pageContext.request.contextPath}"/>
<fmt:message key="common.currency.short" var="currency_label"/>

<a:admin_page title="${pageHeader}" breadcrumbItems="${breadcrumbItems}">
    <div class="pull-right">
        <a class="btn btn-primary" href="${prefix}/controller?action=vehicle-create" role="button"><fmt:message
                key="dict.action.add"/></a></a>
    </div>
    <table class="table table-striped">
        <tr>
            <th><fmt:message key="vehicle.table.title"/></th>
            <th><fmt:message key="vehicle.table.price"/></th>
            <th colspan="2"><fmt:message key="vehicle.table.action"/></th>
        </tr>
        <c:forEach items="${vehicles}" var="vehicle" varStatus="s">
            <fmt:formatNumber var="price" type="currency" currencySymbol="${currency_label}"
                              value="${vehicle.getPrice()}"/>
            <tr class="row-${s.count % 2}">
                <td>${vehicle.getManufacturer().getValue()} ${vehicle.getModel()}, ${vehicle.getYear()}</td>
                <td>${price}</td>
                <td><a href="${prefix}/controller?action=vehicle-update&id=${vehicle.getId()}"><fmt:message
                        key="dict.action.update"/></a></td>
                <td><a href="${prefix}/controller?action=vehicle-delete&id=${vehicle.getId()}"
                       onclick="return confirm('<fmt:message key="dict.action.confirm.delete"/>')"><fmt:message
                        key="dict.action.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</a:admin_page>