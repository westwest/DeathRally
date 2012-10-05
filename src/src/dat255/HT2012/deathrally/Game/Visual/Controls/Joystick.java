package dat255.HT2012.deathrally.Game.Visual.Controls;

import java.util.Observable;

import dat255.HT2012.deathrally.Game.GameModel.Vehicle;


public class Joystick {
	float centerX;
	float centerY;
	float radius;
	Vehicle controlledCar;
	
	public Joystick(Vehicle controlledCar, float centerX, float centerY, float radius){
		this.controlledCar = controlledCar;
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
	}
	
	public void translateToAction(float px, float py){
		System.out.println(px + ", " +py);
		
		if(inCircle(px,py)){
			float dx = px-centerX;
			float dy = py-centerY;
			
			//controlledCar.accelerate(dy/radius);
			controlledCar.turn(dx/radius);
		}
	}

	/**
	 * Calculates if a point is inside joystick area.
	 * May benefit from being generalized.
	 * 
	 * @param px
	 * @param py
	 * @return true if point is inside joystick area.
	 */
	private boolean inCircle(float px, float py) {
		double sq_dist = Math.pow(centerX - px, 2) + 
				Math.pow(centerY - py, 2); 
		return sq_dist <= Math.pow(radius, 2);
	}
}
