package com.va.neoapp.models;

import android.graphics.drawable.Drawable;

public class LatestNewModel {

    private String title;
    private String Description;
    private Drawable drawable;
    private String image_url;

    public LatestNewModel(String title, String description, Drawable drawable) {
        this.title = title;
        Description = description;
        this.drawable = drawable;
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

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
