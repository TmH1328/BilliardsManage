<%-- 
    Document   : confirmorder
    Created on : Mar 18, 2022, 12:33:12 AM
    Author     : LENOVO
--%>

<%@page import="model.OrderDetail"%>
<%@page import="model.Order"%>
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
        <a id="home" href="home">Quay về Trang Chủ</a>
        <br>
        <a id="list" href="list">Tạo hóa đơn cho hôm nay </a>
        <br>
        Chọn hóa đơn theo ngày
        
        <form action="check" method="POST" id="frmSearch">
            Date 
            <input type="date" name="orderdate" value="${requestScope.currentdate}"/>
            <input type="submit" value="Chọn" />
        </form>

        <c:if test="${requestScope.orderDetails.size() gt 0}">
            <table id="table" border="5">
                <tbody>
                    <tr>
                        <td>Tên sản phẩm</td>
                        <td>Số Hàng Đã Bán</td>
                        <td>Giá Bán</td>
                        <td>Tổng Tiền</td>
                        <td>Tiền Lãi</td>

                    </tr>
                    <c:forEach items="${requestScope.orderDetails}" var="od">
                        <tr>
                            <td>${od.storage.name}</td>                                        
                            <td>${od.storage.quantitysell}</td>   
                            <td>${od.unitprice}</td>
                            <td>${od.getTotal()}</td>
                            <td>${od.getProfit()}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="3"></td>
                        <td></td>
                        <td></td>
                    </tr>

                </tbody>
            </table>    
        </c:if>
        <c:if test="${requestScope.orderDetails.size() eq 0}">
            You have not made any order
        </c:if>


    </body>
</html>