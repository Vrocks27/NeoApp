package com.va.neoapp.models;

public class HealthSafetyModel {

    private String title, description;
    private String youtube_id, youtube_url, thumbnail_url;

    public HealthSafetyModel(String title, String description, String youtube_id, String youtube_url, String thumbnail_url) {
        this.title = title;
        this.description = description;
        this.youtube_id = youtube_id;
        this.youtube_url = youtube_url;
        this.thumbnail_url = thumbnail_url;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYoutube_id() {
        return youtube_id;
    }

    public void setYoutube_id(String youtube_id) {
        this.youtube_id = youtube_id;
    }

    public String getYoutube_url() {
        return youtube_url;
    }

    public void setYoutube_url(String youtube_url) {
        this.youtube_url = youtube_url;
    }
}
