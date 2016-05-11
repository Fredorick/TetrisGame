package com.example.game;

import interfaces.IDrawable;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class GameField extends Field implements IDrawable {
	Bitmap bitmap;
	Paint paint;
	private Rect rect;
	int size;
	static int rectleft;
	static int rectright;
	static int rectbottom;
	static boolean next = false;
	public GameField(Resources res, Rect rect) {
		paint = new Paint();
		bitmap = drawer.DrawTetrisGameField(res, bitmap);
		this.rect = rect;
		size = Tetris.GetBlockSize();
		rectleft = rect.left;
		rectright = rect.right;
		rectbottom = rect.bottom;
	}
	public static boolean TryLeft(Rect rect){
		if(rect.left > rectleft){
			return true;
		}
		return false;	
	}
	public static boolean TryNext(){
		return next;
	}
	public static boolean TryRight(Rect rect){
		if(rect.right < rectright){
			return true;
		}
		return false;	
	}
	public static boolean TryBottom(Rect rect){
		if( rect.bottom < rectbottom){
			next = false;
			return true;
		}
		next = true;
		return false;
	}
	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, null, rect, paint);
		
	}

}
