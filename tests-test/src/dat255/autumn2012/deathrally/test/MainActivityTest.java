package dat255.autumn2012.deathrally.test;

import dat255.autumn2012.deathrally.MainActivity;
import android.app.Instrumentation;
import android.test.ActivityTestCase;
import android.widget.Button;

public class MainActivityTest extends ActivityTestCase {
	 private MainActivity activity;
	 private Instrumentation instrumentation;
	 private Button startGameButton;
	 
	 public MainActivityTest () {
		super();
	 }
	 
	 @Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = (MainActivity) getActivity();
		instrumentation = getInstrumentation();
		startGameButton = (Button) activity.findViewById(dat255.autumn2012.deathrally.R.menu.activity_main);
	}
	 
	 
	void newGameTest () {
		activity.runOnUiThread(new Runnable() {
			  //@Override
			  public void run() {
				  startGameButton.performClick();
			  }
			});
		
		instrumentation.waitForIdleSync();
	}
}