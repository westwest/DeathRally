package dat255.autumn2012.deathrally.test;

import dat255.autumn2012.deathrally.MainActivity;
import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityTestCase;
import android.test.InstrumentationTestCase;
import android.test.UiThreadTest;
import android.widget.Button;

public class _MainActivityTest extends ActivityInstrumentationTestCase2 {
	 private Activity activity;
	 private Instrumentation instrumentation;
	 private Button startGameButton;
	 
	 public _MainActivityTest () {
		super("dat255.autumn2012.deathrally.MainActivity", dat255.autumn2012.deathrally.MainActivity.class);
		activity =  this.getActivity();
		instrumentation = getInstrumentation();
		startGameButton = (Button) activity.findViewById(dat255.autumn2012.deathrally.R.layout.activity_main);
	 }
	 
	 @Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	 
	 
	@UiThreadTest
	void newGameTest () {
		instrumentation.setInTouchMode(true);
		
		activity.runOnUiThread(new Runnable() {
			  //@Override
			  public void run() {
				  startGameButton.performClick();
			  }
			});
		
		instrumentation.waitForIdleSync();
		
		// Test if game window has focus
		assertEquals(activity.hasWindowFocus(), false);
		
	}
	
	void testtest () {
		assertEquals(true, false);
	}
}
