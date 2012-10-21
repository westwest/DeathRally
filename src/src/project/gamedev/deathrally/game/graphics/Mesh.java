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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * The Mesh class serves as base-class for all shapes. Drawing primitives like circles and 
 * triangles are derived from this class. It provides a reusable 2D drawing pattern that 
 * allows rotation and translation, and setters for vertices and indices.
 * @author Johannes Westlund
 *
 */
public class Mesh {
	private FloatBuffer vertexBuffer;
	private ShortBuffer indexBuffer;
	
	private int nrIndices;
	private int drawMethod;
	
	//Translation and rotation params
	private float x,y;
	private float z=0;
	private float rz;
	
	public Mesh(){
	}
	public Mesh(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Basic draw method. Does common settings and gives room for rotation and translation. The
	 * method can be chosen, and should be set on init through constructor.
	 * @param gl represent graphical context and is supplied by renderer.
	 */
	public void draw(GL10 gl){
		gl.glFrontFace(GL10.GL_CCW);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		
		gl.glPushMatrix();
		gl.glTranslatef(x, y, z);
		gl.glRotatef(rz+180, 0, 0, 1);
		gl.glDrawElements(drawMethod, nrIndices, GL10.GL_UNSIGNED_SHORT, indexBuffer);
		gl.glPopMatrix();	
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);
	}
	
	protected void setIndices(short[] indices){
		ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
		ibb.order(ByteOrder.nativeOrder());
		indexBuffer = ibb.asShortBuffer();
		indexBuffer.put(indices);
		indexBuffer.position(0);
		nrIndices = indices.length;
	}
	
	protected void setVertices(float[] vertices){
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		vertexBuffer = vbb.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
	}
	
	protected void setDrawMethod(int drawMethod){
		this.drawMethod = drawMethod;
	}
	
	public void rotate(float angle){
		rz += angle;
	}
}
