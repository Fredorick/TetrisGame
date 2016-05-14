package com.example.game;

import util.MyTimer;
import interfaces.IDrawable;
import interfaces.IUpdateable;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class LifeBlock extends Blocks implements IDrawable, IUpdateable{
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
	int x2, y2;
	boolean moveable;
	public LifeBlock (int size, int x, int y, Resources res, Figure figure, int color){
		super(null);
		this.size = size;
		this.x =x+Fieldram;
		this.y = y;
		x2= 0;
		y2= 0;
		moveable = true;
		this.color = color;
		this.figure = figure;
		bitmap = drawer.DrawBlocks(res, bitmap);
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
	void SetCoods(int x, int y){
		SetX(x);
		SetY(y);
	}
	void SetX(int x){
		this.x += x*size;
	}
	void SetY(int y){
		this.y += y*size;
	}
	boolean Move(int x1,int y1){
		moveable= true;
		if((Tetris.GetBlocks(GetX()+x1, GetY()+y1))  instanceof Dead ) moveable = false;
		if((Tetris.GetBlocks(GetX()+x1, GetY()+y1))  ==  null) moveable = false;
		return moveable;
		
	}
	void canGo(){
		if(GameField.TryLeft(rectblock) && !(Tetris.GetBlocks(GetX()-1, GetY()) instanceof Dead))
			canGOLeft = true;
		else canGOLeft =  false;
		
		if(GameField.TryRight(rectblock) && !(Tetris.GetBlocks(GetX()+1, GetY()) instanceof Dead))
			canGORight = true;
		else canGORight =  false;
		
		if(GameField.TryBottom(rectblock) && !(Tetris.GetBlocks(GetX(), GetY()+1) instanceof Dead))
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
