/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.Impl;

import context.DBContext;
import dal.PriceCourseDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.price_package;

/**
 *
 * @author MH
 */
public class PriceCourseDAOImpl extends DBContext implements PriceCourseDAO{
    @Override
    public void insertPricePackage(price_package pk) {
        try {
            String sql = "INSERT INTO [dbo].[price_package]\n"
                    + "           ([course_id]\n"
                    + "           ,[name]\n"
                    + "           ,[price]\n"
                    + "           ,[sale_price]\n"
                    + "           ,[duration]\n"
                    + "           ,[status_sale]\n"
                    + "           ,[status])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pk.getCourse_id());
            stm.setString(2, pk.getName());
            stm.setInt(3, pk.getPrice());
            stm.setInt(4, pk.getSale_price());
            stm.setInt(5, pk.getDuration());
            stm.setInt(6, pk.getStatus_sale());
            stm.setInt(7, pk.getStatus());
            stm.executeUpdate();
        } catch (SQLException ex) {

        }
    }
    @Override
    public void deletePricePackage(int pk_id) {
        try {
            String sql = "DELETE FROM [dbo].[price_package]\n"
                    + "      WHERE price_package_id =  ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pk_id);
            stm.executeUpdate();
        } catch (SQLException ex) {

        }
    }
    @Override
    public void updatePricePackage(price_package pk) {
        try {
            String sql = "UPDATE [dbo].[price_package]\n"
                    + "   SET [course_id] =?\n"
                    + "      ,[name] = ?\n"
                    + "      ,[price] = ?\n"
                    + "      ,[sale_price] = ?\n"
                    + "      ,[duration] = ?\n"
                    + "      ,[status_sale] = ?\n"
                    + "      ,[status] = ?\n"
                    + " WHERE price_package_id =  ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pk.getCourse_id());
            stm.setString(2, pk.getName());
            stm.setInt(3, pk.getPrice());
            stm.setInt(4, pk.getSale_price());
            stm.setInt(5, pk.getDuration());
            stm.setInt(6, pk.getStatus_sale());
            stm.setInt(7, pk.getStatus());
            stm.setInt(8, pk.getPrice_package_id());
            stm.executeUpdate();

        } catch (SQLException ex) {

        }

    }
    @Override
    public price_package getPricePackageByPricePacKageID(int pk_id) {
        price_package pricepk = new price_package();
        try {
            String sql = "select * from price_package where price_package_id = ? ";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pk_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                pricepk.setPrice_package_id(pk_id);
                pricepk.setCourse_id(rs.getInt(2));
                pricepk.setName(rs.getString(3));
                pricepk.setPrice(rs.getInt(4));
                pricepk.setSale_price(rs.getInt(5));
                pricepk.setDuration(rs.getInt(6));
                pricepk.setStatus_sale(rs.getInt(7));
                pricepk.setStatus(rs.getInt(8));

            }
            return pricepk;
        } catch (Exception ex) {
        }

        return null;

    }
    
    public price_package getPricePackageByCourseIDAndDuration(int courseid, int duration){
        String query="SELECT * FROM price_package\n" +
                "WHERE course_id = ? AND duration = ?";
        try{
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, courseid);
            stm.setInt(2, duration);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                return new price_package(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8));
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
    
    
    @Override
    public List<price_package> getPricePackageByCourseID(int courseId) {
        List<price_package> list = new ArrayList<>();
        try {
            String sql = "select * from price_package where course_id = ? ";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                price_package pricepk = new price_package();
                pricepk.setPrice_package_id(rs.getInt(1));
                pricepk.setCourse_id(rs.getInt(2));
                pricepk.setName(rs.getString(3));
                pricepk.setPrice(rs.getInt(4));
                pricepk.setSale_price(rs.getInt(5));
                pricepk.setDuration(rs.getInt(6));
                pricepk.setStatus_sale(rs.getInt(7));
                pricepk.setStatus(rs.getInt(8));
                list.add(pricepk);
            }
            return list;
        } catch (Exception ex) {
        }

        return null;

    }

    @Override
    public List<price_package> getAllPricePackage() {
        List<price_package> list = new ArrayList<>();
        try {
            String sql = "select * from price_package  ";

            PreparedStatement stm = connection.prepareStatement(sql);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                price_package pricepk = new price_package();
                pricepk.setPrice_package_id(rs.getInt(1));
                pricepk.setCourse_id(rs.getInt(2));
                pricepk.setName(rs.getString(3));
                pricepk.setPrice(rs.getInt(4));
                pricepk.setSale_price(rs.getInt(5));
                pricepk.setDuration(rs.getInt(6));
                pricepk.setStatus_sale(rs.getInt(7));
                pricepk.setStatus(rs.getInt(8));
                list.add(pricepk);
            }
            return list;
        } catch (Exception ex) {
            
        }
        return null;
    }

    @Override
    public int countPricePackageStatus(int status,int courseId) {
        int count = 0;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            String sql = "select count(*) from price_package where status = ? and course_id = ?";

            stm = connection.prepareStatement(sql);
            stm.setInt(1,status);
            stm.setInt(2,courseId);
            rs = stm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
        return count;             
    }
    
}
