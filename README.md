简易雷达扫描 及 度网页音乐播放器圆形图片转圈效果
![](https://github.com/zzzzKidd/RadarAndDisc/blob/master/11111.gif)  

SweepView是上层旋转的扫面效果，扫面效果用SweepGradient实现，扫面的起始颜色和终止颜色在attrs中自定义
PlateView是雷达底盘
RadarView就是个RelativeLayout，把上面两个View装进去，然后让扫面View转动
动画效果repeatCount设置为-1，就无限循环转动了
 
