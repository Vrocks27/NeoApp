package com.va.neoapp.models;

import android.graphics.drawable.Drawable;

public class OverviewModel {

    private Drawable drawable;
    private String name;
    private String value;

    public OverviewModel(Drawable drawable, String name, String value) {
        this.drawable = drawable;
        this.name = name;
        this.value = value;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
