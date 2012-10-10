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

package dat255.HT2012.deathrally.Game.Visual;

import java.util.Observable;
import java.util.Observer;

import javax.microedition.khronos.opengles.GL10;

import dat255.HT2012.deathrally.Game.GameModel.GameAction;
import dat255.HT2012.deathrally.Game.GameModel.Vehicle;
import dat255.HT2012.deathrally.Game.Graphics.Mesh;
import dat255.HT2012.deathrally.Game.Graphics.Triangle;

public class VisualVehicle extends VisualEntity implements Observer {
	private float px;
	private float py;
	private Float direction = 180.0f;
	
	private Float wheelAngle = 0.0f;
	private Float rate=1.0f;
	private Float velocity = 0.0f;
	
	private Mesh representation;
	
	public VisualVehicle(float px, float py){
		this.px = px;
		this.py = py;
		this.representation = new Triangle(0.1f, 0.2f, px, py);
	}
	
	public void move(){
		
	}
	
	
	@Override
	public void update(Observable observable, Object event) {
		if(observable instanceof Vehicle){
			Vehicle vehicle = (Vehicle) observable;
			if(event.equals(GameAction.TURN)){
				wheelAngle = vehicle.getAngle();
				representation.rotate(wheelAngle);
				//rate = vehicle.getTurningCapability();
			} else if(event.equals(GameAction.ACCELERATE)){
				velocity = vehicle.getVelocity();
			}
		}
	}

	@Override
	public void draw(GL10 gl) {
		representation.draw(gl);
	}
}
