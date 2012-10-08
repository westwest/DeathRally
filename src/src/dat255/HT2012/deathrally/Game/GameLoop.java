package dat255.HT2012.deathrally.Game;

import android.util.Log;
import android.view.SurfaceHolder;
/**
 * @author Stugatz
 * Game loop, basic design taken from "http://obviam.net/index.php/a-very-basic-the-game-loop-for-android/"
 */
public class GameLoop extends Thread {
	private SurfaceHolder surfaceHolder;
	private MainGamePanel gamePanel;
	private boolean running;
	private static final String TAG = GameLoop.class.getSimpleName();

	
	public GameLoop(SurfaceHolder surfaceHolder, MainGamePanel gamePanel) {
		super();
		this.surfaceHolder = surfaceHolder;
		this.gamePanel = gamePanel;
	}

	// game state
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	//Constant Game Speed independent of Variable FPS method taken from
	//"http://www.koonsolo.com/news/dewitters-gameloop/"
    final int TICKS_PER_SECOND = 25;
    final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    final int MAX_FRAMESKIP = 5;
    
    long next_game_tick = System.nanoTime();
    int loops;
    float interpolation;
    
	@Override
	public void run() {
		while (running) {
			Log.d(TAG, "running loop");
			gamePanel.requestRender();
			
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				Log.d(TAG, "Thread sleep interupted");
			}
			// update game state
			// render state to the screen
		}
	}
}
