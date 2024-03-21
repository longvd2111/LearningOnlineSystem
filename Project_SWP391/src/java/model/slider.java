/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author caomi
 */
public class slider {
    private int slider_id;
    private int author_id;
    private String title;
    private String image;
    private String backlink;
    private int status;
    private String notes;

    public slider() {
    }

    public slider(int slider_id, int author_id, String title, String image, String backlink, int status, String notes) {
        this.slider_id = slider_id;
        this.author_id = author_id;
        this.title = title;
        this.image = image;
        this.backlink = backlink;
        this.status = status;
        this.notes = notes;
    }

    public int getSlider_id() {
        return slider_id;
    }

    public void setSlider_id(int slider_id) {
        this.slider_id = slider_id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBacklink() {
        return backlink;
    }

    public void setBacklink(String backlink) {
        this.backlink = backlink;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "slider{" + "author_id=" + author_id + ", title=" + title + ", image=" + image + ", backlink=" + backlink + ", status=" + status + ", notes=" + notes + '}';
    }

    
}
