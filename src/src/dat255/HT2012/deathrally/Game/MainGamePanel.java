package dat255.HT2012.deathrally.Game;

import java.util.ArrayList;

import dat255.HT2012.deathrally.Game.GameModel.Entity;
import dat255.HT2012.deathrally.Game.GameModel.Vehicle;
import dat255.HT2012.deathrally.Game.Visual.GameRenderer;
import dat255.HT2012.deathrally.Game.Visual.LessonOneRenderer;
import dat255.HT2012.deathrally.Game.Visual.TriangleView;
import dat255.HT2012.deathrally.Game.Visual.VisualEntity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

//Basic design taken from "http://obviam.net/index.php/a-very-basic-the-game-loop-for-android/"
public class MainGamePanel extends GLSurfaceView  {
	
	//private LessonOneRenderer gameRenderer;
	private GameRenderer gameRenderer;
	
	private static final String TAG = MainGamePanel.class.getSimpleName();
	float mPreviousX;
	float mPreviousY;
	
	Vehicle car;
	
	public MainGamePanel(Context context) {		
		super(context);
		
		//create Entities
		ArrayList<VisualEntity> visualEntities = new ArrayList();
		ArrayList<Entity> entities = new ArrayList();
		
		TriangleView Vcar = new TriangleView();
		visualEntities.add(Vcar);
		
		car = new Vehicle();
		car.addObserver(Vcar);
		entities.add(car);
		
		
		//Dont target OpenGL ES 2.0 just yet.
		//setEGLContextClientVersion(2);
		gameRenderer = new GameRenderer(visualEntities);		
		setRenderer(gameRenderer);
		
		getHolder().addCallback(this);		
		setFocusable(true);
		setFocusableInTouchMode(true);
		
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

	        	/*
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
	            //gameRenderer.mAngle += (dx + dy) * 180.0f / 320;//TOUCH_SCALE_FACTOR;  // = 
	             * 
	             */
	        	car.turn(20);
	    }

	    //mPreviousX = x;
	    //mPreviousY = y;
	    return true;
	}
}

