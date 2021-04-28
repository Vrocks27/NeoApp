package com.va.neoapp.custom;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class CustomTextViewMedium extends AppCompatTextView {

    public CustomTextViewMedium(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public CustomTextViewMedium(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTextViewMedium(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getResources().getAssets(), "Montserrat-Medium.ttf");
        setTypeface(tf);
    }

}