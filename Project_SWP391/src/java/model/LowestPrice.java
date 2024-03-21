/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MH
 */
public class LowestPrice {
    private int course_id;
    private int lowest_price;

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getLowest_price() {
        return lowest_price;
    }

    public void setLowest_price(int lowest_price) {
        this.lowest_price = lowest_price;
    }
    
    public LowestPrice(int course_id, int lowest_price) {
        this.course_id = course_id;
        this.lowest_price = lowest_price;
    }

    public LowestPrice() {
    }

    @Override
    public String toString() {
        return "LowestPrice{" + "course_id=" + course_id + ", lowest_price=" + lowest_price + '}';
    }
    
}
