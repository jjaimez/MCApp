package com.tecpro.mcapp;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by jjaimez on 30/5/16.
 */
public class ButtonPlus extends Button{

    public ButtonPlus(Context context) {
        super(context);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/aaaiight-fat.ttf"));
    }

    public ButtonPlus(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/aaaiight-fat.ttf"));
    }

    public ButtonPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/aaaiight-fat.ttf"));
    }

}
