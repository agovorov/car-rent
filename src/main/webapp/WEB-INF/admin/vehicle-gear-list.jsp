<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="dict.gear.pageheader" var="pageHeader"/>
<a:page title="${pageHeader}">
    <fmt:message key="gear.table.list"/>
    <a:breadcrumbs items="${breadcrumbItems}"/>
    <a:alert/>
    <a:dtable list="${gearShifts}"
              actionname="vehicle-gear"/>
</a:page>