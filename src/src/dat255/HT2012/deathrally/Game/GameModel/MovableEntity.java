package dat255.HT2012.deathrally.Game.GameModel;

import java.util.List;

import dat255.HT2012.deathrally.Game.Hitbox;
import dat255.HT2012.deathrally.Game.Position;
import dat255.HT2012.deathrally.Game.Vector2D;

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
