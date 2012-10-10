package dat255.HT2012.deathrally.Game.Graphics;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Rectangle extends Mesh{
	private static final String TAG = Rectangle.class.getSimpleName();
	private final short indices[] = {1,2,3,4};
	
	public Rectangle(float px, float py, float width, float height){
		super(px,py);
		float verticeList[] = {
				px,py+height,0,
				px,py,0,
				px+width,py+height,0,
				px+width,py,0 
		};
		setVertices(verticeList);
		setIndices(indices);
		setDrawMethod(GL10.GL_TRIANGLE_STRIP);
	}
}
