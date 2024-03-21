/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MH
 */
public class cate_dimension {
    private int cate_dimension_id;
    private String cate_dimension_name;

    public cate_dimension() {
    }

    public cate_dimension(int cate_dimension_id, String cate_dimension_name) {
        this.cate_dimension_id = cate_dimension_id;
        this.cate_dimension_name = cate_dimension_name;
    }

    public int getCate_dimension_id() {
        return cate_dimension_id;
    }

    public void setCate_dimension_id(int cate_dimension_id) {
        this.cate_dimension_id = cate_dimension_id;
    }

    public String getCate_dimension_name() {
        return cate_dimension_name;
    }

    public void setCate_dimension_name(String cate_dimension_name) {
        this.cate_dimension_name = cate_dimension_name;
    }

    @Override
    public String toString() {
        return "cate_dimension{" + "cate_dimension_id=" + cate_dimension_id + ", cate_dimension_name=" + cate_dimension_name + '}';
    }
    
}
