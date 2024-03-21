/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MH
 */
public class dimension {
    private int dimension_id;
    private int course_id;
    private String dimension_name;
    private int cate_dimension_id;
    private String description;

    public dimension(int dimension_id, int course_id, String dimension_name, int cate_dimension_id, String description) {
        this.dimension_id = dimension_id;
        this.course_id = course_id;
        this.dimension_name = dimension_name;
        this.cate_dimension_id = cate_dimension_id;
        this.description = description;
    }

    public dimension() {
    }

    public int getDimension_id() {
        return dimension_id;
    }

    public void setDimension_id(int dimension_id) {
        this.dimension_id = dimension_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getDimension_name() {
        return dimension_name;
    }

    public void setDimension_name(String dimension_name) {
        this.dimension_name = dimension_name;
    }

    public int getCate_dimension_id() {
        return cate_dimension_id;
    }

    public void setCate_dimension_id(int cate_dimension_id) {
        this.cate_dimension_id = cate_dimension_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "dimension{" + "dimension_id=" + dimension_id + ", course_id=" + course_id + ", dimension_name=" + dimension_name + ", cate_dimension_id=" + cate_dimension_id + ", description=" + description + '}';
    }
    
}
