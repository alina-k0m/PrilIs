<%--
  Created by IntelliJ IDEA.
  User: Alina
  Date: 10.03.2024
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
First JSP
<p>Today <%= new java.util.Date() %></p>


<%--
My name is ${name} and password is ${password}
--%>


<%--
К ПЯТОМУ СПОСОБУ
--%>
<%--<form action="LoginServlet" method="POST">
    Name : <input name="name" type="text"/>
    <input type="submit"/></form>--%>

<p><font color="red">${errorMessage}</font>
</p>
<form action="LoginServlet" method="POST">
    <p>Name : <input name="name" type="text"/>
    </p>
    <p> Password : <input name="password" type="password"/>
    </p>
    <input type="submit"/></form>


</body>
</html>
