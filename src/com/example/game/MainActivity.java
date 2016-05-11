package com.example.game;
import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Tetris tetris = new Tetris(this);
		setContentView(tetris);
	}
}
