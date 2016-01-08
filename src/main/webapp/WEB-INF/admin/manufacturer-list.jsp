<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="manufacturer.table.list" var="pageHeader"/>

<a:admin_page title="${pageHeader}" breadcrumbItems="${breadcrumbItems}">
    <h1><fmt:message key="manufacturer.table.list"/></h1>
    <a:dtable list="${manufacturerList}"
              actionname="manufacturer"/>
</a:admin_page>

