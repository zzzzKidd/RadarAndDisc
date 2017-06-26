package com.zrq.radaranddisc;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;

/**
 * Created by CAN on 2017/6/26.
 */

public class RadarView extends FrameLayout {

    private Context mContext;
    private static final int DEFAULT_COLOR = 0xFFFFFFFF;
    private float center_x;
    private float center_y;
    private float radius;
    private int start_color = DEFAULT_COLOR;
    private int end_color = DEFAULT_COLOR;
    private SweepView sweepView;

    public RadarView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public RadarView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RadarView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RadarView);
        start_color = a.getColor(R.styleable.RadarView_start_color, DEFAULT_COLOR);
        end_color = a.getColor(R.styleable.RadarView_end_color, DEFAULT_COLOR);
        a.recycle();

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //底盘
        PlateView plateView = new PlateView(context, attrs);
        plateView.setLayoutParams(layoutParams);
        addView(plateView);
        //扫面
        sweepView = new SweepView(context, attrs);
        plateView.setLayoutParams(layoutParams);
        addView(sweepView);
        start();
    }

    public void start(){
        Animation operatingAnim = AnimationUtils.loadAnimation(mContext, R.anim.radar_rotate);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        if (operatingAnim != null) {
            sweepView.startAnimation(operatingAnim);
        }
    }

    public void stot(){
        sweepView.clearAnimation();
    }


}
