package dat255.HT2012.deathrally.Game;

import android.util.Log;
import android.view.SurfaceHolder;
import dat255.HT2012.deathrally.Game.GameModel.*;
/**
 * @author Stugatz
 * Game loop, basic design taken from "http://obviam.net/index.php/a-very-basic-the-game-loop-for-android/"
 */
public class GameLoop extends Thread {
	//private SurfaceHolder surfaceHolder;
	private MainGamePanel gamePanel;
	private GameModel model;
	private boolean isRunning = false;
	//Useful for exception [note to self, Johannes]
	private static final String TAG = GameLoop.class.getSimpleName();

	
	public GameLoop(SurfaceHolder surfaceHolder, MainGamePanel gamePanel, GameModel model) {
		super();
		//*will probably not be used here* this.surfaceHolder = surfaceHolder;
		this.gamePanel = gamePanel;
		this.model = model;

	}

	// game state
	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	//Constant Game Speed independent of Variable FPS method taken from
	//"http://www.koonsolo.com/news/dewitters-gameloop/"
	
    final int TICKS_PER_SECOND = 25;
    final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
    final int MAX_FRAMESKIP = 5;
    
    long next_game_tick = System.nanoTime();
    int loops;
    //*could be used later* float interpolation;
    
	@Override
	public void run() {
		Log.d(TAG, "starting game loop");
		isRunning = true;
		
		while (isRunning) {
		loops = 0;
        	while(System.nanoTime() > next_game_tick && loops < MAX_FRAMESKIP) {
	            model.update();
	
	            next_game_tick += SKIP_TICKS;
	            loops++;
	        }
	
	        /* Coud implement fancy interpolation later if we wish
	         * 
	         * interpolation =  ( System.nanoTime() + SKIP_TICKS - next_game_tick )
	         *               / ( SKIP_TICKS );
	         *( interpolation );
	        **/
	        
	        gamePanel.requestRender();	        				        		
		}
	}
}
