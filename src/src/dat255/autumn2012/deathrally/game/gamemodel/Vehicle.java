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

package dat255.autumn2012.deathrally.game.gamemodel;

import java.util.LinkedList;

public class Vehicle extends MovableEntity {
	private float velocity = 0;
	private float maxVelocity = 10;
	private float acceleration;
	private float angle= 0;
	private LinkedList<Weapon> Weapons = new LinkedList<Weapon>();
	
	
	public Vehicle(float posX, float posY) {
		super(posX,posY);
		
	};
	
	/**
	 * Method changes vehicles speed using a primitive formula v=v0+at, where
	 * time is determined by fps and breaking is considered to be negative a.
	 * @param intensity analogous to how hard one press down the throttle, 
	 *                  or break.
	 */
	public void accelerate(float intensity){
		//v = v0 + at, time?, a adjusted by intensity linear
		//time is set by speed of thread, if connected to render => fps, else something else
		//Algorithm does not support simulation of good/bad breaks. Retardation = -Acceleration
		velocity = velocity + intensity*acceleration; 
		if(velocity > maxVelocity){
			velocity = maxVelocity;
		}
	};
	
	public void turn(float angle){
		if(velocity != 0.0f){
			this.angle = angle;
		}
	}
	
	public float getVelocity(){
		return velocity;
	}
	
	public float getAngle(){
		return angle;
	}
	
}
