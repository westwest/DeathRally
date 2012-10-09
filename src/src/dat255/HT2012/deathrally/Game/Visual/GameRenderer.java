package dat255.HT2012.deathrally.Game.Visual;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11;

import dat255.HT2012.deathrally.Game.GameModel.Players;

import android.opengl.GLES10;
import android.opengl.GLES11;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
//import android.opengl.GLU;
//import android.opengl.GLSurfaceView.Renderer;
import android.util.Log;

/**
 * The class GameRenderer provides the essential rendering functionality. The renderer should only 
 * appear as one instance during runtime, so Singleton pattern is used. This implies that getInstance()
 * should be used instead of constructor.
 * 
 * @author Johannes Vestlund
 *
 */

public class GameRenderer implements Renderer {
	private static volatile GameRenderer instance;
	private static ArrayList<VisualEntity> drawObjs = new ArrayList<VisualEntity>();
	
	private static float[] modelViewMatrix = new float[16];
	private static float[] projectionMatrix = new float[16];
	private static int[] viewport = new int[16];
	
	private GameRenderer(){
	}
	
	/**
	 * Can only have one renderer of this type!
	 */
	public static GameRenderer getInstance(){
		if(instance == null){
			synchronized( GameRenderer .class){
				if(instance == null){
					instance = new GameRenderer();
				}
			}
		}	
		return instance;
	}
	
	public void addDrawObj(VisualEntity ve){
		drawObjs.add(ve);
	}
	
	public void addDrawObj(ArrayList<VisualEntity> drawObjs){
		for(VisualEntity drawObj : drawObjs){
			this.drawObjs.add(drawObj);
		}
	}

	//A bit of confusion of which OpenGL-version to use. For now ES 1.1.
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
		gl.glMatrixMode(GL10.GL_PROJECTION);
		
		gl.glLoadIdentity();
		gl.glOrthof(-aspectRatio, aspectRatio, 1, -1, -1, 1);
		//GLU.gluPerspective(gl, 45.0f, aspectRatio, 1f, -1f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		((GL11) gl).glGetIntegerv(GL11.GL_VIEWPORT, viewport, 0);
		((GL11) gl).glGetFloatv(GL11.GL_MODELVIEW_MATRIX, modelViewMatrix, 0);
		((GL11) gl).glGetFloatv(GL11.GL_PROJECTION_MATRIX, projectionMatrix, 0);
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
	}
	
	public static void disconnect(VisualEntity ve){
		System.out.println("disconnect");
		drawObjs.remove(ve);
	}
	
	public static int[] getViewport(){
		return viewport;
	}
	public static float[] getProjectionMatrix(){
		return projectionMatrix;
	}
	public static float[] getModelViewMatrix(){
		return modelViewMatrix;
	}
	
	
}

