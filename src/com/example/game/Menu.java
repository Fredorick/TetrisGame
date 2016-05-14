package com.example.game;

import util.IDer;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Menu extends Activity{
	DisplayMetrics display;
	int width;
	int height;
	int playId;
	int recId;
	int setId;
	IDer ider;
	RelativeLayout rl;
	public Menu() {
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Resources res = getResources();
		ider = new IDer(res);
		display = res.getDisplayMetrics();
		width = display.widthPixels;
		height= display.heightPixels;
		setContentView(R.layout.menu);
		rl = (RelativeLayout) findViewById(R.id.menufon);
		rl.setBackgroundResource(ider.GetId("fon"));
		Button play = (Button)findViewById(R.id.play);
		Button set = (Button)findViewById(R.id.settings);
		Button rec = (Button)findViewById(R.id.records);
		play.setBackgroundResource(ider.GetId("button"));
		rec.setBackgroundResource(ider.GetId("button"));
		set.setBackgroundResource(ider.GetId("button"));
		play.setHeight(height/10);
		set.setHeight(height/10);
		rec.setHeight(height/10);
		play.setWidth(height/2);
		set.setWidth(height/2);
		rec.setWidth(height/2);
	}
	public void Play(View v){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	public void Records(View v){
		Intent intent = new Intent(this, Records.class);
		startActivity(intent);
	}
	public void Settings(View v){
		Intent intent = new Intent(this, Settings.class);
		startActivity(intent);
	}

}
