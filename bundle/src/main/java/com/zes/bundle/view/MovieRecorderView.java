package com.zes.bundle.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by zes on 16-3-12.
 */
public class MovieRecorderView extends LinearLayout {
    public MovieRecorderView(Context context) {
        this(context, null);
    }


    public MovieRecorderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MovieRecorderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
