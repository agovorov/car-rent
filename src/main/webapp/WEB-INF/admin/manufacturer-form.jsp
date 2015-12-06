<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<a:page title="Main">
    <h1>Manufacturer</h1>

    <form method="post">
        <input type="text" name="manufacturer-name" maxlength="50" placeholder="Enter manufacturer name"
               value="${vehicleManufacturer.getTitle()}">
        <button class="btn btn-default" type="submit">Save</button>
        <c:if test="${not empty errorMessage}">
            <div class="error">${errorMessage}</div>
        </c:if>
    </form>

    <a:alert/>
    <li><a href="/">Index page</a></li>
    <li><a href="/controller?action=manufacturers-list">Manufacturers lists</a></li>
</a:page>