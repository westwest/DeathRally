/***
 * 
 */
package project.gamedev.deathrally.test;

import project.gamedev.deathrally.R;
import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.util.Log;
import android.widget.Button;
import project.gamedev.deathrally.*;
import project.gamedev.deathrally.game.DeathRallyGame;


/**
 * @author Stugatz
 * Tests the main activity by testing the menu buttons.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2 <MainActivity> {
	private static final String TAG = MainActivityTest.class.getSimpleName();
	private Activity mainActivity, gameActivity;
	
	private ActivityMonitor gameActivityMonitor;
	private Button startGameButton;
	private Intent i;
	/**
	 * @param name
	 */
	public MainActivityTest() {
		super("project.gamedev.deathrally", MainActivity.class);	
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		setActivityInitialTouchMode(true);
		mainActivity = getActivity();
		startGameButton = (Button) mainActivity.findViewById(R.id.button1);		
		
		//check if button has a listener
		//assertTrue ("button has no listener", startGameButton.getKeyListener() != null);
		
		// Adds an activity monitor to the game activity class
		gameActivityMonitor = new ActivityMonitor(DeathRallyGame.class.getName(), null, false);
		getInstrumentation().addMonitor(gameActivityMonitor);
		i = new Intent(mainActivity, DeathRallyGame.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	}

	// Tests the menu button 'new game' to see if it starts the game activity.
	@UiThreadTest
	public void testNewGame () {

		
		mainActivity.runOnUiThread(new Runnable() {
			  //@Override
			  public void run() {
				  startGameButton.requestFocus();
				  startGameButton.setSelected(true);
				  boolean listenerHit = startGameButton.performClick();
				  
				  // See if we get two monitor hits
				  startGameButton.performClick();
				  Log.d(TAG, "Button clicked, listener called: " + listenerHit);
			  }
			});
		
		// Check if the game activity was started				
		gameActivity = gameActivityMonitor.waitForActivityWithTimeout(2 *1000);
		
		Log.d(TAG, "hits: " + gameActivityMonitor.getHits());
		Log.d(TAG, "result: " + gameActivityMonitor.getResult());
		Log.d(TAG, "last activity: " + gameActivityMonitor.getLastActivity());
		
		assertNotNull("game activity not started by button", gameActivity);

		//((MainActivity )mainActivity).launchGame(null);		
		//gameActivity = gameActivityMonitor.waitForActivityWithTimeout(2 *1000);
		//assertNotNull("game activity not started by launch method", gameActivity);
		
		//mainActivity.startActivity(i);
		//gameActivity = gameActivityMonitor.waitForActivityWithTimeout(2 *1000);
		//assertNotNull("game activity not started by intent", gameActivity);
		
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
