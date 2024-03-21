package dal;

import context.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.user_course;
import model.users;

public class DAOCourseRegister extends DBContext {

    public boolean registerUserForCourse(int user_id, int course_id) {
    try {
        String sql = "INSERT INTO user_course (user_id, course_id, is_archived, join_time,timeTo) VALUES (?, ?, 1, GETDATE(),GETDATE())";
        try (
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user_id);
            statement.setInt(2, course_id);

            // Execute the INSERT query
            int rowsAffected = statement.executeUpdate();

            // If the INSERT is successful, return true
            if (rowsAffected > 0) {
                return true;
            }
        }
    } catch (SQLException ex) {
        Logger.getLogger(DAOCourseRegister.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false;
}
    public boolean checkUserRegistered(int userId, int courseId) {
        try {
            String sql = "SELECT * FROM user_course WHERE user_id = ? AND course_id = ?";
            try (
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, userId);
                statement.setInt(2, courseId);
                
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next(); // Trả về true nếu có kết quả, ngược lại là false
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCourseRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    
    public users getUserByEmail(String email) {
        users user = null;
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            try (
                 PreparedStatement stm = connection.prepareStatement(sql)) {
                stm.setString(1, email);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        user = new users();
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
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCourseRegister.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }

    
     

    public static void main(String[] args) {
     
    }
    
}
        
