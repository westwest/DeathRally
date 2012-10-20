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

package dat255.autumn2012.deathrally.game.graphics;

import javax.microedition.khronos.opengles.GL10;


public class Circle extends Mesh {
		private int nrPoints = 30;
		private float[] vertices = new float[nrPoints *3];
		private short[] indices = new short[nrPoints];
		
	/**
	 * Creates a new circle centered around px,py and with radius radius 
	 * expressed in glCoordinates. It uses GL_LINE_LOOP over "enough" (in
	 * this context nrPoints=30) vertices to create a "crisp" circle.
	 * @param px
	 * @param py
	 * @param radius
	 */
	public Circle(float px, float py, float radius){
		super(px,py, GL10.GL_LINE_LOOP);
		
		double relAngle = Math.pow(nrPoints, -1)*2*Math.PI;
		for(int i = 0; i< nrPoints; i++){
			vertices[i*3] = (float) (Math.cos(relAngle*i)*radius);
			vertices[i*3+1] = (float) (Math.sin(relAngle*i)*radius);
			vertices[i*3+2] = 0.0f;
			
			indices[i] = (short) i;
		}
		
		setVertices(vertices);
		setIndices(indices);
	}
}
