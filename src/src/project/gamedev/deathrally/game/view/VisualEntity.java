/*
 * Copyright (c) 2012, Johannes Vestlund <Johannes@westlundarna.se>
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

package project.gamedev.deathrally.game.view;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import project.gamedev.deathrally.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;
import android.util.Log;

/**
 * Represents any visual object. May hold texture. Determines rendering 
 * shape.
 * @author Johannes Vestlund
 *
 */
public abstract class VisualEntity {
	private final int byteLength = 4;
	private int texturePointer[] = new int[1];
	private FloatBuffer textureBuffer;
	private float texture[];
	private boolean hasTexture = false;

	/**
	 * Method display is a higher level draw command, motivated by the
	 * need to make gluing of textures optional. It should at least call
	 * a Mesh-type draw-method, but can be extended to include rendering
	 * of textures.
	 * @param gl
	 */
	public abstract void display(GL10 gl);	
	
	/**
	 * Standard texture-loader, to be used by subclasses whenever needed.
	 * @param gl
	 * @param context
	 */
	public void loadGLTexture(GL10 gl, Context context){
		if(hasTexture){
			//Should be replaced with more generic code. R.drawable.test_car I mean
			Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.test_car);
			//Generate texture pointer and bind
			gl.glGenTextures(1, texturePointer,0);
			gl.glBindTexture(GL10.GL_TEXTURE_2D, texturePointer[0]);
			
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
			gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);
			
			GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
		}
	}
	
	public void setTextureMatrix(float[] t){
		texture = t.clone();
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(texture.length * byteLength);
		byteBuffer.order(ByteOrder.nativeOrder());
		textureBuffer = byteBuffer.asFloatBuffer();
		textureBuffer.put(texture);
		textureBuffer.position(0);
		hasTexture = true;
	}
	
	protected int[] getTexturePointer(){
		return texturePointer;
	}
	
	protected FloatBuffer getTextureBuffer(){
		return textureBuffer;
	}
	
}
