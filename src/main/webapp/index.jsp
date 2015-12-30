<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<fmt:setBundle basename="messages/messages"/>

<a:genericpage title="${title}" jsitems="${jsitems}" cssitems="${cssitem}">
    <jsp:attribute name="header"><a:nav/></jsp:attribute>
    <jsp:attribute name="menu">
        <h1><fmt:message key="common.main.title"/></h1>
    </jsp:attribute>
    <jsp:body>
        <a:breadcrumbs items="${breadcrumbItems}"/>
        <p>Main page Main pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain
            pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain
            pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain
            pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain page</p>

        <p>Main pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain
            pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain
            pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain
            pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain
            pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain
            pageMain pageMain pageMain pageMain pageMain pageMain pageMain pageMain page
            Main pageMain pageMain pageMain pageMain pageMain pageMain pageMain page</p>
    </jsp:body>
</a:genericpage>