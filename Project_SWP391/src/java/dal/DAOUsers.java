/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.users;
import model.users_roles;

/**
 *
 * @author ADMIN
 */
public class DAOUsers extends DBContext {

    public static void main(String[] args) {
        DAOUsers dao = new DAOUsers();
        List<users> list = dao.getUserByRole(4);
        String email = "customer2@gmail.com";
        
        users s = dao.getUserbyEmail(email);
        int pass = s.getUser_id();
        System.out.println(pass);
        
//        for (users object : list) {
//            System.out.println(object.toString());
//        }
    }
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

    public void editRegistration(String username, String image, String phone, int gender, String full_name, int id) {
        String query = "UPDATE users\n" +
                "SET [user_name] = ?,\n" +
                "[email] = ?,\n" +
                "[phone_number] = ?,\n" +
                "[gender] = ?,\n" +
                "[created_at] = ?\n" +
                "WHERE user_id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, username);
            stm.setString(2, image);
            stm.setString(3, phone);
            stm.setInt(4, gender);
            stm.setString(5, full_name);
            stm.setInt(6, id);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
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

    public users login(String email, String pass) {
        String query = "SELECT*FROM [users]\n"
                + "WHERE email = ? and password = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, email);
            stm.setString(2, pass);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new users(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getString(10));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public users checkEmailExist(String email) {
        String query = "SELECT* FROM users\n"
                + "WHERE [email] = ?\n";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new users(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getString(10));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    
    
    
    

    public void signUp(String name, String email, String phone, int gender, String password, int status, String fullname, Date creat_at, String img) {
        String query = "INSERT INTO users\n"
                + "VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, name);
            stm.setString(2, email);
            stm.setString(3, phone);
            stm.setInt(4, gender);
            stm.setString(5, password);
            stm.setInt(6, status);
            stm.setString(7, fullname);
            stm.setDate(8, creat_at);
            stm.setString(9, img);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void forgotPassword(String email, String pass) {
        String query = "UPDATE [users]\n"
                + "SET [password] = ?\n"
                + "WHERE [email] = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, pass);
            stm.setString(2, email);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    

    public users getUserByID(int id) {
        users user = new users();
        try {
            String sql = "SELECT * FROM users WHERE user_id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                user.setUser_id(rs.getInt(1));
                user.setUser_name(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPhone(rs.getString(4));
                user.setGender(rs.getInt(5));
                user.setPassword(rs.getString(6));
                user.setStatus(rs.getInt(7));
                user.setFullname(rs.getString(8));
                user.setCreate_at(rs.getDate(9));
            }
        } catch (Exception ex) {
            
        }

        return user;
    }

    public void editUserRegistration(String name, String email, String phone, int gen, Date created_at, int id){
        String query = "UPDATE users\n" +
                "SET [user_name] = ?,\n" +
                "[email] = ?,\n" +
                "[phone_number] = ?,\n" +
                "[gender] = ?,\n" +
                "[created_at] = ?\n" +
                "WHERE user_id = ?";
        try{
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, name);
            stm.setString(2, email);
            stm.setString(3, phone);
            stm.setInt(4, gen);
            stm.setDate(5, created_at);
            stm.setInt(6, id);
            stm.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }        
    }
    
    public void UpdateUser(String username, String image, String phone, int gender, String full_name, int id) {
        String query = "UPDATE users\n"
                + "SET [user_name] = ?, \n"
                + "	[image] = ?,\n"
                + "	[phone_number] = ?,\n"
                + "	[gender] = ?,\n"
                + "	[full_name] = ?\n"
                + "WHERE [user_id] = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, username);
            stm.setString(2, image);
            stm.setString(3, phone);
            stm.setInt(4, gender);
            stm.setString(5, full_name);
            stm.setInt(6, id);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public users getUser(int id) {
        String query = "SELECT*FROM users\n"
                + "WHERE user_id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new users(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getString(10));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    
    public users getUserbyEmail(String email) {
        String query = "SELECT*FROM users\n"
                + "WHERE email = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, email);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return new users(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getDate(9),
                        rs.getString(10));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    

    public void updatePassword(int id, String pass) {
        String query = "UPDATE users\n"
                + "SET [password] = ?\n"
                + "WHERE [user_id] = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, pass);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
