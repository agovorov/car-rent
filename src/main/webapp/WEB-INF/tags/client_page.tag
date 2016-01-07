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
        <jsp:doBody/>
    </jsp:body>
</a:two_column>




