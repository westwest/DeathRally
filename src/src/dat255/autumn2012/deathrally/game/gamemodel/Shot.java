package dat255.autumn2012.deathrally.game.gamemodel;

public abstract class Shot extends MovableEntity {
	private float velocity;
	private float angle;
	private int dist;
	
	public Shot(float posX, float posY, float velocity, float angle, int dist){
		super(posX,posY);
		this.angle = angle;
		this.velocity = velocity;
		this.dist = dist;
	}
	
	public void refresh(){
		if(dist > 0){
			super.refresh();
			dist--;
		} else{
			onReachedRange();
		}
	}
	
	protected abstract void onReachedRange();
	
	public float getVelocity(){
		return velocity;
	}
	public float getAngle(){
		return angle;
	}
}