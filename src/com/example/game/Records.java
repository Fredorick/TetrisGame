package com.example.game;

import java.util.ArrayList;

import util.Drawer;
import util.IDer;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Records extends Activity{
	DBManager dbManager;
	IDer ider;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tetris t = new Tetris(this);
		Resources res = getResources();
		Drawer drawer = new Drawer();
		ider = new IDer(res);
		setContentView(R.layout.records);
		TextView restv = (TextView)this.findViewById(R.id.myrecords);
		Button menuButton = (Button)findViewById(R.id.recordsmenubutton);
		menuButton.setWidth(t.ReturnButtonCoods("w"));
		menuButton.setHeight(t.ReturnButtonCoods("h"));
		menuButton.setBackgroundResource(ider.GetId("button"));
		dbManager = DBManager.getInstance(this);
		restv.setBackgroundColor(Color.argb(100, 255, 255, 255));
		restv.setTextColor(Color.rgb(0, 0, 0));
		ArrayList<Result> results = dbManager.getAllResults();
		String resStr = "";
		for (Result result : results)
		{
			resStr += result.name + ": " + result.score + "\n";
		}	
		restv.setText(resStr);
	}
	public void RecordsMenu(View v){
		Intent intent = new Intent(this, Menu.class);
		startActivity(intent);
	}

}
