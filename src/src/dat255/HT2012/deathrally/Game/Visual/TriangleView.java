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
public class TriangleView extends VisualEntity implements Observer {
	private static final String TAG = TriangleView.class.getSimpleName();
	private FloatBuffer vertexBuffer;
	private ShortBuffer indexBuffer;
	
	private float px;
	private float py;
	
	private Float direction = 180.0f;
	private Float wheelAngle = 0.0f;
	private Float rate=1.0f;
	
	private float vertices[] = new float[9];
	private short indicies[] = {
			0,1,2
	};
	
	public TriangleView(float width, float height, float px, float py){
		this.px = px;
		this.py = py;
		System.out.println("Coords for triangle: "+px+", "+py);
		
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
		
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		vertexBuffer = vbb.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
		
		ByteBuffer ibb = ByteBuffer.allocateDirect(indicies.length * 2);
		ibb.order(ByteOrder.nativeOrder());
		indexBuffer = ibb.asShortBuffer();
		indexBuffer.put(indicies);
		indexBuffer.position(0);
	}
	
	
	public void draw(GL10 gl){
		gl.glFrontFace(GL10.GL_CCW);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		
		gl.glPushMatrix();
		gl.glTranslatef(px,py, 0.0f);
		gl.glRotatef(direction+=(wheelAngle*rate), 0, 0, 1.0f);
		gl.glDrawElements(GL10.GL_TRIANGLES, indicies.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);
		gl.glPopMatrix();	
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);
	}
			
	@Override
	public void update(Observable observable, Object event) {
		System.out.println("Received");
		if(observable instanceof Vehicle){
			Vehicle vehicle = (Vehicle) observable;
			if(event.equals(GameAction.TURN)){
					wheelAngle = vehicle.getAngle();
					rate = vehicle.getTurningCapability();
			}
		}
	}
}
