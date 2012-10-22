package project.gamedev.deathrally.test;

import java.awt.Dimension;
import project.gamedev.deathrally.game.model.Hitbox;
import android.test.AndroidTestCase;

/**
 * @author Stugatz
 * Unit tests for the hitbox, hasCode method not tested.
 */
public class HitboxTest extends AndroidTestCase {

	
	@Override
	protected void setUp() throws Exception {	
		super.setUp();
		
	}

	@Override
	protected void tearDown() throws Exception {		
		super.tearDown();
		
	}
	
	
	/**
	 * Tests the Hitbox equals method
	 */
	public void testEquals() {
		Hitbox hb = new Hitbox(0, 0);
		Hitbox hb1 = new Hitbox(0, 0);
		Hitbox hb2 = new Hitbox(5, 5);
		Hitbox hb3 = new Hitbox(10, 10);
		Hitbox hb4 = new Hitbox(10, 15);
		Hitbox hb5 = new Hitbox(15, 10);
		
		
		assertFalse("Differently sized hitboxes should not be equal.", hb1.equals(hb2));
		assertFalse("Differently sized hitboxes should not be equal.", hb2.equals(hb1));
		assertFalse("Differently sized hitboxes should not be equal.", hb4.equals(hb5));
		assertFalse("Differently sized hitboxes should not be equal.", hb5.equals(hb4));
		assertFalse("Differently sized hitboxes should not be equal.", hb3.equals(hb4));
		assertTrue("Same size hitbox should be equal", hb.equals(hb1));
		assertFalse("Hitbox can't be equal to null", hb.equals(null));
	}
	
	
	/**
	 * Tests all the getters and setters for the HitBox
	 */
	public void testAccessors() {
		int width = 0;
		int height = 0;
		
		Hitbox hitbox = new Hitbox(width, height);
		
		assertEquals("Set heigt and got height don't match.", hitbox.getHeight(), height);
		assertEquals("Set width and got width don't match.", hitbox.getWidth(), width);
		
		width = 5;
		height = 5;
		
		hitbox.setHeight(width);
		hitbox.setWidth(height);
		
		assertEquals("Set heigt and got height don't match.", hitbox.getHeight(), height);
		assertEquals("Set width and got width don't match.", hitbox.getWidth(), width);
		
		Dimension dimension = new Dimension(width, height);
		
		assertTrue("Dimension set and got is not same", hitbox.getDimension().equals(dimension) );
	}

}