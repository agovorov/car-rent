<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@tag %>
<%@attribute name="list" type="java.util.List" required="true" %>
<%@attribute name="actionname" type="java.lang.String" required="true" %>
<fmt:setBundle basename="messages/messages"/>
<c:set var="prefix" value="${pageContext.request.contextPath}"/>

<div class="pull-right">
    <a class="btn btn-primary" href="${prefix}/controller?action=${actionname}-create" role="button"><fmt:message key="dict.action.add"/></a></a>
</div>
<table class="table table-striped">
    <tr>
        <th><fmt:message key="common.form.dictionary_name_title"/></th>
        <th colspan="2"><fmt:message key="common.form.actions"/></th>
    </tr>
    <c:forEach items="${list}" var="item" varStatus="s">
        <tr class="row-${s.count % 2}">
            <td>${item.getValue()}</td>
            <td><a href="${prefix}/controller?action=${actionname}-update&id=${item.getId()}"><fmt:message key="dict.action.update"/></a></td>
            <td><a href="${prefix}/controller?action=${actionname}-delete&id=${item.getId()}" onclick="return confirm('<fmt:message key="dict.action.confirm.delete"/>')"><fmt:message key="dict.action.delete"/></a></td>
        </tr>
    </c:forEach>
</table>