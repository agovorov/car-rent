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

    <ul>
        <li><a href="/"><fmt:message key="main.index.title"/></a></li>
        <li><a href="/controller?action=vehicle-fuel-list"><fmt:message key="dict.fuel.title"/></a></li>
    </ul>

    <a:alert/>
    <form method="post">
        <input type="text" name="fuel-name-ru" maxlength="50" autofocus="true" placeholder="${enterFuelRu}"
               value="${vehicleFuelType.getValue("ru")}">

        <input type="text" name="fuel-name-en" maxlength="50" placeholder="${enterFuelEn}"
               value="${vehicleFuelType.getValue("en")}">
        <button class="btn btn-default" type="submit"><fmt:message key="common.button.save"/></button>
    </form>
</a:page>