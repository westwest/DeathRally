package dat255.HT2012.deathrally.Game.Controls;

import dat255.HT2012.deathrally.Cheap.IntArith;
import dat255.HT2012.deathrally.Game.GameModel.Vehicle;


public class Joystick {
	int centerX;
	int centerY;
	int radius;
	Vehicle controlledCar;
	
	public Joystick(int centerX, int centerY, int radius){
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
	}
	
	public void translateToAction(int px, int py){
		if(inCircle(px,py)){
			int dx = px-centerX;
			int dy = py-centerY;
			
			//scale to get reasonable accuracy without going to decimals
			dx *= 100;
			dy *= 100;
			controlledCar.accelerate(dy/radius);
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
	private boolean inCircle(int px, int py) {
		int sq_dist = IntArith.square(centerX - px) + 
				IntArith.square(centerY - py); 
		return sq_dist <= IntArith.square(radius);
	}
}
