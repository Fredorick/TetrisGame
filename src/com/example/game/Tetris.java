package com.example.game;

import java.util.Stack;

import util.MyButton;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class Tetris extends SurfaceView implements IDrawable, IUpdateable, SurfaceHolder.Callback, OnTouchListener


{
	private static Blocks[][] blocks;
	Stack<LifeBlock> st;
	boolean touched = true;
	DisplayMetrics display;
	int side;
	int width;
	int height;
	static int blocksize;
	int Fieldram;
	int gamefieldheight;
	int gamefieldwidth;
	Bitmap bitmap;
	Rect gameFieldrect;
	Rect scoreFieldrect;
	Rect nextFieldrect;	
	Rect buttonRect;
	Paint paint;
	GameField gameField;
	ScoreField scoreField;
	NextField nextField;
	MyButton menuButoon;
	DeadBlock currentDeadBlock;
	Resources res;
	static MyTimer myTimer;
	int CurX;
	int CurY;
	int score;
	int count;
	public Tetris(Context context) {
		super(context);
		this.getHolder().addCallback(this);
		this.setOnTouchListener(this);
		side = 0;
		score = 0;
		count = 0;
		paint = new Paint();
		blocks = new Blocks[10][21];
		st = new Stack<LifeBlock>();
		res = getResources();
		display = getResources().getDisplayMetrics();
		width = display.widthPixels;
		height = display.heightPixels;
		blocksize = (int)width/15;
		Fieldram =  (int)width/60;
		gamefieldwidth = 10*blocksize;
		gamefieldheight = (int)(21*blocksize);
		gameFieldrect = new Rect(Fieldram, Fieldram, gamefieldwidth+Fieldram, gamefieldheight);
		gameField = new GameField(getResources(), gameFieldrect);
		nextFieldrect = new Rect(gamefieldwidth+2*Fieldram, Fieldram,width-Fieldram, (width-gamefieldwidth - Fieldram));
		nextField = new NextField(getResources(), nextFieldrect);
		scoreFieldrect = new Rect(gamefieldwidth+2*Fieldram,(width-gamefieldwidth), width-Fieldram, (width-gamefieldwidth + Fieldram + nextFieldrect.height()) );
		scoreField = new ScoreField(getResources(), scoreFieldrect);
		buttonRect = new Rect(3*Fieldram,gamefieldheight+Fieldram,width-3*Fieldram,height-Fieldram);
		menuButoon = new MyButton(getResources(), "Menu");
		myTimer = new MyTimer(500, 500);

		for(int x = 0; x<10; x++){
			for(int y = 0; y<21;y++){
				blocks[x][y] = new Blocks(new Rect(x*blocksize+Fieldram, y*blocksize, x*blocksize+blocksize+Fieldram, y*blocksize+blocksize));
			}
		}
		addBlocks(new LifeBlock(blocksize,Fieldram, blocksize, res));
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
	
		Motor motor = new Motor();
		motor.start();
		
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}
	public static void Timer(){
		myTimer.start();
	}
	class Motor extends Thread{
		SurfaceHolder holder = getHolder();
		public void run (){
			Timer();
			//paint.setAntiAlias(true);
			while(true){
				Canvas canvas = holder.lockCanvas();
				if (canvas != null)
				{
					ScoreField.SCupdate(score);
					draw(canvas);
					holder.unlockCanvasAndPost(canvas);
				}
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				update(side);
				side = 0;
			}
		}
	}
	public static int GetBlockSize(){
		return blocksize;
	}
	public void draw(Canvas canvas)
	{	
		canvas.drawColor(Color.DKGRAY);
		gameField.draw(canvas);
		scoreField.draw(canvas);
		nextField.draw(canvas);
		menuButoon.draw(canvas,buttonRect);
		/*if(blocks.size() != 0){
		for(int i = 0; i < blocks.size();i++){
			if (blocks.get(i) != null && blocks.get(i) instanceof IDrawable){
				blocks.get(i).draw(canvas);
			}
			}
		}*/
		for(int x = 0; x<10; x++){
			for(int y = 0; y<21;y++){
				blocks[x][y].draw(canvas);
			}
		}
		if(st.peek() != null){
		st.peek().draw(canvas);
		}
	}
	public void addBlocks(LifeBlock lifeBlock) {

			if(st.isEmpty()){
			st.add(lifeBlock);}
			else{
			CurX = st.peek().GetX();
			CurY = st.peek().GetY();
			blocks[CurX][CurY] = Die(st.pop());
			st.add(lifeBlock);
			}
	}
	static Blocks GetBlocks(int x, int y){
		Blocks block;
		try {
			block = blocks[x][y+1];
		} catch (ArrayIndexOutOfBoundsException exception) {
			block = null;
		}
		return block;
	}
	public DeadBlock Die(LifeBlock lifeBlock){
		DeadBlock db = new DeadBlock(lifeBlock);
		return db;
		
	}
	@Override
	public void update(int x) {
		st.peek().update(x);
		if((st.peek().CanGo() == false)){
		addBlocks(new LifeBlock(blocksize,Fieldram, blocksize, res));
		}
		score = (st.peek()).y;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(touched){
			if((event.getX()) >= width/2){
				side+=15;
			}
			else{
				side-=15;
			}
		}
		return false;
	}
	
}
