package com.example.factory;

import android.content.res.Resources;

import com.example.object.GameObject;
import com.example.object.MyFish;
import com.example.object.SmallFish;
/*游戏对象的工厂类*/
public class GameObjectFactory {
	//创建小型敌机的方法
	public GameObject createSmallFish(Resources resources){ return new SmallFish(resources); }
	//创建玩家飞机的方法
	public GameObject createMyFish(Resources resources){ return new MyFish(resources); }
}
