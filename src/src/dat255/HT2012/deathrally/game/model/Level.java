package dat255.HT2012.deathrally.game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Level {
	
	private List<Entity> entities = new ArrayList<Entity>();
	private boolean gameOver;
	private boolean hasCompletedRace;
	private int playersRacePosition;
	
	public Level() {
		this(new ArrayList<Entity>()); // Defaults to an empty world
		defaultLevel();
	}

	public Level(Level copy) {
		this(copy.getEntities());
	}

	public Level(List<Entity> list) {
		this.entities = new ArrayList<Entity>();
		this.gameOver = false;
		this.hasCompletedRace = false;
		for (Entity e : list) {
			this.add(e);
		}
	}

	public void add(Entity e) {
		entities.add(e);
	}
	
	public void remove(Entity e) {
		entities.remove(e);
	}

	public List<Entity> getEntities() {
		return new ArrayList<Entity>(this.entities);
	}
	
	public boolean isGameOver() {
		return gameOver;
	}

	public boolean hasCompletedRace() {
		return hasCompletedRace;
	}

	public int getPlayersRacePosition() {
		return playersRacePosition;
	}
	
	/**
	 * Moves all entities according to their vectors and collision
	 * 
	 * @param int, time since last update
	 */
	public void update(int elapsedTime) {
		Map<Entity, List<Entity>> collidedEntities = new HashMap<Entity, List<Entity>>();

		for (Entity e : this.getEntities()) {
			collidedEntities.put(e, new ArrayList<Entity>());
		}

		for (Entity e : this.getEntities()) {
			e.update(elapsedTime);
			move(e, collidedEntities);
			checkCollision(e, collidedEntities);
		}
	}
	
	private void checkCollision(Entity e,
			Map<Entity, List<Entity>> collidedEntities) {
		// TODO Need to check collision between entity and all other entities
	}
	
	private void move(Entity e, Map<Entity, List<Entity>> collidedEntities) {
		// TODO Need to move entity along the entity's vector and check each step for collision.
//		Vector2D vector = e.getVector2D();
		
//		stepPositionX(e, vector.getX(), collidedEntities);
//		stepPositionY(e, vector.getY(), collidedEntities);
	}
	
	private void defaultLevel() {
		entities.removeAll(entities);
		entities.add(new Vehicle(new Position(0,0), this, new Player("player")));
	}
}
