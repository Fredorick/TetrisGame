package com.example.game;

import interfaces.IDrawable;
import interfaces.IUpdateable;
import util.MyButton;
import util.MyRect;
import util.MyTimer;
import util.OnSwipeTouchListener;
import util.Saver;
import util.TetrisDrawer;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
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
	Blocks currBlock;
	DeadBlock currDeadBlock;
	Resources res;
	boolean checked;
	Figure figure;
	static MyTimer myTimer;
	int CurX;
	int CurY;
	int score;
	int count;
	int cords[] = new int[8];
	boolean canY;
	int random;
	boolean Go = true;
	TetrisThread tetrisThread;
	static DBManager dbManager;
	Context context;
	Toast toast;
	static Saver saver;
	String[] blocksForSave;
	public Tetris(Context context) {
		super(context);
		this.context = context;
		this.getHolder().addCallback(this);
		dbManager = DBManager.getInstance(context);
		toast = Toast.makeText(context,"Вы проиграли", 1000);
		side = 0;
		score = 0;
		count = 0;
		saver = new Saver(context);
		y1 = new int [21];
		x1 = 0;
		blocksForSave = new String[210];
		paint = new Paint();
		blocks = new Blocks[10][21];
		currBlock = null;
		currDeadBlock = null;
		drawer = new TetrisDrawer();
		res = getResources();
		display = res.getDisplayMetrics();
		width = display.widthPixels;
		height = display.heightPixels;
		blocksize = (int) width / 15;
		Fieldram = (int) width / 60;
		gamefieldwidth = 10 * blocksize;	
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
		canY = true;
		checked = false;
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
	public Tetris(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.getHolder().addCallback(this);
		dbManager = DBManager.getInstance(context);
		side = 0;
		score = 0;
		toast = Toast.makeText(context,"Вы проиграли", 1000);
		y1 = new int [21];
		x1 = 0;
		blocksForSave = new String[210];
		saver = new Saver(context);
		paint = new Paint();
		blocks = new Blocks[10][21];
		drawer = new TetrisDrawer();
		res = getResources();
		display = res.getDisplayMetrics();
		width = display.widthPixels;
		height = display.heightPixels;
		blocksize = (int) width / 15;
		Fieldram = (int) width / 60;
		gamefieldwidth = 10 * blocksize;	
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
	public Tetris(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.getHolder().addCallback(this);
		dbManager = DBManager.getInstance(context);
		side = 0;
		score = 0;
		count = 0;
		y1 = new int [21];
		x1 = 0;
		
		saver = new Saver(context);
		paint = new Paint();
		toast = Toast.makeText(context,"Вы проиграли", 1000);
		blocks = new Blocks[10][21];
		drawer = new TetrisDrawer();
		res = getResources();
		blocksForSave = new String[210];
		display = res.getDisplayMetrics();
		width = display.widthPixels;
		height = display.heightPixels;
		blocksize = (int) width / 15;
		Fieldram = (int) width / 60;
		gamefieldwidth = 10 * blocksize;			
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
		myTimer = new MyTimer(200, 200);
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
		tetrisThread = new TetrisThread();		
		tetrisThread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		PrepareForSave();
		saver.saveFile(blocksForSave, "savedblocks");
		Break();
		
	}
	
	public static void Timer() {
		myTimer.start();
	}
	int ReturnButtonCoods(String s){
		switch(s){
		case "h" :return buttonRect.height();	
		case "w" :return buttonRect.width();
		case "x" :return 3*Fieldram;
		case "y" :return gamefieldheight+Fieldram;
		}
		return 0;
	}
	class TetrisThread extends Thread {
		SurfaceHolder holder = getHolder();
		public void run() {
			while (Go) {
				Canvas canvas = holder.lockCanvas();
				if (canvas != null) {				
					draw(canvas);
					holder.unlockCanvasAndPost(canvas);
				}
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		}
		void Break(){
			Go = false;
		}
	}
	static int GetBlockSize() {
		return blocksize;
	}
	public void onGameFinish(){
		dbManager.addResult("Fredorick",
	            scoreField.GetResult());
	}
	public void PrepareForSave(){
		int i = 0;
		for (int y = 20; y >= 0; y--) {
			for (int x = 9; x >= 0; x--){
				
				if(blocks[x][y] instanceof Dead){
				blocksForSave[i] = Integer.toString(((DeadBlock)blocks[x][y]).GetX())+":"
						+Integer.toString(((DeadBlock)blocks[x][y]).GetY())+":"+((DeadBlock)blocks[x][y]).GetRandom()+"/n";
				}
				else{
					blocksForSave[i] = Integer.toString(blocks[x][y].GetX())
					+":"+Integer.toString(blocks[x][y].GetY())+":null/n";
				}
				i++;
			}
		}
	}

	public void draw(Canvas canvas) {
		canvas.drawBitmap(bitmap, null, Field, paint);
		nextField.draw(canvas);
		scoreField.draw(canvas);
		gameField.draw(canvas);
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 21; y++) {
				blocks[x][y].draw(canvas);
			}
		}
		if (figure != null) {
			figure.draw(canvas);
		}
	}
	public boolean Check(){
		checked = false;
		int numb = 0;;
		for (int y = 20; y >= 0; y--) {
			for (int x = 9; x >= 0; x--){
				if(blocks[x][y] instanceof Dead){
					numb++;
				}
			}
			if(numb==10){
				y1[y] = -1;
				checked = true;
			}
			numb=0;
		}
		return checked;
	}
	public void Deleting(){
		int c = 0;
		int y2= 0;
		boolean deleted = false;
		for (int y = 20; y >= 0; y--) {
			if(y1[y] == -1){
				DeleteLine(y);
				c++;				
				deleted = true;
			}
			if(!deleted && c > 0)
			for (int x = 9; x >= 0; x--){
				try {
					y2 = y+c;
					if(blocks[x][y] instanceof Dead){
					blocks[x][y2] =blocks[x][y];
					((DeadBlock) blocks[x][y2]).SetY(c);					
					}
					else{
						blocks[x][y2] = new Blocks(new Rect(x * blocksize + Fieldram, y
								* blocksize, x * blocksize + blocksize + Fieldram, y
								* blocksize + blocksize));
					}
					}
					catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				blocks[x][y] = new Blocks(new Rect(x * blocksize + Fieldram, y
						* blocksize, x * blocksize + blocksize + Fieldram, y
						* blocksize + blocksize));
			}
			deleted = false;
		}
		scoreField.SCupdate(c);
	}
	public void DeleteLine(int y){
		for (int x = 9; x >= 0; x--){
			blocks[x][y] = new Blocks(new Rect(x * blocksize + Fieldram, y
					* blocksize, x * blocksize + blocksize + Fieldram, y
					* blocksize + blocksize));
		}
	}
	public void Clean(){
		for(int i = 20; i>=0;i--){
			y1[i] = 0;		
		}
		checked = false;
		currBlock = null;
		currDeadBlock = null;
	}
	public void Break(){
		tetrisThread.Break();
	}
	public void addBlocks(int random) {     // добавление новых блоков
		if (figure != null) {
			figure.ResetForm();
			Transform(figure);
			Clean();
			if(Check()){
			Deleting();}
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
	public void Transform(Figure figure) {    // Трансформация каждого блока фигуры в мертвый блок
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

	public DeadBlock Die(LifeBlock lifeBlock) {  //превращение живого блока в мертвый
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
			if(Go){
			addBlocks(random);
			}
		}
	}
	OnSwipeTouchListener Swiper(final Context context){
		return new OnSwipeTouchListener(context, blocksize){ 
			  @Override
			public void onSwipeLeft() {
				side--;
			}
			  @Override
			public void onSwipeRight() {
				side++;
			}
			public void onSwipeTop() {
				figure.Turn(0, 0);		
			}};
}	
	}
