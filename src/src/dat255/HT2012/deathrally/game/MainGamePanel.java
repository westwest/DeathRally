/*
 * Copyright (c) 2012 Johannes Vestlund <Johannes@westlundarna.se>
 * 
 * This file is part of DeathRally
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by 
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even implied warranty of
 * MERCHANTABILITY or FITTNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package dat255.HT2012.deathrally.game;

import java.util.ArrayList;

import dat255.HT2012.deathrally.game.graphics.Circle;
import dat255.HT2012.deathrally.game.graphics.Triangle;
import dat255.HT2012.deathrally.game.model.Entity;
import dat255.HT2012.deathrally.game.model.GameAction;
import dat255.HT2012.deathrally.game.model.Player;
import dat255.HT2012.deathrally.game.model.Vehicle;
import dat255.HT2012.deathrally.game.visual.GameRenderer;
import dat255.HT2012.deathrally.game.visual.JoystickView;
import dat255.HT2012.deathrally.game.visual.LessonOneRenderer;
import dat255.HT2012.deathrally.game.visual.VisualEntity;
import dat255.HT2012.deathrally.game.visual.VisualVehicle;
import dat255.HT2012.deathrally.game.visual.controls.Joystick;
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
	Player user;
	
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
		
		joystick = new Joystick(user, this);
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

	public void recieveAction(Player actor, GameAction action, float impulse) {
		// TODO Auto-generated method stub
		
	}
}
