<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="dict.gear.pageheader" var="pageHeader"/>
<a:page title="${pageHeader}">
    <ul>
        <li><a href="/"><fmt:message key="main.index.title"/></a></li>
        <li><a href="/controller?action=vehicle-gear-create"><fmt:message key="dict.gear.action.create"/></a></li>
    </ul>

    <a:alert/>
    <table class="list">
        <tr>
            <th><fmt:message key="dict.gear.table.title"/></th>
            <th><fmt:message key="dict.gear.table.action"/></th>
        </tr>
        <c:forEach items="${gearShifts}" var="gearShift" varStatus="s">
            <tr class="row-${s.count % 2}">
                <td>${gearShift.getValue()}</td>
                <td><a href="/controller?action=vehicle-gear-update&id=${gearShift.getId()}"><fmt:message key="dict.action.update"/></a></td>
                <td><a href="/controller?action=vehicle-gear-delete&id=${gearShift.getId()}"><fmt:message key="dict.action.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</a:page>