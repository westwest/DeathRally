package project.gamedev.deathrally.test;

import project.gamedev.deathrally.game.model.Position;
import project.gamedev.deathrally.game.model.Vector2D;
import android.test.AndroidTestCase;

public class PositionTest extends AndroidTestCase {
	private float x, y;
	private Position p;
	
	public PositionTest() {		
	}

	protected void setUp() throws Exception {
		super.setUp();
		x = 0f; y = 0f;
		p = null;
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testAccessors() {
		x = 5f; y = 10f;
		p = new Position();
		
		p.setX(x);
		p.setY(y);
		
		assertTrue(Float.compare(p.getX(), x) == 0 );
		assertTrue(Float.compare(p.getY(), y) == 0 );
	}
	
	public void testConstructor() {
		x = 5f; y = 10f;
		
		p = new Position();
		assertTrue("Default constructor initialized with wrong value", Float.compare(p.getX(), 0) == 0 );
		assertTrue("Default constructor initialized with wrong value", Float.compare(p.getY(), 0) == 0 );
		
		p = new Position(x, y);
		assertTrue("Constructor initialized with wrong value", Float.compare(p.getX(), x) == 0 );
		assertTrue("Constructor initialized with wrong value", Float.compare(p.getY(), y) == 0 );
		
		p = new Position(new Position(x, y));
		assertTrue("Constructor initialized with wrong value", Float.compare(p.getX(), x) == 0 );
		assertTrue("Constructor initialized with wrong value", Float.compare(p.getY(), y) == 0 );
	}
	
	public void testEquals() {
		x = 5f; y = 10f;
		p = new Position(x, y);
		
		assertTrue("Same position should be equal.", p.equals(new Position(x, y)) );
		assertFalse("Different size position should not be equal.", p.equals(new Position(x + 1, y - 1)) );
	}
}
