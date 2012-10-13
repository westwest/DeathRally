/*
 * Copyright (c) 2012 Mike Phoohad <anno.tao@gmail.com>
 * 
 * This file is part of DeathRally
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by 
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even implied warranty of
 * MERCHANTABILITY or FITTNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package dat255.HT2012.deathrally.game.model;

import java.util.ArrayList;
import java.util.List;

import dat255.HT2012.deathrally.game.Hitbox;
import dat255.HT2012.deathrally.game.Position;
import dat255.HT2012.deathrally.game.Vector2D;




// Does not fit together with the MovableEntity, nor with the Entity class any more
public class Vehicle implements Entity {
	
	private Position 				pt;
	private Position 				startPos;
	private Vector2D 				v2d;
	private Vector2D 				addVector;
	private Vector2D 				gravity;
	private Vector2D 				acceleration;
	private boolean 				colliding;
	private boolean 				alive;
	private boolean 				moving;
//	private boolean 				onGround;
//	private boolean 				jumping;
//	private boolean 				lifting;
//	private boolean 				justJumped;
//	private boolean 				crouching;
//	private boolean 				waitingToUncrouch;
	private boolean 				gameWon;
//	private boolean 				inWater;
	private Direction				direction;
//	private List<CollisionEvent> 	collidingList;
	private Hitbox 					hitbox;
	private Level 					level;
	private List<String> 			collideList;
	private Player					player;
//	private float 					speedFactor;
	
	
	public Vehicle(Level level, Player player) {
		this(new Position(0, 0), level, player);
	}

	public Vehicle(Position position, Level level, Player player) {
		this.level = level;
		this.collideList = new ArrayList<String>();
		// TODO Add what Vehicles collides with
		this.gameWon = false;
		this.alive = true;
		this.setPosition(position);
		this.startPos = position;
		this.v2d = new Vector2D(0, 0);
		this.addVector = new Vector2D(0, 0);
		this.direction = Direction.RIGHT;
//		this.collidingList = new ArrayList<CollisionEvent>();
		this.gravity = new Vector2D(0, 1);
		this.acceleration = new Vector2D(0, 0);
		this.hitbox = new Hitbox(28, 46);
		this.player = player;
	}
	
	public boolean isMoving() {
		return moving;
	}
	
	public void move() {
		this.moving = true;
	}
	
	public void move(Direction d) {
		this.setDirection(d);
		this.move();
	}
	
	
	public void stopMove() {
		this.moving = false;
	}
	
	public void die() {
		this.alive = false;
	}
	
	public void winGame() {
		this.gameWon = true;
	}
	
	// Getters
	public Direction getDirection() {
		Direction temp = this.direction;

		return temp;
	}
	
	public Position getStartPosition() {
		return new Position(this.startPos);
	}
	
	@Override
	public Position getPosition() {
		return new Position(this.pt);
	}
	
	@Override
	public Hitbox getHitbox() {
		return new Hitbox(hitbox);
	}
	
	@Override
	public Vector2D getVector2D() {
		return new Vector2D(this.v2d);
	}
	
	@Override
	public List<String> getCollideTypes() {
		List<String> list = new ArrayList<String>(this.collideList);
		return list;
	}
	
	@Override
	public String getType() {
		return "Vehicle";
	}
	
	public void update() {
		this.update(10);
	}

	@Override
	public void update(int elapsedTime) {
		this.v2d = new Vector2D(addVector);
		this.addVector = new Vector2D(0, 0);

		if (this.direction.equals(Direction.RIGHT) && this.moving) {
			this.v2d.add(new Vector2D(2.8f, 0));
			this.acceleration.add(new Vector2D(0.15f, 0));

		} else if (this.direction.equals(Direction.LEFT) && this.moving) {
			this.v2d.add(new Vector2D(-2.8f, 0));
			this.acceleration.add(new Vector2D(-0.15f, 0));
		}
		if (Math.abs(this.acceleration.getX()) < 0.1) {
			this.acceleration.setX(0);
		}

		if (this.acceleration.getX() > 0) {
			this.acceleration.add(new Vector2D(-0.1f, 0));
		} else if (this.acceleration.getX() < 0) {
			this.acceleration.add(new Vector2D(0.1f, 0));
		}

		this.v2d.add(acceleration);
//		this.v2d = new Vector2D(this.v2d.getX() * this.speedFactor,
//				this.v2d.getY());
		this.colliding = false;
//		this.speedFactor = 1.0f;
	}

	
	// Setters
	public void setDirection(Direction d) {
		this.direction = d;
	}
	
	public void collide(CollisionEvent evt) {
		this.colliding = true; 
		// TODO Add what happens in what happens in all types of collision
	}
	
	@Override
	public void setPosition(Position p) {
		this.pt = p;
	}
	
	public void setStartPosition(Position p) {
		this.startPos = p;
	}
	
	public void setVector2D(Vector2D v2d) {
		this.v2d = new Vector2D(v2d);
	}
	
	public void addVector2D(Vector2D v2d) {
		this.addVector = new Vector2D(v2d);
	}
	
	// Boolean methods
	@Override
	public boolean isColliding() {
		boolean temp = this.colliding;
		return temp;
	}
	
	public boolean hasWonGame() {
		boolean temp = this.gameWon;
		return temp;
	}
	
	public boolean isAlive() {
		boolean temp = this.alive;
		return temp;
	}
	
	

	
//	private float velocity = 0;
//	private float maxVelocity = 10;
//	private float acceleration;
//	private float angle = 0;
//	private float turningCapability = 10;
//	
//	
//	public Vehicle() {};
//	
//	public Vehicle(Vehicle vehicle) {};
//	
//	public void move(Direction direction) {};
//	
//	public void accelerate(float intensity){
//		//v = v0 + at, time?, a adjusted by intensity linear
//		//time is set by speed of thread, if connected to render => fps, else something else
//		//Algorithm does not give room for simulation of good/bad breaks. Retardation = -Acceleration
//		velocity = velocity + intensity*acceleration; 
//		if(velocity > maxVelocity){
//			velocity = maxVelocity;
//		}
//		setChanged();
//		notifyObservers(GameAction.ACCELERATE);
//	};
//	
//	public void turn(float angle){
//		this.angle = angle;
//		setChanged();
//		notifyObservers(GameAction.TURN);
//	}
//	
//	public float getVelocity(){
//		return velocity;
//	}
//	
//	public float getAngle(){
//		return angle;
//	}
//	
//	public float getTurningCapability(){
//		return turningCapability;
//	}
//	
}
