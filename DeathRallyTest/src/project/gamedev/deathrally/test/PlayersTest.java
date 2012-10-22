package project.gamedev.deathrally.test;

import project.gamedev.deathrally.game.model.Player;
import project.gamedev.deathrally.game.model.Players;
import android.test.AndroidTestCase;

public class PlayersTest extends AndroidTestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testSingleton(){
		Players p1 = Players.getInstance();
		Players p2 = Players.getInstance();
		assertEquals("Instances not the same", p1, p2);
	}
	
	public void testInitState(){
		Players p = Players.getInstance();
		assertTrue("Players initstate is not null", p.getActivePlayer() == null);
	}
	
	public void testSetting(){
		Players p = Players.getInstance();
		Player player1 = new Player("JoeDoe");
		p.setActivePlayer(player1);
		assertEquals("Players not same", player1, p.getActivePlayer());
		p.setActivePlayer(null);
		assertTrue("Players cannot be set to null", p.getActivePlayer() != null);
		p.setActivePlayer(new Player("JoeDoe2"));
		assertNotSame("Player did not change", player1, p.getActivePlayer());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
