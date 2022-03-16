/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class Order {
    private ArrayList<OrderDetail> details = new ArrayList<>();
    private int id;
    private Date orderdate;
    private float profit;
    
    public int getSize() {
        return details.size();
    }
    
    public float getTotalProfit() {
        float sum =0 ;
        for (OrderDetail detail : details) {
            sum += detail.getProfit();
        }
        return sum;
    
    }
    
    public int getTotal() {
        int sum =0 ;
        for (OrderDetail detail : details) {
            sum += detail.getTotal();
        }
        return sum;
    }
    public ArrayList<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<OrderDetail> details) {
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public float getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }
    
}
