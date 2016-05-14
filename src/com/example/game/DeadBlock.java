package com.example.game;

import interfaces.IDrawable;
import interfaces.IUpdateable;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class DeadBlock extends Blocks implements IDrawable, Dead, IUpdateable {
	Rect dedrect;
	Paint paint;
	Bitmap bitmap;
	int size = Tetris.GetBlockSize();
	public DeadBlock(LifeBlock lifeBlock) {
		super(null);
		dedrect = new Rect(lifeBlock.ReturnRect());
		bitmap = lifeBlock.ReturnBitmap();
		paint = new Paint();
	}
	@Override
	public void draw(Canvas canvas){
		canvas.drawBitmap(bitmap, null, dedrect, paint);
		
	}
	int GetY(){
		return dedrect.top/size;
	}
	int GetX(){
		return dedrect.left/size;
	}
	String GetRandom(){
		Config config = bitmap.getConfig();
		String conf = config.toString();
		return conf;		
	}
	@Override
	public void update(int y, boolean cany) {
	}
	public void SetY(int y){
		dedrect.top+=y*size;
		dedrect.bottom+=y*size;
	}
	public DeadBlock Return(){
		return this;
	}
}
