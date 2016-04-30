package com.example.game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class DeadBlock extends Blocks implements IDrawable, Dead {
	Rect dedrect;
	Paint paint;
	public DeadBlock(LifeBlock lifeBlock) {
		super(null);
		dedrect = new Rect(lifeBlock.ReturnRect());
		paint = lifeBlock.ReturnPaint();
	}
	@Override
	public void draw(Canvas canvas){
		canvas.drawRect(dedrect, paint);
		
	}
}