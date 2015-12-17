<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="dict.fuel.pageheader" var="pageHeader"/>
<fmt:message key="dict.fuel.form.fuelname.placeholder-ru" var="enterFuelRu"/>
<fmt:message key="dict.fuel.form.fuelname.placeholder-en" var="enterFuelEn"/>

<c:set var="gear_ru" value='${not empty param.fuel_ru ? param.fuel_ru : model.getValue("ru") }'/>
<c:set var="gear_en" value='${not empty param.fuel_en ? param.fuel_en : model.getValue("en") }'/>

<a:page title="Main">
    <h1><fmt:message key="dict.fuel.form.title"/></h1>
    <a:breadcrumbs items="${breadcrumbItems}"/>
    <a:alert/>
    <form method="post">
        <div class="form-group">
            <label for="fFuelRu"><fmt:message key="fuel.form.ru.fuelname"/></label>
            <input type="text" class="form-control" id="fFuelRu" name="fuel_ru" maxlength="50"
                   placeholder="${enterFuelRu}" value="${gear_ru}" autofocus="true">
        </div>

        <div class="form-group">
            <label for="fFuelEn"><fmt:message key="fuel.form.en.fuelname"/></label>
            <input type="text" class="form-control" id="fFuelEn" name="fuel_en" maxlength="50"
                   placeholder="${enterFuelEn}" value="${gear_en}">
        </div>
        <button class="btn btn-default" type="submit"><fmt:message key="common.button.save"/></button>
    </form>
</a:page>