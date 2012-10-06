package dat255.HT2012.deathrally.Game.Visual;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES10;
import android.opengl.GLES11;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
//import android.opengl.GLU;
//import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

/**
 * For now mostly based on a tutorial, as I understood it the code is public domain.
 * http://www.jayway.com/2009/12/03/opengl-es-tutorial-for-android-part-i/
 * 
 * @author Johannes Vestlund
 *
 */

public class GameRenderer implements Renderer {
	private ArrayList<VisualEntity> drawObjs = new ArrayList<VisualEntity>();
	
	private float[] modelMatrix = new float[16];
	private float[] projectionMatrix = new float[16];
	private int[] viewportMatrix = new int[4];
	
	public void addDrawObj(VisualEntity ve){
		drawObjs.add(ve);
	}
	
	public void addDrawObj(ArrayList<VisualEntity> drawObjs){
		for(VisualEntity drawObj : drawObjs){
			this.drawObjs.add(drawObj);
		}
	}
	
	//TriangleView t = T

	//A bit of confusion of which OpenGL-version to use. For now 1.x.
	public void onDrawFrame(GL10 gl) {
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		GLU.gluLookAt(gl, 0, 0, -5, 0f, 0f, 0f, 1.0f, 1.0f, 0.0f);
		
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glTranslatef(0, 0, -1);
		
		
		for(VisualEntity drawObj : drawObjs){
			drawObj.draw(gl);
		}
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		float aspectRatio = (float) width/ (float)height;
		gl.glViewport(0, 0, width, height);
		setViewportMatrix();
		gl.glMatrixMode(GL10.GL_PROJECTION);
		setProjectionMatrix();
		
		gl.glLoadIdentity();
		gl.glOrthof(-aspectRatio, aspectRatio, 1, -1, -1, 1);
		//GLU.gluPerspective(gl, 45.0f, aspectRatio, 1f, -1f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		setModelMatrix();
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
	}
	
	public int[] getViewportMatrix(){
		return viewportMatrix;
	}
	
	private void setViewportMatrix(){
		IntBuffer viewport = IntBuffer.allocate(4);
		GLES11.glGetIntegerv(GLES11.GL_VIEWPORT, viewport);
		
		int[] intView = new int[4];
		for(int i = 0; i<4; i++){
			intView[i] = viewport.get(i);
		}
		viewportMatrix = intView;
	}
	
	public float[] getProjectionMatrix(){
		return projectionMatrix;
	}
	
	private void setProjectionMatrix(){
		FloatBuffer projectionview = FloatBuffer.allocate(16);
		GLES11.glGetFloatv(GLES11.GL_PROJECTION_MATRIX, projectionview);
		
		float[] floatProjection = new float[16];
		for(int i = 0; i<16; i++){
			floatProjection[i] = projectionview.get(i);
		}
		projectionMatrix = floatProjection;	
	}
	
	public float[] getModelMatrix(){
		return modelMatrix;
	}
	
	private void setModelMatrix(){
		FloatBuffer modelview = FloatBuffer.allocate(16); 
		GLES11.glGetFloatv(GLES11.GL_MODELVIEW_MATRIX, modelview);
		float[] floatModel = new float[16];
		for(int i = 0; i<16; i++){
			floatModel[i] = modelview.get(i);
		}
		modelMatrix = floatModel;
	}
	
}

