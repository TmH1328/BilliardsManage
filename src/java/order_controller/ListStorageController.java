/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package order_controller;

import Account_controller.BaseAuthController;
import dal.StorageDBContext;
import dal.TypesDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.ItemTypes;
import model.Storage;

/**
 *
 * @author LENOVO
 */
public class ListStorageController extends BaseAuthController {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");

        int pagesize = 10;
        String page = request.getParameter("page");
        if (page == null || page.trim().length() == 0) {
            page = "1";
        }
        int pageindex = Integer.parseInt(page);

        StorageDBContext db = new StorageDBContext();
        ArrayList<Storage> storages = db.getStoragesbyPage(account.getUsername(), pageindex, pagesize);
        request.setAttribute("storages", storages);
        int count = db.count();
        int totalpage = (count % pagesize == 0) ? (count / pagesize) : (count / pagesize) + 1;
        request.setAttribute("totalpage", totalpage);
        request.setAttribute("pageindex", pageindex);

        request.getRequestDispatcher("view/order/list.jsp").forward(request, response);

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
