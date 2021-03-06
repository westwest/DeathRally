package project.gamedev.deathrally.game.model;

import java.awt.Dimension;

/**
 * Represents an area which is used to define an entity's "hitbox"
 * (i.e. the area that other areas collide or interact with)
 * @author Mike Phoohad
 *
 */
public class Hitbox {
	private int width;
	private int height;
	
	public Hitbox(Hitbox hb) {
		this.setWidth(hb.getWidth());
		this.setHeight(hb.getHeight());
	}
	
	public Hitbox(int w, int h){
		this.width = w;
		this.height = h;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public Dimension getDimension(){
		return new Dimension(this.width,this.height);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + width;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hitbox other = (Hitbox) obj;
		if (height != other.height)
			return false;
		if (width != other.width)
			return false;
		return true;
	}
}
