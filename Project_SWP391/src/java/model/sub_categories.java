/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MH
 */
public class sub_categories {
    private int sub_category_id;
    private int category_id;
    private String sub_category_name;

    public sub_categories() {
        
    }

    public sub_categories(int sub_category_id, int category_id, String sub_category_name) {
        this.sub_category_id = sub_category_id;
        this.category_id = category_id;
        this.sub_category_name = sub_category_name;
    }

    
    public int getSub_category_id() {
        return sub_category_id;
    }

    public void setSub_category_id(int sub_category_id) {
        this.sub_category_id = sub_category_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getSub_category_name() {
        return sub_category_name;
    }

    public void setSub_category_name(String sub_category_name) {
        this.sub_category_name = sub_category_name;
    }

    @Override
    public String toString() {
        return "sub_categories{" + "sub_category_id=" + sub_category_id + ", category_id=" + category_id + ", sub_category_name=" + sub_category_name + '}';
    }
    
}
