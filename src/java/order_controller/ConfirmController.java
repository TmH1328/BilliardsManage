/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order_controller;

import Account_controller.BaseAuthController;
import dal.OrderDBContext;
import dal.StorageDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Order;
import model.OrderDetail;
import model.Storage;

/**
 *
 * @author LENOVO
 */
public class ConfirmController extends BaseAuthController {

    
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        
        request.setAttribute("currentdate", date);
        
        request.getRequestDispatcher("view/order/check.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        Order neworder = (Order) session.getAttribute("neworder");
        
        StorageDBContext dbs = new StorageDBContext();
       

        String[] sids = request.getParameterValues("sid");
        String[] quantity = request.getParameterValues("quantity");
        String raw_orderdate =  request.getParameter("orderdate");
        //validate data
        Date orderdate = Date.valueOf(raw_orderdate);
        
        
        Order o = new Order();
       // o.setId(id);
        o.setOrderdate(orderdate);
        //cai profit order = total profit
        o.setProfit(neworder.getTotalProfit());
        
//        for (String sid : sids) {
//            int storageid = Integer.parseInt(sid);
//            Storage s = dbs.getStorage(storageid, account.getUsername());
//            OrderDetail od  = new OrderDetail();
//            od.setStorage(s);
//            od.setOrder(neworder);
//            od.setQuantity(10);
//            od.setUnitprice(s.getUnitprice());
//            o.getDetails().add(od);
//        }
        for (int i = 0 ; i < sids.length; i++) {
            int storageid = Integer.parseInt(sids[i]);
            int validatequantity = Integer.parseInt(quantity[i]);
            Storage s = dbs.getStorage(storageid, account.getUsername());
            OrderDetail od  = new OrderDetail();
            od.setStorage(s);
            od.setOrder(neworder);
            od.setQuantity(validatequantity);
            od.setUnitprice(s.getUnitprice());
            o.getDetails().add(od);
        }
        OrderDBContext db = new OrderDBContext();
        db.insert(o);
        request.getSession().setAttribute("neworder", null);
        
        

//        response.getWriter().println("done");
        request.getRequestDispatcher("view/order/confirmorder.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
