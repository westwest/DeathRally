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

package project.gamedev.deathrally.game.controls;


import project.gamedev.deathrally.game.MainGamePanel;
import project.gamedev.deathrally.game.gamemodel.GameAction;
import project.gamedev.deathrally.game.gamemodel.Player;
import project.gamedev.deathrally.game.view.GameRenderer;
import project.gamedev.deathrally.game.view.JoystickView;
import android.opengl.GLU;

public class Joystick {
	private MainGamePanel controller;
	private float centerX;
	private float centerY;
	private int radius = 100;
	private JoystickView vJoystick;
	private Player owner;
	
	/**
	 * The constructor
	 * @param owner is the player that are using the joystick, typically the user. 
	 * @param context is the game-panel responsible for acting controller.
	 */
	public Joystick(Player owner, MainGamePanel context){
		this.owner = owner;
		this.controller = context;
	}
	
	public void createUI(float px, float py){
		this.centerX = px;
		this.centerY = py;
		float[] coordsOrigo = new float[16];
		int[] viewport = GameRenderer.getViewport();
		float posY = viewport[3] - py;
		
		GLU.gluUnProject(px, posY, 0, GameRenderer.getModelViewMatrix(), 0,
				GameRenderer.getProjectionMatrix(), 0, viewport, 0, coordsOrigo, 0);
		float[] coordsEdge = new float[16];
		GLU.gluUnProject(px+radius, posY, 0, GameRenderer.getModelViewMatrix(), 0, 
				GameRenderer.getProjectionMatrix(), 0, GameRenderer.getViewport(), 0, coordsEdge, 0);
		
		float glRadius = coordsOrigo[0] - coordsEdge[0];
		vJoystick = new JoystickView(coordsOrigo[0], coordsOrigo[1], glRadius);
		controller.addVisualObj(vJoystick);
	}
	
	/**
	 * When touch-motion is aborted, the user stops touching the screen, the visual view
	 * of the joystick should disappear and the actions should be reset.
	 */
	public void reset(){
		controller.recieveAction(owner, GameAction.ACCELERATE, 0);
		controller.recieveAction(owner, GameAction.TURN, 0);
		if(vJoystick != null){
			vJoystick.destroy();
		}
		vJoystick = null;
	}
	
	/**
	 * When action is dispatched to joystick this method catches it and sends meaningful
	 * GameActions back.
	 * 
	 * @param px x-position of touched pixel.
	 * @param py y-position of touched pixel.
	 */
	public void catchAction(float px, float py){
		
		if(inCircle(px,py)){
			float dx = px-centerX;
			float dy = py-centerY;
			
			controller.recieveAction(owner, GameAction.ACCELERATE, dy/radius);
			controller.recieveAction(owner, GameAction.TURN, dx/radius);
		}
	}

	/**
	 * Calculates if a point is inside joystick area.
	 * May benefit from being generalized.
	 * 
	 * @param px
	 * @param py
	 * @return true if point is inside joystick area.
	 */
	private boolean inCircle(float px, float py) {
		double sq_dist = Math.pow(centerX - px, 2) + 
				Math.pow(centerY - py, 2); 
		return sq_dist <= Math.pow(radius, 2);
	}
}
