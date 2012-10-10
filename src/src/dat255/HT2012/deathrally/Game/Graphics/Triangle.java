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

package dat255.HT2012.deathrally.Game.Graphics;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.Observable;
import java.util.Observer;

import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.opengles.GL10;

import dat255.HT2012.deathrally.Game.GameModel.GameAction;
import dat255.HT2012.deathrally.Game.GameModel.Vehicle;

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
	//Matrix-stuff and related
	private float vertices[] = new float[9];
	private short indices[] = {
			0,1,2
	};
	
	public Triangle(float width, float height, float px, float py){
		setDrawMethod(GL10.GL_TRIANGLES);
		
		//Not found a better way of doing it yet...
		//Vertex 1 [bottom left]
		vertices[0] = -width/2;
		vertices[1] = -height/2;
		vertices[2] = 0.0f;
		//Vertex 2 [top center]
		vertices[3] = 0.0f;
		vertices[4] = height/2;
		vertices[5] = 0.0f;
		//Vertex 3 [bottom right]
		vertices[6] = width/2;
		vertices[7] = -height/2;
		vertices[8] = 0.0f;
		setVertices(vertices);
		setIndices(indices);
	}
}
