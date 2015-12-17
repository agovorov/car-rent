<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="dict.gear.pageheader" var="pageHeader"/>
<fmt:message key="dict.gear.form.gearname.placeholder-ru" var="enterGearRu"/>
<fmt:message key="dict.gear.form.gearname.placeholder-en" var="enterGearEn"/>

<c:set var="gear_ru" value='${not empty param.gearshift_ru ? param.gearshift_ru : model.getValue("ru") }'/>
<c:set var="gear_en" value='${not empty param.gearshift_en ? param.gearshift_en : model.getValue("en") }'/>
<a:page title="Main">
    <h1><fmt:message key="dict.gear.form.title"/></h1>
    <a:alert/>
    <form method="post">
        <div class="form-group">
            <label for="fGearRu"><fmt:message key="gear.form.ru.model-name"/></label>
            <input type="text" class="form-control" id="fGearRu" name="gearshift_ru" maxlength="50"
                   placeholder="${enterGearRu}" value="${gear_ru}" autofocus="true">
        </div>

        <div class="form-group">
            <label for="fGearEn"><fmt:message key="gear.form.en.model-name"/></label>
            <input type="text" class="form-control" id="fGearEn" name="gearshift_en" maxlength="50"
                   placeholder="${enterGearEn}" value="${gear_en}">
        </div>
        <button class="btn btn-default" type="submit"><fmt:message key="common.button.save"/></button>
    </form>
</a:page>