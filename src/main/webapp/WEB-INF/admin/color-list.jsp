<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="dict.color.pageheader" var="pageHeader"/>
<a:page title="${pageHeader}">
    <ul>
        <li><a href="/"><fmt:message key="main.index.title"/></a></li>
        <li><a href="/controller?action=color-create"><fmt:message key="dict.color.action.create"/></a></li>
    </ul>

    <a:alert/>
    <table class="list">
        <tr>
            <th><fmt:message key="dict.color.table.title"/></th>
            <th><fmt:message key="dict.color.table.action"/></th>
        </tr>
        <c:forEach items="${colors}" var="color" varStatus="s">
            <tr class="row-${s.count % 2}">
                <td>${color.getValue()}</td>
                <td><a href="/controller?action=color-update&id=${color.getId()}"><fmt:message key="dict.color.action.update"/></a></td>
                <td><a href="/controller?action=color-delete&id=${color.getId()}"><fmt:message key="dict.color.action.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</a:page>