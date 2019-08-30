package com.example.oemtest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.oemtest.R;

public class SegmentView extends View {
    private final Paint mPaint;
    private final Paint paintIcon;
    private final Paint paintIcon2;
    private final Paint colorPrimary_02;
    private final int startAngle;
    private final int angle;
    private final int drawableId;
    private final Paint pShadow;

    private Integer width = null;

    public SegmentView(Context context) {
        this(context, null);
    }

    public SegmentView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SegmentView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.SegmentView);
        startAngle = ta.getInteger(R.styleable.SegmentView_startAngle, 0);
        angle = ta.getInteger(R.styleable.SegmentView_angle, 0);
        drawableId = ta.getResourceId(R.styleable.SegmentView_android_drawable, 0);
        int colorId = ta.getResourceId(R.styleable.SegmentView_android_color, 0);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor((colorId == 0) ? Color.RED : ContextCompat.getColor(getContext(), colorId));
        paintIcon = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintIcon.setColor(Color.WHITE);
        paintIcon2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintIcon2.setColor(Color.BLACK);

        colorPrimary_02 = new Paint(Paint.ANTI_ALIAS_FLAG);
        colorPrimary_02.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary_02));


        pShadow = new Paint(Paint.ANTI_ALIAS_FLAG);
        pShadow.setShadowLayer(12, 0, 0, Color.YELLOW);
        setLayerType(LAYER_TYPE_SOFTWARE, pShadow);


        ta.recycle();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int size = getWidth() - 5;
        width = size;
        RectF ovalShadow = new RectF(0, 0, getWidth(), getWidth());
        RectF oval = new RectF(0, 0, size, size);
        super.onDraw(canvas);
//        canvas.drawArc(ovalShadow, startAngle, angle, true, pShadow);
        canvas.drawArc(oval, startAngle, angle, true, mPaint);
        double alpha = Math.toRadians(startAngle + angle / 2);
        double left = (Math.cos(alpha) * 0.7 * size / 2);
        double top = (Math.sin(alpha) * 0.7 * size / 2);


        int pLeft = (int) (left + size / 2);
        int pTop = (int) (top + size / 2);


        if (drawableId != 0) {
            Drawable d = getResources().getDrawable(R.drawable.ic_android, null);
            d.setBounds(pLeft - 24, pTop - 24, pLeft + 24, pTop + 24);
            d.setBounds(pLeft, pTop, pLeft + 48, pTop + 48);
            d.setTint(Color.WHITE);
            d.draw(canvas);
        }
    }

    static boolean checkPoint(int radius, int x, int y, float startAngle, float endAngle) {

        // Calculate polar co-ordinates
        double polarradius = Math.sqrt(x * x + y * y);
        double Angle = Math.toDegrees(Math.atan(y / x));

        // Check whether polarradius is
        // less then radius of circle
        // or not and Angle is between
        // startAngle and endAngle
        // or not
        return (Angle >= startAngle && Angle <= endAngle && polarradius < radius);
//            System.out.print("Point" + "(" + x + "," + y + ")" +
//                    " exist in the circle sector\n");
//        else
//            System.out.print("Point" + "(" + x + "," + y + ")" +
//                    " exist in the circle sector\n");
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (width == null) return false;
        Log.e("anatolii", "in sector - " + checkPoint(
                getWidth() / 2,
                (int) event.getX() ,
                (int) event.getY(),
                startAngle,
                startAngle + angle));
        return true;
    }


}
