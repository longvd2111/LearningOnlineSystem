/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal.Impl;

import context.DBContext;
import dal.DimensionDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.dimension;

/**
 *
 * @author MH
 */
public class DimensionDAOImpl extends DBContext implements DimensionDAO{
    @Override
    public dimension getDimensionByDimensionID(int dimenId) {
        dimension dimen = new dimension();
        try {
            String sql = "select * from dimension where dimension_id = ? ";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, dimenId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                
                dimen.setDimension_id(rs.getInt(1));
                dimen.setCourse_id(rs.getInt(2));
                dimen.setDimension_name(rs.getString(3));
                dimen.setCate_dimension_id(rs.getInt(4));
                dimen.setDescription(rs.getString(5));
                
            }
            return dimen;
        } catch (Exception ex) {
        }
        return null;
    }

    // da check
    @Override
    public List<dimension> getDimensionByCourseId(int courseId) {
        List<dimension> list = new ArrayList<>();
        try {
            String sql = "select * from dimension where course_id = ? ";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, courseId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                dimension dimen = new dimension();
                dimen.setDimension_id(rs.getInt(1));
                dimen.setCourse_id(rs.getInt(2));
                dimen.setDimension_name(rs.getString(3));
                dimen.setCate_dimension_id(rs.getInt(4));
                dimen.setDescription(rs.getString(5));
                list.add(dimen);
            }
            return list;
        } catch (Exception ex) {
        }
        return null;
    }

    //da check
    @Override
    public void insertDimension(dimension dimen) {
        try {
            String sql = "INSERT INTO [dbo].[dimension]\n"
                    + "           ([course_id]\n"
                    + "           ,[dimension_name]\n"
                    + "           ,[cate_dimension_id]\n"
                    + "           ,[description])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?)";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, dimen.getCourse_id());
            stm.setString(2, dimen.getDimension_name());
            stm.setInt(3, dimen.getCate_dimension_id());
            stm.setString(4, dimen.getDescription());
            stm.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    //da check
    @Override
    public void updateDimension(dimension dimen) {
        try {
            String sql = "UPDATE [dbo].[dimension]\n"
                    + "   SET [course_id] = ?\n"
                    + "      ,[dimension_name] = ?\n"
                    + "      ,[cate_dimension_id] = ?\n"
                    + "      ,[description] = ?\n"
                    + " WHERE [dimension_id] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, dimen.getCourse_id());
            stm.setString(2, dimen.getDimension_name());
            stm.setInt(3, dimen.getCate_dimension_id());
            stm.setString(4, dimen.getDescription());
            stm.setInt(5, dimen.getDimension_id());
            stm.executeUpdate();

        } catch (SQLException ex) {

        }

    }

    //da check
    @Override
    public void deleteDimension(int dimen_id) {
        try {
            String sql = "DELETE FROM [dbo].[dimension]\n"
                    + "      WHERE [dimension_id] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, dimen_id);
            stm.executeUpdate();
        } catch (SQLException ex) {

        }
    }
}
