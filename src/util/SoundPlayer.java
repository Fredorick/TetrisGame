package util;

import com.example.game.R;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

public class SoundPlayer  {
private static SoundPool soundPool;
AudioAttributes attributes;
private boolean loaded;
private Context context = null;
	public SoundPlayer(Context context) {
		this.context = context;
		
	}
	public void play(){
		
	}
	 @TargetApi(Build.VERSION_CODES.LOLLIPOP)
	private void initializeRecentAPISoundPool() {
		attributes =  new AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_GAME)
        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
        .build();
		soundPool = new SoundPool.Builder()
		.setAudioAttributes(attributes)
		.setMaxStreams(7) 
		.build();
	}
	 @SuppressWarnings("deprecation")
	    private void initializeDeprecatedAPISoundPool() {
	        SoundPlayer.soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,0);
	    }
	 
	 public void setInitialSoundPool() {
	        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
	            initializeRecentAPISoundPool();
	        }
	        else {
	            initializeDeprecatedAPISoundPool();
	        }

	        SoundPlayer.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {

	            @Override
	            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
	                loaded = true;
	                soundPool.load(context, R.raw.boxfall, 0);                
	            }
	        });
	 }
}
