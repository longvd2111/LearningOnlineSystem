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
public class orders_register {
    private int order_id;
    private int user_id;
    private int course_id;
    private Date create_at;
    private int price_final;
    private int status;
    private int price_package_id;

    public orders_register() {
    }

    public orders_register(int order_id, int user_id, int course_id, Date create_at, int price_final, int status, int price_package_id) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.course_id = course_id;
        this.create_at = create_at;

        this.price_final = price_final;
        this.status = status;
        this.price_package_id = price_package_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    

    public int getPrice_final() {
        return price_final;
    }

    public void setPrice_final(int price_final) {
        this.price_final = price_final;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPrice_package_id() {
        return price_package_id;
    }

    public void setPrice_package_id(int price_package_id) {
        this.price_package_id = price_package_id;
    }

    @Override
    public String toString() {
        return "orders_register{" + "order_id=" + order_id + ", user_id=" + user_id + ", course_id=" + course_id + ", create_at=" + create_at + ", price_final=" + price_final + ", status=" + status + ", price_package_id=" + price_package_id + '}';
    }

    
    
}
