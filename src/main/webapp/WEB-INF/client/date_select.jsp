<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="select.date.pageheader" var="pageHeader"/>

<c:set var="date_start" value='${not empty param.date_start ? param.date_start : "01.12.2015" }'/>
<c:set var="date_end" value='${not empty param.date_end ? param.date_end : "05.12.2015" }'/>


<a:order title="${pageHeader}" jsitems="/js/order.js">
    <a:alert/>
    <form class="form-inline" method="post">

        <div class="form-group">
            <label for="fDateStart"><fmt:message key="order.date.start"/></label>
            <input type="text" class="form-control datepicker date" id="fDateStart" name="date_start" maxlength="50"
                   value="${date_start}" autofocus="true">
        </div>


        <div class="form-group">
            <label for="fDateEnd"><fmt:message key="order.data.end"/></label>
            <input type="text" class="form-control date" id="fDateEnd" name="date_end" maxlength="50"
                   value="${date_end}">
        </div>

        <div class="btns">
            <button class="btn btn-default" type="submit"><fmt:message key="order.button.step2"/></button>
        </div>
    </form>

    <c:set var="prefix" value="${pageContext.request.contextPath}"/>
</a:order>
<script type='text/javascript' src='${prefix}/js/order.js'></script>
