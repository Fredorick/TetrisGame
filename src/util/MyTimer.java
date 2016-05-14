package util;

import android.os.CountDownTimer;

public class MyTimer extends CountDownTimer{
	public boolean finish;
    public MyTimer(long millisInFuture, long countDownInterval){
          super(millisInFuture, countDownInterval);
    }
    
    @Override
    public void onFinish() 
    {
    	finish = true;
    }
    public void Break(){
    	finish = false;
    }
    public void onTick(long millisUntilFinished) 
    {
    }
}