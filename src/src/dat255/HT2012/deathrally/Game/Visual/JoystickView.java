package dat255.HT2012.deathrally.Game.Visual;

import javax.microedition.khronos.opengles.GL10;

public class JoystickView extends VisualEntity{
	CircleView boundary;
	
	public JoystickView(float px, float py, float radius){
		boundary = new CircleView(px,py,radius);
	}

	@Override
	public void draw(GL10 gl) {
		boundary.draw(gl);	
	}
}
