<%@tag %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@attribute name="type" type="java.lang.String" required="false" %>

<c:if test="${not empty systemMessage}">
    <div class="" style="border: 1px solid green">${systemMessage.getMessage()}</div>
    <c:remove var="systemMessage" scope="session" />
</c:if>