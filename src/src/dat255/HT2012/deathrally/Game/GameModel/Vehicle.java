package dat255.HT2012.deathrally.Game.GameModel;

public class Vehicle extends Movable {
	private int acceleration;
	private int maxSpeed;
	private int turn;
	private int maxTurnAngle;
	
	public Vehicle(int acceleration, int maxSpeed){
		this.acceleration = acceleration;
		this.maxSpeed = maxSpeed;
	}
	public void accelerate(int intensity){
		//Change velocity accordingly
		
		
		//Notify observer
		
	}
	
	public void turn(int intensity){
		
		
	}
}
