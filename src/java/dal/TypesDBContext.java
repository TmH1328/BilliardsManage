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
import model.ItemTypes;
import model.Storage;

/**
 *
 * @author LENOVO
 */
public class TypesDBContext extends DBContext {

    public ArrayList<ItemTypes> getTypes() {
        ArrayList<ItemTypes> types = new ArrayList();
        try {
            String sql = "SELECT  * from  Types";

            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ItemTypes s = new ItemTypes();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("types"));
                
                types.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypesDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return types;
    }
    
}
