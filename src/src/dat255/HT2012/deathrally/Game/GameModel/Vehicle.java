package dat255.HT2012.deathrally.Game.GameModel;

public class Vehicle extends MovableEntity {
	int velocity;
	float angle = 0;
	float turningCapability = 10;
	
	public Vehicle() {};
	
	public void move(Direction direction) {};
	
	public void accelerate(int intensity){};
	
	public void turn(float angle){
		this.angle = angle;
		setChanged();
		notifyObservers(GameAction.TURN);
	}
	
	public float getAngle(){
		return angle;
	}
	
	public float getTurningCapability(){
		return turningCapability;
	}
	
}
