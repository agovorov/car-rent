<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="dict.color.pageheader" var="pageHeader"/>
<fmt:message key="dict.color.form.colorname.placeholder-ru" var="enterColorRu"/>
<fmt:message key="dict.color.form.colorname.placeholder-en" var="enterColorEn"/>
<a:page title="Main">
    <h1><fmt:message key="dict.color.form.title"/></h1>

    <ul>
        <li><a href="/"><fmt:message key="main.index.title"/></a></li>
        <li><a href="/controller?action=color-list"><fmt:message key="dict.color.title"/></a></li>
    </ul>

    <a:alert/>
    <form method="post">
        <input type="text" name="color-name-ru" maxlength="50" autofocus="true" placeholder="${enterColorRu}"
               value="${vehicleColor.getValue("ru")}">

        <input type="text" name="color-name-en" maxlength="50" placeholder="${enterColorEn}"
               value="${vehicleColor.getValue("en")}">
        <button class="btn btn-default" type="submit"><fmt:message key="common.button.save"/></button>
    </form>
</a:page>