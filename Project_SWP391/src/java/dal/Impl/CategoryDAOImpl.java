/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.Impl;

import context.DBContext;
import dal.CategoryDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.cate_dimension;
import model.categories;

/**
 *
 * @author MH
 */
public class CategoryDAOImpl extends DBContext implements CategoryDAO{

    @Override
    public List<cate_dimension> getAllCateDimen() {
        List<cate_dimension> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM cate_dimension ";

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                cate_dimension cate = new cate_dimension();
                cate.setCate_dimension_id(rs.getInt(1));
                cate.setCate_dimension_name(rs.getString(2));

                list.add(cate);
            }
            return list;
        } catch (Exception ex) {
            
        }

        return null;
    }
    

    @Override
    public List<categories> getAllCategory() {
        List<categories> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Categories ";

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                categories cate = new categories();
                cate.setCategory_id(rs.getInt(1));
                cate.setCategory_name(rs.getString(2));

                list.add(cate);
            }
            return list;
        } catch (Exception ex) {
        }

        return null;
    }
    
}
