/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author LENOVO
 */
public class Storage {

    private int id;
    private String name;
    private Date dateofWarehousing;
    private int quantityWarehousing;
    private int purchaseMoney;
    private int stocks;
    private String types;
    private int unitprice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateofWarehousing() {
        return dateofWarehousing;
    }

    public void setDateofWarehousing(Date dateofWarehousing) {
        this.dateofWarehousing = dateofWarehousing;
    }

    public int getQuantityWarehousing() {
        return quantityWarehousing;
    }

    public void setQuantityWarehousing(int quantityWarehousing) {
        this.quantityWarehousing = quantityWarehousing;
    }

    public int getPurchaseMoney() {
        return purchaseMoney;
    }

    public void setPurchaseMoney(int purchaseMoney) {
        this.purchaseMoney = purchaseMoney;
    }

    public int getStocks() {
        return stocks;
    }

    public void setStocks(int stocks) {
        this.stocks = stocks;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public int getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(int unitprice) {
        this.unitprice = unitprice;
    }
    
}
