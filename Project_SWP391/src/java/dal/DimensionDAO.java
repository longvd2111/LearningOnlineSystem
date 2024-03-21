/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.List;
import model.dimension;

/**
 *
 * @author MH
 */
public interface DimensionDAO {
    public dimension getDimensionByDimensionID(int dimenId);
    
    public List<dimension> getDimensionByCourseId(int courseId);
    
    public void insertDimension(dimension dimen);
    
    public void updateDimension(dimension dimen);
    
    public void deleteDimension(int dimen_id);
}
