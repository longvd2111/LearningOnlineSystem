/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.Impl;

import context.DBContext;
import dal.CourseDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;

/**
 *
 * @author MH
 */
public class CourseDAOImpl extends DBContext implements dal.CourseDAO {

    public static void main(String[] args) {
        CourseDAOImpl daoCourse = new CourseDAOImpl();
//        int a =1;
//        
//        for (Course o : list){
//            System.out.println(o);
//        }

    }

    @Override
    public int CountCourseByName(String courseName) {
        int count = 0;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT COUNT(course_id) FROM courses WHERE NAME LIKE ?  ";

            stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + courseName + "%");
            rs = stm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (Exception ex) {
            Logger.getLogger(CourseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                try {
                    rs.close();

                } catch (SQLException ex) {
                    Logger.getLogger(CourseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

        return 0;
    }

    //da check
    @Override
    public int CountCourseByCategory(int cateId) {

        try {
            String sql = "SELECT COUNT(*) \n"
                    + "FROM courses\n"
                    + "JOIN categories ON courses.category_id = categories.category_id\n"
                    + "WHERE categories.category_id = ? ";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cateId);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(CourseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    // da check
    @Override
    public List<Course> getCourseByCateId(int Cateid) {
        List<Course> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM courses\n"
                    + "WHERE category_id IN (SELECT category_id FROM categories WHERE category_id = ?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, Cateid);
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
            return list;
        } catch (Exception ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    // da check
    @Override
    public int CountAllCourse() {
        try {
            String sql = "select Count(course_id) from courses where status = 1";

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public List<Course> getCourseByCateIdPaging(int cateId, int pageIndex, int pageSize) {
        List<Course> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM courses JOIN categories ON courses.category_id = categories.category_id\n"
                    + "WHERE categories.category_id = ? ORDER BY created_at DESC offset (?-1)*? ROW FETCH NEXT ? ROWS only";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cateId);
            stm.setInt(2, pageIndex);
            stm.setInt(3, pageSize);
            stm.setInt(4, pageSize);
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
            Logger.getLogger(CourseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    //da check
    @Override
    public List<Course> getCourseBySearchPaging(String txt, int pageIndex, int pageSize) {
        List<Course> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM courses WHERE NAME LIKE ? AND status = 1 ORDER BY created_at DESC offset (?-1)*? ROW FETCH NEXT ? ROWS only";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + txt + "%");
            stm.setInt(2, pageIndex);
            stm.setInt(3, pageSize);
            stm.setInt(4, pageSize);
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
            Logger.getLogger(CourseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    //da check
    @Override
    public Course getCourseByCourseId(int courseId) {
        Course course = new Course();
        try {
            String sql = "SELECT * FROM courses WHERE course_id = ? ";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseId);
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
                course.setFeature(rs.getInt(10));
                return course;
            }
        } catch (Exception ex) {
            Logger.getLogger(CourseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Course> getAllCourse(int pageIndex, int pageSize) {
        List<Course> list = new ArrayList<>();
        try {
            String sql = "select * from courses where status = 1 ORDER BY created_at DESC offset (?-1)*? ROW FETCH NEXT ? ROWS only";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, pageIndex);
            stm.setInt(2, pageSize);
            stm.setInt(3, pageSize);
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
            Logger.getLogger(CourseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override

    public List<Course> findAll(String sqlQuery) {
        List<Course> list = new ArrayList<>();

        String sql = sqlQuery;
        try {
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
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Course> getCourseActiveByExpertId(int userId) {
        List<Course> list = new ArrayList<>();
        try {
            String sql = "select * from courses where expert_id = ? and status = 1";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);

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
            return list;
        } catch (Exception ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public int CountCourseByExpertId(int userId) {
        try {
            String sql = "select count(*) from courses where expert_id = ? ";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(CourseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    @Override
    public List<Course> getCourseByUserIdPaging(int userId, int pageIndex, int pageSize) {
        List<Course> list = new ArrayList<>();
        try {
            String sql = "select * from courses where expert_id = ? ORDER BY created_at DESC offset (?-1)*? ROW FETCH NEXT ? ROWS only";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, pageIndex);
            stm.setInt(3, pageSize);
            stm.setInt(4, pageSize);
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
            return list;
        } catch (Exception ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public void insertCourse(Course course) {
        try {
            String sql = "INSERT INTO [dbo].[courses]\n"
                    + "           ([expert_id]\n"
                    + "           ,[category_id]\n"
                    + "           ,[name]\n"
                    + "           ,[img_url]\n"
                    + "           ,[tagline]\n"
                    + "           ,[status]\n"
                    + "           ,[description]\n"
                    + "           ,[created_at]\n"
                    + "           ,[feature])\n"
                    + "     VALUES\n"
                    + "           (?, ?, ?, ?, ?, 0, ?, ?, ?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, course.getExpert_id());
            stm.setInt(2, course.getCategory_id());
            stm.setString(3, course.getCourse_name());
            stm.setString(4, course.getImage());
            stm.setString(5, course.getTagline());
            stm.setString(6, course.getDescription());
            stm.setDate(7, course.getCreated_at());
            stm.setInt(8, course.getFeature());
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteCourse(int course_id) {
        try {
            String sql = "DELETE FROM [dbo].[courses]\n"
                    + "      WHERE course_id = ?\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, course_id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void updateCourse(Course course) {
        try {
            String sql = "UPDATE [dbo].[courses]\n"
                    + "   SET [expert_id] = ?\n"
                    + "      ,[category_id] = ?\n"
                    + "      ,[name] = ?\n"
                    + "      ,[img_url] = ?\n"
                    + "      ,[tagline] = ?\n"
                    + "      ,[status] = ?\n"
                    + "      ,[description] = ?\n"
                    + "      ,[created_at] = ?\n"
                    + "      ,[feature] = ?\n"
                    + " WHERE [course_id] = ?\n";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, course.getExpert_id());
            stm.setInt(2, course.getCategory_id());
            stm.setString(3, course.getCourse_name());
            stm.setString(4, course.getImage());
            stm.setString(5, course.getTagline());
            stm.setInt(6, course.getStatus());
            stm.setString(7, course.getDescription());
            stm.setDate(8, course.getCreated_at());
            stm.setInt(9, course.getFeature());
            stm.setInt(10, course.getCourse_id());

            stm.executeUpdate();
            System.out.println(course);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public List<Course> getCourseByCustomerId(int userId) {
        List<Course> list = new ArrayList<>();
        try {
            String sql = "SELECT *\n"
                    + "  FROM user_course uc\n"
                    + "  join courses c on c.course_id = uc.course_id "
                    + "where expert_id = ? ";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourse_id(rs.getInt(5));
                course.setExpert_id(rs.getInt(6));
                course.setCategory_id(rs.getInt(7));
                course.setCourse_name(rs.getString(8));
                course.setImage(rs.getString(9));
                course.setTagline(rs.getString(10));
                course.setStatus(rs.getInt(11));
                course.setDescription(rs.getString(12));
                course.setCreated_at(rs.getDate(13));
                list.add(course);

            }
            return list;
        } catch (Exception ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public List<Course> getAllCourse() {
        List<Course> list = new ArrayList<>();
        try {
            String sql = "select * from courses where status = 1 ORDER BY created_at DESC";
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
            Logger.getLogger(CourseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Course> getAllCourseActiveAdmin() {
        List<Course> list = new ArrayList<>();
        try {
            String sql = "select * from courses where status = 1  ORDER BY course_id ASC";
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
            Logger.getLogger(CourseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public int CountAllCourseActive() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Course getCourseBestSeller() {
        Course course = new Course();
        try {
            String sql = "SELECT *\n"
                    + "FROM courses\n"
                    + "WHERE course_id = (\n"
                    + "    SELECT TOP 1 course_id\n"
                    + "    FROM user_course\n"
                    + "    GROUP BY course_id\n"
                    + "    ORDER BY COUNT(*) DESC\n"
                    + ");";
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
                course.setFeature(rs.getInt(10));
                return course;
            }
        } catch (Exception ex) {
            Logger.getLogger(CourseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Course> getAllCourseAdmin() {
        List<Course> list = new ArrayList<>();
        try {
            String sql = "select * from courses  ORDER BY course_id ASC";
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
            Logger.getLogger(CourseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Course> getCourseByExpertId(int userId) {
        List<Course> list = new ArrayList<>();
        try {
            String sql = "select * from courses where expert_id = ? ";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);

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
            return list;
        } catch (Exception ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    @Override
    public int countCourseUserRegisted(int course_id) {
        try {
            String sql = "select Count(course_id) from user_course where course_id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, course_id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(CourseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    @Override
    public void deleteUserCourse(int userId, int courseId) {
        try {
            String sql = "DELETE FROM [dbo].[user_course]\n"
                    + "      WHERE user_id = ? and course_id = ?\n";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, courseId);
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
}
