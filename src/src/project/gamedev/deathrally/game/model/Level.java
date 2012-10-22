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
		Vector2D vector = e.getVector2D();
		
		moveX(e, vector.getX(), collidedEntities);
		moveY(e, vector.getY(), collidedEntities);
	}
	
	private void defaultLevel() {
		entities.removeAll(entities);
		entities.add(new Vehicle(new Position(0,0), this, new Player("player")));
	}
	
	private boolean moveX(Entity e, float movex, Map<Entity, List<Entity>> collidedEntities) {
		
		// A downside right now is that it only checks for integer positions

		int preferredx = Math.round(movex);
		List<Entity> collided = collidedEntities.get(e);
		int addx = Math.signum(preferredx) > 0 ? 1 : -1;

		float[][] searchRectangle = new float[2][2];
		searchRectangle[0][0] = e.getPosition().getX()+Math.signum(preferredx);
		searchRectangle[0][1] = e.getPosition().getY();
		searchRectangle[1][0] = e.getHitbox().getWidth();				
		searchRectangle[1][1] = e.getHitbox().getHeight();

		for (int x = 0; x < Math.abs(preferredx); x++) {
			searchRectangle[0][0] = e.getPosition().getX()
					+ (x * Math.signum(preferredx)) + addx;

			for (Entity colliding : this.getEntitiesAt(searchRectangle[0][0], searchRectangle[0][1],
					(int)searchRectangle[1][0], (int)searchRectangle[1][1])) {
				if (colliding != e) {

					Direction d1 = Math.signum(preferredx) > 0 ? Direction.LEFT
							: Direction.RIGHT;
					Direction d2 = Math.signum(preferredx) > 0 ? Direction.RIGHT
							: Direction.LEFT;

					if (!collided.contains(colliding)) {
						colliding.collide(new CollisionEvent(e, d1));
						e.collide(new CollisionEvent(colliding, d2));
						collided.add(colliding);
					}

					// If the entity is set to collide with this entity, check
					// if the other has this in its list, in that case try to
					// move that
					// away and continue.
					if (e.getCollideTypes().contains(colliding.getType())) {
						if (colliding.getCollideTypes().contains(e.getType())) {
							if (!moveX(colliding,
									Math.signum(preferredx), collidedEntities)) {
								return false;
							}
						} else {
							return false;
						}
					}

					// Else if the collided entity has this entity in his list,
					// move it out of the way
					else if (colliding.getCollideTypes().contains(e.getType())) {
						if (!moveX(colliding, Math.signum(preferredx),
								collidedEntities)) {
							return false;
						}
					}

					// Otherwise just pass through the entity
				}
			}
		}

		// Add that it moves the last bit until the collision also before returning
		return true;
	}
	
	private boolean moveY(Entity e, float movey,
			Map<Entity, List<Entity>> collidedEntities) {

		int preferredy = Math.round(movey);
		List<Entity> collided = collidedEntities.get(e);
		int addy = Math.signum(preferredy) > 0 ? 1 : -1;
		float[][] searchRectangle = new float[2][2];
		
		searchRectangle[0][0] = e.getPosition().getX();
		searchRectangle[0][1] = e.getPosition().getY();
		searchRectangle[1][0] = e.getHitbox().getWidth();				
		searchRectangle[1][1] = e.getHitbox().getHeight();

		for (int y = 0; y < Math.abs(preferredy); y++) {			
			searchRectangle[0][1] = e.getPosition().getY()
					+ (y * Math.signum(preferredy)) + addy;
					
			for (Entity colliding : this.getEntitiesAt(searchRectangle[0][0], searchRectangle[0][1],
					(int)searchRectangle[1][0], (int)searchRectangle[1][1])) {
				if (colliding != e) {

					Direction d1 = Math.signum(preferredy) > 0 ? Direction.TOP
							: Direction.BOTTOM;
					Direction d2 = Math.signum(preferredy) > 0 ? Direction.BOTTOM
							: Direction.TOP;

					if (!collided.contains(colliding)) {
						colliding.collide(new CollisionEvent(e, d1));
						e.collide(new CollisionEvent(colliding, d2));
						collided.add(colliding);
					}

					if (e.getCollideTypes().contains(colliding.getType())) {
						if (colliding.getCollideTypes().contains(e.getType())) {
							if (!moveY(colliding,
									Math.signum(preferredy), collidedEntities)) {
								return false;
							}
						} else {
							return false;
						}
					} else if (colliding.getCollideTypes()
							.contains(e.getType())) {
						moveY(colliding, Math.signum(preferredy),
								collidedEntities);
					}
				}
			}
		}
		return true;
	}
}
