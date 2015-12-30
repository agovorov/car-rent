<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setBundle basename="messages/messages"/>
<fmt:message key="common.contact.title" var="pageHeader"/>

<a:genericpage title="${title}" jsitems="${jsitems}" cssitems="${cssitem}">
    <jsp:attribute name="header"><a:nav/></jsp:attribute>
    <jsp:attribute name="menu">
        <h1><fmt:message key="common.contact.title"/></h1>
    </jsp:attribute>

    <jsp:body>
        <a:breadcrumbs items="${breadcrumbItems}"/>
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec risus massa, tincidunt eu nulla quis, dictum efficitur orci. Donec pulvinar, orci quis vehicula fringilla, nisl mi dictum lorem, et vestibulum dui libero id tellus. Nam condimentum scelerisque justo. Etiam blandit, felis at auctor ullamcorper, dui turpis aliquam velit, commodo fringilla metus libero ac nulla. Donec id metus ut mauris sodales faucibus. Sed eu elit elementum, sagittis turpis non, sagittis mauris. Vestibulum lobortis dui magna, at venenatis tellus lobortis eget. Vivamus non euismod nunc. Integer tellus nisi, iaculis a laoreet et, bibendum eget mi. Suspendisse a sollicitudin lacus, nec molestie felis. Sed sit amet suscipit purus. Vivamus ut mi nisl. Donec volutpat est quis magna imperdiet maximus.</p>
        <p>karaganda, Lenina 3a, Phone: 555-213-123</p>
    </jsp:body>
</a:genericpage>