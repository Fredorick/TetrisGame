package util;

import com.example.game.R;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Handler;
import android.util.SparseIntArray;

public class SoundManager {
	
	private SoundPool mSoundPool;
	
	private SparseIntArray mSoundPoolMap = new SparseIntArray();
	
	private Handler mHandler = new Handler();
	private boolean mMuted = false;
	private AudioAttributes attributes;
	private static final int MAX_STREAMS = 2;
	private static final int STOP_DELAY_MILLIS = 3000;
	
	public static final int SOUND_START = R.raw.start;
	public static final int SOUND_FON = R.raw.fonmusic;
	public static final int SOUND_DELETE = R.raw.deleting;
	public static final int SOUND_TURN = R.raw.turn;

	public SoundManager() {
		attributes =  new AudioAttributes.Builder()
        .setUsage(AudioAttributes.USAGE_GAME)
        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
        .build();
		mSoundPool = new SoundPool.Builder()
		.setAudioAttributes(attributes)
		.setMaxStreams(7) 
		.build();
	}

	/**
	 * Put the sounds to their correspondig keys in sound pool.
	 */
	public void addSound(Context context, int soundID) {
		mSoundPoolMap.put(soundID, mSoundPool.load(context, soundID, 1));
	}

	/**
	 * Find sound with the key and play it
	 */
	public void playSound(int soundID) {
		if(mMuted){
			return;
		}
		
		boolean hasSound = mSoundPoolMap.indexOfKey(soundID) >= 0;
		if(!hasSound){
			return;
		}
		
		final int soundId = mSoundPool.play(mSoundPoolMap.get(soundID), 1, 1, 1, 0, 1f);
		scheduleSoundStop(soundId);
	}
	
	/**
	 * Schedule the current sound to stop after set milliseconds
	 */
	private void scheduleSoundStop(final int soundId){
		mHandler.postDelayed(new Runnable() {
			public void run() {
				mSoundPool.stop(soundId);
			}
		}, STOP_DELAY_MILLIS);
	}
	
	/**
	 * Initialize the control stream with the activity to music
	 */
	public static void initStreamTypeMedia(Activity activity){
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
	}

	public static int getStreamMusicLevel(Activity activity){
		AudioManager am = (AudioManager) activity.getSystemService(Context.AUDIO_SERVICE);
		return am.getStreamVolume(AudioManager.STREAM_MUSIC);
	}
	
	/**
	 * Is sound muted
	 */
	public void setMuted(boolean muted) {
		this.mMuted = muted;
	}

}
