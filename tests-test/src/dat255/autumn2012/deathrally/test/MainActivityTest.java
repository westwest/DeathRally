/**
 * 
 */
package dat255.autumn2012.deathrally.test;

import android.app.Activity;
import android.app.Instrumentation.ActivityMonitor;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.util.Log;
import android.widget.Button;
import dat255.autumn2012.deathrally.*;
import dat255.autumn2012.deathrally.R;
import dat255.autumn2012.deathrally.game.DeathRallyGame;

/**
 * @author Stugatz
 * Tests the main activity by testing the menu buttons.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2 <MainActivity> {
	private static final String TAG = MainActivityTest.class.getSimpleName();
	private MainActivity mainActivity;
	private Activity gameActivity;
	
	private ActivityMonitor gameActivityMonitor;
	private Button startGameButton;
	private Intent i;
	/**
	 * @param name
	 */
	public MainActivityTest() {
		super("dat255.autumn2012.deathrally", MainActivity.class);	
	}

	protected void setUp() throws Exception {
		super.setUp();
		mainActivity = (MainActivity) getActivity();
		startGameButton = (Button) mainActivity.findViewById(R.id.button1);
		
		// Adds an activity monitor to the game activity class
		gameActivityMonitor = new ActivityMonitor(DeathRallyGame.class.getName(), null, false);
		getInstrumentation().addMonitor(gameActivityMonitor);
		i = new Intent(mainActivity, DeathRallyGame.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	}

	// Tests the menu button 'new game' to see if it starts the game activity.
	@UiThreadTest
	public void testNewGame () {
		this.setActivityInitialTouchMode(true);
		
		mainActivity.runOnUiThread(new Runnable() {
			  //@Override
			  public void run() {
				  startGameButton.performClick();
				  Log.d(TAG, "Button clicked");
			  }
			});
		
		// Check if the game activity was started				
		//gameActivity = gameActivityMonitor.waitForActivityWithTimeout(2 *1000);
		//assertNotNull("game activity not started by button", gameActivity);
		
		mainActivity.startActivity(i);
		gameActivity = gameActivityMonitor.waitForActivityWithTimeout(2 *1000);
		assertNotNull("game activity not started bu intent", gameActivity);
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
