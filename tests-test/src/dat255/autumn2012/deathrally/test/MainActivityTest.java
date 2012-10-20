/**
 * 
 */
package dat255.autumn2012.deathrally.test;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.util.Log;
import android.widget.Button;
import dat255.autumn2012.deathrally.*;
import dat255.autumn2012.deathrally.R;
import android.util.Log;

/**
 * @author Stugatz
 *
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2 <MainActivity> {
	private static final String TAG = MainActivityTest.class.getSimpleName();
	private Instrumentation instrumentation; 
	private MainActivity mainActivity;
	private Button startGameButton;
	
	/**
	 * @param name
	 */
	public MainActivityTest() {
		super("dat255.autumn2012.deathrally", MainActivity.class);	
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		instrumentation = getInstrumentation();
		mainActivity = (MainActivity) getActivity();
		startGameButton = (Button) mainActivity.findViewById(R.id.button1);
		  Log.d(TAG, startGameButton.getText().toString());
	}

	/* (non-Javadoc)
	 * @see android.test.ActivityInstrumentationTestCase2#tearDown()
	 */
	
	@UiThreadTest
	public void test_newGame () {
		this.setActivityInitialTouchMode(true);
		
		mainActivity.runOnUiThread(new Runnable() {
			  //@Override
			  public void run() {
				  startGameButton.performClick();
			  }
			});
		
		//instrumentation.waitForIdleSync();
		
		// Test if game window has focus
		assertEquals(mainActivity.hasWindowFocus(), true);
		
	}
	

	public void testTheTest () {
		assertEquals(true, true);
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
