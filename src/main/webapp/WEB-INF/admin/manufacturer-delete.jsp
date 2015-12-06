<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="a" tagdir="/WEB-INF/tags" %>
<a:page title="Manufacturers delete confirmation">

    <form action="" method="post">
        <input type="hidden" name="remove" value="1">
        <div>Are you sure you want to delete record: "${model.getTitle()}"?</div>
        <button class="btn btn-default" type="submit">Yes, delete</button> or go <a href="/controller?action=manufacturers-list">back</a>
    </form>

    <a href="/controller?action=manufacturers-list">List of manufacturers</a>
    <a href="/controller?action=manufacturer-create">Add manufacturer</a>
</a:page>
