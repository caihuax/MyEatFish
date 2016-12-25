package com.example.object;

import com.example.interfaces.IMyPlane;
import com.example.mybeatplane.R;
import com.example.view.MainView;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

/*玩家鱼的类*/
public class MyFish extends GameObject implements IMyPlane{
	private float middle_x;			 // 鱼的中心坐标
	private float middle_y;
	private Bitmap myfish;			 // 鱼的图片
	private MainView mainView;
	public MyFish(Resources resources) {
		super(resources);
		// TODO Auto-generated constructor stub
        this.speed = 8;
        this.size = .3f;
		initBitmap();
	}
	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}
	// 设置屏幕宽度和高度
	@Override
	public void setScreenWH(float screen_width, float screen_height) {
		super.setScreenWH(screen_width, screen_height);
		object_x = screen_width/2 - object_width/2;
		object_y = screen_height - object_height;
		middle_x = object_x + object_width/2;
		middle_y = object_y + object_height/2;
	}
	// 初始化图片资源的
	@Override
	public void initBitmap() {
		// TODO Auto-generated method stub
		myfish = BitmapFactory.decodeResource(resources, R.drawable.myfish);
        object_width = myfish.getWidth(); // 获得每一帧位图的宽
        object_height = myfish.getHeight(); 	// 获得每一帧位图的高
        object_width *= size;
        object_height *= size;
	}
	// 对象的绘图方法
	@Override
	public void drawSelf(Canvas canvas) {
		// TODO Auto-generated method stub
		if(isAlive) {
            object_width = myfish.getWidth(); // 获得每一帧位图的宽
            object_height = myfish.getHeight(); 	// 获得每一帧位图的高
            object_width *= size;
            object_height *= size;
            Matrix matrix = new Matrix();
            matrix.postScale(size, size);
            Bitmap bmp = Bitmap.createBitmap(myfish, 0, 0, myfish.getWidth(), myfish.getHeight(), matrix, true);
            canvas.save();
            canvas.clipRect(object_x, object_y, object_x + object_width, object_y + object_height);
            canvas.drawBitmap(bmp, object_x, object_y, paint);
            canvas.restore();
        }
	}
	// 释放资源的方法
	@Override
	public void release() {
		// TODO Auto-generated method stub
		if(!myfish.isRecycled()){
            myfish.recycle();
		}
	}
	//getter和setter方法
	public float getMiddle_x() { return middle_x; }
	@Override
	public void setMiddle_x(float middle_x) {
		this.middle_x = middle_x;
		this.object_x = middle_x - object_width/2;
	}
	@Override
	public float getMiddle_y() { return middle_y; }
	@Override
	public void setMiddle_y(float middle_y) {
		this.middle_y = middle_y;
		this.object_y = middle_y - object_height/2;
	}	
}
