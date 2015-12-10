<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag %>
<%@attribute name="items" type="java.util.List" required="true" %>

<ol class="breadcrumb">
    <c:forEach var="item" items="${items}">
        <c:if test="${item.isActive()}">
            <li>${item.getLabel()}</li>
        </c:if>
        <c:if test="${!item.isActive()}">
            <li><a href="/controller?action=${item.getActionName()}">${item.getLabel()}</a></li>
        </c:if>
    </c:forEach>
</ol>