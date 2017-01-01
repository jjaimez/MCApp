package com.tecpro.mcapp;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.CheckBox;

/**
 * Created by jjaimez on 30/5/16.
 */
public class CheckBoxPlus extends CheckBox {

    public CheckBoxPlus(Context context) {
        super(context);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/aaaiight-fat.ttf"));
    }

    public CheckBoxPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/aaaiight-fat.ttf"));
    }
}
