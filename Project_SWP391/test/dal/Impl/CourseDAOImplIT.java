/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dal.Impl;

import dal.CourseDAO;
import java.util.List;
import model.Course;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MH
 */
public class CourseDAOImplIT {

    CourseDAO daoCourse = new CourseDAOImpl();

    

    

    @Test
    public void testCountCourseByNameExist() {
        assertEquals(daoCourse.CountAllCourse(), daoCourse.CountCourseByName(""));
        
    }
    @Test
    public void testCountCourseByNameNotExist() {
        assertEquals(0, daoCourse.CountCourseByName("dddddddd"));
        
    }
    @Test
    public void testCountCourseByNameSpace() {
        assertEquals(daoCourse.CountAllCourse(), daoCourse.CountCourseByName("     "));
        
    }
    
    @Test(expected = NullPointerException.class)
    public void testCountCourseByName_Null() {
        
        int result = daoCourse.CountCourseByName(null);
    }
    

    //GetCourseByCateId() 
    @Test
    public void testGetCourseByExistingCateId() {
        int categoryId = 1;
        List<Course> list = daoCourse.getCourseByCateId(categoryId);
        assertNotNull(list);
        assertFalse(list.isEmpty());

        for (Course course : list) {
            assertEquals(categoryId, course.getCategory_id());
        }
    }

    @Test
    public void testGetCourseByNonExistingCategoryId() {

        int categoryId = 99;

        
        List<Course> courses = daoCourse.getCourseByCateId(categoryId);
        
        assertNotNull(courses);
        assertTrue(courses.isEmpty());
    }

    @Test
    public void testGetCourseByNonExistingCategory() {
        int nonExistingCategoryId = -1; 

        
        List<Course> courses = daoCourse.getCourseByCateId(nonExistingCategoryId);

        
        assertNotNull(courses);
        assertTrue(courses.isEmpty());
    }

    //testCountAllCourse() 
    @Test
    public void testCountAllActiveCourses() {

        int count = daoCourse.CountAllCourse();

        
        assertEquals(9, count);
    }

    // testGetCourseByCateIdPaging() 
    @Test
    public void testGetCourseByCateIdPaging_Success() {
        
        int categoryId = 1; 
        int pageIndex = 1; 
        int pageSize = 10; 

        
        List<Course> courses = daoCourse.getCourseByCateIdPaging(categoryId, pageIndex, pageSize);

                assertNotNull(courses);
        assertFalse(courses.isEmpty());

        assertTrue(courses.size() <= pageSize); 
        
        for (Course course : courses) {
            assertEquals(categoryId, course.getCategory_id());
        }
    }

    @Test
    public void testGetCourseByCateIdPaging_NoCoursesFound() {
        
        int categoryId = -1;
        int pageIndex = 1; 
        int pageSize = 10; 

        
        List<Course> courses = daoCourse.getCourseByCateIdPaging(categoryId, pageIndex, pageSize);

    
        assertNotNull(courses);
        assertTrue(courses.isEmpty());
    }
    
    @Test
    public void testGetCourseByCateIdPaging_Error() {
        
        int categoryId = 1; 
        int pageIndex = 10;
        int pageSize = -1; 

        // Thực hiện hàm
        List<Course> courses = daoCourse.getCourseByCateIdPaging(categoryId, pageIndex, pageSize);

        
        assertNotNull(courses);
        assertTrue(courses.isEmpty()); 
    }

    

    //testGetCourseBySearchPaging
    @Test
    public void testGetCourseBySearchPaging_Success() {
        
        String searchText = "Java"; 
        int pageIndex = 1; 
        int pageSize = 10; 

        
        List<Course> courses = daoCourse.getCourseBySearchPaging(searchText, pageIndex, pageSize);

       
        assertNotNull(courses);
        assertFalse(courses.isEmpty());
       
        assertTrue(courses.size() <= pageSize); 
        
        for (Course course : courses) {
            assertTrue(course.getCourse_name().toLowerCase().contains(searchText.toLowerCase()));
        }
    }

    @Test
    public void testGetCourseBySearchPaging_NoResultsFound() {
        
        String searchText = "NonExistent";
        int pageIndex = 1; 
        int pageSize = 10; 

        
        List<Course> courses = daoCourse.getCourseBySearchPaging(searchText, pageIndex, pageSize);

       
        assertNotNull(courses);
        assertTrue(courses.isEmpty()); 
    }

    //testGetCourseByCourseId() 
    @Test
    public void testGetCourseByCourseId_ExistingCourseId() {

        int existingCourseId = 1; 


        Course course = daoCourse.getCourseByCourseId(existingCourseId);


        assertNotNull(course); 
        assertEquals(existingCourseId, course.getCourse_id()); 
        
    }

    @Test
    public void testGetCourseByCourseId_NonExistingCourseId() {
        
        int nonExistingCourseId = -1; 

        
        Course course = daoCourse.getCourseByCourseId(nonExistingCourseId);

       
        assertNull(course); 
    }

    // testGetAllCourse() 
    @Test
    public void testGetAllCourse_Success() {
        
        int pageIndex = 1; 
        int pageSize = 10; 

        
        List<Course> courses = daoCourse.getAllCourse(pageIndex, pageSize);

        
        assertNotNull(courses);
        assertFalse(courses.isEmpty());
        
        assertTrue(courses.size() <= pageSize); 

    }

    @Test
    public void testGetAllCourse_NoCoursesFound() {
        
        List<Course> courses = daoCourse.getAllCourse(100, 10); 

        
        assertNotNull(courses);
        assertTrue(courses.isEmpty()); 
    }

    
    @Test
    public void testGetCourseByUserId_UserHasCourses() {
        
        int userId = 2; 

        
        List<Course> courses = daoCourse.getCourseByUserId(userId);

        
        assertNotNull(courses);
        assertFalse(courses.isEmpty());
        
        for (Course course : courses) {
            assertEquals(userId, course.getExpert_id()); 
        }
    }

    @Test
    public void testGetCourseByUserId_UserHasNoCourses() {
       
        int userId = -1; 
        
        List<Course> courses = daoCourse.getCourseByUserId(userId);

       
        assertNotNull(courses);
        assertTrue(courses.isEmpty()); 
    }

    //testCountCourseByUserId() 
    @Test
    public void testCountCourseByUserId_UserHasCourses() {
        
        int userId = 2; 

        
        int count = daoCourse.CountCourseByUserId(userId);

        
        assertEquals(9, count);
    }

    @Test
    public void testCountCourseByUserId_UserHasNoCourses() {
        
        int userId = -1; 

        
        int count = daoCourse.CountCourseByUserId(userId);

       
        assertEquals(0, count); 
    }

    //testGetCourseByUserIdPaging() 
    @Test
    public void testGetCourseByUserIdPaging_UserHasCourses() {
        // Chuẩn bị dữ liệu
        int userId = 2;
        int pageIndex = 1;
        int pageSize = 10;

       
        List<Course> courses = daoCourse.getCourseByUserIdPaging(userId, pageIndex, pageSize);

        
        assertNotNull(courses);
        assertFalse(courses.isEmpty()); 
        
        assertTrue(courses.size() <= pageSize); 
        
        for (Course course : courses) {
            assertEquals(userId, course.getExpert_id()); 
        }
    }

    @Test
    public void testGetCourseByUserIdPaging_UserHasNoCourses() {
       
        int userId = -1;
        int pageIndex = 1; 
        int pageSize = 10;
        // Thực hiện hàm
        List<Course> courses = daoCourse.getCourseByUserIdPaging(userId, pageIndex, pageSize);

        // Kiểm tra kết quả
        assertNotNull(courses);
        assertTrue(courses.isEmpty()); 
    }

    
    @Test
    public void testDeleteCourseExist() {
        
        int courseIdToDelete = 10; 
        
        daoCourse.deleteCourse(courseIdToDelete);

        
    }

    @Test
    public void testDeleteCourseNotExist() {
       
        int courseIdToDelete = 100;

       
        daoCourse.deleteCourse(courseIdToDelete);

       
    }

}
