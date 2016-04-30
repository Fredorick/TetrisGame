package com.example.game;

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
	public LifeBlock (int size, int x){
		super(null);
		this.size = size;
		this.x = x+2*size;
		Fieldram = x;
		y = 0;
		paint = new Paint();
		paint.setColor(Color.rgb(green, blue, red));		
		rectblock = new Rect(x, y, size+x, size+y);
		myTimer = new MyTimer(1000, 1000);
		myTimer.start();
	}
	public void draw(Canvas canvas){
		canvas.drawRect(x, y, size+x, size+y , paint);
		
	}
	public void Timer(){
		myTimer.start();
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
	int GetX(){
		int blocksX = x;
		blocksX = (blocksX - Fieldram)/size;
		return blocksX;
	}
	int GetY(){
		int blocksY = x;
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

			if(canGOB){		
				if (myTimer.finish){
					y+=size;
					myTimer.Break();;
					Timer();
				}
			if(side!=0)
						if (side>0){  if(canGORight)  x+=size;   }
						else       {  if(canGOLeft)   x-=size;   }	
			
			}
			else{
			myTimer.cancel();	
			}
		}
	@Override
	public void moveable() {
		// TODO Auto-generated method stub
		
	}
}
