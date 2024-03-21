/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MH
 */
public class roles {
    private int role_id;
    private String name;

    public roles(int role_id, String name) {
        this.role_id = role_id;
        this.name = name;
    }

    public roles() {
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "roles{" + "role_id=" + role_id + ", name=" + name + '}';
    }
    
}
