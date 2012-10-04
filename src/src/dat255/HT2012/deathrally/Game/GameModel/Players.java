package dat255.HT2012.deathrally.Game.GameModel;


/**
 * Class is responsible for managing players. Key is keeping track of who's active. There
 * have to be only one instance in the system, so singleton is used. 
 * 
 * @author Johannes Vestlund
 *
 */
public class Players {
	private static volatile Players instance = null;
	private Player activePlayer;
	
	private Players(){
	}
	
	public static Players getInstance(){
		if(instance == null){
			synchronized( Players .class){
				if(instance == null){
					instance = new Players();
				}
			}
		}	
		return instance;
	}
	
	public Player getActivePlayer(){
		return activePlayer;
	}
	
	public void setActivePlayer(Player p){
		activePlayer = p;
	}
}
