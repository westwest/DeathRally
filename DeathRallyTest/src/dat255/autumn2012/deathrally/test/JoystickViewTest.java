package dat255.autumn2012.deathrally.test;

import dat255.autumn2012.deathrally.game.view.JoystickView;
import android.test.AndroidTestCase;

public class JoystickViewTest extends AndroidTestCase {

	private JoystickView jw;
	
	protected void setUp() throws Exception {
		super.setUp();
		jw = new JoystickView(0f,0f,0.5f);
	}
	
	public void testToString(){
		assertEquals("toString gives wrong answer", JoystickView.class.getSimpleName(), jw.toString());
	}
	
	public void testBoundary(){
		//assert
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
