<%@tag %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<fmt:setBundle basename="messages/messages"/>
<h3>ADMIN`s</h3>
<ul class="nav nav-sidebar">
    <li><a href="/controller?action=manufacturer-list"><fmt:message key="manufacturer.table.list"/></a></li>
    <li><a href="/controller?action=manufacturer-create"><fmt:message key="manufacturer.table.create"/></a></li>
    <li role="separator" class="divider"></li>
    <li><a href="/controller?action=color-list"><fmt:message key="color.list.title"/></a></li>
    <li><a href="/controller?action=color-create"><fmt:message key="color.create.title"/></a></li>
    <li role="separator" class="divider"></li>
    <li><a href="/controller?action=vehicle-type-list"><fmt:message key="type.list.title"/></a></li>
    <li><a href="/controller?action=vehicle-type-create"><fmt:message key="type.create.title"/></a></li>
    <li role="separator" class="divider"></li>
    <li><a href="/controller?action=vehicle-fuel-list"><fmt:message key="fuel.list.title"/></a></li>
    <li><a href="/controller?action=vehicle-fuel-create"><fmt:message key="fuel.create.title"/></a></li>
    <li role="separator" class="divider"></li>
    <li><a href="/controller?action=vehicle-gear-list"><fmt:message key="gear.list.title"/></a></li>
    <li><a href="/controller?action=vehicle-gear-create"><fmt:message key="gear.create.title"/></a></li>
    <li role="separator" class="divider"></li>
    <li><a href="/controller?action=vehicle-list"><fmt:message key="vehicle.list.href"/></a></li>
    <li><a href="/controller?action=vehicle-create"><fmt:message key="vehicle.create.href"/></a></li>
</ul>
<hr>
<h3>CLIENT`s</h3>
<ul class="nav nav-sidebar">
    <li><a href="">Nav item</a></li>
</ul>
<hr>
<h3>COMMON</h3>
<ul class="nav nav-sidebar">
    <li><a href="">Nav item again</a></li>
</ul>