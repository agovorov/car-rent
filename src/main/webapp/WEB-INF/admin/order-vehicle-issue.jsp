<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="user.pageheader" var="pageHeader"/>
<a:admin_page title="${pageHeader}">
    <a:orderDisplay entity="${order}"/>

    <form method="post" enctype="multipart/form-data">
        <div class="checkbox">
            <label>
                <input type="checkbox" name="issued"> <fmt:message key="order.issue.vehicle.confirm"/>
            </label>
        </div>

        <button class="btn btn-default" name="btn-confirm" value="accept" type="submit"><fmt:message
                key="order.btn.issue.vehicle"/></button>
    </form>
</a:admin_page>