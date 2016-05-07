package util;

import android.R.menu;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.game.Menu;
import com.example.game.R;

public class MyButton implements OnClickListener {
	Bitmap bitmap;
	Drawable drawable;
	Paint paint;
	Rect rect;
	Align align = Align.CENTER;
	String text;
	public MyButton(Resources res, String text) {
		bitmap = BitmapFactory.decodeResource(res, R.drawable.fon2);
		paint = new Paint();
		this.text = text;
	}
	public void draw(Canvas canvas, Rect rect){
	canvas.drawBitmap(bitmap, null, rect, paint);
	}
	@Override
	public void onClick(View v) {
	Intent intent = new Intent(v.getContext(), Menu.class);
	}
}
