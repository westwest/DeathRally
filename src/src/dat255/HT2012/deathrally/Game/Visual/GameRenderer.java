package dat255.HT2012.deathrally.Game.Visual;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLES20;
import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;

/**
 * For now mostly based on a tutorial, as I understood it the code is public domain.
 * http://www.jayway.com/2009/12/03/opengl-es-tutorial-for-android-part-i/
 * 
 * @author johves
 *
 */

public class GameRenderer implements Renderer {
	private Triangle t = new Triangle();

	//A bit of confusion of which OpenGL-version to use. For now 1.x.
	public void onDrawFrame(GL10 gl) {
		/*
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		
		GLU.gluLookAt(gl, 0, 0, -5, 0f, 0f, 0f, 1.0f, 1.0f, 0.0f);
		*/
		//GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glTranslatef(0, 0, -8);
		t.draw(gl);
	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		//GLES20.glViewport(0, 0, width, height);
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		GLU.gluPerspective(gl, 45.0f, (float) width/ (float) height, 0.1f, 100.0f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		//GLES20.glClearColor(0.2f,0.2f,0.2f,1.0f);
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
	}

}
