package com.example.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;

public class ScoreField extends Field implements Drawable {
	public static String textScore;
	static int score;
	Bitmap bitmap;
	Paint paint;
	Rect rect;
	Align align = Align.CENTER;
	public ScoreField(Resources res, Rect rect) {
		textScore = "0";
		paint = new Paint();
		bitmap = drawer.DrawTetrisScoreBox(res, bitmap);
		this.rect = rect;
	}

	public static void SCupdate(int x) {
		textScore = Integer.toString(x);
		score = x;
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, null, rect, paint);
		if(score<1000 && score >=0){
		paint.setTextSize(rect.height()/2);}
		else if(score<10000 && score >=1000){
		paint.setTextSize(rect.height()/3);
		}
		else if(score>=10000){
		paint.setTextSize(rect.height()/4);
		}
		paint.setTextAlign(align);
		canvas.drawText(textScore, rect.centerX(), (int)(rect.centerY()*1.1), paint);
		
	}

}
