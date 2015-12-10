<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="dict.fuel.pageheader" var="pageHeader"/>
<fmt:message key="dict.fuel.form.colorname.placeholder-ru" var="enterFuelRu"/>
<fmt:message key="dict.fuel.form.colorname.placeholder-en" var="enterFuelEn"/>
<a:page title="Main">
    <h1><fmt:message key="dict.fuel.form.title"/></h1>
    <a:alert/>
    <form method="post">
        <div class="form-group">
            <label for="fFuelRu"><fmt:message key="vehicle.form.model-name"/></label>
            <input type="text" class="form-control" id="fFuelRu" name="fuel-name-ru" maxlength="50"
                   placeholder="${enterFuelRu}" value="${vehicleFuelType.getValue("ru")}" autofocus="true">
        </div>

        <div class="form-group">
            <label for="fFuelEn"><fmt:message key="vehicle.form.model-name"/></label>
            <input type="text" class="form-control" id="fFuelEn" name="fuel-name-en" maxlength="50"
                   placeholder="${enterFuelEn}" value="${vehicleFuelType.getValue("en")}">
        </div>
        <button class="btn btn-default" type="submit"><fmt:message key="common.button.save"/></button>
    </form>
</a:page>