/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RegistrationsList;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class RegistrationList {
    private int user_id;
    private String email;
    private Date created_at;
    private int course_id;
    private String name;
    private int price_package_id;
    private String pack;
    private int duration;
    private double price;
    private int is_archived;
    private Date join_time;
    private Date timeTo; 

    public RegistrationList() {
    }

    public RegistrationList(int user_id, String email, Date created_at, int course_id, String name, int price_package_id, String pack, int duration, double price, int is_archived, Date join_time, Date timeTo) {
        this.user_id = user_id;
        this.email = email;
        this.created_at = created_at;
        this.course_id = course_id;
        this.name = name;
        this.price_package_id = price_package_id;
        this.pack = pack;
        this.duration = duration;
        this.price = price;
        this.is_archived = is_archived;
        this.join_time = join_time;
        this.timeTo = timeTo;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice_package_id() {
        return price_package_id;
    }

    public void setPrice_package_id(int price_package_id) {
        this.price_package_id = price_package_id;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getIs_archived() {
        return is_archived;
    }

    public void setIs_archived(int is_archived) {
        this.is_archived = is_archived;
    }

    public Date getJoin_time() {
        return join_time;
    }

    public void setJoin_time(Date join_time) {
        this.join_time = join_time;
    }

    public Date getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(Date timeTo) {
        this.timeTo = timeTo;
    }

    @Override
    public String toString() {
        return "RegistrationList{" + "user_id=" + user_id + ", email=" + email + ", created_at=" + created_at + ", course_id=" + course_id + ", name=" + name + ", price_package_id=" + price_package_id + ", pack=" + pack + ", duration=" + duration + ", price=" + price + ", is_archived=" + is_archived + ", join_time=" + join_time + ", timeTo=" + timeTo + '}';
    }
    
    

}