package com.example.game;
import android.content.res.Resources;
import android.graphics.Canvas;


public class Figure implements Drawable, IUpdateable{
	LifeBlock bl[] = new LifeBlock[4];
	int side;
	int random;
	boolean CanRight = true;
	boolean CanLeft = true;
	boolean CanGo = true;
	int size;
	public Figure(int size, int x, int y, Resources res, int random) {
		this.random = random;
		this.size = size;
		switch(random){
		case 1 : Form1(size,x,y, res);
				 break;
		case 2 : Form2(size,x,y, res);
		 		 break;
		case 3 : Form3(size,x,y, res);
		 		 break;
		case 4 : Form4(size,x,y, res);
		 		 break;
		case 5 : Form5(size,x,y, res);
		 		 break;
		case 6 : Form6(size,x,y, res);
		 		 break;
		case 7 : Form7(size,x,y, res);
		 		 break;
		}
	}

	@Override
	public void draw(Canvas canvas) {
		bl[0].draw(canvas);
		bl[1].draw(canvas);
		bl[2].draw(canvas);
		bl[3].draw(canvas);
		
	}
	public void Form1(int size, int x, int y, Resources res){ // прямой
		bl[0] = new LifeBlock(size, x, y, res, this, 1);
		bl[1] = new LifeBlock(size, x, y+size, res, this, 1);
		bl[2] = new LifeBlock(size, x, y+2*size, res, this, 1);
		bl[3] = new LifeBlock(size, x, y+3*size, res, this, 1);
	}
	public void Form2(int size, int x, int y, Resources res){ // Г-образный слева
		bl[0] = new LifeBlock(size, x-size, y, res, this, 2);
		bl[1] = new LifeBlock(size, x, y, res, this, 2);
		bl[2] = new LifeBlock(size, x, y+size, res, this, 2);
		bl[3] = new LifeBlock(size, x, y+2*size, res, this, 2);;
	}
	public void Form3(int size, int x, int y, Resources res){ // Г-образный справа
		bl[0] = new LifeBlock(size, x+size, y, res, this, 3);
		bl[1] = new LifeBlock(size, x, y, res, this, 3);
		bl[2] = new LifeBlock(size, x, y+size, res, this, 3);
		bl[3] = new LifeBlock(size, x, y+2*size, res, this, 3);
	}
	public void Form4(int size, int x, int y, Resources res){ // лесенка-образный справа
		bl[0] = new LifeBlock(size, x-size, y, res, this, 4);
		bl[1] = new LifeBlock(size, x, y, res, this, 4);
		bl[2] = new LifeBlock(size, x, y+size, res, this, 4);
		bl[3] = new LifeBlock(size, x+size, y+size, res, this, 4);
	}
	public void Form5(int size, int x, int y, Resources res){ // лесенка-образный слева
		bl[0] = new LifeBlock(size, x+size, y, res, this, 5);
		bl[1] = new LifeBlock(size, x, y, res, this, 5);
		bl[2] = new LifeBlock(size, x, y+size, res, this, 5);
		bl[3] = new LifeBlock(size, x-size, y+size, res, this, 5);
	}
	public void Form6(int size, int x, int y, Resources res){ // квадрат
		bl[0] = new LifeBlock(size, x+size, y, res, this,6);
		bl[1] = new LifeBlock(size, x, y, res, this,6);
		bl[2] = new LifeBlock(size, x, y+size, res, this,6);
		bl[3] = new LifeBlock(size, x+size, y+size, res, this,6);
	}
	public void Form7(int size, int x, int y, Resources res){ // пирамида
		bl[0] = new LifeBlock(size, x, y, res, this,7);
		bl[1] = new LifeBlock(size, x, y+size, res, this,7);
		bl[2] = new LifeBlock(size, x-size, y+size, res, this,7);
		bl[3] = new LifeBlock(size, x+size, y+size, res, this,7);
	}
	public void Turn(){
		switch(random){
		case 2: Turn2();
		}
	}
	
	void Turn2(){
		bl[1].SetY(2*size);
		bl[2].SetY(size); bl[2].SetX(size);
		bl[4].SetY(-size);
	}
	
	
	public LifeBlock ReturnBL1(){
		return bl[0];
	}
	public LifeBlock ReturnBL2(){
		return bl[1];
	}
	public LifeBlock ReturnBL3(){
		return bl[2];
	}
	public LifeBlock ReturnBL4(){
		return bl[3];
	}
	public boolean CanGo(){
		if(bl[0].canGOB && bl[1].canGOB && bl[2].canGOB && bl[3].canGOB)
		return true;
		else
		return false;
	}
	@Override
	public void update(int side, boolean canY) {
		if(bl[0].canGOLeft && bl[1].canGOLeft && bl[2].canGOLeft && bl[3].canGOLeft){
			CanLeft = true;
		}
		else {CanLeft = false;}
		
		if(bl[0].canGORight && bl[1].canGORight && bl[2].canGORight && bl[3].canGORight){
			CanRight = true;
		}
		else {CanRight = false;}
		
		if(CanGo()) CanGo = true; else CanGo = false;
		
		bl[0].update(side, canY);
		bl[1].update(side, canY);
		bl[2].update(side, canY);
		bl[3].update(side, canY);
		bl[0].canGo();
		bl[1].canGo();
		bl[2].canGo();
		bl[3].canGo();
	}
	

}
