package dat255.HT2012.deathrally.Game.GameModel;

import java.util.Observable;

public abstract class Movable extends Observable {
	private int velocity = 0;
	private int angle = 0;
	
	public Movable() {
		
	};
	
	public void move(Direction direction) {
		
		if(hasChanged()){
			setChanged();
			notifyObservers(velocity);
		}
	};
	
	public int getVelocity(){
		return velocity;
	}
	public int getAngle(){
		return angle;
	}
}
