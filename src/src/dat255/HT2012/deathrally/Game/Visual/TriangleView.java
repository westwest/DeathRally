package dat255.HT2012.deathrally.Game.Visual;

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
public class TriangleView extends Mesh {
	private static final String TAG = TriangleView.class.getSimpleName();
	//Matrix-stuff and related
	private float vertices[] = new float[9];
	private short indices[] = {
			0,1,2
	};
	
	public TriangleView(float width, float height, float px, float py){
		setDrawMethod(GL10.GL_TRIANGLES);
		//this.px = px;
		//this.py = py;
		
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
