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

package dat255.autumn2012.deathrally.game.view;

import javax.microedition.khronos.opengles.GL10;

import dat255.autumn2012.deathrally.game.graphics.Circle;

public class JoystickView extends VisualEntity{
	
	public JoystickView(float px, float py, float radius){
		super(new Circle(px,py,radius));
	}

	@Override
	public void display(GL10 gl) {
		representation.draw(gl);	
	}
	
	public void destroy(){
		GameRenderer.disconnect(this);
	}
	
	public String toString(){
		return "JoystickView";
	}
}
