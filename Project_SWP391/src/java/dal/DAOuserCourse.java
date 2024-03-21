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
import model.Course;
import model.user_course;

/**
 *
 * @author MH
 */
public class DAOuserCourse extends DBContext {

    public Course getCourseBestSeller() {
        Course course = new Course();
        try {
            String sql = "SELECT c.*\n"
                    + "FROM courses c\n"
                    + "JOIN (\n"
                    + "    SELECT TOP 1 course_id\n"
                    + "    FROM user_course\n"
                    + "    GROUP BY course_id\n"
                    + "    ORDER BY COUNT(course_id) DESC\n"
                    + ") uc ON c.course_id = uc.course_id;";

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                course.setCourse_id(rs.getInt(1));
                course.setExpert_id(rs.getInt(2));
                course.setCategory_id(rs.getInt(3));
                course.setCourse_name(rs.getString(4));
                course.setImage(rs.getString(5));
                course.setTagline(rs.getString(6));
                course.setStatus(rs.getInt(7));
                course.setDescription(rs.getString(8));
                course.setCreated_at(rs.getDate(9));

            }
        } catch (Exception ex) {
            
        }

        return course;

    }
    
    public void editUserCourse(int user_id, int course_id, int status, Date join_time, Date timeTo){
        String query = "UPDATE user_course\n" +
                "SET [course_id] = ?,\n" +
                "[is_archived] = ?,\n" +
                "[join_time] = ?,\n" +
                "[timeTo] = ?\n" +
                "WHERE user_id = ?";
        try{
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, course_id);
            stm.setInt(2, status);
            stm.setDate(3, join_time);
            stm.setDate(4, timeTo);
            stm.setInt(5, user_id);
            stm.executeUpdate();
        } catch (Exception e){
            System.out.println(e);
        }
    }
    
     public List<user_course> getAllUsetCourse() {
        List<user_course> list = new ArrayList<>();
        try {
            String sql = " SELECT * FROM user_course";

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                list.add(new user_course(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getDate(4),
                        rs.getDate(5)));

            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return list;

    }
    
    
    public List<Course> getCourseTop6BestSeller() {
        List<Course> list = new ArrayList<Course>();
        try {
            String sql = " SELECT top 6 * FROM (courses c left outer JOIN (SELECT top 6 course_id\n"
                    + "  FROM user_course\n"
                    + "  GROUP BY course_id\n"
                    + "  ORDER BY COUNT(course_id) DESC)\n"
                    + "  uc ON c.course_id = uc.course_id)";

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {

                Course course = new Course();
                course.setCourse_id(rs.getInt(1));
                course.setExpert_id(rs.getInt(2));
                course.setCategory_id(rs.getInt(3));
                course.setCourse_name(rs.getString(4));
                course.setImage(rs.getString(5));
                course.setTagline(rs.getString(6));
                course.setStatus(rs.getInt(7));
                course.setDescription(rs.getString(8));
                course.setCreated_at(rs.getDate(9));
                list.add(course);

            }
        } catch (Exception ex) {
            
        }

        return list;

    }

     public List<Course> getAllUserCourses(int userId) {
    List<Course> userCourses = new ArrayList<>();
    try {
        String sql = "SELECT c.course_id, c.expert_id, c.category_id, c.name AS course_name, c.img_url, c.tagline, c.status, c.description, c.created_at, c.feature, uc.join_time\n" +
                        "FROM courses c\n" +
                        "INNER JOIN user_course uc ON c.course_id = uc.course_id\n" +
                        "WHERE uc.user_id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, userId);
        ResultSet rs = stm.executeQuery();
        
        while (rs.next()) {
            Course course = new Course();
            course.setCourse_id(rs.getInt("course_id"));
            course.setExpert_id(rs.getInt("expert_id"));
            course.setCategory_id(rs.getInt("category_id"));
            course.setCourse_name(rs.getString("course_name"));
            course.setImage(rs.getString("img_url"));
            course.setTagline(rs.getString("tagline"));
            course.setStatus(rs.getInt("status"));
            course.setDescription(rs.getString("description"));
            course.setCreated_at(rs.getDate("created_at"));
            // Đảm bảo các trường còn lại của đối tượng Course được thiết lập đúng
            
            userCourses.add(course);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return userCourses;
}
     
    public void deleteUserCourseByCourseIdAndUserId(int course_id, int user_id) {
    try {
        String sql = "DELETE FROM user_course\n" +
                    "WHERE course_id = ? AND user_id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1,course_id );
        stm.setInt(2, user_id);
        int rowsAffected = stm.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Delete successful!");
        } else {
            System.out.println("Delete course Unsuccessful");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
     
     public void deleteUserCourse(int userid, String cid) {
    try {
        String sql = "DELETE FROM user_course WHERE user_id = ? AND course_id = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, userid);
        stm.setString(2, cid);
        int rowsAffected = stm.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Delete successful!");
        } else {
            System.out.println("Delete course Unsuccessful");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}
    public static void main(String[] args) {
        DAOuserCourse dao = new DAOuserCourse();
        
        
        System.out.println(dao.getAllUsetCourse());
    }
    
    public  user_course getUserCourseById(int user_id, int course_id){
        user_course u = new user_course();
        String sql = "SELECT*FROM user_course\n" +
            "WHERE user_id = ? AND course_id = ?";
        try{
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, user_id);
            stm.setInt(2, course_id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                return new user_course(rs.getInt("user_id"),
                        rs.getInt("course_id"),
                        rs.getInt("is_archived"),
                        rs.getDate("join_time"),
                        rs.getDate("timeTo"));
            }
            
        } catch (Exception e){
            System.out.println(e);
        }
        
        return u;
        
    }
    
}
