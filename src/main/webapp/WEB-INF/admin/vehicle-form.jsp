<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="vehicle.pageheader" var="pageHeader"/>
<fmt:message key="vehicle.form.model.placeholder" var="enterModelPlaceholder"/>
<fmt:message key="vehicle.form.price.placeholder" var="enterPricePlaceholder"/>
<fmt:message key="vehicle.form.volume.placeholder" var="enterVolumePlaceholder"/>
<fmt:message key="vehicle.form.year.placeholder" var="enterYearPlaceholder"/>
<fmt:message key="vehicle.form.consumption.placeholder" var="enterConsumptPlaceholder"/>

<c:set var="year" value="2015"/>

<a:page title="Main">

    <a:breadcrumbs items="${breadcrumbItems}"/>
    <a:alert/>

    <fmt:formatNumber var="vehicleVolumeValue" value="${vehicle.getVolume()}" maxFractionDigits="2" />
    <fmt:formatNumber var="vehiclePrice" value="${vehicle.getPrice()}" maxFractionDigits="0" />

    <div class="row">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="fManufacturer"><fmt:message key="vehicle.form.manufactor"/></label>
                <select class="form-control" id="fManufacturer" name="v-manufactor">
                    <c:forEach items="${manufactors}" var="manufactor">
                        <option ${manufactor.getId() == vehicle.getManufactorId() ? 'selected' : ''} value="${manufactor.getId()}">${manufactor.getValue()}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="fModel"><fmt:message key="vehicle.form.model-name"/></label>
                <input type="text" class="form-control" id="fModel" name="v-model" maxlength="50"
                       placeholder="${enterModelPlaceholder}" value="${vehicle.getModel()}">
            </div>

            <div class="form-group">
                <label for="fYear"><fmt:message key="vehicle.form.year"/></label>
                <input type="number" class="format-number bfh-number form-control" id="fYear" name="v-year" min="1990"
                       max="${year}" step="1"
                       placeholder="${enterYearPlaceholder}" value="${vehicle.getYear()}">
            </div>

            <div class="form-group">
                <label for="fVolume"><fmt:message key="vehicle.form.volume"/></label>
                <input type="text" class="format-number form-control" id="fVolume" name="v-volume" maxlength="5"
                       placeholder="${enterVolumePlaceholder}" value="${vehicleVolumeValue}">
            </div>

            <div class="form-group">
                <label for="fCompump"><fmt:message key="vehicle.form.consumption"/></label>
                <input type="number" class="format-number form-control" id="fCompump" name="v-consumpt" min="1" max="50"
                       placeholder="${enterConsumptPlaceholder}" value="${vehicle.getConsumption()}">
            </div>

            <div class="form-group">
                <label for="fPrice"><fmt:message key="vehicle.form.price"/></label>
                <input type="text" class="format-number form-control" id="fPrice" name="v-price" maxlength="10"
                       placeholder="${enterPricePlaceholder}" value="${vehiclePrice}">
            </div>

            <div class="form-group">
                <label for="fBodytype"><fmt:message key="vehicle.form.bodytype"/></label>
                <select class="form-control" id="fBodytype" name="v-bodytype">
                    <c:forEach items="${types}" var="type">
                        <option ${type.getId() == vehicle.getBodyType() ? 'selected' : ''} value="${type.getId()}">${type.getValue()}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="fColor"><fmt:message key="vehicle.form.color"/></label>
                <select class="form-control" id="fColor" name="v-color">
                    <c:forEach items="${colors}" var="color">
                        <option ${color.getId() == vehicle.getColorId() ? 'selected' : ''} value="${color.getId()}">${color.getValue()}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="fGearshift"><fmt:message key="vehicle.form.gearshift"/></label>
                <select class="form-control" id="fGearshift" name="v-gear">
                    <c:forEach items="${gearShifts}" var="gearShift">
                        <option ${gearShift.getId() == vehicle.getGearShift() ? 'selected' : ''} value="${gearShift.getId()}">${gearShift.getValue()}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="fFuel"><fmt:message key="vehicle.form.fuel"/></label>
                <select class="form-control" id="fFuel" name="v-fuel">
                    <c:forEach items="${fuels}" var="fuel">
                        <option ${fuel.getId() == vehicle.getFuel() ? 'selected' : ''} value="${fuel.getId()}">${fuel.getValue()}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="fGallery"><fmt:message key="vehicle.form.gallery"/></label>
                <input type="file" id="fGallery" name="f-gallery">
            </div>

            <button class="btn btn-default" type="submit"><fmt:message key="common.button.save"/></button>
        </form>
    </div>
</a:page>