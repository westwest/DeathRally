package dat255.HT2012.deathrally.game.model;

import java.util.List;

import dat255.HT2012.deathrally.game.Hitbox;
import dat255.HT2012.deathrally.game.Position;
import dat255.HT2012.deathrally.game.Vector2D;

public interface Entity {
		
	public void setPosition(Position p);
	
	public Position getPosition();
	
	public Hitbox getHitbox();
	
	public Vector2D getVector2D();
	
	public List<String> getCollideTypes();
	
	public String getType();
	
	public boolean isColliding();
	
	public void update(int elapsedTime);

}
