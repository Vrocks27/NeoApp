package com.va.neoapp.models;

public class LatestNewModel {

    private String title;
    private String Description;
    private String image_url;

    public LatestNewModel(String title, String description, String image_url) {
        this.title = title;
        Description = description;
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
