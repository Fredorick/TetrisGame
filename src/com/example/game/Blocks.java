package com.example.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Blocks implements Drawable {
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
		//paint.setColor(Color.argb(120, 150, 150, 150));
		//canvas.drawRect(rect, paint);
	}

}
