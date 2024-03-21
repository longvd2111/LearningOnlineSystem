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
    
    public void insertCourse(Course course);
    
    public void deleteCourse(int course_id);
    
    public void updateCourse(Course course);
    
    
    
    public int CountCourseByUserId(int userId);
    
    public List<Course> getCourseByUserId(int userId);
    
    public List<Course> getCourseByUserIdPaging(int userId,int pageIndex, int pageSize);
    
    public List<Course> findAll(String sqlQuery) ;
    
    public int CountCourseByName(String courseName);
    
    public int CountCourseByCategory(int cateId);
    
    public List<Course> getCourseByCateId(int Cateid);
    
    public int CountAllCourse();
    
    public List<Course> getCourseByCateIdPaging(int cateId, int pageIndex, int pageSize);
    
    public List<Course> getCourseBySearchPaging(String txt, int pageIndex, int pageSize);
    
    public Course getCourseByCourseId(int courseId);
    
    public List<Course> getAllCourse(int pageIndex, int pageSize);
    
    public List<Course> getAllCourse();
    
    public List<Course> getCourseByCustomerId(int userId);
    
}
