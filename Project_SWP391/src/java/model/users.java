/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author MH
 */
public class users {
    private int user_id;
    private String user_name;
    private String email;
    private String phone;
    private int gender;
    private String password;
    private int status;
    private String fullname;
    private Date create_at;
    private String image;

    public users() {
    }

    public users(int user_id, String user_name, String email, String phone, int gender, String password, int status, String fullname, Date create_at, String image) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.password = password;
        this.status = status;
        this.fullname = fullname;
        this.create_at = create_at;
        this.image = image;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "users{" + "user_id=" + user_id + ", user_name=" + user_name + ", email=" + email + ", phone=" + phone + ", gender=" + gender + ", password=" + password + ", status=" + status + ", fullname=" + fullname + ", create_at=" + create_at + ", image=" + image + '}';
    }

    
}