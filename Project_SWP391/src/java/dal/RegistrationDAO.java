/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import RegistrationsList.RegistrationList;
import context.DBContext;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.price_package;
import model.user_course;

/**
 *
 * @author ADMIN
 */
public class RegistrationDAO extends DBContext {

    public List<RegistrationList> RegitrationList() {
        List<RegistrationList> list = new ArrayList<>();
        String query = "SELECT users.user_id, users.email, users.created_at, courses.course_id, courses.name, price_package.price_package_id, price_package.name AS pack, price_package.duration, price_package.price, user_course.is_archived, user_course.join_time, user_course.timeTo\n"
                + "FROM     courses INNER JOIN\n"
                + "					price_package ON courses.course_id = price_package.course_id INNER JOIN\n"
                + "					user_course ON courses.course_id = user_course.course_id INNER JOIN\n"
                + "					users ON courses.expert_id = users.user_id OR user_course.user_id = users.user_id";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new RegistrationList(rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getDate("created_at"),
                        rs.getInt("course_id"),
                        rs.getString("name"),
                        rs.getInt("price_package_id"),
                        rs.getString("pack"),
                        rs.getInt("duration"),
                        rs.getDouble("price"),
                        rs.getInt("is_archived"),
                        rs.getDate("join_time"),
                        rs.getDate("timeTo")));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public int countRegistrationList() {
        String query = "SELECT COUNT(*) AS total_commands\n"
                + "FROM (\n"
                + "    SELECT users.user_id, users.email, users.created_at, courses.course_id, courses.name, price_package.price_package_id, price_package.name AS pack, price_package.duration, price_package.price, user_course.is_archived, user_course.join_time, user_course.timeTo\n"
                + "    FROM courses\n"
                + "    INNER JOIN price_package ON courses.course_id = price_package.course_id\n"
                + "    INNER JOIN user_course ON courses.course_id = user_course.course_id\n"
                + "    INNER JOIN users ON courses.expert_id = users.user_id OR user_course.user_id = users.user_id\n"
                + ") AS subquery;";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public void editPricePackageById(String name, int price, int price_id, int course_id) {
        String query = "UPDATE price_package\n"
                + "SET [name] = ?,\n"
                + "[price] = ?,\n"
                + "WHERE price_package_id = ? AND course_id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, name);
            stm.setInt(2, price);
            stm.setInt(3, price_id);
            stm.setInt(4, course_id);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editUserCourseById(int status, int user_id, int course_id) {
        String query = "UPDATE user_course\n"
                + "SET [is_archived] = ?\n"
                + "WHERE user_id = ? AND course_id = ? ";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, status);
            stm.setInt(2, user_id);
            stm.setInt(3, course_id);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void editCourseByCourseId(String name, int id) {
        String query = "UPDATE courses\n"
                + "SET [name] = ?\n"
                + "WHERE course_id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, name);
            stm.setInt(2, id);
            stm.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<RegistrationList> pagingRegistrationList(int index) {
        List<RegistrationList> list = new ArrayList<>();
        String query = "SELECT users.user_id, users.email, users.created_at, courses.course_id, courses.name, price_package.price_package_id, price_package.name AS pack, price_package.duration, price_package.price, user_course.is_archived, user_course.join_time, user_course.timeTo\n"
                + "	FROM     courses INNER JOIN\n"
                + "				price_package ON courses.course_id = price_package.course_id INNER JOIN\n"
                + "				user_course ON courses.course_id = user_course.course_id INNER JOIN\n"
                + "				users ON courses.expert_id = users.user_id OR user_course.user_id = users.user_id\n"
                + "ORDER BY user_id\n"
                + "OFFSET ? ROWS FETCH NEXT 4 ROWS ONLY;";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, (index - 1) * 4);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new RegistrationList(rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getDate("created_at"),
                        rs.getInt("course_id"),
                        rs.getString("name"),
                        rs.getInt("price_package_id"),
                        rs.getString("pack"),
                        rs.getInt("duration"),
                        rs.getDouble("price"),
                        rs.getInt("is_archived"),
                        rs.getDate("join_time"),
                        rs.getDate("timeTo")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) {
        RegistrationDAO dao = new RegistrationDAO();
        String s = "ios";
        int a = 1;
//        Course c = dao.getCourseByCourseId(a);
//        System.out.println(c);
//        int count = dao.countRegistrationList();
//        System.out.println(count);
        List<Course> list = dao.searchCourseByName(s);
        for (Course o : list){
            System.out.println(o);
        }
    }

    public List<RegistrationList> getRegitrationListByEmail(String email) {
        List<RegistrationList> list = new ArrayList<>();
        String query = "SELECT users.user_id, users.email, users.created_at, courses.course_id, courses.name, price_package.price_package_id, price_package.name AS pack, price_package.duration, price_package.price, user_course.is_archived, user_course.join_time, user_course.timeTo\n"
                + "FROM     courses INNER JOIN\n"
                + "					price_package ON courses.course_id = price_package.course_id INNER JOIN\n"
                + "					user_course ON courses.course_id = user_course.course_id INNER JOIN\n"
                + "					users ON courses.expert_id = users.user_id OR user_course.user_id = users.user_id\n"
                + "WHERE email LIKE ?";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, "%" + email + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new RegistrationList(rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getDate("created_at"),
                        rs.getInt("course_id"),
                        rs.getString("name"),
                        rs.getInt("price_package_id"),
                        rs.getString("pack"),
                        rs.getInt("duration"),
                        rs.getDouble("price"),
                        rs.getInt("is_archived"),
                        rs.getDate("join_time"),
                        rs.getDate("timeTo")));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public Course getCourseByCourseId(int courseId) {
        Course course = new Course();
        try {
            String sql = "SELECT * FROM courses WHERE course_id = ? ";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseId);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                return new Course(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getDate(9));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return course;

    }

    public List<RegistrationList> getRegitrationListById(int id) {
        List<RegistrationList> list = new ArrayList<>();
        String query = "SELECT users.user_id, users.email, users.created_at, courses.course_id, courses.name, price_package.price_package_id, price_package.name AS pack, price_package.duration, price_package.price, user_course.is_archived, user_course.join_time, user_course.timeTo\n"
                + "FROM     courses INNER JOIN\n"
                + "					price_package ON courses.course_id = price_package.course_id INNER JOIN\n"
                + "					user_course ON courses.course_id = user_course.course_id INNER JOIN\n"
                + "					users ON courses.expert_id = users.user_id OR user_course.user_id = users.user_id\n"
                + "WHERE courses.course_id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new RegistrationList(rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getDate("created_at"),
                        rs.getInt("course_id"),
                        rs.getString("name"),
                        rs.getInt("price_package_id"),
                        rs.getString("pack"),
                        rs.getInt("duration"),
                        rs.getDouble("price"),
                        rs.getInt("is_archived"),
                        rs.getDate("join_time"),
                        rs.getDate("timeTo")));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public List<RegistrationList> getRegitrationListBySubject(String subject) {
        List<RegistrationList> list = new ArrayList<>();
        String query = "SELECT users.user_id, users.email, users.created_at, courses.course_id, courses.name, price_package.price_package_id, price_package.name AS pack, price_package.duration, price_package.price, user_course.is_archived, user_course.join_time, user_course.timeTo\n"
                + "FROM     courses INNER JOIN\n"
                + "					price_package ON courses.course_id = price_package.course_id INNER JOIN\n"
                + "					user_course ON courses.course_id = user_course.course_id INNER JOIN\n"
                + "					users ON courses.expert_id = users.user_id OR user_course.user_id = users.user_id\n"
                + "WHERE courses.name LIKE ?";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, "%" + subject + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new RegistrationList(rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getDate("created_at"),
                        rs.getInt("course_id"),
                        rs.getString("name"),
                        rs.getInt("price_package_id"),
                        rs.getString("pack"),
                        rs.getInt("duration"),
                        rs.getDouble("price"),
                        rs.getInt("is_archived"),
                        rs.getDate("join_time"),
                        rs.getDate("timeTo")));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public List<RegistrationList> getRegitrationListByStatus(int status) {
        List<RegistrationList> list = new ArrayList<>();
        String query = "SELECT users.user_id, users.email, users.created_at, courses.course_id, courses.name, price_package.price_package_id, price_package.name AS pack, price_package.duration, price_package.price, user_course.is_archived, user_course.join_time, user_course.timeTo\n"
                + "FROM     courses INNER JOIN\n"
                + "					price_package ON courses.course_id = price_package.course_id INNER JOIN\n"
                + "					user_course ON courses.course_id = user_course.course_id INNER JOIN\n"
                + "					users ON courses.expert_id = users.user_id OR user_course.user_id = users.user_id\n"
                + "WHERE is_archived = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setInt(1, status);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new RegistrationList(rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getDate("created_at"),
                        rs.getInt("course_id"),
                        rs.getString("name"),
                        rs.getInt("price_package_id"),
                        rs.getString("pack"),
                        rs.getInt("duration"),
                        rs.getDouble("price"),
                        rs.getInt("is_archived"),
                        rs.getDate("join_time"),
                        rs.getDate("timeTo")));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public List<RegistrationList> getRegitrationListBySubjectandEmail(String subject, String email) {
        List<RegistrationList> list = new ArrayList<>();
        String query = "SELECT users.user_id, users.email, users.created_at, courses.course_id, courses.name, price_package.price_package_id, price_package.name AS pack, price_package.duration, price_package.price, user_course.is_archived, user_course.join_time, user_course.timeTo\n"
                + "FROM     courses INNER JOIN\n"
                + "					price_package ON courses.course_id = price_package.course_id INNER JOIN\n"
                + "					user_course ON courses.course_id = user_course.course_id INNER JOIN\n"
                + "					users ON courses.expert_id = users.user_id OR user_course.user_id = users.user_id\n"
                + "WHERE courses.name LiKE ? AND users.email LIKE ?";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, "%" + subject + "%");
            stm.setString(2, "%" + email + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new RegistrationList(rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getDate("created_at"),
                        rs.getInt("course_id"),
                        rs.getString("name"),
                        rs.getInt("price_package_id"),
                        rs.getString("pack"),
                        rs.getInt("duration"),
                        rs.getDouble("price"),
                        rs.getInt("is_archived"),
                        rs.getDate("join_time"),
                        rs.getDate("timeTo")));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public List<Course> getAllCourse() {
        List<Course> list = new ArrayList<>();
        String query = "SELECT*FROM courses";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getDate(9)));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }
    
    public List<Course> searchCourseByName(String name) {
        List<Course> list = new ArrayList<>();
        String query = "SELECT*FROM courses\n" +
                        "WHERE [name] LIKE ?";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, "%"+name+"%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new Course(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8),
                        rs.getDate(9)));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }
    

    public List<user_course> getAllUserCourse() {
        List<user_course> list = new ArrayList<>();
        String query = "SELECT*FROM user_course";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new user_course(rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getDate(4),
                        rs.getDate(5)));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public List<RegistrationList> getRegitrationListByJoinTimel(Date jointime) {
        List<RegistrationList> list = new ArrayList<>();
        String query = "SELECT users.user_id, users.email, users.created_at, courses.course_id, courses.name, price_package.price_package_id, price_package.name AS pack, price_package.duration, price_package.price, user_course.is_archived, user_course.join_time, user_course.timeTo\n"
                + "FROM     courses INNER JOIN\n"
                + "					price_package ON courses.course_id = price_package.course_id INNER JOIN\n"
                + "					user_course ON courses.course_id = user_course.course_id INNER JOIN\n"
                + "					users ON courses.expert_id = users.user_id OR user_course.user_id = users.user_id\n"
                + "WHERE CONVERT(DATE, user_course.join_time) = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setDate(1, jointime);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new RegistrationList(rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getDate("created_at"),
                        rs.getInt("course_id"),
                        rs.getString("name"),
                        rs.getInt("price_package_id"),
                        rs.getString("pack"),
                        rs.getInt("duration"),
                        rs.getDouble("price"),
                        rs.getInt("is_archived"),
                        rs.getDate("join_time"),
                        rs.getDate("timeTo")));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public List<RegistrationList> getRegitrationListBytimeTo(Date timeTo) {
        List<RegistrationList> list = new ArrayList<>();
        String query = "SELECT users.user_id, users.email, users.created_at, courses.course_id, courses.name, price_package.price_package_id, price_package.name AS pack, price_package.duration, price_package.price, user_course.is_archived, user_course.join_time, user_course.timeTo\n"
                + "FROM     courses INNER JOIN\n"
                + "					price_package ON courses.course_id = price_package.course_id INNER JOIN\n"
                + "					user_course ON courses.course_id = user_course.course_id INNER JOIN\n"
                + "					users ON courses.expert_id = users.user_id OR user_course.user_id = users.user_id\n"
                + "WHERE CONVERT(DATE, user_course.timeTo) = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setDate(1, timeTo);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new RegistrationList(rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getDate("created_at"),
                        rs.getInt("course_id"),
                        rs.getString("name"),
                        rs.getInt("price_package_id"),
                        rs.getString("pack"),
                        rs.getInt("duration"),
                        rs.getDouble("price"),
                        rs.getInt("is_archived"),
                        rs.getDate("join_time"),
                        rs.getDate("timeTo")));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

    public List<RegistrationList> getRegitrationListByNameCourse(String name) {
        List<RegistrationList> list = new ArrayList<>();
        String query = "SELECT users.user_id, users.email, users.created_at, courses.course_id, courses.name, price_package.price_package_id, price_package.name AS pack, price_package.duration, price_package.price, user_course.is_archived, user_course.join_time, user_course.timeTo\n"
                + "FROM     courses INNER JOIN\n"
                + "					price_package ON courses.course_id = price_package.course_id INNER JOIN\n"
                + "					user_course ON courses.course_id = user_course.course_id INNER JOIN\n"
                + "					users ON courses.expert_id = users.user_id OR user_course.user_id = users.user_id\n"
                + "WHERE courses.name = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                list.add(new RegistrationList(rs.getInt("user_id"),
                        rs.getString("email"),
                        rs.getDate("created_at"),
                        rs.getInt("course_id"),
                        rs.getString("name"),
                        rs.getInt("price_package_id"),
                        rs.getString("pack"),
                        rs.getInt("duration"),
                        rs.getDouble("price"),
                        rs.getInt("is_archived"),
                        rs.getDate("join_time"),
                        rs.getDate("timeTo")));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

}
