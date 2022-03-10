<%-- 
    Document   : register
    Created on : Mar 10, 2022, 8:12:50 PM
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
        <form action="register" method="POST">
            Display name: <input type="text" placeholder="Displayname" name="displayname" /> <br/>
            Username: <input type="text" placeholder="Username" name="username" /> <br/>
            Password: <input type="password" placeholder="Password" name="password"/> <br/>
            Re-Enter Password: <input type="password" placeholder="Repeat Password" name="repassword" required><br>
            <p >${registerFailed}</p>
            <input type="submit" value="Register" /><br>
            <a href="login">Already have an Account?</a><br>
        </form>
    </body>
</html>
