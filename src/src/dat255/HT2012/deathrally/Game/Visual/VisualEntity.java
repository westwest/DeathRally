package dat255.HT2012.deathrally.Game.Visual;

import java.util.Observable;
import java.util.Observer;

import javax.microedition.khronos.opengles.GL10;

public abstract class VisualEntity {

	private GL10 gl;
	
	public abstract void draw(GL10 gl);
}
