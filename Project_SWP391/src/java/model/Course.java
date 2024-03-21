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
public class Course {
    private int course_id;
    private int expert_id;
    private int category_id;
    private String course_name;
    private String image;
    private String tagline;
    private int status;
    private String description;  
    private Date created_at;
    private int feature;

    public Course(int course_id, int expert_id, int category_id, String course_name, String image, String tagline, int status, String description, Date created_at, int feature) {
        this.course_id = course_id;
        this.expert_id = expert_id;
        this.category_id = category_id;
        this.course_name = course_name;
        this.image = image;
        this.tagline = tagline;
        this.status = status;
        this.description = description;
        this.created_at = created_at;
        this.feature = feature;
    }

    
    
    
    public Course(int course_id, int expert_id, int category_id, String course_name, String image, String tagline, int status, String description, Date created_at) {
        this.course_id = course_id;
        this.expert_id = expert_id;
        this.category_id = category_id;
        this.course_name = course_name;
        this.image = image;
        this.tagline = tagline;
        this.status = status;
        this.description = description;
        this.created_at = created_at;
    }

    
    public Course() {
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getExpert_id() {
        return expert_id;
    }

    public void setExpert_id(int expert_id) {
        this.expert_id = expert_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
    
    public int getFeature() {
        return feature;
    }

    public void setFeature(int feature) {
        this.feature = feature;
    }

    @Override
    public String toString() {
        return "Course{" + "course_id=" + course_id + ", expert_id=" + expert_id + ", category_id=" + category_id + ", course_name=" + course_name + ", image=" + image + ", tagline=" + tagline + ", status=" + status + ", description=" + description + ", created_at=" + created_at + ", feature=" + feature + '}';
    }
    
    

    

    
    
    
            
}
