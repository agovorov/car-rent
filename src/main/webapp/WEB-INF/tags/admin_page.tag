<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="title" type="java.lang.String" required="true" %>
<%@ attribute name="jsitems" %>
<%@ attribute name="cssitem" %>
<fmt:setBundle basename="messages/messages"/>

<a:genericpage title="${title}" jsitems="${jsitems}" cssitems="${cssitem}">
    <jsp:attribute name="header"><a:nav/></jsp:attribute>
    <jsp:attribute name="menu">
        <h2>Admin`s page</h2>
        <ul class="nav nav-sidebar">
            <%--This is dynamic list--%>
            <li><a href="/controller?action=order-list"><fmt:message key="table.order.all.list"/></a></li>
            <li><a href="/controller?action=order-confirm"><fmt:message key="table.order.confirm.list"/></a></li>
            <li><a href="/controller?action=order-payed"><fmt:message key="table.order.payed.list"/></a></li>


            <li role="separator" class="divider"><hr></li>
            <li><a href="/controller?action=manufacturer-list"><fmt:message key="manufacturer.table.list"/></a></li>
            <li><a href="/controller?action=manufacturer-create"><fmt:message key="manufacturer.table.create"/></a></li>
            <li role="separator" class="divider"></li>
            <li><a href="/controller?action=color-list"><fmt:message key="color.list.title"/></a></li>
            <li><a href="/controller?action=color-create"><fmt:message key="color.create.title"/></a></li>
            <li role="separator" class="divider"></li>
            <li><a href="/controller?action=vehicle-type-list"><fmt:message key="type.list.title"/></a></li>
            <li><a href="/controller?action=vehicle-type-create"><fmt:message key="type.create.title"/></a></li>
            <li role="separator" class="divider"></li>
            <li><a href="/controller?action=vehicle-fuel-list"><fmt:message key="fuel.list.title"/></a></li>
            <li><a href="/controller?action=vehicle-fuel-create"><fmt:message key="fuel.create.title"/></a></li>
            <li role="separator" class="divider"></li>
            <li><a href="/controller?action=vehicle-gear-list"><fmt:message key="gear.list.title"/></a></li>
            <li><a href="/controller?action=vehicle-gear-create"><fmt:message key="gear.create.title"/></a></li>
            <li role="separator" class="divider"></li>
            <li><a href="/controller?action=vehicle-list"><fmt:message key="vehicle.list.href"/></a></li>
            <li><a href="/controller?action=vehicle-create"><fmt:message key="vehicle.create.href"/></a></li>
            <li role="separator" class="divider"></li>
            <li><a href="/controller?action=user-list"><fmt:message key="user.list.href"/></a></li>
            <li><a href="/controller?action=user-create"><fmt:message key="user.create.href"/></a></li>
        </ul>
    </jsp:attribute>

    <jsp:body>
        <a:breadcrumbs items="${breadcrumbItems}"/>
        <a:alert/>
        <jsp:doBody/>
    </jsp:body>
</a:genericpage>




