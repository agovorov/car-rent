<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<c:set var="prefix" value="${pageContext.request.contextPath}"/>
<fmt:message key="user.list.pageheader" var="pageHeader"/>

<a:admin_page title="${pageHeader}" breadcrumbItems="${breadcrumbItems}">
    <div class="pull-right">
        <a class="btn btn-primary" href="${prefix}/controller?action=user-create" role="button"><fmt:message
                key="dict.action.add"/></a></a>
    </div>
    <table class="table table-striped">
        <tr>
            <th><fmt:message key="user.table.name"/></th>
            <th><fmt:message key="user.table.email"/></th>
            <th><fmt:message key="user.table.role"/></th>
            <th colspan="2"><fmt:message key="common.form.actions"/></th>
        </tr>
        <c:forEach items="${users}" var="user" varStatus="s">
            <tr class="row-${s.count % 2}">
                <td>${user.getFirstName()} ${user.getLastName()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getRole().getValue()}</td>
                <td><a href="${prefix}/controller?action=user-update&id=${user.getId()}"><fmt:message
                        key="dict.action.update"/></a></td>

                <td><a href="${prefix}/controller?action=user-delete&id=${user.getId()}"
                       onclick="return confirm('<fmt:message key="dict.action.confirm.delete"/>')"><fmt:message
                        key="dict.action.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</a:admin_page>