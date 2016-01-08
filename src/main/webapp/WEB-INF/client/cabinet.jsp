<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<fmt:setBundle basename="messages/messages"/>
<fmt:message key="cabinet.pageheader" var="pageHeader"/>

<a:client_page title="${pageHeader}" cssitems="/css/cabinet-client.css" breadcrumbItems="${breadcrumbItems}">

</a:client_page>