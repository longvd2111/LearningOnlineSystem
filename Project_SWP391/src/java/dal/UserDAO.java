/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.util.List;
import model.users;
import model.users_roles;

/**
 *
 * @author MH
 */
public interface UserDAO {
    
    public List<users> getUser();
    
    public List<users> getUserByRole(int role_id);
    
    public users_roles getRoleByUserID(int user_id);
    
    public users login(String email, String pass);
    
    public users checkEmailExist(String email);
    
    public void signUp(String name, String email, String phone, int gender, String password, int status, String fullname, Date creat_at) ;
    
    public void forgotPassword(String email, String pass);
    
    public users getUserByID(int id);
    
    public void UpdateUser(String username, String image, String phone, int gender, String full_name, int id) ;
    
    public users getUser(int id);
    
    public void updatePassword(int id, String pass);
    
}
