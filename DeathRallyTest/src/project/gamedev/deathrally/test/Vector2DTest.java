package project.gamedev.deathrally.test;

import project.gamedev.deathrally.game.model.Vector2D;
import android.test.AndroidTestCase;

public class Vector2DTest extends AndroidTestCase {
	private float x;
	private float y;
	private Vector2D v;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		x = 5f;
		y = 10f;
		v = new Vector2D(x, y);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testAccessors() {
		assertTrue("Value set by constructor missmatch", Float.compare(v.getX(), x) == 0 );
		assertTrue("Value set by constructor missmatch", Float.compare(v.getY(), y) == 0 );
	
		x = 6.6f;
		y = 7.7f;
				
		v.setX(x);
		v.setY(y);
		
		assertTrue("Setter/getter value missmatch", Float.compare(v.getX(), x) == 0 );
		assertTrue("Setter/getter value missmatch", Float.compare(v.getY(), y) == 0 );
	}
	
	public void testConstructor() {
		Vector2D v2 = new Vector2D(v);		
		assertTrue("Copy constructor failed", v.equals(v2));
	}
	
	public void testVectorArithmetic() {
		float x2 = 5f, y2 = 5f;
		
		
		Vector2D v2 = new Vector2D(x2, y2);
		v2.add(v);
		
		assertTrue("Vector addition failed", Float.compare(v2.getX(), x + x2) == 0 );
		assertTrue("Vector addition failed", Float.compare(v2.getY(), y + y2) == 0 );
		
		v2.subtract(v);
		
		assertTrue("Vector subtraction failed", Float.compare(v2.getX(), x2) == 0 );
		assertTrue("Vector subtraction failed", Float.compare(v2.getY(), y2) == 0 );
	}
	
	public void testEquals() {
		assertTrue("Same sized vectors should be equal.", v.equals(new Vector2D(x, y)) );
		assertFalse("Different size vectors should not be equal.", v.equals(new Vector2D(x + 1, y - 1)) );
	}
}

