/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.Impl;

import context.DBContext;
import dal.LessonDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.chapters;
import model.lessons;
import model.user_lesson;

/**
 *
 * @author MH
 */
public class LessonDAOImpl extends DBContext implements LessonDAO {

    @Override
    public void insertChapter(chapters chapter) {
        try {
            String sql = "INSERT INTO [dbo].[chapters]\n"
                    + "           ([chapter_name]\n"
                    + "           ,[course_id]\n"
                    + "           ,[status])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, chapter.getChapter_name());
            stm.setInt(2, chapter.getCourse_id());
            stm.setInt(3, chapter.getStatus());
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void updateChapter(chapters chapter) {
        try {
            String sql = "UPDATE [dbo].[chapters]\n"
                    + "   SET [chapter_name] = ?\n"
                    + "      ,[status] = ?"
                    + " WHERE chapter_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, chapter.getChapter_name());
            stm.setInt(2, chapter.getStatus());
            stm.setInt(3, chapter.getChapter_id());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void deleteChapter(chapters chapter) {
        try {
            String sql = "DELETE FROM [dbo].[chapters]\n"
                    + "      WHERE chapter_id = ?\n";
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, chapter.getChapter_id());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void insertLesson(lessons lesson) {
        try {
            String sql = "INSERT INTO [dbo].[lessons]\n"
                    + "           ([lesson_name]\n"
                    + "           ,[chapter_id]\n"
                    + "           ,[video]\n"
                    + "           ,[status]\n"
                    + "           ,[content])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?)\n";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lesson.getLesson_name());
            stm.setInt(2, lesson.getChapter_id());
            stm.setString(3, lesson.getContent());
            stm.setInt(4, lesson.getStatus());
            stm.setString(5, lesson.getContent_text());

            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void updateLesson(lessons lesson) {
        try {
            String sql = "UPDATE [dbo].[lessons]\n"
                    + "   SET [lesson_name] = ?\n"
                    + "      ,[chapter_id] = ?\n"
                    + "      ,[video] = ?\n"
                    + "      ,[status] = ?\n"
                    + "      ,[content] = ?\n"
                    + " WHERE [lesson_id] = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lesson.getLesson_name());
            stm.setInt(2, lesson.getChapter_id());
            stm.setString(3, lesson.getContent());
            stm.setInt(4, lesson.getStatus());
            stm.setString(5, lesson.getContent_text());
            stm.setInt(6, lesson.getLesson_id());

            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteLesson(lessons lesson) {
        try {
            String sql = "DELETE FROM [dbo].[lessons]\n"
                    + "      WHERE lesson_id = ?\n";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lesson.getLesson_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<chapters> getChapterByCourseID(int courseID) {
        List<chapters> list = new ArrayList<>();
        try {
            String sql = "select * from chapters where course_id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                chapters chapter = new chapters();
                chapter.setChapter_id(rs.getInt(1));
                chapter.setChapter_name(rs.getString(2));
                chapter.setCourse_id(rs.getInt(3));
                chapter.setStatus(rs.getInt(4));
                list.add(chapter);

            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return null;
    }

    @Override
    public List<lessons> getLessonsByChapterID(int chapterID) {
        List<lessons> list = new ArrayList<>();
        try {
            String sql = "select * from lessons where chapter_id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, chapterID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                lessons lesson = new lessons();
                lesson.setLesson_id(rs.getInt(1));
                lesson.setLesson_name(rs.getString(2));
                lesson.setChapter_id(rs.getInt(3));
                lesson.setContent(rs.getString(4));
                lesson.setStatus(rs.getInt(5));
                lesson.setContent_text(rs.getString(6));
                list.add(lesson);

            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    @Override
    public List<chapters> getChapterByCourseIDPaging(int courseID, int pageIndex, int pageSize) {
        List<chapters> list = new ArrayList<>();
        try {
            String sql = "select * from chapters where course_id = ? order by chapter_id asc offset (?-1)*? ROW FETCH NEXT ? ROWS only";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            stm.setInt(2, pageIndex);
            stm.setInt(3, pageSize);
            stm.setInt(4, pageSize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                chapters chapter = new chapters();
                chapter.setChapter_id(rs.getInt(1));
                chapter.setChapter_name(rs.getString(2));
                chapter.setCourse_id(rs.getInt(3));
                chapter.setStatus(rs.getInt(4));
                list.add(chapter);

            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return null;
    }

    @Override
    public List<chapters> getChapterByChapterNamePaging(String chapter_name, int pageIndex, int pageSize) {
        List<chapters> list = new ArrayList<>();
        try {
            String sql = "select * from lessons where lesson_name like '%?%' order by lesson_id asc offset (?-1)*? ROW FETCH NEXT ? ROWS only";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, chapter_name);
            stm.setInt(2, pageIndex);
            stm.setInt(3, pageSize);
            stm.setInt(4, pageSize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                chapters chapter = new chapters();
                chapter.setChapter_id(rs.getInt(1));
                chapter.setChapter_name(rs.getString(2));
                chapter.setCourse_id(rs.getInt(3));
                chapter.setStatus(rs.getInt(4));
                list.add(chapter);

            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    @Override
    public List<lessons> getLessonByLessonNamePaging(String lesson_name, int pageIndex, int pageSize) {
        List<lessons> list = new ArrayList<>();
        try {
            String sql = "select * from lessons where lesson_name like '%?%' order by lesson_id asc offset (?-1)*  ? ROW FETCH NEXT ? ROWS only";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, lesson_name);
            stm.setInt(2, pageIndex);
            stm.setInt(3, pageSize);
            stm.setInt(4, pageSize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                lessons lesson = new lessons();
                lesson.setLesson_id(rs.getInt(1));
                lesson.setLesson_name(rs.getString(2));
                lesson.setChapter_id(rs.getInt(3));
                lesson.setContent(rs.getString(4));
                lesson.setStatus(rs.getInt(5));
                lesson.setContent_text(rs.getString(6));
                list.add(lesson);

            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    @Override
    public int countChapterByCourseID(int courseID) {
        int count = 0;
        try {
            String sql = "select COUNT(*) from chapters where course_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }

    @Override
    public int countLessonByCourseID(int courseID) {
        int count = 0;
        try {
            String sql = "SELECT  COUNT(l.lesson_id) AS lesson_count\n"
                    + "FROM courses c\n"
                    + "LEFT JOIN chapters ch ON c.course_id = ch.course_id\n"
                    + "LEFT JOIN lessons l ON ch.chapter_id = l.chapter_id\n"
                    + "WHERE c.course_id = ?\n"
                    + "GROUP BY c.course_id";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }

    @Override
    public int countLessonByChapterID(int chapterID) {
        int count = 0;
        try {
            String sql = "select COUNT(*) from lessons where chapter_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, chapterID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }

    @Override
    public List<lessons> getLessonsByCourseID(int courseID) {
        List<lessons> list = new ArrayList<>();
        try {
            String sql = "SELECT *\n"
                    + "FROM lessons\n"
                    + "WHERE chapter_id IN (SELECT chapter_id FROM chapters WHERE course_id = ?);";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                lessons lesson = new lessons();
                lesson.setLesson_id(rs.getInt(1));
                lesson.setLesson_name(rs.getString(2));
                lesson.setChapter_id(rs.getInt(3));
                lesson.setContent(rs.getString(4));
                lesson.setStatus(rs.getInt(5));
                lesson.setContent_text(rs.getString(6));
                list.add(lesson);

            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    @Override
    public void deleteLessonByChapterID(int chapterID) {
        try {
            String sql = "DELETE FROM [dbo].[lessons]\n"
                    + "      WHERE chapter_id = ?\n";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, chapterID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteLessonByLessonID(int lessonID) {
        try {
            String sql = "DELETE FROM [dbo].[lessons]\n"
                    + "      WHERE lesson_id = ?\n";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lessonID);
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override

    public lessons getLessonByLessonID(int lessonID) {
        lessons lesson = new lessons();
        try {
            String sql = "select * from lessons where lesson_id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, lessonID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                lesson.setLesson_id(rs.getInt(1));
                lesson.setLesson_name(rs.getString(2));
                lesson.setChapter_id(rs.getInt(3));
                lesson.setContent(rs.getString(4));
                lesson.setStatus(rs.getInt(5));
                lesson.setContent_text(rs.getString(6));
            }
            return lesson;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;

    }

    @Override
    public lessons getLessonById(int lessonId) {

        lessons lesson = new lessons();
        try {
            String sql = "select * from lessons where lesson_id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, lessonId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                lesson.setLesson_id(rs.getInt(1));
                lesson.setLesson_name(rs.getString(2));
                lesson.setChapter_id(rs.getInt(3));
                lesson.setContent(rs.getString(4));
                lesson.setContent_text(rs.getString(5));
            }
            return lesson;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    @Override
    public boolean lessonIsArchived(int userId, int lessonId) {
        user_lesson ul = null;

        try {
            String sql = "select * from user_lesson where lesson_id = ? and user_id =?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lessonId);
            stm.setInt(2, userId);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                ul = new user_lesson();
                ul.setLesson_id(rs.getInt(2));
                ul.setUser_id(rs.getInt(1));
                ul.setIs_archived(rs.getInt(3));
                return true;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    @Override
    public void doneLesson(int userId, int lessonId) {
        try {
            String sql = "INSERT INTO [dbo].[user_lesson]\n"
                    + "([user_id]\n"
                    + "           ,[lesson_id]\n"
                    + "           ,[is_archived])"
                    + "     VALUES\n"
                    + "           (?,?,?)\n";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, lessonId);
            stm.setInt(3, 1);

            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<lessons> findAll(String sqlQuery) {
        List<lessons> list = new ArrayList<>();

        String sql = sqlQuery;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                lessons lesson = new lessons();
                lesson.setLesson_id(rs.getInt(1));
                lesson.setLesson_name(rs.getString(2));
                lesson.setChapter_id(rs.getInt(3));
                lesson.setContent(rs.getString(4));
                lesson.setStatus(rs.getInt(5));
                lesson.setContent_text(rs.getString(6));
                list.add(lesson);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public static void main(String[] args) {

        LessonDAO daoLesson = new LessonDAOImpl();

        System.out.println(daoLesson.lessonIsArchived(5, 1));

    }

    @Override
    public List<chapters> findAllChapter(String sqlQuery) {
        List<chapters> list = new ArrayList<>();

        String sql = sqlQuery;
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                chapters chapter = new chapters();
                chapter.setChapter_id(rs.getInt(1));
                chapter.setChapter_name(rs.getString(2));
                chapter.setCourse_id(rs.getInt(3));
                chapter.setStatus(rs.getInt(4));
                list.add(chapter);
            }
            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    @Override
    public int countDoneLessonByUserId(int userId, int courseId) {
        int count = 0;
        try {
            String sql = "SELECT count(*)\n"
                    + "  FROM [SWP391_dbInter1_11].[dbo].[user_lesson] ul\n"
                    + "  join lessons l on ul.lesson_id = l.lesson_id\n"
                    + "  join chapters c on l.chapter_id = c.chapter_id\n"
                    + "  where user_id = ? and course_id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, courseId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            return count;

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return count;
    }

    @Override
    public chapters getChapterByChapterId(int chapterId) {

        try {
            String sql = "select * from chapters where chapter_id = ?";
            chapters chapter = new chapters();
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, chapterId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                chapter.setChapter_id(rs.getInt(1));
                chapter.setChapter_name(rs.getString(2));
                chapter.setCourse_id(rs.getInt(3));
                chapter.setStatus(rs.getInt(4));

            }
            return chapter;
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return null;
    }

    @Override
    public int countLessonByChapterID(int chapterID, int status) {
        int count = 0;
        try {
            String sql = "select COUNT(*) from lessons where chapter_id = ? and status = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, chapterID);
            stm.setInt(2, status);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return count;
    }

    @Override
    public chapters getChapterByLessonID(int lessonID) {
        try {
            String sql = "select c.chapter_id,c.chapter_name,c.course_id,c.status from chapters c inner join lessons l on c.chapter_id = l.chapter_id where lesson_id = ?";
            chapters chapter = new chapters();
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, lessonID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                chapter.setChapter_id(rs.getInt(1));
                chapter.setChapter_name(rs.getString(2));
                chapter.setCourse_id(rs.getInt(3));
                chapter.setStatus(rs.getInt(4));

            }
            return chapter;
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return null;
    }

}
