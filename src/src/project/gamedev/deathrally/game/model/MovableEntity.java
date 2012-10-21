package project.gamedev.deathrally.game.model;

import java.util.List;


public abstract class MovableEntity implements Entity {
	
//	Doesn't have to actually implement any of the methods below since it is abstract

	@Override
	public void setPosition(Position p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Position getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Hitbox getHitbox() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector2D getVector2D() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getCollideTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isColliding() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(int elapsedTime) {
		// TODO Auto-generated method stub
		
	}
	
}
