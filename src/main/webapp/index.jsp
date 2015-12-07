<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<a:page title="Main">
    <h3>Админка</h3>
    <ul>
        <li><a href="/controller?action=manufacturers-list">Manufacturers lists</a></li>
        <li><a href="/controller?action=manufacturer-create">Add manufacturer</a></li>

        <li><a href="/controller?action=color-list"><fmt:message key="color.list.title"/></a></li>
        <li><a href="/controller?action=color-create"><fmt:message key="color.create.title"/></a></li>

        <br>

        <li><a href="/controller?action=vehicle-list">List vehicle</a></li>
        <li><a href="/controller?action=vehicle-create">Add vehicle</a></li>
    </ul>
</a:page>