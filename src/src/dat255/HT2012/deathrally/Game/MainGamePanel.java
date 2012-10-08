package dat255.HT2012.deathrally.Game;

import java.util.ArrayList;

import dat255.HT2012.deathrally.Game.GameModel.Entity;
import dat255.HT2012.deathrally.Game.GameModel.Vehicle;
import dat255.HT2012.deathrally.Game.Visual.CircleView;
import dat255.HT2012.deathrally.Game.Visual.GameRenderer;
import dat255.HT2012.deathrally.Game.Visual.JoystickView;
import dat255.HT2012.deathrally.Game.Visual.LessonOneRenderer;
import dat255.HT2012.deathrally.Game.Visual.TriangleView;
import dat255.HT2012.deathrally.Game.Visual.VisualEntity;
import dat255.HT2012.deathrally.Game.Visual.VisualVehicle;
import dat255.HT2012.deathrally.Game.Visual.Controls.Joystick;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.WindowManager;

//Basic design taken from "http://obviam.net/index.php/a-very-basic-the-game-loop-for-android/"
public class MainGamePanel extends GLSurfaceView  {
	
	//private LessonOneRenderer gameRenderer;
	private GameRenderer gameRenderer;
	
	private static final String TAG = MainGamePanel.class.getSimpleName();
	float mPreviousX;
	float mPreviousY;
	
	Joystick joystick;
	
	public MainGamePanel(Context context) {		
		super(context);
		
		//Dont target OpenGL 2.0 just yet
		//setEGLContextClientVersion(2);
		gameRenderer = GameRenderer.getInstance();		
		setRenderer(gameRenderer);
		
		getHolder().addCallback(this);		
		setFocusable(true);
		setFocusableInTouchMode(true);
		
		//create Entities
		ArrayList<VisualEntity> visualEntities = new ArrayList<VisualEntity>();
		VisualVehicle vCar = new VisualVehicle(0.0f, 00f);
		visualEntities.add(vCar);
		
		Vehicle car = new Vehicle();
		car.addObserver(vCar);
		gameRenderer.addDrawObj(visualEntities);
		
		joystick = new Joystick(car, this);
	}
	
	public void addVisualObj(VisualEntity ve){
		gameRenderer.addDrawObj(ve);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {
	    // MotionEvent reports input details from the touch screen
	    // and other input controls. In this case, you are only
	    // interested in events where the touch position changed.
		
	    float px = e.getX();
	    float py = e.getY();
	    
	    
	    switch (e.getAction()) {	    	
	    	case MotionEvent.ACTION_DOWN:
	    		if(px > 100 && px < 300){
	    			joystick.createUI(px,py);
	    		}
    			break;
	        case MotionEvent.ACTION_MOVE:
	        	joystick.catchAction(px, py);
	        	break;
	        case MotionEvent.ACTION_UP:
	    		joystick.reset();
	    		break;
	    }
	    return true;
	}
}

