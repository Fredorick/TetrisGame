package com.example.game;

import interfaces.IDrawable;
import interfaces.IUpdateable;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class DeadBlock extends Blocks implements IDrawable, Dead, IUpdateable {
	Rect dedrect;
	Paint paint;
	Bitmap bitmap;
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
	@Override
	public void update(int y, boolean cany) {
		dedrect.bottom+=y;
		dedrect.top+=y;
		
	}
}
