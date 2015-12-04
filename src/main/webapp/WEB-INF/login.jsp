<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <input type="text" name="login">
    ${loginError}
    <input type="text" name="password">
    ${passwordError}
    <button type="submit">REgister</button>
</form>
</body>
</html>
