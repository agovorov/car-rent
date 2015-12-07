<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<a:page title="Main">
    <h3>Index</h3>
    <ul>
        <li><a href="/controller?action=manufacturers-list">Manufacturers lists</a></li>
        <li><a href="/controller?action=manufacturer-create">Add manufacturer</a></li>
        <hr>
        <li><a href="/controller?action=color-list"><fmt:message key="color.list.title"/></a></li>
        <li><a href="/controller?action=color-create"><fmt:message key="color.create.title"/></a></li>
        <hr>
        <li><a href="/controller?action=vehicle-type-list"><fmt:message key="type.list.title"/></a></li>
        <li><a href="/controller?action=vehicle-type-create"><fmt:message key="type.create.title"/></a></li>
        <hr>
        <li><a href="/controller?action=vehicle-fuel-list"><fmt:message key="fuel.list.title"/></a></li>
        <li><a href="/controller?action=vehicle-fuel-create"><fmt:message key="fuel.create.title"/></a></li>
        <hr>
        <li><a href="/controller?action=vehicle-gear-list"><fmt:message key="gear.list.title"/></a></li>
        <li><a href="/controller?action=vehicle-gear-create"><fmt:message key="gear.create.title"/></a></li>
        <hr>
    </ul>
</a:page>