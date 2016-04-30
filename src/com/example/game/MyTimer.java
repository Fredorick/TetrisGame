package com.example.game;

import android.os.CountDownTimer;

public class MyTimer extends CountDownTimer{
	boolean finish;
    public MyTimer(long millisInFuture, long countDownInterval){
          super(millisInFuture, countDownInterval);
    }
    
    @Override
    public void onFinish() 
    {
    	finish = true;
    }
    void Break(){
    	finish = false;
    }
    public void onTick(long millisUntilFinished) 
    {
    }
}