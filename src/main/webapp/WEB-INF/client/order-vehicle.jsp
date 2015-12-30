<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="select.date.pageheader" var="pageHeader"/>

<c:set var="prefix" value="${pageContext.request.contextPath}"/>

<c:set var="date_start" value='${not empty param.date_start ? param.date_start : "" }'/>
<c:set var="date_end" value='${not empty param.date_end ? param.date_end : "" }'/>

<a:order title="${pageHeader}" jsitems="/js/order.js" cssitems="/css/order.css">
    <a:alert/>
    ${order.getStartDate()} - ${order.getEndDate()}

    <c:if test="${not empty vehicles}">
        <ul class="nav nav-pills">
            <li role="presentation" class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                    Dropdown <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    ...
                </ul>
            </li>
            ...
        </ul>
        <div class="vehicles-list">
            <c:forEach var="vehicle" items="${vehicles}">
                <a:vehicleBlock vehicle="${vehicle}" order="${order}"/>
            </c:forEach>
        </div>
    </c:if>

    <c:if test="${empty vehicles}">
        Nothing found!
    </c:if>
</a:order>