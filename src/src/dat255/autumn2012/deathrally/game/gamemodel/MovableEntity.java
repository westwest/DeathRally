package dat255.autumn2012.deathrally.game.gamemodel;
public abstract class MovableEntity extends Entity {
	private float pos_x;
	private float pos_y;
	
	public void move(float pos_x, float pos_y){
		float velocity = getVelocity();
		float angle = getAngle();
		
		float old_x = pos_x;
		float old_y = pos_y;
		
		pos_x = old_x + velocity*((float) Math.cos(angle));
		pos_y = old_y + velocity*((float) Math.sin(angle));
	};
	
	public abstract float getVelocity();
	public abstract float getAngle();
	
	public float getX(){
		return pos_x;
	}
	public float getY(){
		return pos_y;
	}
}
