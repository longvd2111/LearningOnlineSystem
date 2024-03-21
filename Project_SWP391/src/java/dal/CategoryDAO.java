/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.List;
import model.cate_dimension;
import model.categories;

/**
 *
 * @author MH
 */
public interface CategoryDAO {
    public List<cate_dimension> getAllCateDimen();
    
    public List<categories> getAllCategory();
    
}
