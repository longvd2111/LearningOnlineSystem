/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.List;
import model.chapters;
import model.lessons;

/**
 *
 * @author MH
 */
public interface LessonDAO {
    
    
    
    public String getLinkVideo (String link);
    
    public String getLinkVideoEmbed (String link);
    
    public chapters getChapterByLessonID(int lessonID);
    
    public List<lessons> findAll(String sqlQuery) ;
    
    public List<chapters> findAllChapter(String sqlQuery) ;
    public void insertChapter(chapters chapter);
    
    public void updateChapter(chapters chapter);
    
    public void deleteChapter(int chapterId);
    
    public void deleteChapterByCourseID(int courseID);
    
    public void insertLesson(lessons lesson);
    
    public void updateLesson(lessons lesson);
    
    public void deleteLesson(lessons lesson);
    
    public void deleteLessonByLessonID(int lessonID);
    
    public void deleteLessonByChapterID(int chapterID);
    
    public void deleteLessonByCourseID(int courseID);
    
    public List<chapters> getChapterByCourseID(int courseID);
    
    public List<lessons> getLessonsByChapterID(int chapterID);
    
    public List<chapters> getChapterByCourseIDPaging(int courseID,int pageIndex,int pageSize);
    
    public List<lessons> getLessonsByCourseID(int courseID);
    
    public List<chapters> getChapterByChapterNamePaging(String chapter_name,int pageIndex,int pageSize);
    
    public List<lessons> getLessonByLessonNamePaging(String lesson_name,int pageIndex,int pageSize);
    
    public int countChapterByCourseID(int courseID);
    
    public int countChapterByCourseID(int courseID,int status);
    
    public int countLessonByCourseID(int courseID);
    
    public int countLessonByChapterID(int chapterID);
        
    public int countLessonByChapterID(int chapterID,int status);
    
    public lessons getLessonByLessonID(int lessonID);

    public lessons getLessonById(int lessonId);
    
    public boolean lessonIsArchived(int userId,int lessonId);
    
    public void doneLesson(int userId,int lessonId);

    public int countDoneLessonByUserId(int userId,int courseId);
    
    public chapters getChapterByChapterId(int chapterId);
    
    
}
