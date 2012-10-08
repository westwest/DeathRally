package dat255.HT2012.deathrally.Game;


import android.app.Activity;
import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import dat255.HT2012.deathrally.Game.GameModel.*;
import dat255.HT2012.deathrally.Game.Visual.*;
import dat255.HT2012.deathrally.Game.Constants.LevelName;

public class DeathRallyGame extends Activity {
	MainGamePanel view;
	GameLoop gameLoop;
	
	private static final String TAG = DeathRallyGame.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {				
		super.onCreate(savedInstanceState);		
		
		Log.d(TAG, "game activity created");
		view = new MainGamePanel(this);
		GameModel model = new GameModel(LevelName.LEVEL_1);
		gameLoop = new GameLoop(view.getHolder(), view, model);
		
		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		/* Checking OpenGL support */
		/*
		final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
		final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;
		
		//OpenGL ES 2.0 works on some devices, but seems a bit unstable on emulator. 
		// For now ES 1.1 will be main attempt.
		if(supportsEs2){
			//glSurface.setEGLContextClientVersion(2);
			System.out.println("ES2 supported");
		}else{
			System.out.println("ES2 not supported");
		}
		*/
		setContentView(view);
	}
	
	protected void onPause() {
		super.onPause();
		view.onPause();
	}
	
	protected void onResume() {
		super.onResume();
		view.onResume();
	}


}
