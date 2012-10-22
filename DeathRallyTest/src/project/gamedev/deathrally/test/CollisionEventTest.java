package project.gamedev.deathrally.test;

import project.gamedev.deathrally.game.model.CollisionEvent;
import project.gamedev.deathrally.game.model.Direction;
import project.gamedev.deathrally.game.model.Entity;
import project.gamedev.deathrally.game.model.Level;
import project.gamedev.deathrally.game.model.Player;
import project.gamedev.deathrally.game.model.Vehicle;
import android.test.AndroidTestCase;

/**
 * @author Stugatz
 * Unit test of the CollisionEvent class
 */
public class CollisionEventTest extends AndroidTestCase {
    private Entity e;
	private Direction d;
	private CollisionEvent ce;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		d = Direction.FORWARD;
		e =  new Vehicle(new Level(), new Player("JohnDoe"));
		ce = new CollisionEvent(e, Direction.FORWARD );
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	/**
	 * Accessor test
	 */
	public void testAccessors() {
		assertTrue( ce.getEntity().equals(e) );
		assertTrue( ce.getDirection().equals(d) );
	}
}
