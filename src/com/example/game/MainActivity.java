package com.example.game;
import util.Drawer;
import util.IDer;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	IDer ider;
	Tetris t;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		t = new Tetris(this);
		Resources res = getResources();
		Drawer drawer = new Drawer();
		ider = new IDer(res);
		setContentView(R.layout.activity_main);
		Button menuButton = (Button)findViewById(R.id.tetrismenubutton);
		menuButton.setY(t.ReturnButtonCoods("y"));
		menuButton.setX(t.ReturnButtonCoods("x"));
		menuButton.setWidth(t.ReturnButtonCoods("w"));
		menuButton.setHeight(t.ReturnButtonCoods("h"));
		menuButton.setBackgroundResource(ider.GetId("button"));
	}
	public void TetrisMenu(View v){
		Intent intent = new Intent(this, Menu.class);
		startActivity(intent);
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}
}
