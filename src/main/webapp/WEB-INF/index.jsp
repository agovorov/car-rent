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
        <div class="row center_div_block">
            <div class="col-md-12 col-md-offset-2- main_page">
                <h1><fmt:message key="index.best.service" /></h1>
                <h1><fmt:message key="index.rent.car" /></h1>
            </div>
                <%--<div class="col-md-12 col-md-offset-2">--%>
            <div class="col-md-12 col-md-offset-2- search-main-form">
                <form class="form-inline" method="post" action="/controller?action=order">
                    <div class="form-group">
                        <label for="fDateStart"><fmt:message key="order.i.want.order"/></label>
                        <label for="fDateStart"><fmt:message key="order.from"/></label>
                        <input type="text" class="form-control date" id="fDateStart" name="date_start"
                               maxlength="50"
                               placeholder="<fmt:message key="order.start.date"/>"
                               value="${date_start}" autofocus="true">
                    </div>

                    <div class="form-group">
                        <label for="fDateStart"><fmt:message key="order.until"/></label>
                        <input type="text" class="form-control date" id="fDateEnd" name="date_end" maxlength="50"
                               placeholder="<fmt:message key="order.end.date"/>"
                               value="${date_end}">
                    </div>

                    <button class="btn btn-red" type="submit"><fmt:message key="order.find.car"/></button>
                </form>
            </div>
        </div>
    </jsp:body>
</a:genericpage>