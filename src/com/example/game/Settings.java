package com.example.game;

import util.Drawer;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.ToggleButton;

public class Settings extends Activity {
	String theme;
	ToggleButton tg;
	boolean checked = false;
	public Settings() {
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		tg = (ToggleButton) findViewById(R.id.toggleButton1);
		
	}
	@Override
	protected void onResume() {
		if(checked){
			tg.setChecked(checked);
		}
		else{
			tg.setChecked(!checked);
		}
		super.onResume();
	}
	@Override
	protected void onPause() {
		if(tg.isChecked()){
			Drawer.i = 1;
			checked = true;
		}
		else{
			Drawer.i = 0;
			checked = false;
		}
		super.onPause();
	}
	@Override
	protected void onStop() {
		if(tg.isChecked()){
			Drawer.i = 1;
			checked = true;
		}
		else{
			Drawer.i = 0;
			checked = false;
		}
		super.onStop();
	}
	public int GetTheme(){		
		int  i= -1;
		if(theme != null){
		switch(theme){
		case "Toxic": i =1;
		case "Classic": i =0;
		default: i =0;
		}
		}
		else{
		i=0;
		}
		return i;
	}

}
