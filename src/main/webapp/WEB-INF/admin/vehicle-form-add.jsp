<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andy
  Date: 04.12.2015
  Time: 13:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <h1>Add vehicle</h1>
    <form method="post">
        <input type="text" name="vehicle-name" value="">
        <button class="btn btn-default" type="submit">Add</button>
        <c:if test="${not empty vehicleError}">
            <div class="error">${vehicleError}</div>
        </c:if>
    </form>
</body>
</html>
