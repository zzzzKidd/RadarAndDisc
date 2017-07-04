简易雷达扫描 及 度网页音乐播放器圆形图片转圈效果  

![](https://github.com/zzzzKidd/RadarAndDisc/blob/master/11111.gif)  

第一种方式实现:RadarView  
SweepView是上层旋转的扫面效果，扫面效果用SweepGradient实现，扫面的起始颜色和终止颜色在attrs中自定义  
PlateView是雷达底盘  
RadarView就是个RelativeLayout，把上面两个View装进去，然后让扫面View转动  
动画效果repeatCount设置为-1，就无限循环转动了  
动画周期可以设置  
   
第二种方式实现:RadarView2  
这种方式只有一个类,在渐变上添加Matrix旋转, 旋转角度不断变化, 然后不断重绘  
这种方式没有绘制扫描指针  
动画周期不可以设置  
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
 
