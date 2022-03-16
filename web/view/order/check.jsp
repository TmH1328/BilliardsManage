<%-- 
    Document   : check
    Created on : Mar 17, 2022, 1:10:36 AM
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
        <script src="js/paggercheck.js" type="text/javascript"></script>
    </head>
    <%
            Order order  = (Order)session.getAttribute("neworder");       
        %>
    <body>
        <a id="home" href="home">Quay về Trang Chủ</a>
        <br>
        <a id="list" href="list">Thêm Vào Hóa Đơn </a>
        <form action="confirmorder" method="POST" >
        <% if (order != null) {%>
        <table id="table" border="5">

            <tbody>
                <tr>
                   
                    <td>Tên sản phẩm</td>
               
                    <td>Số Hàng Đã Bán</td>
                    <td>Giá Bán</td>
                    <td>Tổng Tiền</td>
                    <td>Tiền Lãi</td>
         
                    
                </tr>
                <%  for (OrderDetail od : order.getDetails())    {                   %>
                    <tr>
                        <td><%=od.getStorage().getName()%></td>
                        <td><%=od.getQuantity() %></td>                                             
                        <td><%=od.getUnitprice() %></td>                        
                        <td><%=od.getTotal() %></td>
                        <td><%=od.getProfit() %></td>
                    </tr>
               <% }%>
               <tr>
                   <td colspan="3"></td>
                   <td><%=order.getTotal() %></td>
                   <td><%=order.getTotalProfit()%></td>
               </tr>
               <tr>
                   <td colspan="3">Ngày Bán Hàng</td>
                   <td><input type="date" name="orderdate" value="${requestScope.currentdate}"/></td>
                    
               </tr>
            </tbody>
        </table>    
        <div id ="containerbot" class ="pagger">        </div>
        <script>
            pagger("containerbot",${requestScope.pageindex},${requestScope.totalpage}, 3);
        </script>
        <%} else {%>
        You have not made any order
        <%}%>
        <br>
        <input type="submit" value="Xác Nhận Hóa Đơn" />
        </form>
    </body>
</html>
