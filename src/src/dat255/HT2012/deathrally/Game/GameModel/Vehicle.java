package dat255.HT2012.deathrally.Game.GameModel;

public abstract class Vehicle extends MovableEntity {
	public Vehicle() {};
	
	public void move(Direction direction) {};
	
	public void accelerate(int intensity){};
	
	public void turn(int angle){};
	
	public void update(){};
}
