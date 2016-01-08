<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="dict.type.pageheader" var="pageHeader"/>

<a:admin_page title="${pageHeader}" breadcrumbItems="${breadcrumbItems}">
    <h1><fmt:message key="color.table.list"/></h1>
    <a:dtable list="${types}"
              actionname="vehicle-type"/>
</a:admin_page>