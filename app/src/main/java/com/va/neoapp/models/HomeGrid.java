package com.va.neoapp.models;

import android.graphics.drawable.Drawable;

public class HomeGrid {

    private String textGrid;
    private Drawable drawable;

    public HomeGrid(Drawable drawable, String textGrid) {
        this.drawable = drawable;
        this.textGrid = textGrid;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getTextGrid() {
        return textGrid;
    }

    public void setTextGrid(String textGrid) {
        this.textGrid = textGrid;
    }
}
