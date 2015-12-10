<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="dict.type.pageheader" var="pageHeader"/>
<fmt:message key="dict.type.form.colorname.placeholder-ru" var="enterTypeRu"/>
<fmt:message key="dict.type.form.colorname.placeholder-en" var="enterTypeEn"/>
<a:page title="Main">
    <h1><fmt:message key="dict.type.form.title"/></h1>
    <a:alert/>
    <form method="post">
        <div class="form-group">
            <label for="fTypeRu"><fmt:message key="vehicle.form.model-name"/></label>
            <input type="text" class="form-control" id="fTypeRu" name="type-name-ru" maxlength="50"
                   placeholder="${enterTypeRu}" value="${vehicleBodyType.getValue("ru")}" autofocus="true">
        </div>

        <div class="form-group">
            <label for="fTypeEn"><fmt:message key="vehicle.form.model-name"/></label>
            <input type="text" class="form-control" id="fTypeEn" name="type-name-en" maxlength="50"
                   placeholder="${enterTypeEn}" value="${vehicleBodyType.getValue("en")}">
        </div>
        <button class="btn btn-default" type="submit"><fmt:message key="common.button.save"/></button>
    </form>
</a:page>