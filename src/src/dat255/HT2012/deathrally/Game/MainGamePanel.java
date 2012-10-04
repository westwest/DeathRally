package dat255.HT2012.deathrally.Game;

import dat255.HT2012.deathrally.Game.Visual.GameRenderer;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

//Basic design taken from "http://obviam.net/index.php/a-very-basic-the-game-loop-for-android/"
public class MainGamePanel extends GLSurfaceView implements SurfaceHolder.Callback {
	private GameLoop loopThread;
	private static final String TAG = MainGamePanel.class.getSimpleName();
	
	public MainGamePanel(Context context) {
		super(context);
		getHolder().addCallback(this);
		
		setFocusable(true);
		setFocusableInTouchMode(true);
		
		// Instantiate renderer and game loop
		loopThread = new GameLoop(getHolder(), this);
		setRenderer(new GameRenderer());
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		loopThread.setRunning(true);
		loopThread.start();
	}
	
		
	// block the thread and wait for it to die.
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		while (retry) {
			try {
				loopThread.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			}
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		float x = e.getX();
		float y = e.getY();
		// Writes coordinates of touch to logCat
		if (e.getAction() == MotionEvent.ACTION_DOWN) {
			Log.d(TAG, "Coords: x=" + e.getX() + ",y=" + e.getY());
		}

		return super.onTouchEvent(e);

	}
	
	public void checkInit(){
		System.out.println("MainGamePanel-instance initiated");
	}

}
