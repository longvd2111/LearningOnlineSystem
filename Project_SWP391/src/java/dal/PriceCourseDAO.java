/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.List;
import model.price_package;

/**
 *
 * @author MH
 */
public interface PriceCourseDAO {
    public List<price_package> getPricePackageByCourseID(int courseId);
    
    public void insertPricePackage(price_package pk);
    
    public void deletePricePackage(int pk_id);
    
    public void updatePricePackage(price_package pk);
    
    public price_package getPricePackageByPricePacKageID(int pk_id);
    
    public List<price_package> getAllPricePackage();
    
    public int countPricePackageStatus(int status,int courseId);
    
}
