<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="dict.type.pageheader" var="pageHeader"/>
<fmt:message key="dict.type.form.colorname.placeholder-ru" var="enterTypeRu"/>
<fmt:message key="dict.type.form.colorname.placeholder-en" var="enterTypeEn"/>

<c:set var="type_ru" value='${not empty param.type_ru ? param.type_ru : model.getValue("ru") }'/>
<c:set var="type_en" value='${not empty param.type_en ? param.type_en : model.getValue("en") }'/>

<a:page title="Main">
    <h1><fmt:message key="dict.type.form.title"/></h1>
    <a:breadcrumbs items="${breadcrumbItems}"/>
    <a:alert/>
    <form method="post">
        <div class="form-group">
            <label for="fTypeRu"><fmt:message key="type.form.ru.typename"/></label>
            <input type="text" class="form-control" id="fTypeRu" name="type_ru" maxlength="50"
                   placeholder="${enterTypeRu}" value="${type_ru}" autofocus="true">
        </div>

        <div class="form-group">
            <label for="fTypeEn"><fmt:message key="type.form.en.typename"/></label>
            <input type="text" class="form-control" id="fTypeEn" name="type_en" maxlength="50"
                   placeholder="${enterTypeEn}" value="${type_en}">
        </div>
        <button class="btn btn-default" type="submit"><fmt:message key="common.button.save"/></button>
    </form>
</a:page>