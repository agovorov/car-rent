<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@attribute name="title" type="java.lang.String" required="true" %>
<%@ attribute name="left" fragment="true" %>
<%@ attribute name="jsitems" %>
<%@ attribute name="cssitems" %>
<%@ attribute name="breadcrumbItems" type="java.util.List" %>
<fmt:setBundle basename="messages/messages"/>

<a:genericpage title="${title}" jsitems="${jsitems}" cssitems="${cssitems}">
    <jsp:attribute name="header"><a:nav/></jsp:attribute>
    <jsp:body>
        <div class="row">
            <div class="col-sm-4 sidebar">
                <jsp:invoke fragment="left"/>
            </div>

            <div class="col-sm-8 main">
                <a:breadcrumbs items="${breadcrumbItems}"/>
                <a:alert/>
                <jsp:doBody/>
            </div>
        </div>
    </jsp:body>
</a:genericpage>
