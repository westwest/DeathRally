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

import javax.microedition.khronos.opengles.GL10;
import dat255.HT2012.deathrally.Game.Graphics.Mesh;
import dat255.HT2012.deathrally.Game.Graphics.Rectangle;

/**
 * Represent the view of vehicles. Basically provides an interface between
 * the (somewhat primitive) openGL shape-rendering and 
 * @author Johannes Vestlund
 *
 */
public class VisualVehicle extends VisualEntity {	
	private float px;
	private float py;
	private float direction = 180.0f;
	private Mesh representation;
	
	public VisualVehicle(float px, float py){
		this.px = px;
		this.py = py;
		this.representation = new Rectangle(px,py,0.2f,0.4f);
		float[] vertices = representation.getVertices();
		float[] textureMatrix = {
				vertices[3], vertices[4],  //v2
				vertices[0], vertices[1],  //v1
				vertices[9], vertices[10], //v4
				vertices[6],vertices[7]    //v3	
		};
		setTextureMatrix(textureMatrix);
	}
	
	@Override
	public void display(GL10 gl) {
		representation.refresh(px,py,direction);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, getTexturePointer()[0]);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, getTextureBuffer());
		representation.draw(gl);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
	}
}
