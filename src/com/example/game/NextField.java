package com.example.game;

import interfaces.IDrawable;
import interfaces.IUpdateable;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class NextField extends Field implements IDrawable, IUpdateable {
	Bitmap field;
	Paint paint;
	Rect rect;
	Bitmap nextbl[] = new Bitmap[8];
	int randomNextBlock;
	public NextField(Resources res, Rect rect) {
		paint = new Paint();
		field  = drawer.DrawTetrisNextBox(res, field);
		nextbl = drawer.DrawNextBlocks(res,nextbl);
		Nextupdate();
		this.rect = rect;
	}

	@Override
	public void update(int x, boolean  y) {

	}
	void Nextupdate(){
		randomNextBlock = (int)(Math.random()*7+1);
	}
	int GetRandomNextBlock(){
		return randomNextBlock;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(field, null, rect, paint);
		canvas.drawBitmap(nextbl[randomNextBlock], null, rect, paint);
	}

}
