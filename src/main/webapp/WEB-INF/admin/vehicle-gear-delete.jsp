<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<a:page title="Vehicle`s gear shift delete confirmation">

    <form action="" method="post">
        <input type="hidden" name="remove" value="1">
        <div>Are you sure you want to delete record: "${model.getValue()}"?</div>
        <button class="btn btn-default" type="submit">Yes, delete</button> or go <a href="/controller?action=vehicle-gear-list">back</a>
    </form>

    <a href="/controller?action=vehicle-gear-list">List of vehicle`s gearshift</a>
    <a href="/controller?action=vehicle-gear-create">Add vehicle`s gearshift</a>
</a:page>