package util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class TetrisDrawer extends Drawer {

	String numb;
	int id;	
	public TetrisDrawer() {
		numb = "";
		id = 0 ;
	}
	
	public Bitmap DrawTetrisFon(Resources res, Bitmap bitmap) {
		numb = "fon" +GetTheme();
		id = res.getIdentifier(numb, "drawable", "com.example.game");
		bitmap = BitmapFactory.decodeResource(res, id);
		return bitmap;
	}
	public Bitmap DrawTetrisGameField(Resources res, Bitmap bitmap){
		numb = "gamefield_fon" +GetTheme();
		id = res.getIdentifier(numb, "drawable", "com.example.game");
		bitmap = BitmapFactory.decodeResource(res, id);
		return bitmap;	
	}
	public Bitmap DrawTetrisNextBox(Resources res, Bitmap bitmap){
		numb = "nextbox" +GetTheme();
		id = res.getIdentifier(numb, "drawable", "com.example.game");
		bitmap = BitmapFactory.decodeResource(res, id);
		return bitmap;
	}
	public Bitmap DrawTetrisScoreBox(Resources res, Bitmap bitmap){
		numb = "scorebox" +GetTheme();
		id = res.getIdentifier(numb, "drawable", "com.example.game");
		bitmap = BitmapFactory.decodeResource(res, id);
		return bitmap;
	}
	public Bitmap[] DrawBlocks(Resources res, Bitmap bitmap[]){
		String numb;
		int id;
		for(int i = 1; i<=7; i++){
			numb = "bl" + Integer.toString(i)+GetTheme();
			id = res.getIdentifier(numb, "drawable", "com.example.game");
			bitmap[i] = BitmapFactory.decodeResource(res, id);
		}
		return bitmap;
	}
	public Bitmap[] DrawNextBlocks(Resources res, Bitmap bitmap[]){
		for(int i = 1; i<=7; i++){
			numb = "prevbl" + Integer.toString(i)+GetTheme();
			id = res.getIdentifier(numb, "drawable", "com.example.game");
			bitmap[i] = BitmapFactory.decodeResource(res, id);
		}
		return bitmap;
	}
	public Bitmap DrawButton(Resources res, Bitmap bitmap){
		numb = "button" + Integer.toString(i)+GetTheme();
		id = res.getIdentifier(numb, "drawable", "com.example.game");
		bitmap = BitmapFactory.decodeResource(res, id);
		return bitmap;
	}
	
	
}
