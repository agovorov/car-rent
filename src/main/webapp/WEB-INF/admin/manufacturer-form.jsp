<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="manufacturer.table.create" var="enterManufacturerPlaceholder"/>
<a:page title="Main">
    <h1><fmt:message key="manufacturer.table.create"/></h1>
    <a:alert/>
    <form method="post">
        <div class="form-group">
            <label for="fManufacturer"><fmt:message key="vehicle.form.model-name"/></label>
            <input type="text" class="form-control" id="fManufacturer" name="manufacturer-name" maxlength="50"
                   placeholder="${enterManufacturerPlaceholder}" value="${vehicleManufacturer.getTitle()}">
        </div>
        <button class="btn btn-default" type="submit"><fmt:message key="common.button.save"/></button>
    </form>
</a:page>