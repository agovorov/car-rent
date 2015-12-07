<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setBundle basename="messages/messages"/>
<a:page title="Manufacturers list">
    Список авто
    <ul>
        <li><a href="/">Index page</a></li>
        <li><a href="/controller?action=manufacturer-create">Add manufacturer</a></li>
    </ul>

    <a:alert/>
    <table class="list">
        <tr>
            <th><fmt:message key="manufactors.table.title"/></th>
            <th><fmt:message key="manufactors.table.action"/></th>
        </tr>
        <c:forEach items="${manufacturerList}" var="manufactor" varStatus="s">
            <tr class="row-${s.count % 2}">
                <td>${manufactor.getTitle()}</td>
                <td><a href="/controller?action=manufacturer-update&id=${manufactor.getId()}">UPDATE</a></td>
                <td><a href="/controller?action=manufacturer-delete&id=${manufactor.getId()}">DELETE</a></td>
            </tr>
        </c:forEach>
    </table>
</a:page>

