package dat255.HT2012.deathrally.Game.Visual;

import javax.microedition.khronos.opengles.GL10;
import java.util.Observer;
import android.opengl.GLSurfaceView.Renderer;

public abstract class VisualEntity implements Observer {
	GameRenderer renderer;
	
	
	public VisualEntity(GameRenderer renderer) {
		super();
		this.renderer = renderer;		
		renderer.addDrawObj(this);
	}


	public abstract void draw(GL10 gl);
	
}
