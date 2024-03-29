/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dal.Impl;

import dal.PriceCourseDAO;
import java.util.List;
import model.price_package;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author MH
 */
public class PriceCourseDAOImplIT {
    
    PriceCourseDAO daoPrice = new PriceCourseDAOImpl();
    
    
    @Test
    public void testGetPricePackageByPricePacKageIDSucces() {
        int existingPriceId = 1; 


        price_package pk = daoPrice.getPricePackageByPricePacKageID(existingPriceId);


        assertNotNull(pk); 
        assertEquals(existingPriceId, pk.getPrice_package_id()); 
    }
    
    @Test
    public void testGetPricePackageByPricePacKageNotExist() {
        int nonExistingPriceId = 1000; 


        price_package pk = daoPrice.getPricePackageByPricePacKageID(nonExistingPriceId);


        assertNotNull(pk); 
        assertEquals(0, pk.getPrice_package_id()); 
    }


    @Test
    public void testGetPricePackageByCourseIDSuccess() {
        int ExistingCourseId = 1; 


        List<price_package> pk = daoPrice.getPricePackageByCourseID(ExistingCourseId);


        assertNotNull(pk); 
        assertFalse(pk.isEmpty());
        for (price_package object : pk) {
            assertEquals(ExistingCourseId, object.getCourse_id());
        }
        
    }
    
    @Test
    public void testGetPricePackageByCourseIDNotExits() {
        int ExistingCourseId = 1000; 


        List<price_package> pk = daoPrice.getPricePackageByCourseID(ExistingCourseId);


        assertNotNull(pk); 
        assertFalse(pk.isEmpty());
        for (price_package object : pk) {
            assertEquals(ExistingCourseId, object.getCourse_id());
        }
    }


    
    @Test
    public void testGetAllPricePackage() {
    // Thực hiện hàm
        List<price_package> pricePackages = daoPrice.getAllPricePackage();
    
    // Kiểm tra kết quả
    assertNotNull(pricePackages); 
    assertFalse(pricePackages.isEmpty()); 
    
    
    for (price_package pricePackage : pricePackages) {
        assertNotNull(pricePackage.getPrice_package_id()); 
        assertNotNull(pricePackage.getCourse_id()); 
        assertNotNull(pricePackage.getName()); 
        assertNotNull(pricePackage.getPrice()); 
        assertNotNull(pricePackage.getStatus_sale()); 
        assertNotNull(pricePackage.getDuration()); 
        assertNotNull(pricePackage.getStatus()); 

    }
}

    
    
}
