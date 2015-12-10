<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="dict.gear.pageheader" var="pageHeader"/>
<fmt:message key="dict.gear.form.colorname.placeholder-ru" var="enterGearRu"/>
<fmt:message key="dict.gear.form.colorname.placeholder-en" var="enterGearEn"/>
<a:page title="Main">
    <h1><fmt:message key="dict.type.form.title"/></h1>
    <a:alert/>
    <form method="post">
        <div class="form-group">
            <label for="fGearRu"><fmt:message key="vehicle.form.model-name"/></label>
            <input type="text" class="form-control" id="fGearRu" name="gear-name-ru" maxlength="50"
                   placeholder="${enterGearRu}" value="${vehicleGearShift.getValue("ru")}" autofocus="true">
        </div>

        <div class="form-group">
            <label for="fGearEn"><fmt:message key="vehicle.form.model-name"/></label>
            <input type="text" class="form-control" id="fGearEn" name="gear-name-en" maxlength="50"
                   placeholder="${enterGearEn}" value="${vehicleGearShift.getValue("en")}">
        </div>
        <button class="btn btn-default" type="submit"><fmt:message key="common.button.save"/></button>
    </form>
</a:page>