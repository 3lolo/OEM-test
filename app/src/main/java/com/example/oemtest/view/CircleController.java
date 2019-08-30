package com.example.oemtest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.oemtest.R;

public class CircleController extends ConstraintLayout {
    public CircleController(Context context) {
        this(context, null);
    }

    public CircleController(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleController(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(getContext()).inflate(R.layout.item_circle, this, true);
    }
}
