package com.example.game;

import interfaces.IDrawable;
import interfaces.IUpdateable;
import util.MyButton;
import util.MyRect;
import util.OnSwipeTouchListener;
import util.TetrisDrawer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

public class Tetris extends SurfaceView implements IDrawable, IUpdateable, SurfaceHolder.Callback
	{
	private static Blocks[][] blocks;
	DisplayMetrics display;
	int side;
	int width;
	int height;
	int[] y1;
	int x1;
	static int blocksize;
	static int Fieldram;
	int gamefieldheight;
	int gamefieldwidth;
	TetrisDrawer drawer;
	MyRect rect;
	Bitmap bitmap;
	Rect gameFieldrect;
	Rect scoreFieldrect;
	Rect nextFieldrect;
	Rect buttonRect;
	Rect Field;
	Paint paint;
	GameField gameField;
	ScoreField scoreField;
	NextField nextField;
	MyButton menuButton;
	DeadBlock currentDeadBlock;
	Resources res;
	Figure figure;
	Intent intent;
	static MyTimer myTimer;
	int CurX;
	int CurY;
	int score;
	int count;
	int cords[] = new int[8];
	boolean canY;
	int random;
	SurfaceHolder holder;
	Canvas canvas;
	Activity act;
	@SuppressLint("ShowToast") public Tetris(Context context) {
		super(context);
		this.getHolder().addCallback(this);
		side = 0;
		score = 0;
		count = 0;
		y1 = new int [21];
		x1 = 0;
		paint = new Paint();
		blocks = new Blocks[10][21];
		drawer = new TetrisDrawer();
		res = getResources();
		holder = getHolder();
		canvas = holder.lockCanvas();
		display = res.getDisplayMetrics();
		width = display.widthPixels;
		height = display.heightPixels;
		blocksize = (int) width / 15;
		Fieldram = (int) width / 60;
		gamefieldwidth = 10 * blocksize;	
		intent = new Intent(context, Menu.class);
		gamefieldheight = (int) (21 * blocksize);
		rect = new MyRect(Fieldram, gamefieldwidth, gamefieldheight, width, height);
		gameFieldrect = new Rect(rect.getRect("gamefield", gameFieldrect));
		gameField = new GameField(getResources(), gameFieldrect);
		nextFieldrect = new Rect(rect.getRect("nextfield", nextFieldrect));
		nextField = new NextField(getResources(), nextFieldrect);
		scoreFieldrect = new Rect(rect.getRect("scorefield", scoreFieldrect));
		scoreField = new ScoreField(getResources(), scoreFieldrect);
		buttonRect = new Rect(rect.getRect("button", buttonRect));
		Field = new Rect(rect.getRect("field", Field));
		menuButton = new MyButton(getResources(), "Menu", buttonRect );
		myTimer = new MyTimer(500, 500);
		canvas = new Canvas();
		canY = true;
		bitmap = drawer.DrawTetrisFon(res, bitmap);
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 21; y++) {
				blocks[x][y] = new Blocks(new Rect(x * blocksize + Fieldram, y
						* blocksize, x * blocksize + blocksize + Fieldram, y
						* blocksize + blocksize));
			}
		}
		random = (int) (Math.random() * 7 + 1);
		addBlocks(random);
		Timer();
		this.setOnTouchListener(Swiper(context));

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		TetrisThread tetrisThread = new TetrisThread();
		tetrisThread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	public static void Timer() {
		myTimer.start();
	}

	class TetrisThread extends Thread {
		public void run() {
			while (true) {
				
				if (canvas != null) {
					Do(canvas, holder);
				}
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			}
		}
	}

	void Do(Canvas canvas, SurfaceHolder holder){
		ScoreField.SCupdate(score);
		canvas = holder.lockCanvas();
		draw(canvas);
		holder.unlockCanvasAndPost(canvas);
		if (myTimer.finish) {
			canY = true;
			myTimer.Break();
			Timer();
		} else {
			canY = false;
		}
		update(side, canY);
		side = 0;
	}
	static int GetBlockSize() {
		return blocksize;
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, null, Field, paint);
		nextField.draw(canvas);
		scoreField.draw(canvas);
		gameField.draw(canvas);
		menuButton.draw(canvas);
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 21; y++) {
				blocks[x][y].draw(canvas);
			}
		}
		if (figure != null) {
			figure.draw(canvas);
		}
	}
	public void Proverka(){

			for (int y = 0; y < 21; y++) {
				for (int x = 0; x < 10; x++){
				if (blocks[x][y] instanceof Dead)
				x1++;	
			}
				if(x1==10){
					y1[y]++;
				}
				x1 = 0;					
		}
	}
	public void Deleting(){
		x1 = 0;
		for (int y = 0; y < 21; y++) {
			for (int x = 0; x < 10; x++){
				if(blocks[x][y] instanceof Dead){
					try {
						blocks[x][y+x1] = blocks[x][y];
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(y1[y] == 1){					
					blocks[x][y] = new Blocks(new Rect(x * blocksize + Fieldram, y
							* blocksize, x * blocksize + blocksize + Fieldram, y
							* blocksize + blocksize));				
				}
				
			}
			if(y1[y] == 1){
			x1++;}
			y1[y] = 0;
		}
	}
	public void addBlocks(int random) {
		if (figure != null) {
			figure.ResetForm();
			Transform(figure);
			Proverka();
			Deleting();
		}
		random = nextField.GetRandomNextBlock();
		figure = new Figure(blocksize, blocksize, blocksize, res, random);
		nextField.Nextupdate();
	}
	static Blocks GetBlocks(int x, int y) {
		Blocks block;
		try {
			block = blocks[x][y];
		} catch (ArrayIndexOutOfBoundsException exception) {
			block = null;
		}
		return block;
	}
	public void Transform(Figure figure) {
		cords[0] = figure.ReturnBL1().GetX();
		cords[1] = figure.ReturnBL1().GetY();
		blocks[cords[0]][cords[1]] = Die(figure.ReturnBL1());
		cords[2] = figure.ReturnBL2().GetX();
		cords[3] = figure.ReturnBL2().GetY();
		blocks[cords[2]][cords[3]] = Die(figure.ReturnBL2());
		cords[4] = figure.ReturnBL3().GetX();
		cords[5] = figure.ReturnBL3().GetY();
		blocks[cords[4]][cords[5]] = Die(figure.ReturnBL3());
		cords[6] = figure.ReturnBL4().GetX();
		cords[7] = figure.ReturnBL4().GetY();
		blocks[cords[6]][cords[7]] = Die(figure.ReturnBL4());

	}

	public DeadBlock Die(LifeBlock lifeBlock) {
		DeadBlock db = new DeadBlock(lifeBlock);
		return db;

	}

	@Override
	public void update(int x, boolean y) {
		random = (int) (Math.random() * 6 + 1);
		if (figure != null && figure.CanGo) {
			figure.update(x, y);
		}
		if (!figure.CanGo) {
			addBlocks(random);
		}
		score = 100;
	}
	OnSwipeTouchListener Swiper(final Context context){
		return new OnSwipeTouchListener(context, blocksize){ 
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getX() >= buttonRect.left && event.getX() <= buttonRect.left ){
					if(event.getY() >= buttonRect.top && event.getY() <= buttonRect.bottom)
								context.startActivity(intent);
				}
				return super.onTouch(v, event);
			}
			  @Override
			public void onSwipeLeft() {
				side--;
				Do(canvas, holder);
			}
			  @Override
			public void onSwipeRight() {
				side++;
				Do(canvas, holder);
			}
			public void onSwipeTop() {
				figure.Turn(0, 0);		
			}};
}
	}
