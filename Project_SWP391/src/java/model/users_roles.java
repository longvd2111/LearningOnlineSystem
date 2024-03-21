/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author MH
 */
public class users_roles {
    private int user_id;
    private int role_id;

    public users_roles(int user_id, int role_id) {
        this.user_id = user_id;
        this.role_id = role_id;
    }

    public users_roles() {
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    

    

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    @Override
    public String toString() {
        return "users_roles{" + "user_id=" + user_id + ", role_id=" + role_id + '}';
    }
    
}
