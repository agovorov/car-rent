<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="dict.color.pageheader" var="pageHeader"/>
<fmt:message key="dict.color.form.colorname.placeholder-ru" var="enterColorRu"/>
<fmt:message key="dict.color.form.colorname.placeholder-en" var="enterColorEn"/>

<c:set var="gear_ru" value='${not empty param.color_ru ? param.color_ru : model.getValue("ru") }'/>
<c:set var="gear_en" value='${not empty param.color_en ? param.color_en : model.getValue("en") }'/>

<a:page title="Main">
    <h1><fmt:message key="dict.color.form.title"/></h1>
    <a:breadcrumbs items="${breadcrumbItems}"/>
    <a:alert/>
    <form method="post">
        <div class="form-group">
            <label for="fColorRu"><fmt:message key="color.form.ru.colorname"/></label>
            <input type="text" class="form-control" id="fColorRu" name="color_ru" maxlength="50"
                   placeholder="${enterColorRu}" value="${gear_ru}" autofocus="true">
        </div>

        <div class="form-group">
            <label for="fColorEn"><fmt:message key="color.form.en.colorname"/></label>
            <input type="text" class="form-control" id="fColorEn" name="color_en" maxlength="50"
                   placeholder="${enterColorEn}" value="${gear_en}">
        </div>
        <button class="btn btn-default" type="submit"><fmt:message key="common.button.save"/></button>
    </form>
</a:page>