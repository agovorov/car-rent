<%@tag %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@attribute name="type" type="java.lang.String" required="false" %>
<fmt:setBundle basename="messages/messages"/>
<c:if test="${not empty systemMessage}">
    <div class="alert alert-${systemMessage.getType()}" role="alert">
        ${systemMessage.getMessage()}
        <c:if test="${systemMessage.isShowErrorsList()}">
            <c:set var="errorMap" value="${systemMessage.getErrors()}"/>
            <c:if test="${not empty errorMap}">
                <ul class="alerts-list">
                    <c:forEach items="${errorMap}" var="map">
                        <li><fmt:message key="${map.value}"/></li>
                    </c:forEach>
                </ul>
            </c:if>
        </c:if>
    </div>
    <c:remove var="systemMessage" scope="session"/>
</c:if>