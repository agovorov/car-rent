<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="index.title" var="title"/>

<c:set var="date_start" value="${not empty param.date_start ? param.date_start : '01.12.2015' }"/>
<c:set var="date_end" value="${not empty param.date_end ? param.date_end : '15.12.2015' }"/>

<a:genericpage title="${title}" jsitems="${jsitems}" cssitems="${cssitem}">
    <jsp:attribute name="header"><a:nav/></jsp:attribute>
    <jsp:body>
        <a:breadcrumbs items="${breadcrumbItems}"/>
        <a:alert/>
        <div class="row center_div">
            <div class="col-md-6 col-md-offset-3">
                <form class="form-inline" method="post" action="/controller?action=order">
                    <div class="form-group">
                        <label class="sr-only" for="fDateStart"><fmt:message key="order.date.start"/></label>
                        <input type="text" class="form-control datepicker date" id="fDateStart" name="date_start"
                               maxlength="50"
                               value="${date_start}" autofocus="true">
                    </div>

                    <div class="form-group">
                        <label class="sr-only" for="fDateEnd"><fmt:message key="order.data.end"/></label>
                        <input type="text" class="form-control date" id="fDateEnd" name="date_end" maxlength="50"
                               value="${date_end}">
                    </div>

                    <button class="btn btn-default" type="submit"><fmt:message key="order.button.step2"/></button>
                </form>
            </div>
        </div>
    </jsp:body>
</a:genericpage>