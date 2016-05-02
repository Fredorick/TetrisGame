import android.content.res.Resources;
import android.graphics.Canvas;

import com.example.game.IDrawable;
import com.example.game.IUpdateable;
import com.example.game.LifeBlock;


public class Figure implements IDrawable, IUpdateable{
	LifeBlock bl1;
	LifeBlock bl2;
	LifeBlock bl3;
	LifeBlock bl4;
	int side;
	int random;
	public Figure(int size, int x, int y, Resources res, int random) {
		this.random = random;
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
		bl1.draw(canvas);
		bl2.draw(canvas);
		bl3.draw(canvas);
		bl4.draw(canvas);
		
	}
	public void Form1(int size, int x, int y, Resources res){
		bl1 = new LifeBlock(size, x, y, res);
		bl2 = new LifeBlock(size, x, y, res);
		bl3 = new LifeBlock(size, x, y, res);
		bl4 = new LifeBlock(size, x, y, res);
	}
	public void Form2(int size, int x, int y, Resources res){
		bl1 = new LifeBlock(size, x, y, res);
		bl2 = new LifeBlock(size, x, y, res);
		bl3 = new LifeBlock(size, x, y, res);
		bl4 = new LifeBlock(size, x, y, res);
	}
	public void Form3(int size, int x, int y, Resources res){
		bl1 = new LifeBlock(size, x, y, res);
		bl2 = new LifeBlock(size, x, y, res);
		bl3 = new LifeBlock(size, x, y, res);
		bl4 = new LifeBlock(size, x, y, res);;
	}
	public void Form4(int size, int x, int y, Resources res){
		bl1 = new LifeBlock(size, x, y, res);
		bl2 = new LifeBlock(size, x, y, res);
		bl3 = new LifeBlock(size, x, y, res);
		bl4 = new LifeBlock(size, x, y, res);
	}
	public void Form5(int size, int x, int y, Resources res){
		bl1 = new LifeBlock(size, x, y, res);
		bl2 = new LifeBlock(size, x, y, res);
		bl3 = new LifeBlock(size, x, y, res);
		bl4 = new LifeBlock(size, x, y, res);
	}
	public void Form6(int size, int x, int y, Resources res){
		bl1 = new LifeBlock(size, x, y, res);
		bl2 = new LifeBlock(size, x, y, res);
		bl3 = new LifeBlock(size, x, y, res);
		bl4 = new LifeBlock(size, x, y, res);
	}
	public void Form7(int size, int x, int y, Resources res){
		bl1 = new LifeBlock(size, x, y, res);
		bl2 = new LifeBlock(size, x, y, res);
		bl3 = new LifeBlock(size, x, y, res);
		bl4 = new LifeBlock(size, x, y, res);
	}
	public LifeBlock Return1(){
		return bl1;
	}
	@Override
	public void update(int x) {
		bl1.update(side);
		bl2.update(side);
		bl3.update(side);
		bl4.update(side);
		
	}
	

}
