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

    public ArrayList<Storage> getStoragesbyPage(String username, int pageindex, int pagesize) {
        ArrayList<Storage> storages = new ArrayList<>();
        try {
            String sql = "SELECT s.id, s.name, s.dateofWarehousing,s.purchaseMoney,s.quantityWarehousing,s.stocks,s.types FROM \n"
                    + "(SELECT *, ROW_NUMBER() OVER (ORDER BY dateofWarehousing DESC) as row_index FROM Storage) s INNER JOIN Account a\n"
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

    public void insertItems(Storage s, String username) {
        String sql = "INSERT INTO [Storage]\n"
                + "           ([name]\n"
                + "           ,[dateofWarehousing]\n"
                + "           ,[purchaseMoney]\n"
                + "           ,[quantityWarehousing]\n"
                + "           ,[stocks]\n"
                + "           ,[types]\n"
                + "           ,[username])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);

            stm.setString(1, s.getName());
            stm.setDate(2, s.getDateofWarehousing());
            stm.setInt(3, s.getPurchaseMoney());
            stm.setInt(4, s.getQuantityWarehousing());
            stm.setInt(5, s.getStocks());
            stm.setString(6, s.getTypes());
            stm.setString(7, username);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public Storage getStorage(int id, String username) {
        try {
            String sql = "SELECT * FROM Storage\n"
                    + "WHERE username = ?\n"
                    + "AND id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(2, id);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Storage s = new Storage();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setDateofWarehousing(rs.getDate("dateofWarehousing"));
                s.setPurchaseMoney(rs.getInt("purchaseMoney"));
                s.setQuantityWarehousing(rs.getInt("quantityWarehousing"));
                s.setStocks(rs.getInt("stocks"));
                s.setTypes(rs.getString("types"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateItems(Storage s, String username) {
        String sql = "UPDATE [Storage]\n"
                + "   SET [name] = ?\n"
                + "      ,[dateofWarehousing] = ?\n"
                + "      ,[purchaseMoney] = ?\n"
                + "      ,[quantityWarehousing] = ?\n"
                + "      ,[stocks] = ?\n"
                + "      ,[types] = ?\n"
                + "      ,[username] = ?\n"
                + " WHERE [id] = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);

            stm.setInt(8, s.getId());
            stm.setString(1, s.getName());
            stm.setDate(2, s.getDateofWarehousing());
            stm.setInt(3, s.getPurchaseMoney());
            stm.setInt(4, s.getQuantityWarehousing());
            stm.setInt(5, s.getStocks());
            stm.setString(6, s.getTypes());
            stm.setString(7, username);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void deleteStudent(Storage s, String username) {
        String sql = "DELETE FROM [Storage]\n"
                + "      WHERE id = ? \n"
                + "	  AND username = ?";
        PreparedStatement stm = null;
        try {
            stm = connection.prepareStatement(sql);

            stm.setInt(1, s.getId());
            stm.setString(2, username);

            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StorageDBContext.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}
