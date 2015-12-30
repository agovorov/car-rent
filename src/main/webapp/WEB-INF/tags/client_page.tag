<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="title" type="java.lang.String" required="true" %>
<fmt:setBundle basename="messages/messages"/>



<a:genericpage title="${title}">
    <jsp:attribute name="header"><a:nav/></jsp:attribute>
    <jsp:attribute name="menu">
        <h3><fmt:message key="order.menu.orders"/></h3>
        <ul class="nav nav-sidebar">
            <li><a href="/controller?action=order"><fmt:message key="order.create.href"/></a></li>

            <li><a href="/controller?action=orders"><fmt:message key="order.menu.current.order"/></a></li>
            <li><a href="/controller?action=history"><fmt:message key="order.menu.history.order"/></a></li>
        </ul>

        <h3><fmt:message key="order.menu.profile.head"/></h3>
        <ul class="nav nav-sidebar">
            <li><a href="/controller?action=profile"><fmt:message key="order.menu.profile"/></a></li>
        </ul>
    </jsp:attribute>

    <jsp:body>
        <a:breadcrumbs items="${breadcrumbItems}"/>
        <a:alert/>
        <jsp:doBody/>
    </jsp:body>
</a:genericpage>




