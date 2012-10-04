package dat255.HT2012.deathrally.Game;

import dat255.HT2012.deathrally.Game.Visual.GameRenderer;
import dat255.HT2012.deathrally.Game.Visual.LessonOneRenderer;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

//Basic design taken from "http://obviam.net/index.php/a-very-basic-the-game-loop-for-android/"
public class MainGamePanel extends GLSurfaceView  { //implements SurfaceHolder.Callback {
	//private GameLoop loopThread;
	private LessonOneRenderer gameRenderer;
	private static final String TAG = MainGamePanel.class.getSimpleName();
	float mPreviousX;
	float mPreviousY;
	
	public MainGamePanel(Context context) {		
		super(context);
		
		Log.d(TAG, "setting renderer");
		
		setEGLContextClientVersion(2);
		gameRenderer = new LessonOneRenderer();		
		setRenderer(gameRenderer);
		
		Log.d(TAG, "renderer set");
		// Render the view only when there is a change in the drawing data
		//setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
		
		/*
		getHolder().addCallback(this);		
		setFocusable(true);
		setFocusableInTouchMode(true);
		*/
		
		// Instantiate renderer and game loop
		//loopThread = new GameLoop(getHolder(), this);


	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {
	    // MotionEvent reports input details from the touch screen
	    // and other input controls. In this case, you are only
	    // interested in events where the touch position changed.

	    float x = e.getX();
	    float y = e.getY();

	    switch (e.getAction()) {
	        case MotionEvent.ACTION_MOVE:

	            float dx = x - mPreviousX;
	            float dy = y - mPreviousY;

	            // reverse direction of rotation above the mid-line
	            if (y > getHeight() / 2) {
	              dx = dx * -1 ;
	            }

	            // reverse direction of rotation to left of the mid-line
	            if (x < getWidth() / 2) {
	              dy = dy * -1 ;
	            }

	            gameRenderer.mAngle += (dx + dy) * 180.0f / 320;//TOUCH_SCALE_FACTOR;  // = 
	            requestRender();
	    }

	    mPreviousX = x;
	    mPreviousY = y;
	    return true;
	}
	
	/*
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
			
			if (gameRenderer != null)
            {
                // Ensure we call switchMode() on the OpenGL thread.
                // queueEvent() is a method of GLSurfaceView that will do this for us.
                queueEvent(new Runnable()
                {
                    @Override
                    public void run()
                    {
                    	gameRenderer.foo();
                    }
                });

                return true;
            }
			
			Log.d(TAG, "Coords: x=" + e.getX() + ",y=" + e.getY());
		}

		return super.onTouchEvent(e);

	}
	
	public void checkInit(){
		System.out.println("MainGamePanel-instance initiated");
	}

	*/
}

