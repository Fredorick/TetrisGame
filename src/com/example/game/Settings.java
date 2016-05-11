package com.example.game;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Settings extends Activity {
	int theme;
	SharedPreferences sp;
	String listV; 
	public Settings() {
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		listV= sp.getString("list", "0");
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	public int GetTheme(){		
		return theme;
	}

}
