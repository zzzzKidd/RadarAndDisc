package com.zrq.radaranddisc;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class RadarView2 extends View {
    private static final int DEFAULT_COLOR = 0xFFFFFFFF;
    private float center_x;
    private float center_y;
    private float radius;
    private int start_color = DEFAULT_COLOR;
    private int end_color = DEFAULT_COLOR;
    private Paint paint;
    private Matrix mMatrix = new Matrix();
    private float mRotate;
    private int m_color;
    private boolean isAnim = true;

    public RadarView2(Context context) {
        super(context);
        init(context, null);
    }

    public RadarView2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RadarView2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RadarView);
        start_color = a.getColor(R.styleable.RadarView_start_color, DEFAULT_COLOR);
        end_color = a.getColor(R.styleable.RadarView_end_color, DEFAULT_COLOR);
        m_color = a.getColor(R.styleable.RadarView_m_color, DEFAULT_COLOR);
        a.recycle();

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setDither(true);
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

        paint.setColor(m_color);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setShader(null);

        canvas.save();
        canvas.rotate(-18, center_x, center_y);
        //指针
        for (int i = 0; i < 5; i++) {
            canvas.drawLine(center_x, center_y, center_x + radius, radius, paint);
            canvas.rotate(-72, center_x, center_y);
        }
        //圆边
        for (int i = 1; i <= 5; i++) {
            canvas.drawCircle(center_x, center_y, radius / 5 * i, paint);
        }
        canvas.restore();


        canvas.save();
        paint.setColor(Color.BLACK);
        canvas.rotate(-18, center_x, center_y);
        //扫面指针
//        canvas.drawLine(center_x, center_y, center_x + radius, radius, paint);
        //扫面
        SweepGradient sweepGradient = new SweepGradient(center_x, center_y, start_color, end_color);
        paint.setShader(sweepGradient);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        mMatrix.setRotate(mRotate, center_x, center_y);
        //渐变加旋转
        sweepGradient.setLocalMatrix(mMatrix);
        mRotate += 1.5;
        if (mRotate >= 360) {
            mRotate = 0;
        }
        if (isAnim){
            invalidate();
        }
        canvas.drawCircle(center_x, center_y, radius, paint);
    }


    public void start(){
        if (!isAnim) {
            isAnim = true;
            invalidate();
        }
    }
    public void stop(){
        if (isAnim) {
            isAnim = false;
            mRotate = 0;
            invalidate();
        }
    }
}
