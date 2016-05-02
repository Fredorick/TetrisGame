package com.example.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class LifeBlock extends Blocks implements IDrawable, IUpdateable, Moveable{
	int x;
	int y;
	int size;
	Paint paint;
	Rect rectblock;
	boolean canGO = true;
	boolean canGOLeft;
	boolean canGORight;
	boolean canGOB;
	MyTimer myTimer;
	int red = (int) (Math.random()*255);
	int green = (int) (Math.random()*255);
	int blue = (int) (Math.random()*255);
	int Fieldram;
	Bitmap bitmap;
	public LifeBlock (int size, int x, int y, Resources res){
		super(null);
		this.size = size;
		this.x =x;
		Fieldram = x;
		this.y = y;
		bitmap = BitmapFactory.decodeResource(res, R.drawable.bl1);
		paint = new Paint();
		paint.setColor(Color.rgb(green, blue, red));		
		rectblock = new Rect(x, y, size+x, size+y);
		
	}
	public void draw(Canvas canvas){
		canvas.drawBitmap(bitmap, null, rectblock, paint);
		//canvas.drawRect(x, y, size+x, size+y , paint);
		
	}
	public boolean CanGo(){
		if(!canGOB)
		return false;
		else
		return true;
	}
	int ReturnSize(){
		return size;
	}
	Rect ReturnRect(){
		return rectblock;
	}
	Paint ReturnPaint(){
		return paint;
	}
	Rect ReturnNextBlock(){
		return new Rect(x, y+size, x+size, y+2*size);
	}
	Bitmap ReturnBitmap(){
		return bitmap;
	}
	int GetX(){
		int blocksX = x;
		blocksX = (blocksX - Fieldram)/size;
		return blocksX;
	}
	int GetY(){
		int blocksY = y;
		blocksY /= size;
		return blocksY;
	}
	@Override
	public void update(int side) {
		rectblock = new Rect(x, y, size+x, size+y);
		if(!(GameField.TryLeft(rectblock)))
			canGOLeft = false;
		else canGOLeft =  true;
		
		if(!(GameField.TryRight(rectblock)))
			canGORight = false;
		else canGORight =  true;
		
		if(GameField.TryBottom(rectblock))
			 canGOB =  true;		
		else canGOB = false;
		if(Tetris.GetBlocks(GetX(), GetY()) instanceof Dead){
			 canGOB =  false;	
		
		}
			if(canGOB){		
				if (Tetris.myTimer.finish){
					y+=size;
				    (Tetris.myTimer).Break();;
					Tetris.Timer();
				}
			if(side!=0)
						if (side>0){  if(canGORight)  x+=size;   }
						else       {  if(canGOLeft)   x-=size;   }	
			
			}
			else{
			//Tetris.myTimer.cancel();	
			}
		}
	@Override
	public void moveable() {
		// TODO Auto-generated method stub
		
	}
}
