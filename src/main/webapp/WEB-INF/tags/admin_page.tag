<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="left" fragment="true" %>
<%@ attribute name="jsitems" %>
<%@ attribute name="cssitems" %>
<%@ attribute name="breadcrumbItems" type="java.util.List" %>
<%@attribute name="title" type="java.lang.String" required="true" %>
<fmt:setBundle basename="messages/messages"/>


<a:two_column title="${title}"
              jsitems="${jsitems}"
              cssitems="${cssitems}"
              breadcrumbItems="${breadcrumbItems}">
    <jsp:attribute name="left">
        <h3><fmt:message key="order.menu.orders"/></h3>

        <ul class="nav nav-sidebar">
                <%--This is dynamic list--%>
            <li><a href="/controller?action=order-list"><fmt:message key="table.order.all.list"/></a></li>
            <li><a href="/controller?action=order-confirm"><fmt:message key="table.order.confirm.list"/></a></li>
            <%--<li><a href="/controller?action=order-payed"><fmt:message key="table.order.payed.list"/></a></li>--%>

            <li role="separator" class="divider"><hr></li>
            <li><a href="/controller?action=manufacturer-list"><fmt:message key="manufacturer.table.list"/></a></li>
            <li><a href="/controller?action=color-list"><fmt:message key="color.list.title"/></a></li>
            <li><a href="/controller?action=vehicle-type-list"><fmt:message key="type.list.title"/></a></li>
            <li><a href="/controller?action=vehicle-fuel-list"><fmt:message key="fuel.list.title"/></a></li>
            <li><a href="/controller?action=vehicle-gear-list"><fmt:message key="gear.list.title"/></a></li>
            <li><a href="/controller?action=vehicle-list"><fmt:message key="vehicle.list.href"/></a></li>
            <li><a href="/controller?action=user-list"><fmt:message key="user.list.href"/></a></li>
        </ul>
    </jsp:attribute>

    <jsp:body>
        <jsp:doBody/>
    </jsp:body>
</a:two_column>




