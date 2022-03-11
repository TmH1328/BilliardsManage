<%-- 
    Document   : display
    Created on : Mar 11, 2022, 11:05:09 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1">
            </thead>
            <tbody>
                <tr>
                    <td>ID</td>
                    <td>Name</td>
                    <td>Date of Warehousing</td>
                    <td>Purchase Money</td>
                    <td>Quantity Warehousing</td>
                    <td>Stocks</td>
                    <td>Types</td>
                </tr>
                <c:forEach items="${requestScope.storages}" var="s">
                <tr>
                    <td>${s.id}</td>
                    <td>${s.name}</td>
                    <td>${s.dateofWarehousing}</td>
                    <td>${s.purchaseMoney}</td>
                    <td>${s.quantityWarehousing}</td>
                    <td>${s.stocks}</td>
                    <td>${s.types}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
