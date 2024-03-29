/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dal.Impl;

import dal.LessonDAO;
import java.util.List;
import model.chapters;
import model.lessons;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MH
 */
public class LessonDAOImplIT {
    
    LessonDAO daoLesson = new LessonDAOImpl();
    
    

    @Test
    public void testGetChapterByCourseIDSuccess() {
        int courseId = 1;
        List<chapters> list = daoLesson.getChapterByCourseID(courseId);
        assertNotNull(list);
        assertFalse(list.isEmpty());

        for (chapters object : list) {
            assertEquals(courseId, object.getCourse_id());
        }
        
    }
    
    @Test
    public void testGetChapterByCourseIDNotExist() {
        int courseId = 10000;
        List<chapters> list = daoLesson.getChapterByCourseID(courseId);
        assertNotNull(list);
        assertFalse(list.isEmpty());

        for (chapters object : list) {
            assertEquals(courseId, object.getCourse_id());
        }
        
    }

    @Test
    public void testGetLessonsByChapterIDSuccess() {
        int chapterId = 1;
        List<lessons> list = daoLesson.getLessonsByChapterID(chapterId);
        assertNotNull(list);
        assertFalse(list.isEmpty());

        for (lessons object : list) {
            assertEquals(chapterId, object.getChapter_id());
        }
    }
    
    @Test
    public void testGetLessonsByChapterIDNotExits() {
        int chapterId = 1000;
        List<lessons> list = daoLesson.getLessonsByChapterID(chapterId);
        assertNotNull(list);
        assertFalse(list.isEmpty());

        for (lessons object : list) {
            assertEquals(chapterId, object.getChapter_id());
        }
    }

    
    @Test
    public void testCountChapterByCourseIDExist() {
        int courseId=1;
        int count = daoLesson.countChapterByCourseID(courseId);
        assertEquals(5, count);
    }
    @Test
    public void testCountChapterByCourseIDNotExist() {
        int courseId=-1;
        int count = daoLesson.countChapterByCourseID(courseId);
        assertEquals(0, count);
    }

    @Test
    public void testCountLessonByCourseIDExist() {
        assertEquals(14, daoLesson.countLessonByCourseID(1));
    }
    
    @Test
    public void testCountLessonByCourseIDNotExist() {
        assertEquals(0, daoLesson.countLessonByCourseID(-1));
    }

    @Test
    public void testCountLessonByChapterIDExist() {
        assertEquals(3, daoLesson.countLessonByChapterID(1));
    }
    
    @Test
    public void testCountLessonByChapterIDNotExist() {
        assertEquals(0, daoLesson.countLessonByChapterID(-1));
    }

    @Test
    public void testGetLessonsByCourseIDSuccess() {
        int courseId = 1;
        List<lessons> list = daoLesson.getLessonsByCourseID(courseId);
        
        assertNotNull(list);
        assertFalse(list.isEmpty());

        
    }
    
    @Test
    public void testGetLessonsByCourseIDNotExist() {
        int courseId = 1000;
        List<lessons> list = daoLesson.getLessonsByCourseID(courseId);
        
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    

    @Test
    public void testGetLessonByLessonIDSucces() {
        int lessonId = 1;
        lessons lesson = daoLesson.getLessonById(lessonId);
        
        assertNotNull(lesson);
        assertEquals(lessonId, lesson.getLesson_id());
    }
    
    @Test
    public void testGetLessonByLessonIDNotExist() {
        int lessonId = 1000;
        lessons lesson = daoLesson.getLessonById(lessonId);
        
        assertNotNull(lesson);
        assertEquals(0, lesson.getLesson_id());
    }

    
    
}
