<%@tag %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setBundle basename="messages/messages"/>
<h3>ADMIN`s</h3>
<ul class="nav nav-sidebar">
    <li><a href="/controller?action=orders-list"><fmt:message key="order.table.list"/></a></li>
    <li role="separator" class="divider"></li>


    <li><a href="/controller?action=manufacturer-create"><fmt:message key="manufacturer.table.create"/></a></li>
    <li role="separator" class="divider"></li>
    <li><a href="/controller?action=user-list"><fmt:message key="user.list.href"/></a></li>
    <li><a href="/controller?action=user-create"><fmt:message key="user.create.href"/></a></li>
</ul>