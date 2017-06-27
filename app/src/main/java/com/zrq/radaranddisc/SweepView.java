package com.zrq.radaranddisc;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by CAN on 2017/6/26.
 */

public class SweepView extends View {

    private static final int DEFAULT_COLOR = 0xFFFFFFFF;
    private float center_x;
    private float center_y;
    private float radius;
    private int start_color = DEFAULT_COLOR;
    private int end_color = DEFAULT_COLOR;

    public SweepView(Context context) {
        super(context);
        init(context, null);
    }

    public SweepView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SweepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RadarView);
//        center_x = a.getFloat(R.styleable.RadarView_center_x, 0);
//        center_y = a.getFloat(R.styleable.RadarView_center_y, 0);
//        radius = a.getFloat(R.styleable.RadarView_radius, 0);
        start_color = a.getColor(R.styleable.RadarView_start_color, DEFAULT_COLOR);
        end_color = a.getColor(R.styleable.RadarView_end_color, DEFAULT_COLOR);
        a.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        radius = Math.min(width, height) / 2 - 1;//半径减1, 是为了不和边界相切
        center_x = width / 2;
        center_y = height / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setDither(true);

        canvas.save();
        canvas.rotate(-18,center_x,center_y);
        //扫面指针
        canvas.drawLine(center_x, center_y, center_x + radius, radius, paint);
        //圆边
//        paint.setColor(start_color);
//        paint.setStrokeWidth(1);
//        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawCircle(center_x, center_y, radius-1, paint);

        //扫面
        SweepGradient sweepGradient = new SweepGradient(center_x, center_y, start_color, end_color);
        paint.setShader(sweepGradient);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(center_x, center_y, radius, paint);
    }
}
