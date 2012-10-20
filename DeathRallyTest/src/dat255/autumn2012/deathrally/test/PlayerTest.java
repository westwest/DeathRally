package dat255.autumn2012.deathrally.test;

import dat255.autumn2012.deathrally.game.gamemodel.Player;
import dat255.autumn2012.deathrally.game.gamemodel.Vehicle;
import android.test.AndroidTestCase;

public class PlayerTest extends AndroidTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}
	public void testIfNoName(){
		Player p1 = new Player("");
		assertEquals("If no name supplied use default name ", 
				p1.getName(),Player.getDefaultName());
		Player p2 = new Player(null);
		assertEquals("If null supplied use default name", 
				p2.getName(), Player.getDefaultName());
	}
	public void testTypicalInit(){
		String name = "JoeDoe";
		Player p = new Player(name);
		assertEquals("Player should have correct name", p.getName(), name);
		assertTrue("Player should not own vehicle", !p.hasVehicle());
	}
	public void testVehicleOwnage(){
		Vehicle v = new Vehicle();
		Player p = new Player("JoeDoe");
		p.setVehicle(v);
		assertTrue("Player should own vehicle", p.hasVehicle());
		assertEquals("Player should own vehicle v", p.getVehicle(),v);	
	}
	public void testToString(){
		String name = "R2D2";
		Player p = new Player(name);
		assertEquals("To String should follow pattern [class]: [name]", p.toString(),"Player: "+name);
	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
