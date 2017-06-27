package com.zrq.radaranddisc;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class PlateView extends View {

    private static final int DEFAULT_COLOR = 0xFFFFFFFF;
    private float center_x;
    private float center_y;
    private float radius;
    private int color = DEFAULT_COLOR;

    public PlateView(Context context) {
        super(context);
        init(context, null);
    }

    public PlateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PlateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RadarView);
        color = a.getColor(R.styleable.RadarView_m_color, DEFAULT_COLOR);
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
        paint.setColor(color);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);

        canvas.rotate(-18,center_x,center_y);
        //指针
        for (int i = 0; i < 5; i++ ){
            canvas.drawLine(center_x, center_y, center_x + radius, radius, paint);
            canvas.rotate(-72,center_x,center_y);
        }
        //圆边
        for (int i = 1; i <= 5; i++ ){
            canvas.drawCircle(center_x, center_y, radius / 5 * i, paint);
        }
    }
}
