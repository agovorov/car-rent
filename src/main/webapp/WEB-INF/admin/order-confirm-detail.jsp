<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setBundle basename="messages/messages"/>
<fmt:message key="admin.order.confirm.pageheader" var="pageHeader"/>
<c:set var="reason" value="${not empty param.reason ? param.reason : '' }"/>

<a:admin_page title="${pageHeader}">
    <div>
        <a:orderDisplay entity="${order}"/>
        <form method="post" enctype="multipart/form-data">
            <textarea class="form-control" name="reason" rows="3"></textarea>
            <button class="btn btn-success" name="btn-confirm" value="accept" type="submit"><fmt:message
                    key="common.button.accept"/></button>
            <button class="btn btn-danger" name="btn-confirm" value="denied" type="submit"><fmt:message
                    key="common.button.refuse"/></button>
        </form>
    </div>
</a:admin_page>