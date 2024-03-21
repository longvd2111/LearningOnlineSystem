/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author MH
 */
public class chapters {

    private int chapter_id;
    private String chapter_name;
    private int course_id;
    private int status;

    public chapters(int chapter_id, String chapter_name, int course_id) {
        this.chapter_id = chapter_id;
        this.chapter_name = chapter_name;
        this.course_id = course_id;
    }

    public chapters(int chapter_id, String chapter_name, int course_id, int status) {
        this.chapter_id = chapter_id;
        this.chapter_name = chapter_name;
        this.course_id = course_id;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public chapters() {
    }

    public int getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(int chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getChapter_name() {
        return chapter_name;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    @Override
    public String toString() {
        return "chapters{" + "chapter_id=" + chapter_id + ", chapter_name=" + chapter_name + ", course_id=" + course_id + '}';
    }

    private List<lessons> listLesson;

    public List<lessons> getListLesson() {
        return listLesson;
    }

    public void setListLesson(List<lessons> listLesson) {
        this.listLesson = listLesson;
    }

}
