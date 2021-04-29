<%--
  Created by IntelliJ IDEA.
  User: III
  Date: 29.04.2021
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create directory</title>
</head>
<body>
<form action="create_dir" method="post">
    <p>Parent id</p>
    <input type="text" name="parent_id">
    <p>Author id</p>
    <input type="text" name="author_id">
    <p>Name</p>
    <input type="text" name="name"><br/>
    <p>Free Access</p>
    <input type="radio" id="true" name="free_access" value="true">
    <label for="true">Yes</label><br>
    <input type="radio" id="false" name="free_access" value="false">
    <label for="false">No</label><br>
    <input type="submit" value="Create">
</form>
</body>
</html>
