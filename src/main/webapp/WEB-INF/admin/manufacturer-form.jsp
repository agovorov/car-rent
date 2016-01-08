<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="manufacturer.form.placeholder" var="enterManufacturerPlaceholder"/>

<c:set var="manufacturer" value='${not empty param.manufacturer ? param.manufacturer : model.getTitle() }'/>

<a:admin_page title="${pageHeader}" breadcrumbItems="${breadcrumbItems}">
    <form method="post">
        <div class="form-group">
            <label for="fManufacturer"><fmt:message key="vehicle.form.model-name"/></label>
            <input type="text" class="form-control" id="fManufacturer" name="manufacturer" maxlength="50"
                   placeholder="${enterManufacturerPlaceholder}" value="${manufacturer}">
        </div>
        <button class="btn btn-default" type="submit"><fmt:message key="common.button.save"/></button>
    </form>
</a:admin_page>