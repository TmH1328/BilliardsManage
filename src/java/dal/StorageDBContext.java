/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Storage;

/**
 *
 * @author LENOVO
 */
public class StorageDBContext extends DBContext {

    public ArrayList<Storage> getStorages(String username) {
        ArrayList<Storage> storages = new ArrayList();
        try {
            String sql = "SELECT s.id, s.name, s.dateofWarehousing,s.purchaseMoney,s.quantityWarehousing,s.stocks,s.types FROM Account a INNER JOIN Storage s\n"
                    + "ON s.username = a.username \n"
                    + "WHERE s.username = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            if (username != null) {
                stm.setString(1, username);
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Storage s = new Storage();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setDateofWarehousing(rs.getDate("dateofWarehousing"));
                s.setPurchaseMoney(rs.getInt("purchaseMoney"));
                s.setQuantityWarehousing(rs.getInt("quantityWarehousing"));
                s.setStocks(rs.getInt("stocks"));
                s.setTypes(rs.getString("types"));
                storages.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return storages;
    }

    public int count() {
        ArrayList<Storage> storages = new ArrayList<>();
        try {
            String sql = "SELECT COUNT(*) as Total  FROM Storage ";
            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<Storage> getStoragesbyPage(String username,int pageindex, int pagesize) {
        ArrayList<Storage> storages = new ArrayList<>();
        try {
            String sql = "SELECT s.id, s.name, s.dateofWarehousing,s.purchaseMoney,s.quantityWarehousing,s.stocks,s.types FROM \n"
                    + "(SELECT *, ROW_NUMBER() OVER (ORDER BY id ASC) as row_index FROM Storage) s INNER JOIN Account a\n"
                    + "ON s.username = a.username \n"
                    + "	WHERE row_index >= (?-1)*?+ 1\n"
                    + "	AND row_index <= ? * ?\n"
                    + "AND s.username = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageindex);
            stm.setInt(2, pagesize);
            stm.setInt(3, pageindex);
            stm.setInt(4, pagesize);
            stm.setString(5, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Storage s = new Storage();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setDateofWarehousing(rs.getDate("dateofWarehousing"));
                s.setPurchaseMoney(rs.getInt("purchaseMoney"));
                s.setQuantityWarehousing(rs.getInt("quantityWarehousing"));
                s.setStocks(rs.getInt("stocks"));
                s.setTypes(rs.getString("types"));
                storages.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return storages;
    }
}
