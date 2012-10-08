package dat255.HT2012.deathrally.Game.Visual;

import java.util.Observable;

import javax.microedition.khronos.opengles.GL10;

public class JoystickView extends VisualEntity{
	CircleView boundary;
	
	public JoystickView(float px, float py, float radius, GameRenderer gameRenderer){
		super(gameRenderer);
		boundary = new CircleView(px,py,radius);
		
	}

	@Override
	public void draw(GL10 gl) {
		boundary.draw(gl);	
	}
	
	public void destroy(){
		GameRenderer.disconnect(this);
	}
	
	public String toString(){
		return "JoystickView";
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		// will probably be a call to draw? Or maybe that will mess with the framrate
		
	}
}
