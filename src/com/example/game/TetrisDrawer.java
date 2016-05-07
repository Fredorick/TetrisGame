package com.example.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class TetrisDrawer extends Drawer {

	public TetrisDrawer() {
	}
	
	public Bitmap DrawTetrisFon(Resources res, Bitmap bitmap) {
		bitmap = BitmapFactory.decodeResource(res, R.drawable.fon);
		return bitmap;
	}
	public Bitmap DrawTetrisGameField(Resources res, Bitmap bitmap){
		bitmap = BitmapFactory.decodeResource(res, R.drawable.gamefield_fon);	
		return bitmap;	
	}
	public Bitmap DrawTetrisNextBox(Resources res, Bitmap bitmap){
		bitmap = BitmapFactory.decodeResource(res, R.drawable.nextbox);
		return bitmap;
	}
	public Bitmap DrawTetrisScoreBox(Resources res, Bitmap bitmap){
		bitmap = BitmapFactory.decodeResource(res, R.drawable.scorebox);
		return bitmap;
	}
	public Bitmap[] DrawBlocks(int theme,Resources res, Bitmap bitmap[]){
		String numb;
		int id;
		for(int i = 1; i<=7; i++){
			numb = "bl" + Integer.toString(i);
			id = res.getIdentifier(numb, "drawable", "com.example.game");
			bitmap[i] = BitmapFactory.decodeResource(res, id);
		}
		return bitmap;
	}
	public Bitmap[] DrawNextBlocks(int theme,Resources res, Bitmap bitmap[]){
		String numb;
		int id;
		for(int i = 1; i<=7; i++){
			numb = "prevbl" + Integer.toString(i);
			id = res.getIdentifier(numb, "drawable", "com.example.game");
			bitmap[i] = BitmapFactory.decodeResource(res, id);
		}
		return bitmap;
	}
	public Bitmap DrawButton(Resources res, Bitmap bitmap){
		return bitmap;
	}
	
	
}
