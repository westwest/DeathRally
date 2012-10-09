package dat255.HT2012.deathrally.Game.Visual;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * The Mesh class serves as base-class for all shapes. Drawing primitives like circles and 
 * triangles are derived from this class. It provides a reusable 2D drawing pattern that 
 * allows rotation and translation, and setters for vertices and indices.
 * @author Johannes Westlund
 *
 */
public class Mesh {
	private FloatBuffer vertexBuffer;
	private ShortBuffer indexBuffer;
	
	private int nrIndices;
	private int drawMethod;
	
	//Translation and rotation params
	private float x,y;
	private float z=0;
	private float rz;
	
	public Mesh(){
	}
	public Mesh(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Basic draw method. Does common settings and gives room for rotation and translation. The
	 * method can be chosen, and should be set on init through constructor.
	 * @param gl represent graphical context and is supplied by renderer.
	 */
	public void draw(GL10 gl){
		gl.glFrontFace(GL10.GL_CCW);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		
		gl.glPushMatrix();
		gl.glTranslatef(x, y, z);
		gl.glRotatef(rz+180, 0, 0, 1);
		gl.glDrawElements(drawMethod, nrIndices, GL10.GL_UNSIGNED_SHORT, indexBuffer);
		gl.glPopMatrix();	
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);
	}
	
	protected void setIndices(short[] indices){
		ByteBuffer ibb = ByteBuffer.allocateDirect(indices.length * 2);
		ibb.order(ByteOrder.nativeOrder());
		indexBuffer = ibb.asShortBuffer();
		indexBuffer.put(indices);
		indexBuffer.position(0);
		nrIndices = indices.length;
	}
	
	protected void setVertices(float[] vertices){
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length * 4);
		vbb.order(ByteOrder.nativeOrder());
		vertexBuffer = vbb.asFloatBuffer();
		vertexBuffer.put(vertices);
		vertexBuffer.position(0);
	}
	
	protected void setDrawMethod(int drawMethod){
		this.drawMethod = drawMethod;
	}
	
	public void rotate(float angle){
		rz += angle;
	}
}
