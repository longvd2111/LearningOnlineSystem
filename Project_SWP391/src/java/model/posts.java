/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author MH
 */
public class posts {
    private int post_id;
    private int author_id;
    private String title;
    private String content;
    private Date date_post;
    private int is_feature;
    private String sub_content;
    private int category_post_id;
    private String thumbnail;

    public posts(int post_id, int author_id, String title, String content, Date date_post, int is_feature, String sub_content, int category_post_id, String thumbnail) {
        this.post_id = post_id;
        this.author_id = author_id;
        this.title = title;
        this.content = content;
        this.date_post = date_post;
        this.is_feature = is_feature;
        this.sub_content = sub_content;
        this.category_post_id = category_post_id;
        this.thumbnail = thumbnail;
        
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
    

    public Date getDate_post() {
        return date_post;
    }

    public void setDate_post(Date date_post) {
        this.date_post = date_post;
    }

    public String getSub_content() {
        return sub_content;
    }

    public void setSub_content(String sub_content) {
        this.sub_content = sub_content;
    }

    public int getIs_feature() {
        return is_feature;
    }

    public void setIs_feature(int is_feature) {
        this.is_feature = is_feature;
    }

    public int getCategory_post_id() {
        return category_post_id;
    }

    public void setCategory_post_id(int category_post_id) {
        this.category_post_id = category_post_id;
    }
    

    public posts() {
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "posts{" + "post_id=" + post_id + ", author_id=" + author_id + ", title=" + title + ", content=" + content + '}';
    }
    
    private String authorName;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    
}
