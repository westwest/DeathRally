package dat255.HT2012.deathrally.Game.GameModel;

import java.util.Observable;

public class Vehicle extends Observable {
	private int velocity = 0;
	private int angle = 0;
	private int acceleration;
	private int maxSpeedForward;
	private int maxSpeedReverse;
	private int turn;
	private int maxTurnAngle;
	
	public Vehicle(int acceleration, int maxSpeedForward, int maxSpeedReverse, int turn, int maxTurnAngle){
		this.acceleration = acceleration;
		this.maxSpeedForward = maxSpeedForward;
		this.maxSpeedReverse = maxSpeedReverse;
		this.turn = turn;
		this.maxTurnAngle = maxTurnAngle;
	}
	
	public void accelerate(int impulse){
		//Change velocity accordingly
		
		
		//Notify observer	
	}
	public void turn(int intensity){
	}
}
