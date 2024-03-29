/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MH
 */
public class price_package {
    private int price_package_id;
    private int course_id;
    private String name;
    private int price;
    private int sale_price;
    private int duration;
    private int status_sale;
    private int status;

    public price_package() {
    }

    public price_package(int price_package_id, int course_id, String name, int price, int sale_price, int duration, int status_sale, int status) {
        this.price_package_id = price_package_id;
        this.course_id = course_id;
        this.name = name;
        this.price = price;
        this.sale_price = sale_price;
        this.duration = duration;
        this.status_sale = status_sale;
        this.status = status;
    }

    public int getPrice_package_id() {
        return price_package_id;
    }

    public void setPrice_package_id(int price_package_id) {
        this.price_package_id = price_package_id;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSale_price() {
        return sale_price;
    }

    public void setSale_price(int sale_price) {
        this.sale_price = sale_price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getStatus_sale() {
        return status_sale;
    }

    public void setStatus_sale(int status_sale) {
        this.status_sale = status_sale;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "price_package{" + "price_package_id=" + price_package_id + ", course_id=" + course_id + ", name=" + name + ", price=" + price + ", sale_price=" + sale_price + ", duration=" + duration + ", status_sale=" + status_sale + ", status=" + status + '}';
    }

    
}
