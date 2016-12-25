package com.example.object;

import java.util.Random;
import com.example.mybeatplane.R;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

/*小型敌机的类*/
public class SmallFish extends EnemyFish{
	private static int currentCount = 0;	 //	对象当前的数量
	private Bitmap smallFish; // 对象图片
	public static int sumCount = 8;	 	 	 //	对象总的数量
	public SmallFish(Resources resources) {
		super(resources);
		// TODO Auto-generated constructor stub
		this.score = 100;		// 为对象设置分数
        initBitmap();
	}
	//初始化数据
	@Override
	public void initial(int arg0,float arg1,float arg2){
		isAlive = true;
		Random ran = new Random();
		speed = ran.nextInt(8) + 8 * arg0;
		size = ran.nextFloat() * .7f + .1f;
		object_x = ran.nextInt((int)(screen_width - object_width));
		object_y = -object_height * (currentCount*2 + 1);
		currentCount++;
		if(currentCount >= sumCount){
			currentCount = 0;
		}
	}
	// 初始化图片资源
	@Override
	public void initBitmap() {
		// TODO Auto-generated method stub
		smallFish = BitmapFactory.decodeResource(resources, R.drawable.enemyfish);
		object_width = smallFish.getWidth();			//获得每一帧位图的宽
		object_height = smallFish.getHeight();		   //获得每一帧位图的高
	}
	// 对象的绘图函数
	@Override
	public void drawSelf(Canvas canvas) {
		// TODO Auto-generated method stub
		//判断敌机是否死亡状态
		if(isAlive) {
            if (isVisible) {
                Matrix matrix = new Matrix();
                matrix.postScale(size, size);
                Bitmap bmp = Bitmap.createBitmap(smallFish, 0, 0, smallFish.getWidth(), smallFish.getHeight(), matrix, true);
                canvas.save();
                canvas.clipRect(object_x, object_y, object_x + object_width * size, object_y + object_height * size);
                canvas.drawBitmap(bmp, object_x, object_y, paint);
                canvas.restore();
            }
            logic();
        }
	}
	// 释放资源
	@Override
	public void release() {
		// TODO Auto-generated method stub
		if(!smallFish.isRecycled()){
            smallFish.recycle();
		}
	}
}
