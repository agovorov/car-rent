<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<a:page title="Main">
    <h3>Админка</h3>
    <ul>
        <li><a href="/controller?action=manufacturers-list">Manufacturers lists</a></li>
        <li><a href="/controller?action=manufacturer-create">Add manufacturer</a></li>

        <li><a href="/controller?action=vehicle-list">List vehicle</a></li>
        <li><a href="/controller?action=vehicle-create">Add vehicle</a></li>
    </ul>
</a:page>