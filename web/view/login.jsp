<%-- 
    Document   : login
    Created on : Feb 16, 2022, 10:16:36 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="login" method="POST">
            Username: <input type="text" name="username" /> <br/>
            Password: <input type="password" name="password"/> <br/>
            <input type="submit" value="Đăng Nhập" /><br>
            <a href="register">Tạo tài khoản mới</a><br>
        </form>
    </body>
</html>
