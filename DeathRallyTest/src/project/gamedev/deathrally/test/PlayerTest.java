package project.gamedev.deathrally.test;

import project.gamedev.deathrally.game.model.Level;
import project.gamedev.deathrally.game.model.Player;
import project.gamedev.deathrally.game.model.Vehicle;
import android.test.AndroidTestCase;

public class PlayerTest extends AndroidTestCase {

	String stdName = "JoeDoe";
	
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
		Player p = new Player(stdName);
		assertEquals("Player should have correct name", stdName, p.getName());
		assertTrue("Player should not own vehicle", !p.hasVehicle());
	}
	
	public void testGetMoney(){
		Player p = new Player(stdName);
		assertEquals("Player should have standard ammount of money",
				10000, p.getMoney());
	}
	
	public void testAddMoney(){
		int addAmount = 100;
		Player p = new Player(stdName);
		p.addMoney(addAmount);
		assertEquals("Players wallet should have grown by "+addAmount,10000+addAmount,p.getMoney());
	}
	
	public void testPay(){
		final int startWallet = 10000;
		int buySum = 5000;
		Player p = new Player(stdName);
		assertTrue("Player should be able to afford 5000 first time",p.pay(buySum));
		assertEquals("Player wallet should be "+(startWallet-buySum), 
				startWallet-buySum, p.getMoney());
		int currWallet = p.getMoney();
		assertTrue("Players should not be ablle to afford 5000 another time", !p.pay(buySum));
		assertEquals("Player wallet should be unchanged", currWallet, p.getMoney());
	}
	
	public void testVehicleOwnage(){
		Player p = new Player("JoeDoe");
		Vehicle v = new Vehicle(new Level(), p);
		p.setVehicle(v);
		
		assertTrue("Player should own vehicle", p.hasVehicle());
		assertEquals("Player should own vehicle v", p.getVehicle(),v);	
	}
	
	public void testSave(){
		Player p = new Player(stdName);
		p.addMoney(100);
		assertTrue("Save returned false => exceptions were thrown", p.save(getContext()));
		
	}
	
	public void testLoad(){
		assertTrue("Load returned null => exceptions were thrown", 
				Player.load(getContext(), stdName) != null);
		assertTrue("Object is not instance of Player class", 
				Player.load(getContext(), stdName) instanceof Player);
	}
	
	public void testToString(){
		String name = "R2D2";
		Player p = new Player(name);
		assertEquals("To String should follow pattern [class]: [name]", p.toString(),"Player: "+name);
	}
	protected void tearDown() throws Exception {
		super.tearDown();
		Player.delete(stdName, getContext());
	}

}
