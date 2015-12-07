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

    <ul>
        <li><a href="/"><fmt:message key="main.index.title"/></a></li>
        <li><a href="/controller?action=vehicle-type-list"><fmt:message key="dict.type.title"/></a></li>
    </ul>

    <a:alert/>
    <form method="post">
        <input type="text" name="type-name-ru" maxlength="50" autofocus="true" placeholder="${enterTypeRu}"
               value="${vehicleBodyType.getValue("ru")}">

        <input type="text" name="type-name-en" maxlength="50" placeholder="${enterTypeEn}"
               value="${vehicleBodyType.getValue("en")}">
        <button class="btn btn-default" type="submit"><fmt:message key="common.button.save"/></button>
    </form>
</a:page>