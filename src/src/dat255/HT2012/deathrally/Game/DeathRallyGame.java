package dat255.HT2012.deathrally.Game;

import dat255.HT2012.deathrally.Game.Visual.GameRenderer;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;

public class DeathRallyGame extends Activity {
	MainGamePanel view;
	
	protected void onPause(){
		super.onPause();
		view.onPause();
	}
	protected void onResume(){
		super.onResume();
		view.onResume();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		view = new MainGamePanel(this);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
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

}
