package com.example.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class LifeBlock extends Blocks implements Drawable, IUpdateable{
	int x;
	int y;
	int size;
	Paint paint;
	Rect rectblock;
	boolean canGOLeft = true;
	boolean canGORight = true;
	boolean canGOB = true;
	MyTimer myTimer;
	int red = (int) (Math.random()*255);
	int green = (int) (Math.random()*255);
	int blue = (int) (Math.random()*255);
	int Fieldram = Tetris.Fieldram;
	Bitmap bitmap[] = new Bitmap[8];
	Figure figure;
	int id;
	String numb;
	int color;
	public LifeBlock (int size, int x, int y, Resources res, Figure figure, int color){
		super(null);
		this.size = size;
		this.x =x+Fieldram;
		this.y = y;
		this.color = color;
		this.figure = figure;
		bitmap = drawer.DrawBlocks(color, res, bitmap);
		paint = new Paint();	
		rectblock = new Rect(x, y, size+x, size+y);
	}
	public void draw(Canvas canvas){
		canvas.drawBitmap(bitmap[color], null, rectblock, paint);
		//canvas.drawRect(x, y, size+x, size+y , paint);
		
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
		return bitmap[color];
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
	void SetX(int x){
		this.x += x;
	}
	void SetY(int y){
		this.y += y;
	}
	void canGo(){
		if(GameField.TryLeft(rectblock) && !(Tetris.GetLeftBlocks(GetX(), GetY()) instanceof Dead))
			canGOLeft = true;
		else canGOLeft =  false;
		
		if(GameField.TryRight(rectblock) && !(Tetris.GetRightBlocks(GetX(), GetY()) instanceof Dead))
			canGORight = true;
		else canGORight =  false;
		
		if(GameField.TryBottom(rectblock) && !(Tetris.GetBottomBlocks(GetX(), GetY()) instanceof Dead))
			 canGOB =  true;		
		else canGOB = false;
		
	}
	@Override
	public void update(int side, boolean canY) {
		rectblock = new Rect(x, y, size+x, size+y);
		canGo();
		if(!figure.CanLeft){
			canGOLeft = false;
		}
		if(!figure.CanRight){
			canGORight = false;
		}
		
			if(canGOB){		
				if (canY){
					y+=size;
				}
			if(side!=0)
						if (side>0){  if(canGORight)  x+=size;   }
						else       {  if(canGOLeft)   x-=size;   }	
			
			}
			else{
			}
		}
}
