<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setBundle basename="messages/messages"/>
<fmt:message key="user.pageheader" var="pageHeader"/>
<fmt:message key="user.form.firstname.placeholder" var="firstnamePlaceholder"/>
<fmt:message key="common.currency.short" var="currency_label"/>

<a:admin_page title="${pageHeader}" breadcrumbItems="${breadcrumbItems}">
    <a:orderDisplay entity="${order}" />
</a:admin_page>