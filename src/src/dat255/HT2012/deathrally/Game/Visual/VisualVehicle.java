package dat255.HT2012.deathrally.Game.Visual;

import java.util.Observable;
import javax.microedition.khronos.opengles.GL10;

import dat255.HT2012.deathrally.Game.GameModel.GameAction;
import dat255.HT2012.deathrally.Game.GameModel.Vehicle;

public class VisualVehicle extends VisualEntity {
	GameRenderer gameRenderer;
	private float px;
	private float py;
	private Float direction = 180.0f;
	
	private Float wheelAngle = 0.0f;
	private Float rate=1.0f;
	private Float velocity = 0.0f;
	
	private Mesh representation;
	
	public VisualVehicle(float px, float py, GameRenderer gameRenderer){
		super(gameRenderer);
		this.gameRenderer = gameRenderer;		
		this.px = px;
		this.py = py;
		this.representation = new TriangleView(0.1f, 0.2f, px, py);
	}
	
	public void move(){
		
	}
	
	
	@Override
	public void update(Observable observable, Object event) {
		if(observable instanceof Vehicle){
			Vehicle vehicle = (Vehicle) observable;
			if(event.equals(GameAction.TURN)){
				wheelAngle = vehicle.getAngle();
				representation.rotate(wheelAngle);
				//rate = vehicle.getTurningCapability();
			} else if(event.equals(GameAction.ACCELERATE)){
				velocity = vehicle.getVelocity();
			}
		}
	}

	@Override
	public void draw(GL10 gl) {
		representation.draw(gl);
	}
}
