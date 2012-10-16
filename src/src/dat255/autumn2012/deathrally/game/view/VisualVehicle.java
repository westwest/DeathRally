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

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import dat255.autumn2012.deathrally.R;
import dat255.autumn2012.deathrally.game.graphics.Mesh;
import dat255.autumn2012.deathrally.game.graphics.Rectangle;
import dat255.autumn2012.deathrally.game.graphics.Triangle;

/**
 * Represent the view of vehicles. Basically provides an interface between
 * the (somewhat primitive) openGL shape-rendering and 
 * @author Johannes Vestlund
 *
 */
public class VisualVehicle extends VisualEntity {	
	private float px;
	private float py;
	private float direction = 0.0f;
	private static int image = R.drawable.test_car;
	
	//Texture
	private FloatBuffer textureBuffer;
	private float texture[];
	
	public VisualVehicle(float px, float py){
		super(new Rectangle(px,py,0.2f,0.4f), image, new int[1]);
		
		this.px = px;
		this.py = py;
		this.representation = new Rectangle(px,py,0.2f,0.4f);
		float[] textureMatrix = {
				1.0f, 0.0f,  //3
				1.0f, 1.0f,  //2
				0.0f, 1.0f,  //0
				0.0f, 0.0f   //1
		};
		setTextureMatrix(textureMatrix);
		setHasTexture();
	}
	
	public void update(float px, float py, float direction){
		this.px = px;
		this.py = py;
		this.direction = direction;
	}
	
	@Override
	public void display(GL10 gl) {
		representation.refresh(px,py,direction);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		gl.glBindTexture(GL10.GL_TEXTURE_2D, getTexturePointer()[0]);
		gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, getTextureBuffer());
		representation.draw(gl);
		gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
		gl.glDisable(GL10.GL_TEXTURE_2D);
	}
	
	public int getImage(){
		return image;
	}
	
	public void setTextureMatrix(float[] texture){
		this.texture = texture;
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(texture.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		textureBuffer = byteBuffer.asFloatBuffer();
		textureBuffer.put(this.texture);
		textureBuffer.position(0);
	}
	
	public int[] getTexturePointer(){
		return texturePointer;
	}
	
	public FloatBuffer getTextureBuffer(){
		return textureBuffer;
	}

}
