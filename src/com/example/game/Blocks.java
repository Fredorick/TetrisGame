package com.example.game;

import util.TetrisDrawer;
import interfaces.IDrawable;
import interfaces.IUpdateable;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Blocks implements IDrawable, IUpdateable {
	Rect rect;
	Rect rect2;
	Paint paint;
	TetrisDrawer drawer;
	public Blocks(Rect rect) {
	drawer = new TetrisDrawer();
	this.rect = new Rect(rect);
	paint = new Paint();
	}
	@Override
	public void draw(Canvas canvas) {
		paint.setColor(Color.argb(120, 150, 150, 150));
		canvas.drawRect(rect, paint);
	}
	@Override
	public void update(int x, boolean y) {
		// TODO Auto-generated method stub
		
	}
	public Blocks Return(){
		return this;
	}

}
