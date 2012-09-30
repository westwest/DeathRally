package dat255.HT2012.deathrally.Game;

import dat255.HT2012.deathrally.Game.Visual.GameRenderer;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class MainGamePanel extends GLSurfaceView {
	
	public MainGamePanel(Context context) {
		super(context);
		//getHolder().addCallback(this);
		
		setFocusable(true);
		setFocusableInTouchMode(true);
		setRenderer(new GameRenderer());
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		float x = e.getX();
		float y = e.getY();
		return true;
	}
	
	public void checkInit(){
		System.out.println("MainGamePanel-instance initiated");
	}

}
