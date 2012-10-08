package dat255.HT2012.deathrally.Game.GameModel;

import android.util.Log;
import dat255.HT2012.deathrally.Game.DeathRallyGame;
import dat255.HT2012.deathrally.Game.Constants.LevelName;

public class GameModel implements Runnable {
	private Player player;
	private static final String TAG = GameModel.class.getSimpleName();
	
	public GameModel(LevelName name) {
		Log.d(TAG, "game model created");
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

	public void run() {
		// TODO Auto-generated method stub
		
	}
}
