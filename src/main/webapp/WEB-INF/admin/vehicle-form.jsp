<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@page import="java.text.DecimalFormat" %>

<fmt:setBundle basename="messages/messages"/>
<fmt:message key="vehicle.pageheader" var="pageHeader"/>
<fmt:message key="vehicle.form.model.placeholder" var="enterModelPlaceholder"/>
<fmt:message key="vehicle.form.price.placeholder" var="enterPricePlaceholder"/>
<fmt:message key="vehicle.form.volume.placeholder" var="enterVolumePlaceholder"/>
<fmt:message key="vehicle.form.year.placeholder" var="enterYearPlaceholder"/>
<fmt:message key="vehicle.form.consumption.placeholder" var="enterConsumptPlaceholder"/>

<c:set var="max_year" value="2015"/>

<c:set var="manufactur_id2" value="${vehicle.getManufactorId()}"/>
<c:set var="manufactur_id" value="${param.manufactor}"/>
<c:set var="model" value="${not empty param.model ? param.model : vehicle.getModel() }"/>
<c:set var="year" value="${not empty param.year ? param.year : vehicle.getYear() }"/>

<c:set var="volume" value="${not empty param.volume ? param.volume : vehicle.vehicleVolume() }"/>
<c:if test="price eq Double.NaN">
    <fmt:formatNumber var="volume" value="${volume}" maxFractionDigits="2" />
</c:if>

<c:set var="consumption" value="${not empty param.consumption ? param.consumption : vehicle.getConsumption() }"/>

<c:set var="price" value="${not empty param.price ? param.price : vehicle.getPrice() }"/>
<%--<fmt:formatNumber var="price" value="${price}" maxFractionDigits="0" />--%>

<c:set var="bodytype_id" value="${not empty param.bodytype ? param.bodytype : vehicle.getBodyType() }"/>
<c:set var="color_id" value="${not empty param.color ? param.color : vehicle.getColorId() }"/>
<c:set var="gear_id" value="${not empty param.gear ? param.gear : vehicle.getGearShift() }"/>
<c:set var="fuel_id" value="${not empty param.fuel ? param.fuel : vehicle.getFuel() }"/>

<a:page title="Main">
    <a:breadcrumbs items="${breadcrumbItems}"/>
    <a:alert/>

    <div class="row">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="fManufacturer"><fmt:message key="vehicle.form.manufactor"/></label>
                <select class="form-control" id="fManufacturer" name="manufactor">
                    <c:forEach items="${manufactors}" var="manufactor">
                        <option ${manufactor.getId() == manufactur_id ? 'selected' : ''} value="${manufactor.getId()}">${manufactor.getValue()}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="fModel"><fmt:message key="vehicle.form.model-name"/></label>
                <input type="text" class="form-control" id="fModel" name="model" maxlength="50"
                       placeholder="${enterModelPlaceholder}" value="${model}">
            </div>

            <div class="form-group">
                <label for="fYear"><fmt:message key="vehicle.form.year"/></label>
                <input type="number" class="format-number bfh-number form-control" id="fYear" name="year" min="1990"
                       max="${max_year}" step="1"
                       placeholder="${enterYearPlaceholder}" value="${year}">
            </div>

            <div class="form-group">
                <label for="fVolume"><fmt:message key="vehicle.form.volume"/></label>
                <input type="text" class="format-number form-control" id="fVolume" name="volume" maxlength="5"
                       placeholder="${enterVolumePlaceholder}" value="${volume}">
            </div>

            <div class="form-group">
                <label for="fCompump"><fmt:message key="vehicle.form.consumption"/></label>
                <input type="number" class="format-number form-control" id="fCompump" name="consumption" min="1" max="50"
                       placeholder="${enterConsumptPlaceholder}" value="${consumption}">
            </div>

            <div class="form-group">
                <label for="fPrice"><fmt:message key="vehicle.form.price"/></label>
                <input type="text" class="format-number form-control" id="fPrice" name="price" maxlength="10"
                       placeholder="${enterPricePlaceholder}" value="${price}">
            </div>

            <div class="form-group">
                <label for="fBodytype"><fmt:message key="vehicle.form.bodytype"/></label>
                <select class="form-control" id="fBodytype" name="bodytype">
                    <c:forEach items="${types}" var="type">
                        <option ${type.getId() == bodytype_id ? 'selected' : ''} value="${type.getId()}">${type.getValue()}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="fColor"><fmt:message key="vehicle.form.color"/></label>
                <select class="form-control" id="fColor" name="color">
                    <c:forEach items="${colors}" var="color">
                        <option ${color.getId() == color_id ? 'selected' : ''} value="${color.getId()}">${color.getValue()}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="fGearshift"><fmt:message key="vehicle.form.gearshift"/></label>
                <select class="form-control" id="fGearshift" name="gear">
                    <c:forEach items="${gearShifts}" var="gearShift">
                        <option ${gearShift.getId() == gear_id ? 'selected' : ''} value="${gearShift.getId()}">${gearShift.getValue()}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="fFuel"><fmt:message key="vehicle.form.fuel"/></label>
                <select class="form-control" id="fFuel" name="fuel">
                    <c:forEach items="${fuels}" var="fuel">
                        <option ${fuel.getId() == fuel_id ? 'selected' : ''} value="${fuel.getId()}">${fuel.getValue()}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group">
                <label for="fGallery"><fmt:message key="vehicle.form.gallery"/></label>
                <input type="file" id="fGallery" name="gallery">
            </div>

            <button class="btn btn-default" type="submit"><fmt:message key="common.button.save"/></button>
        </form>
    </div>
</a:page>