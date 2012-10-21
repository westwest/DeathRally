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

package project.gamedev.deathrally.game.graphics;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;


/**
 * Developed from square-class in public domain. There are similarities, but code has been significantly
 * modified and the class has been adapted to support motion.
 * Source: http://www.jayway.com/2009/12/04/opengl-es-tutorial-for-android-part-ii-building-a-polygon/
 * 
 * @author Johannes Vestlund
 *
 */
public class Triangle extends Mesh {
	private static final String TAG = Triangle.class.getSimpleName();
	private short indices[] = {0,1,2};
	
	public Triangle(float width, float height, float px, float py){
		super(px,py, GL10.GL_TRIANGLES);
		float[] vertices = {
				-width/2, -height/2, 0.0f,
				0.0f, height/2, 0.0f,
				width/2, -height/2, 0.0f 
		};
		setVertices(vertices);
		setIndices(indices);
		Log.d(TAG,"Triangle created successfully");
	}
}
