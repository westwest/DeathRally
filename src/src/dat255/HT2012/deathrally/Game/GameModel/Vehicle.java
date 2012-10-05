package dat255.HT2012.deathrally.Game.GameModel;

public class Vehicle extends MovableEntity {
	int velocity;
	float angle = 0;
	
	public Vehicle() {};
	
	public void move(Direction direction) {};
	
	public void accelerate(int intensity){};
	
	public void turn(int angle){
		this.angle = angle;
		setChanged();
		notifyObservers(GameAction.TURN);
	}
	
	public float getTurnAngle(){
		return angle;
	}
	
}
