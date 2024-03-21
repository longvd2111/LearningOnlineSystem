/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import context.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.categories;

import model.posts;

/**
 *
 * @author caomi
 */
public class DAOPost extends DBContext {

    public List<posts> getAllPost() {
        List<posts> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM posts order by date_post";

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                posts post = new posts();
                post.setPost_id(rs.getInt(1));
                post.setAuthor_id(rs.getInt(2));
                post.setTitle(rs.getString(3));
                post.setContent(rs.getString(4));
                post.setDate_post(rs.getDate(5));
                post.setIs_feature(rs.getInt(6));
                post.setSub_content(rs.getString(7));
                post.setCategory_post_id(8);
                post.setThumbnail(rs.getString(9));
                list.add(post);
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOPost.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;

    }

    public posts getPostByPostId(int postId) {
        posts post = new posts();
        try {
            String sql = "SELECT * FROM posts WHERE post_id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, postId);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {

                post.setPost_id(rs.getInt(1));
                post.setAuthor_id(rs.getInt(2));
                post.setTitle(rs.getString(3));
                post.setContent(rs.getString(4));
                post.setDate_post(rs.getDate(5));
                post.setIs_feature(rs.getInt(6));
                post.setSub_content(rs.getString(7));
                post.setCategory_post_id(rs.getInt(8));
                post.setThumbnail(rs.getString(9));
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOPost.class.getName()).log(Level.SEVERE, null, ex);
        }

        return post;

    }

    public List<posts> getPostByCategory(int cate) {
        List<posts> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM posts where category_id= ? order by date_post";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, cate);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                posts post = new posts();
                post.setPost_id(rs.getInt(1));
                post.setAuthor_id(rs.getInt(2));
                post.setTitle(rs.getString(3));
                post.setContent(rs.getString(4));
                post.setDate_post(rs.getDate(5));
                post.setIs_feature(rs.getInt(6));
                post.setSub_content(rs.getString(7));
                post.setCategory_post_id(8);
                post.setThumbnail(rs.getString(9));
                list.add(post);
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOPost.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public List<posts> getAllHotPost() {
        List<posts> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM posts where is_feature = 1 order by date_post";

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                posts post = new posts();
                post.setPost_id(rs.getInt(1));
                post.setAuthor_id(rs.getInt(2));
                post.setTitle(rs.getString(3));
                post.setContent(rs.getString(4));
                post.setDate_post(rs.getDate(5));
                post.setIs_feature(rs.getInt(6));
                post.setSub_content(rs.getString(7));
                post.setCategory_post_id(8);
                post.setThumbnail(rs.getString(9));
                list.add(post);
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOPost.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public int getPostCount() {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) FROM posts";

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
                break;
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOPost.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;
    }

    public List<posts> getSearchPost(String search) {
        List<posts> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM posts\n"
                    + "WHERE title LIKE CONCAT('%', ?, '%') order by date_post";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, search);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                posts post = new posts();
                post.setPost_id(rs.getInt(1));
                post.setAuthor_id(rs.getInt(2));
                post.setTitle(rs.getString(3));
                post.setContent(rs.getString(4));
                post.setDate_post(rs.getDate(5));
                post.setIs_feature(rs.getInt(6));
                post.setSub_content(rs.getString(7));
                post.setCategory_post_id(8);
                post.setThumbnail(rs.getString(9));
                list.add(post);
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOPost.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public List<posts> getSearchCatePost(String search, int cateId) {
        List<posts> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM posts\n"
                    + "WHERE title LIKE CONCAT('%', ?, '%') and category_id=? order by date_post";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, search);
            stm.setInt(2, cateId);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                posts post = new posts();
                post.setPost_id(rs.getInt(1));
                post.setAuthor_id(rs.getInt(2));
                post.setTitle(rs.getString(3));
                post.setContent(rs.getString(4));
                post.setDate_post(rs.getDate(5));
                post.setIs_feature(rs.getInt(6));
                post.setSub_content(rs.getString(7));
                post.setCategory_post_id(8);
                post.setThumbnail(rs.getString(9));
                list.add(post);
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOPost.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public List<posts> getPostOrder() {
        List<posts> list = new ArrayList<>();
        try {
            String sql = "select * from posts  where is_feature = 1 order by date_post  ";

            PreparedStatement stm = connection.prepareStatement(sql);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                posts post = new posts();
                post.setPost_id(rs.getInt(1));
                post.setAuthor_id(rs.getInt(2));
                post.setTitle(rs.getString(3));
                post.setContent(rs.getString(4));
                post.setDate_post(rs.getDate(5));
                post.setIs_feature(rs.getInt(6));
                post.setSub_content(rs.getString(7));
                post.setCategory_post_id(8);
                post.setThumbnail(rs.getString(9));
                list.add(post);
            }
        } catch (Exception ex) {
            Logger.getLogger(DAOPost.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;

    }

    public void createPost(posts p) {
        try {
            String sql = "INSERT INTO [dbo].[posts]" + " VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)\n";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, p.getAuthor_id());
            stm.setString(2, p.getTitle());
            stm.setString(3, p.getContent());
            stm.setDate(4, p.getDate_post());
            stm.setInt(5, p.getIs_feature());
            stm.setString(6, p.getSub_content());
            stm.setInt(7, p.getCategory_post_id());
            stm.setString(8, p.getThumbnail());
            stm.executeQuery();

        } catch (Exception ex) {
            Logger.getLogger(DAOPost.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updatePost(posts p) {
        try {
            String sql = "UPDATE [dbo].[posts]\n"
                    + "   SET [title] = ?\n"
                    + "      ,[content] = ?\n"
                    + "      ,[date_post] = ?\n"
                    + "      ,[is_feature] = ?\n"
                    + "      ,[sub_content] = ?\n"
                    + "      ,[category_id] = ?\n"
                    + "      ,[thumbnail] = ?\n"
                    + " WHERE post_id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            
            stm.setString(1, p.getTitle());
            stm.setString(2, p.getContent());
            stm.setDate(3, p.getDate_post());
            stm.setInt(4, p.getIs_feature());
            stm.setString(5, p.getSub_content());
            stm.setInt(6, p.getCategory_post_id());
            stm.setString(7, p.getThumbnail());
            stm.setInt(8,p.getPost_id());
            stm.executeQuery();

        } catch (Exception ex) {
            Logger.getLogger(DAOPost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deletePost(int post_id){
        try {
            String sql = "delete from [dbo].[posts]\n"
                   
                    + " WHERE post_id = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            
            stm.setInt(1, post_id);
            
            stm.executeQuery();

        } catch (Exception ex) {
            Logger.getLogger(DAOPost.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {

        //Check Get Course by cateID
        DAOPost dao = new DAOPost();
        
        
    }
}
