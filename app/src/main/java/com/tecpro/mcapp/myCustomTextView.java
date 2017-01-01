package com.tecpro.mcapp;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by jjaimez on 1/3/16.
 */
public class myCustomTextView extends TextView{


    public myCustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/aaaiight-fat.ttf"));
    }
}
