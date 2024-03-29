/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.List;
import model.Course;

/**
 *
 * @author MH
 */
public interface CourseDAO {
    
    public int countCourseUserRegisted(int course_id);
    
    public List<Course> getAllCourseAdmin();
    
    public List<Course> getCourseByExpertId(int userId);
    
    public Course getCourseBestSeller();
    
    public List<Course> getAllCourseActiveAdmin();
    
    public void insertCourse(Course course);
    
    public void deleteCourse(int course_id);
    
    public void updateCourse(Course course);
    
    
    
    public int CountCourseByExpertId(int userId);
    
    public List<Course> getCourseActiveByExpertId(int userId);
    
    public List<Course> getCourseByUserIdPaging(int userId,int pageIndex, int pageSize);
    
    public List<Course> findAll(String sqlQuery) ;
    
    public int CountCourseByName(String courseName);
    
    public int CountCourseByCategory(int cateId);
    
    public List<Course> getCourseByCateId(int Cateid);
    
    public int CountAllCourse();
    
    public int CountAllCourseActive();
    
    public List<Course> getCourseByCateIdPaging(int cateId, int pageIndex, int pageSize);
    
    public List<Course> getCourseBySearchPaging(String txt, int pageIndex, int pageSize);
    
    public Course getCourseByCourseId(int courseId);
    
    public List<Course> getAllCourse(int pageIndex, int pageSize);
    
    public List<Course> getAllCourse();
    
    public List<Course> getCourseByCustomerId(int userId);
    
    public void deleteUserCourse(int userId, int courseId);
}
