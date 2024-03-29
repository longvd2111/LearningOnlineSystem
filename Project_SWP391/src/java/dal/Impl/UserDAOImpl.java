/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.Impl;

import context.DBContext;
import dal.UserDAO;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.users;
import model.users_roles;



/**
 *
 * @author MH
 */
public class UserDAOImpl extends DBContext implements UserDAO{

    @Override
    public List<users> getUserByRole(int role_id) {
        String sql = "SELECT u.*\n"
                + "FROM [users] u\n"
                + "JOIN [users_roles] ur ON u.[user_id] = ur.[user_id]\n"
                + "WHERE ur.[role_id] = ?";
        List<users> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, role_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
               users user = new users(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getString(10));
               list.add(user);
               
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
    @Override
    public users_roles getRoleByUserID(int user_id) {
        String sql = "select * from users_roles where user_id = ?";
        users_roles users_role = new users_roles();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, user_id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                users_role.setUser_id(rs.getInt(1));
                users_role.setRole_id(rs.getInt(2));
            }
            return users_role;
        } catch (Exception e) {
        }
        return null;
    }


    @Override
    public users login(String email, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public users checkEmailExist(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void signUp(String name, String email, String phone, int gender, String password, int status, String fullname, Date creat_at) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void forgotPassword(String email, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public users getUserByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void UpdateUser(String username, String image, String phone, int gender, String full_name, int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public users getUser(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updatePassword(int id, String pass) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<users> getUser() {
        String sql = "select * from users";
        List<users> list = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {                
               users user = new users(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getString(10));
               list.add(user);
               
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }
    
}
