package dat255.HT2012.deathrally.Game.Visual;

import java.util.Observable;
import java.util.Observer;

import javax.microedition.khronos.opengles.GL10;

public abstract class VisualEntity implements Observer {

	private GL10 gl;
	
	@Override
	public void update(Observable observable, Object data) {}
	
	public void draw(GL10 gl){}
}
