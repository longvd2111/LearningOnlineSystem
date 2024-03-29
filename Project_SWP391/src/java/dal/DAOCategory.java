/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import dal.Impl.CategoryDAOImpl;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.cate_dimension;
import model.categories;


/**
 *
 * @author MH
 */
public class DAOCategory extends DBContext {
    
    public static void main(String[] args) {
        DAOCategory dao = new DAOCategory();
        List<cate_dimension> list = dao.getAllCateDimen();
        for (cate_dimension object : list) {
            System.out.println(object.getCate_dimension_name());
        }
    }
    
    public List<cate_dimension> getAllCateDimen(){
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
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

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
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    //da check
//    public List<sub_categories> getAllSubCategory() {
//        List<sub_categories> list = new ArrayList<>();
//        try {
//            String sql = "SELECT * FROM sub_categories ";
//
//            PreparedStatement stm = connection.prepareStatement(sql);
//
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                sub_categories subCate = new sub_categories();
//                subCate.setCategory_id(rs.getInt(2));
//                subCate.setSub_category_id(rs.getInt(1));
//                subCate.setSub_category_name(rs.getString(3));
//
//                list.add(subCate);
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(DAOCourse.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return list;
//
//    }

    //da check
//    public List<sub_categories> getSubCategoryByCategory(int cateId) {
//        List<sub_categories> list = new ArrayList<>();
//        try {
//            String sql = "SELECT * FROM sub_categories where category_id=?";
//
//            PreparedStatement stm = connection.prepareStatement(sql);
//            stm.setInt(1, cateId);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                sub_categories subCate = new sub_categories();
//                subCate.setCategory_id(rs.getInt(2));
//                subCate.setSub_category_id(rs.getInt(1));
//                subCate.setSub_category_name(rs.getString(3));
//
//                list.add(subCate);
//            }
//        } catch (Exception ex) {
//            Logger.getLogger(DAOCourse.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        return list;
//
//    }

    public categories getCateByCourseId(int courseId) {
        categories cate = new categories();
        try {
            String sql = "SELECT \n"
                    + "    categories.category_id,\n"
                    + "    categories.category_name\n"
                    + "FROM \n"
                    + "    courses\n"
                    + "JOIN \n"
                    + "    sub_categories ON courses.sub_category_id = sub_categories.sub_category_id\n"
                    + "JOIN \n"
                    + "    categories ON sub_categories.category_id = categories.category_id\n"
                    + "WHERE \n"
                    + "    courses.course_id = ?;";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                cate.setCategory_id(rs.getInt(1));
                cate.setCategory_name(rs.getString(2));
                return cate;
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    

}
