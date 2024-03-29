/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author caomi
 */
public class user_lesson {
    private int user_id;
    private int lesson_id;
    private int is_archived;

    public user_lesson(int user_id, int lesson_id, int is_archived) {
        this.user_id = user_id;
        this.lesson_id = lesson_id;
        this.is_archived = is_archived;
    }

    public user_lesson() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public int getIs_archived() {
        return is_archived;
    }

    public void setIs_archived(int is_archived) {
        this.is_archived = is_archived;
    }
    
}
