package dat255.HT2012.deathrally.Game.GameModel;

import android.util.Log;
import dat255.HT2012.deathrally.Game.Constants.LevelName;
import java.util.*;

public class GameModel {
	private static final String TAG = GameModel.class.getSimpleName();
	private Player player;
	// Holds list of all game objects in the model
	ArrayList<Entity> gameEntities;
		
	public GameModel(LevelName name) {
		Log.d(TAG, "game model created");
	}
	
	public void addEntity (Entity entity) {
		gameEntities.add(entity);
		Log.d(TAG, "game entity added of class: " + entity.getClass().getSimpleName());
	}
	
	
	public void sendAction(GameAction action) {
		// TODO
	}
	
	public void update() {
		// TODO
	}
//	public void movePlayerVehicle(Player player, Direction direction) {
//		player.moveVehicle(direction);
//	}
//	
//	public void handBrakePlayerVehicle(Player player) {
//		player.setHandBrakeOn();
//	}

}
