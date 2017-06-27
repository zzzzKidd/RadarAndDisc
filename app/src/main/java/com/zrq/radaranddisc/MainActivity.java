package com.zrq.radaranddisc;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    MediaPlayer mediaPlayer;
    ImageView infoOperatingIV;//转盘图片

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoOperatingIV = (ImageView) findViewById(R.id.infoOperating);
        final RadarView radarView = (RadarView) findViewById(R.id.radar);

        Button play = (Button) findViewById(R.id.play);
        Button stop = (Button) findViewById(R.id.stop);
        play.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                playMusic();
                radarView.start();
            }
        });

        stop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMusic();
                radarView.stot();
            }
        });

    }

    private void playMusic() {
        mediaPlayer = MediaPlayer.create(this, R.raw.hua);
        mediaPlayer.start();

        Animation operatingAnim = AnimationUtils.loadAnimation(this, R.anim.tip);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        if (operatingAnim != null) {
            infoOperatingIV.startAnimation(operatingAnim);
        }

        mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.release();
                mp = null;
                mediaPlayer = null;
                infoOperatingIV.clearAnimation();
            }
        });
    }

    private void stopMusic() {
        if (mediaPlayer != null)
            mediaPlayer.stop();
        infoOperatingIV.clearAnimation();
    }

//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		
//		System.out.println(event.getRepeatCount());
//		
//		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//			stopMusic();
//			finish();
//		}
//		return super.onKeyDown(keyCode, event);
//	}

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click();        //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        try {
            if (isExit == false) {
                isExit = true; // 准备退出
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                tExit = new Timer();
                tExit.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isExit = false; // 取消退出
                    }
                }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

            } else {
                finish();
                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
