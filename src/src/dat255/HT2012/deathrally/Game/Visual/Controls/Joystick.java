package dat255.HT2012.deathrally.Game.Visual.Controls;

import java.nio.IntBuffer;
import java.util.Observable;

import javax.microedition.khronos.opengles.GL;
import javax.microedition.khronos.opengles.GL11;

import android.opengl.GLES11;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

import dat255.HT2012.deathrally.Game.GameModel.Vehicle;
import dat255.HT2012.deathrally.Game.Visual.GameRenderer;
import dat255.HT2012.deathrally.Game.Visual.MatrixTracker.MatrixGrabber;


public class Joystick {
	float centerX;
	float centerY;
	float radius;
	Vehicle controlledCar;
	
	public Joystick(Vehicle controlledCar, float centerX, float centerY, float radius, GameRenderer renderer){
		this.controlledCar = controlledCar;
		float[] coords = new float[16];
		
		GLU.gluProject(centerX, centerY, 0, renderer.getModelViewMatrix(), 0, renderer.getProjectionMatrix(), 0, renderer.getViewport(), 0, coords,0);
		System.out.println("Joystick model COORDs: "+centerX+", "+centerY);
		System.out.println("Joystick screen COORDs: "+ coords[1]+", "+coords[0]*-1);
		this.centerX = coords[1];
		this.centerY = coords[0]*-1;
		this.radius = 150;
	}
	
	public void reset(){
		controlledCar.accelerate(0);
		controlledCar.turn(0);
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
