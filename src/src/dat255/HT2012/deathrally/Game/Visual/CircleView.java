package dat255.HT2012.deathrally.Game.Visual;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class CircleView extends VisualEntity {
		private int nrPoints = 30;
		private float[] vertices = new float[nrPoints *3];
		private short[] indicies = new short[nrPoints];
		private FloatBuffer vertexBuffer;
		private ShortBuffer indexBuffer;
		private float px;
		private float py;
		
	
	public CircleView(float px, float py, float radius){
		this.px = px;
		this.py = py;
		
		double relAngle = Math.pow(nrPoints, -1)*2*Math.PI;
		for(int i = 0; i< nrPoints; i++){
			//x, y and z coords for each point;
			vertices[i*3] = (float) (Math.cos(relAngle*i)*radius);
			vertices[i*3+1] = (float) (Math.sin(relAngle*i)*radius);
			vertices[i*3+2] = 0.0f;
			
			indicies[i] = (short) i;
		}
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

	@Override
	public void draw(GL10 gl) {		
		//gl.glFrontFace(GL10.GL_CCW);
		//gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
		
		gl.glPushMatrix();
		gl.glTranslatef(px, py, 0);
		gl.glDrawElements(GL10.GL_LINE_LOOP, indicies.length, GL10.GL_UNSIGNED_SHORT, indexBuffer);
		gl.glPopMatrix();
		
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		//gl.glDisable(GL10.GL_CULL_FACE);

		
	}
}
