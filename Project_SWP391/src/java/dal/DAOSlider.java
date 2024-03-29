/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.posts;
import model.slider;

/**
 *
 * @author caomi
 */
public class DAOSlider extends DBContext{
    public List<slider> getAllSlider(){
        List<slider> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM slider ";

            PreparedStatement stm = connection.prepareStatement(sql);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                slider s = new slider();
                s.setSlider_id(rs.getInt(1));
                s.setAuthor_id(rs.getInt(2));
                s.setTitle(rs.getString(3));
                s.setImage(rs.getString(4));
                s.setBacklink(rs.getString(5));
                s.setStatus(rs.getInt(6));
                s.setNotes(rs.getString(7));
                list.add(s);
            }
        } catch (Exception ex) {
             Logger.getLogger(DAOSlider.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
        
        
    }
    
    
    public List<slider> getSliderByTitle(String title){
        List<slider> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM slider\n" +
                        "WHERE [title] LIKE ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%"+title+"%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                slider s = new slider();
                s.setSlider_id(rs.getInt(1));
                s.setAuthor_id(rs.getInt(2));
                s.setTitle(rs.getString(3));
                s.setImage(rs.getString(4));
                s.setBacklink(rs.getString(5));
                s.setStatus(rs.getInt(6));
                s.setNotes(rs.getString(7));
                list.add(s);
            }
        } catch (Exception ex) {
             Logger.getLogger(DAOSlider.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
        
        
    }

public List<slider> getSliderByStatus(int status){
        List<slider> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM slider\n" +
                        "WHERE [status] LIKE ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, status);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                slider s = new slider();
                s.setSlider_id(rs.getInt(1));
                s.setAuthor_id(rs.getInt(2));
                s.setTitle(rs.getString(3));
                s.setImage(rs.getString(4));
                s.setBacklink(rs.getString(5));
                s.setStatus(rs.getInt(6));
                s.setNotes(rs.getString(7));
                list.add(s);
            }
        } catch (Exception ex) {
             Logger.getLogger(DAOSlider.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
        
        
    }
     
    public List<slider> getSliderByTitleORBacklink(String title, String backlink){
        List<slider> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM slider\n" +
                        "WHERE [title] LIKE ?\n" +
                        "AND [backlink] LiKE ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1,"%"+title+"%");
            stm.setString(2,"%"+backlink+"%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                slider s = new slider();
                s.setSlider_id(rs.getInt(1));
                s.setAuthor_id(rs.getInt(2));
                s.setTitle(rs.getString(3));
                s.setImage(rs.getString(4));
                s.setBacklink(rs.getString(5));
                s.setStatus(rs.getInt(6));
                s.setNotes(rs.getString(7));
                list.add(s);
            }
        } catch (Exception ex) {
             Logger.getLogger(DAOSlider.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
        
        
    } 
     
     
    
    public List<slider> getSliderByTitleAndBacklinkAndStatus(String title, String backlink, int status){
        List<slider> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM slider\n" +
                        "WHERE [title] LIKE ?\n" +
                        "AND [backlink] LiKE ?\n" +
                        "AND [status] LIKE ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1,"%"+title+"%");
            stm.setString(2,"%"+backlink+"%");
            stm.setInt(3, status);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                slider s = new slider();
                s.setSlider_id(rs.getInt(1));
                s.setAuthor_id(rs.getInt(2));
                s.setTitle(rs.getString(3));
                s.setImage(rs.getString(4));
                s.setBacklink(rs.getString(5));
                s.setStatus(rs.getInt(6));
                s.setNotes(rs.getString(7));
                list.add(s);
            }
        } catch (Exception ex) {
             Logger.getLogger(DAOSlider.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
        
        
    }

public List<slider> getSliderByTitle(String title, int status){
        List<slider> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM slider\n" +
                    "WHERE [title] LIKE ?\n" +
                    "AND [status] LiKE ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%"+title+"%");
            stm.setInt(2, status);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                slider s = new slider();
                s.setSlider_id(rs.getInt(1));
                s.setAuthor_id(rs.getInt(2));
                s.setTitle(rs.getString(3));
                s.setImage(rs.getString(4));
                s.setBacklink(rs.getString(5));
                s.setStatus(rs.getInt(6));
                s.setNotes(rs.getString(7));
                list.add(s);
            }
        } catch (Exception ex) {
             Logger.getLogger(DAOSlider.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
        
        
    }
    
    
    
    
    
    public slider getSliderById(int id){
        slider s = new slider();
        try {
            String sql = "SELECT * FROM slider where [slider_id] = ? ";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                s.setSlider_id(rs.getInt(1));
                s.setAuthor_id(rs.getInt(2));
                s.setTitle(rs.getString(3));
                s.setImage(rs.getString(4));
                s.setBacklink(rs.getString(5));
                s.setStatus(rs.getInt(6));
                s.setNotes(rs.getString(7));
            }
        } catch (Exception ex) {
             Logger.getLogger(DAOSlider.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return s;
        
        
    }
    
    public void editSliderById(String title, String image, String backlink, int status, String note, int id){
        try{
            String sql = "UPDATE [slider]\n" +
                    "Set [title] = ?,\n" +
                    "[image] = ?,\n" +
                    "[backlink] = ?,\n" +
                    "[status]=?,\n" +
                    "[notes]=?\n" +
                    "WHERE [slider_id] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, title);
            stm.setString(2, image);
            stm.setString(3, backlink);
            stm.setInt(4,status );
            stm.setString(5, note);
            stm.setInt(6, id);
            stm.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }

 public List<slider> getAllActiveSlider(){
        List<slider> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM slider where status = 1 ";

            PreparedStatement stm = connection.prepareStatement(sql);
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                slider s = new slider();
                s.setSlider_id(rs.getInt(1));
                s.setAuthor_id(rs.getInt(2));
                s.setTitle(rs.getString(3));
                s.setImage(rs.getString(4));
                s.setBacklink(rs.getString(5));
                s.setStatus(rs.getInt(6));
                s.setNotes(rs.getString(7));
                list.add(s);
            }
        } catch (Exception ex) {
             Logger.getLogger(DAOSlider.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
        
        
    }
    public static void main(String[] args) {

        //Check Get Course by cateID
        DAOSlider dao = new DAOSlider();
        String title = "t";
        int st = 1;
        String back ="2";
        List<slider> list = dao.getSliderByTitleAndBacklinkAndStatus(title, back, st);
        for(slider s : list){
            System.out.println(s);
        }
//        slider s = dao.getSliderByBacklinkAndTitle("the", "");
//        System.out.println(s);
    }

}
