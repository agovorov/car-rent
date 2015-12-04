<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    Список авто
    <ul class="vehicle-list">
        <c:forEach items="${vehicles}" var="vehicle" varStatus="s">
            <li class="row-${s.count % 2}">${vehicle}</li>
        </c:forEach>
    </ul>

    <a href="/controller?action=vehicle-create">Add vehicle</a>
</body>
</html>
