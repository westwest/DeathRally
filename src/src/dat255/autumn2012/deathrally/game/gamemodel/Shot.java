package dat255.autumn2012.deathrally.game.gamemodel;

public abstract class Shot extends MovableEntity {
	private float velocity;
	private float angle;
	
	public Shot(float velocity, float angle){
		this.angle = angle;
		this.velocity = velocity;
	}
	
	public float getVelocity(){
		return velocity;
	}
	public float getAngle(){
		return angle;
	}
}
