package dat255.HT2012.deathrally.Game.GameModel;

import java.util.List;

import dat255.HT2012.deathrally.Game.Hitbox;
import dat255.HT2012.deathrally.Game.Position;
import dat255.HT2012.deathrally.Game.Vector2D;

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
