package project.gamedev.deathrally.game.model;

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
	 * Returns a list of all entities that are contained in an area
	 * 
	 * @param x, X-coordinate of upper-left corner of area
	 * @param y, Y-coordinate of upper-left corner of area
	 * @param width, Width of the area
	 * @param height, Height of the area
	 * @return
	 */
	public List<Entity> getEntitiesAt(float x, float y, int width, int height) {
		List<Entity> list = new ArrayList<Entity>();
		float minX = x;
		float maxX = x+width;
		float minY = y;
		float maxY = y+height;

		for (Entity e : this.getEntities()) {
			float entityMinX = e.getPosition().getX();
			float entityMaxX = e.getPosition().getX() + e.getHitbox().getWidth();
			float entityMinY = e.getPosition().getY();
			float entityMaxY = e.getPosition().getY() + e.getHitbox().getHeight();
			
			
			// I don't believe this works as a way to check if the two areas somehow overlaps or intersects with each other.
			boolean isOverlap = ((maxX >= entityMinX) &&
	                (maxY >= entityMinY) &&
	                (minX <= entityMaxX) &&
	                (minY <= entityMaxY));
			
			boolean outerContainsInner = ((maxX >= entityMaxX) &&
					(maxY >= entityMaxY) &&
					(minX <= entityMinX) &&
					(minY <= entityMinY));
			
			boolean innerContainsOuter = ((entityMaxX >= maxX) && 
					(entityMaxY >= maxY) && 
					(entityMinY <= minY) && 
					(entityMinX <= minX));
			
			if (isOverlap || outerContainsInner || innerContainsOuter) {
				list.add(e);
			}
		}
		return list;
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
	
	/*
	 * Is used to check if there is a collision happening at the entities "area".
	 */
	private void checkCollision(Entity e,
			Map<Entity, List<Entity>> collidedEntities) {
		
		List<Entity> collided = collidedEntities.get(e);
		
//		The area it searches is the entities area. 
		for (Entity colliding : getEntitiesAt(e.getPosition().getX(), 
				e.getPosition().getY(), 
				e.getHitbox().getWidth(), 
				e.getHitbox().getHeight())) {
			if (!collided.contains(colliding)) {
				colliding.collide(new CollisionEvent(e, Direction.NONE));
				e.collide(new CollisionEvent(colliding, Direction.NONE));
				collided.add(colliding);
			}
		}		
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
