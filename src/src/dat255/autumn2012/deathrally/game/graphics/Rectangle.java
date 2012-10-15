package dat255.autumn2012.deathrally.game.graphics;

import javax.microedition.khronos.opengles.GL10;

public class Rectangle extends Mesh{
	private static final String TAG = Rectangle.class.getSimpleName();
	//Painting CCW but remember shapes are rotated 180 by default
	private final short indices[] = {0,2,1,0,3,2};
	
	public Rectangle(float px, float py, float width, float height){
		super(px,py);
		float[] verticeList = {
				px-width/2,py+height/2,0.0f,
				px-width/2,py-height/2,0.0f,
				px+width/2,py-height/2,0.0f, 
				px+width/2,py+height/2,0.0f
		};

		setVertices(verticeList);
		setIndices(indices);
		setDrawMethod(GL10.GL_TRIANGLES);
	}
}
