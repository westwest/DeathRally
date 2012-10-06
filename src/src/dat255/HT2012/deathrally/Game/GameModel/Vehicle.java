package dat255.HT2012.deathrally.Game.GameModel;

public class Vehicle extends MovableEntity {
	private float velocity = 0;
	private float maxVelocity = 10;
	private float acceleration;
	private float angle = 0;
	private float turningCapability = 10;
	
	
	public Vehicle() {};
	
	public void move(Direction direction) {};
	
	public void accelerate(float intensity){
		//v = v0 + at, time?, a adjusted by intensity linear
		//time is set by speed of thread, if connected to render => fps, else something else
		//Algorithm does not give room for simulation of good/bad breaks. Retardation = -Acceleration
		velocity = velocity + intensity*acceleration; 
		if(velocity > maxVelocity){
			velocity = maxVelocity;
		}
		setChanged();
		notifyObservers(GameAction.ACCELERATE);
	};
	
	public void turn(float angle){
		this.angle = angle;
		setChanged();
		notifyObservers(GameAction.TURN);
	}
	
	public float getVelocity(){
		return velocity;
	}
	
	public float getAngle(){
		return angle;
	}
	
	public float getTurningCapability(){
		return turningCapability;
	}
	
}
