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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class CircleView extends Mesh {
		private int nrPoints = 30;
		private float[] vertices = new float[nrPoints *3];
		private short[] indices = new short[nrPoints];
		
	
	public CircleView(float px, float py, float radius){
		super(px,py);
		
		double relAngle = Math.pow(nrPoints, -1)*2*Math.PI;
		for(int i = 0; i< nrPoints; i++){
			//x, y and z coords for each point;
			vertices[i*3] = (float) (Math.cos(relAngle*i)*radius);
			vertices[i*3+1] = (float) (Math.sin(relAngle*i)*radius);
			vertices[i*3+2] = 0.0f;
			
			indices[i] = (short) i;
		}
		
		setVertices(vertices);
		setIndices(indices);
		setDrawMethod(GL10.GL_LINE_LOOP);
	}
}
